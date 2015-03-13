package netwrok.analyzer.threads;

import java.io.IOException;
import java.util.Date;


public class ClientPingingThread extends AbstractWorkerThread {

	private byte[] sendingBytes;

	public ClientPingingThread(String host, int port) {
		super(host, port, "Ping_" + port, false);
		this.sendingBytes = new byte[1];
		this.port = port;
		this.host = host;
	}

	@Override
	public void run() {

		this.createConnection(this.host, this.port);

		while (true) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			long currentTime = System.nanoTime();
			try{
				this.sendBytes(this.sendingBytes);
				byte[] receivedData = this.readBytes();
				long elapsedTime = ((System.nanoTime() - currentTime) / 1000000L);
				//System.out.println("Time: " + elapsedTime + "ms");
				this.logger.logMessage(this.threadID + ";" + this.port + ";" + this.sdf.format(new Date()) + ";" + elapsedTime + ";N/A;" + this.sendingBytes.length + ";N/A;0;false");
			} catch(IOException e){
				this.logger.logError(e.getMessage());
				if(!this.connectionFailed){
					this.connectionFailed = true;
				}
				this.createConnection(this.host, this.port);
			}
		}
	}
}
