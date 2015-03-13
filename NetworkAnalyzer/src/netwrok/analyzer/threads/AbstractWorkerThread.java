package netwrok.analyzer.threads;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import netwrok.analyzer.utils.LoggingManager;

public abstract class AbstractWorkerThread implements Runnable{

	protected SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy - HH:mm:ss");
	protected DataInputStream inputStream;
	protected DataOutputStream outputStream;
	protected volatile Socket socket;
	protected Random random = new Random();
	protected volatile boolean connectionFailed = false;
	protected long timeToEstablishConnection = 0L;
	protected LoggingManager logger;
	protected String threadID;
	protected int port;
	protected String host;
	protected String connectionStartDate;
	public volatile boolean receivedData = false;
	public volatile int deadCounter = 0;
	private Lock counterLock = new ReentrantLock();

	public AbstractWorkerThread(String host, int port, String threadID, boolean isServer){
		this.host = host;
		this.port = port;
		this.threadID = threadID;
		if(!isServer){
			this.logger = new LoggingManager(this.threadID);
			this.logger.logMessage("Thread ID;port;time;processingTime (ms);speed (B/s);Packet Size (B);Connection Time;Time to Connect (ms);error occured");
		}
	}

	public void incrementDeadCounter(){
		this.counterLock.lock();
		this.deadCounter++;
		this.counterLock.unlock();
	}

	public void resetConnection(){
		this.deadCounter = 0;
		this.createConnection(this.host, this.port);
	}

	protected void createConnection(String host, int port){

		this.counterLock.lock();
		long connectionStartTime = System.nanoTime();
		this.connectionStartDate = this.sdf.format(new Date(System.currentTimeMillis()));

		if(this.socket != null){
			try {
				this.socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
			this.socket = null;
		}

		while(this.socket == null){
			try {
				System.out.println("Trying to establish connection...");
				this.socket = new Socket(host, port);
				//this.socket.setKeepAlive(true);
				if(this.socket == null){
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				} else {
					this.timeToEstablishConnection = (System.nanoTime() - connectionStartTime) / 1000000L;
					this.logger.logMessage(this.threadID + ";" + this.socket.getPort() + ";0;0;0;" + this.connectionStartDate + ";"+ this.timeToEstablishConnection + ";" + this.connectionFailed);
					this.connectionFailed = false;
					System.out.println("Connection established!");
					//System.out.println("Time to establish connection: " + this.timeToEstablishConnection + " ms");
				}
			} catch (UnknownHostException e) {
				this.logger.logError(e.getMessage());
			} catch (IOException e) {
				this.logger.logError(e.getMessage());
			}
		}
		this.initStreams(this.socket);
		this.counterLock.unlock();
	}

	protected void initStreams(Socket socket){

		try {
			this.inputStream = new DataInputStream(socket.getInputStream());
			this.outputStream = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			this.logger.logError(e.getMessage());
		}
	}

	public byte[] readBytes() throws IOException{
		byte[] data = null;
		int len = this.inputStream.readInt();
		data = new byte[len];

		if (len > 0) {
			this.inputStream.readFully(data);
		}

		this.deadCounter = 0;
		return data;
	}

	public void sendBytes(byte[] myByteArray) throws IOException{
		this.sendBytes(myByteArray, 0, myByteArray.length);
	}

	public void sendBytes(byte[] myByteArray, int start, int len) throws IOException{
		if (len < 0) {
			throw new IllegalArgumentException("Negative length not allowed");
		}
		if ((start < 0) || (start >= myByteArray.length)) {
			throw new IndexOutOfBoundsException("Out of bounds: " + start);
		}

		this.outputStream.writeInt(len);
		if (len > 0) {
			this.outputStream.write(myByteArray, start, len);
			this.outputStream.flush();
		}
		this.deadCounter = 0;
	}
}
