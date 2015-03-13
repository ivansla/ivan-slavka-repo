package ivan.slavka;

import ivan.slavka.client.adapter.connections.ClientConnection;
import ivan.slavka.client.controller.Control;
import ivan.slavka.client.controller.MessageController;
import ivan.slavka.model.application.Application;
import ivan.slavka.utils.interfaces.IApplication;
import ivan.slavka.utils.interfaces.IMTGClientView;
import ivan.slavka.utils.interfaces.IMessageControllerClient;
import ivan.slavka.utils.properties.MTGClientProperties;
import ivan.slavka.view.panels.ClientConnectionPanel;
import ivan.slavka.view.panels.MTGClientPanel;

import java.io.FileInputStream;
import java.io.IOException;
import java.net.Socket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class ApplicationFactory {

	private static final Log logger = LogFactory.getLog(ApplicationFactory.class);

	public static void main(String[] args) {

		try{
			String resourcePath = "./resources/";
			if(args.length > 0){
				resourcePath = args[0];
			}

			MTGClientProperties properties = MTGClientProperties.getInstance();
			properties.load(new FileInputStream(resourcePath + "mtg-client.properties"));

			MTGSchemaFactory schemaFactory = MTGSchemaFactory.getInstance(properties);

			Control applicationController = new Control();
			IApplication applicationModel = new Application(applicationController);
			IMTGClientView applicationView = new MTGClientPanel(applicationController);
			IMessageControllerClient messageController = new MessageController(applicationController);

			ClientConnection clientConnection = new ClientConnection(messageController);
			Socket clientSocket = clientConnection.connect();
			messageController.createClient(clientSocket);

			ClientConnectionPanel connectionPanel = new ClientConnectionPanel(applicationController);

			applicationController.addModelObserver(applicationModel);
			applicationController.addViewObserver(applicationView);
			applicationController.registerMessageController(messageController);
			applicationController.registerClientConnectionView(connectionPanel);

			connectionPanel.initWindow();

		} catch (IOException ioe){
			logger.error("### ERROR ### - Unable to initialize application.", ioe);
		}
	}


}
