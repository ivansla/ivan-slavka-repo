package netwrok.analyzer.threads;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class ListeningThread implements Runnable {

	private static final byte SENDING_BYTE = 11;

	private ServerSocket serverSocket;
	private int threadID = 0;

	public ListeningThread(int port) {

		try {
			this.serverSocket = new ServerSocket(port);
			System.out.println("Listening socket created at port: " + port);
		} catch (IOException e) {
			System.out.println("Failed to create server socket");
		}
	}

	@Override
	public void run() {
		while (true) {
			try {
				Socket socket = this.serverSocket.accept();
				System.out.println("Client socket accepted at port: " + socket.getPort());
				Thread worker = new Thread(new ServerWorkerThread(socket, SENDING_BYTE, "Server_" + this.threadID));
				worker.start();
				this.threadID++;
			} catch (IOException e) {
				System.out.println("Failed to accept socket");
			}

		}
	}
}
