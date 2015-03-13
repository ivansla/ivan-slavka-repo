package ivan.slavka.server.factories;

import ivan.slavka.MTGSchemaFactory;
import ivan.slavka.server.adapter.connections.ConnectionReceiver;
import ivan.slavka.server.adapter.connections.MessageSender;
import ivan.slavka.server.application.Application;
import ivan.slavka.server.controller.Controller;
import ivan.slavka.utils.interfaces.IApplicationServer;
import ivan.slavka.utils.interfaces.IMessageControllerServer;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MTGServerFactory {

	private static final Log logger = LogFactory.getLog(MTGServerFactory.class);
	private static MTGSchemaFactory schemaFactory;

	public static void main(String[] args){

		String resourcePath = "./resources/";
		if(args.length > 0){
			resourcePath = args[0];
		}

		try{
			Properties schemaFactoryProperties = new Properties();
			schemaFactoryProperties.load(new FileInputStream(resourcePath + "schema_factory.properties"));
			schemaFactory = MTGSchemaFactory.getInstance(schemaFactoryProperties);

			MessageSender messageSender = new MessageSender();
			messageSender.start();
			IApplicationServer application = new Application();
			IMessageControllerServer messageController = new Controller(application, messageSender);
			application.registerMessageController(messageController);

			ConnectionReceiver server = new ConnectionReceiver(5555, messageController);
			server.start();

			if(logger.isInfoEnabled()){
				logger.info("### INFO ### - Server started successfully!");
			}

		} catch (FileNotFoundException e){
			logger.error("### ERROR ### - Unable to open properties file at path: " + resourcePath, e);
		} catch (IOException e){
			logger.error("### ERROR ### - Error while creting server socket", e);
		}
	}
}
