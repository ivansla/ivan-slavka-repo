package ivan.slavka.server.adapter.connections;

import ivan.slavka.utils.interfaces.IMessageControllerServer;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ConnectionReceiver extends Thread{

	private static Log logger = LogFactory.getLog(ConnectionReceiver.class);

	private final ServerSocket welcomeSocket;
	private final IMessageControllerServer messageController;

	public ConnectionReceiver(int port, IMessageControllerServer messageController) throws IOException{
		this.welcomeSocket = new ServerSocket(port);
		this.messageController = messageController;
	}

	@Override
	public void run(){

		Socket clientSocket;
		while(true){
			try{
				clientSocket = this.welcomeSocket.accept();
				clientSocket.setKeepAlive(true);
				new MessageAccepter(clientSocket, this.messageController);
			} catch (IOException e){
				logger.error("### ERROR ### - Error while creating client socket", e);
			}
		}
	}
}
