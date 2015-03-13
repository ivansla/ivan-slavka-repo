package ivan.slavka.client.adapter.connections;

import ivan.slavka.utils.interfaces.IMessageControllerClient;
import ivan.slavka.utils.properties.MTGClientProperties;

import java.io.IOException;
import java.net.Socket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ClientConnection{

	private static final Log logger = LogFactory.getLog(ClientConnection.class);

	private final MTGClientProperties clientProperties = MTGClientProperties.getInstance();
	private final IMessageControllerClient messageController;
	private final String host;
	private final int port;

	public ClientConnection(IMessageControllerClient messageController){
		this.messageController = messageController;
		this.host = this.clientProperties.getProperty("client.host");
		this.port = Integer.parseInt(this.clientProperties.getProperty("client.port"));
	}

	public Socket connect(){

		try {
			Socket clientSocket = new Socket(this.host, this.port);

			MessageSender sender = new MessageSender();
			this.messageController.registerMessageSender(sender);
			sender.start();

			MessageAccepter accepter = new MessageAccepter(clientSocket, this.messageController);
			accepter.start();

			return clientSocket;

		} catch(IOException e) {
			System.out.println(e);
			e.printStackTrace();
		}

		return null;
	}
}
