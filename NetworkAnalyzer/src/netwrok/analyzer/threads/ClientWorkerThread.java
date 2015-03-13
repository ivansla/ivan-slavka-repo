package netwrok.analyzer.threads;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Date;

public class ClientWorkerThread extends AbstractWorkerThread {

	private static NumberFormat formatter = new DecimalFormat("###.#####");
	private byte[] sendingBytes;
	private int amountOfDataSent = 0;
	private long byteSize;
	private int pausePeriod;
	private long processingTime;
	private char env;
	private boolean loadSuccess = false;

	public ClientWorkerThread(String host, int port, long byteSize, int chunkSize, int pausePeriodInSeconds,	int processingTimeInSeconds, String threadID, char env) {
		super(host, port, threadID, false);
		this.sendingBytes = new byte[chunkSize];
		this.env = env;
		this.byteSize = byteSize;
		this.pausePeriod = pausePeriodInSeconds * 1000;
		this.processingTime = processingTimeInSeconds * 1000000000L;
	}

	public boolean loadFile(String filename){
		try {
			RandomAccessFile f = new RandomAccessFile(filename, "r");
			// Get and check length
			long longlength = f.length();
			int length = (int) longlength;
			// Read file and return data
			this.sendingBytes = new byte[length];
			this.byteSize = this.sendingBytes.length;
			f.readFully(this.sendingBytes);
			this.loadSuccess = true;
		} catch(IOException e) {
			this.loadSuccess = false;
			System.out.println("Padlo nacitanie fajlu!!");
		}
		return this.loadSuccess;
	}

	@Override
	public void run() {

		this.createConnection(this.host, this.port);

		long startingTime = System.nanoTime();

		boolean completed = false;
		long totalTime = 0L;
		long totalSpeed = 0L;
		int counter = 0;

		byte[] receivedData = null;

		while (true && !completed) {

			// Generate random byte array
			if(!this.loadSuccess){
				long seed = System.currentTimeMillis();
				this.random.setSeed(seed);
				this.random.nextBytes(this.sendingBytes);
			}

			long currentTime = System.nanoTime();

			long procTime = ((currentTime - startingTime) / 1000000L);
			/*
			if (this.amountOfDataSent == this.byteSize) {
				try {
					long averageChunkSpeed = (totalSpeed / counter);//double averageChunkSpeed = (((double)this.byteSize / (1024 * 1024)) / ((double)totalTime / 1000000000L));
					counter = 0;
					System.out.println("Managed to send and receive all data: "	+ ((this.amountOfDataSent * 2) / (double) (1024 * 1024)) + " MB");
					//System.out.println("System processing time: " + procTime + " ms");
					//System.out.println("Average speed chunk speed: " + formatter.format(averageChunkSpeed) + " MB/s");
					this.logger.logMessage(this.threadID + ";" + this.socket.getPort() + ";" + this.sdf.format(new Date()) + ";" + procTime + ";" + formatter.format(averageChunkSpeed) + ";" + this.sendingBytes.length + ";N/A;0;false");
					this.amountOfDataSent = 0;
					totalTime = 0L;
					totalSpeed = 0L;
					Thread.sleep(this.pausePeriod);
					startingTime = System.nanoTime();
					currentTime = startingTime;
					//completed = true;

					FileOutputStream output = new FileOutputStream(new File("outputTestFile.twx"));
					output.write(receivedData);
					output.flush();

				} catch (InterruptedException e) {
					this.logger.logError(e.getMessage());
				} catch (IOException e){
					System.out.println("Unable to write file");
				}
			}

			 */
			if ((currentTime - startingTime) >= this.processingTime) {
				try {
					long averageChunkSpeed = (totalSpeed / counter); //(((double)this.byteSize / (1024 * 1024)) / ((double)totalTime / 1000000000L));

					counter = 0;
					//System.out.println("Managed to send and receive: " + ((this.amountOfDataSent * 2) / (double) (1024 * 1024)) + " MB");
					//System.out.println("System processing time: " + procTime + " ms");
					//System.out.println("Average speed chunk speed: " + formatter.format(averageChunkSpeed) + " MB/s");
					this.logger.logMessage(this.threadID + ";" + this.socket.getPort() + ";" + this.sdf.format(new Date()) + ";" + procTime + ";" + formatter.format(averageChunkSpeed)+ ";" + this.sendingBytes.length + ";N/A;0;false");
					this.amountOfDataSent = 0;
					totalTime = 0L;
					totalSpeed = 0L;
					Thread.sleep(this.pausePeriod);
					startingTime = System.nanoTime();
					currentTime = startingTime;
				} catch (InterruptedException e) {
					this.logger.logError(e.getMessage());
				}
			}

			counter++;

			try{

				this.sendBytes(this.sendingBytes);
				receivedData = this.readBytes();
				long elapsedTime = System.nanoTime() - currentTime;

				totalTime += elapsedTime;
				totalSpeed += (long)((this.sendingBytes.length * 2)  / (elapsedTime / 1000000000.0));

				boolean corruption = false;
				for(int i = 0; i < receivedData.length; i++){
					if(receivedData[i] != this.sendingBytes[i]){
						corruption = true;
						this.logger.logError("Data corruption occured");
					}
				}

				this.amountOfDataSent += this.sendingBytes.length;

				if(this.env == 't'){
					double speed = 0;
					if (elapsedTime != 0L) {
						double speedInSeconds = (((this.sendingBytes.length * 2) / (1024D * 1024D)) / (elapsedTime / 1000000000D));
						speed = (((this.sendingBytes.length * 2) / (double)(1024 * 1024)) / (elapsedTime / 1000000000D));
						if (this.env == 't') {
							System.out.println("Speed in seconds: " + speedInSeconds + " B/s");
							System.out.println("Time: " + elapsedTime + "ns");
							System.out.println("Speed: " + formatter.format(speed) + " MB/s");
						}
						this.logger.logMessage(this.threadID + ";" + this.socket.getPort() + ";" + elapsedTime + ";" + formatter.format(speed));
					} else {
						if (this.env == 't') {
							System.out.println("Time: < 0 ms");
							System.out.println("Speed: can't calculate");
						}
						this.logger.logMessage(this.threadID + ";" + this.socket.getPort() + ";0;N/A");
					}
				}
			} catch (IOException e){
				this.logger.logError(e.getMessage());
				if(!this.connectionFailed){
					this.connectionFailed = true;
				}
				this.createConnection(this.host, this.port);
			}
		}
	}
}
