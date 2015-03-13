package netwrok.analyzer.threads;

import java.io.IOException;
import java.net.Socket;


public class ServerWorkerThread extends AbstractWorkerThread {

	private Socket socket;

	public ServerWorkerThread(Socket socket, byte sendByte, String threadID) {
		super("localhost", socket.getPort(), threadID, true);
		this.socket = socket;
	}

	@Override
	public void run() {

		this.initStreams(this.socket);
		boolean connectionFailed = false;
		while (true && !connectionFailed) {
			try{
				byte[] receivedData = this.readBytes();
				this.sendBytes(receivedData);
			} catch (IOException e){
				connectionFailed = true;
			}
		}
	}
}
