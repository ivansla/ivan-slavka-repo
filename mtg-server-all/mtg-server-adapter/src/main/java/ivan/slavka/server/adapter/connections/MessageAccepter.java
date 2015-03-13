package ivan.slavka.server.adapter.connections;

import ivan.slavka.utils.enums.MTGSchemaEnum;
import ivan.slavka.utils.interfaces.IMessageControllerServer;
import ivan.slavka.utils.parsers.BeanParser;
import ivan.slavka.utils.unmarshallers.InviteUnmarshaller;
import ivan.slavka.utils.unmarshallers.LoginUnmarshaller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MessageAccepter extends Thread{

	private static Log logger = LogFactory.getLog(MessageAccepter.class);

	private final Socket clientSocket;
	private final IMessageControllerServer messageController;

	private boolean clientConnected = true;

	// Unmarshallers
	private final LoginUnmarshaller loginUnmarshaller = LoginUnmarshaller.getInstance();
	private final InviteUnmarshaller inviteUnmarshaller = InviteUnmarshaller.getInstance();

	// Readers
	private StringReader stringReader = null;

	public MessageAccepter(Socket clientSocket, IMessageControllerServer messageController){

		this.messageController = messageController;
		this.clientSocket = clientSocket;
		this.start();
	}

	@Override
	public void run(){

		BufferedReader reader = null;
		try{
			reader = new BufferedReader(new InputStreamReader(this.clientSocket.getInputStream()));
			while(this.clientConnected){
				this.stringReader = new StringReader(reader.readLine());
				this.processMessage();
			}
		} catch(IOException e){
			logger.error("### ERROR ### - Error while trying to create reader for client socket", e);
		}
	}

	private void processMessage(){

		boolean successfullUnmarshalling = false;

		/**
		 * TODO: Find a better solution, to verify which message has arrived.
		 * 		 Now we are saving all the exceptions that come,
		 * 		 but the error exist only if none of the validations are successfull and then we show the error.
		 */
		List<JAXBException> jaxbExceptions = new ArrayList<JAXBException>();

		for(MTGSchemaEnum schemaEnum : MTGSchemaEnum.values()){
			try{
				switch(schemaEnum){
				case LOGIN:
					this.clientConnected = this.messageController.onLogin(BeanParser.jaxbToBean(this.loginUnmarshaller.unmarshal(this.stringReader)), this.clientSocket);
					successfullUnmarshalling = true;
					break;
				case INVITATION:
					this.messageController.onInvitation(BeanParser.jaxbToBean(this.inviteUnmarshaller.unmarshal(this.stringReader)));
					successfullUnmarshalling = true;
					break;
				}

				// Jump out of the loop after successful unmarshalling.
				break;
			} catch (JAXBException e){
				try{
					this.stringReader.reset();
				} catch (IOException ioe){
					logger.error(String.format("### ERROR ### - Unexpected error while reseting StringReader, containing XML String. XML String: %s", this.stringReader), ioe);
				}
				jaxbExceptions.add(e);
				continue;
			}
		}

		if(!successfullUnmarshalling){
			logger.error("### ERROR ### - Failed to unmarshal message. Showing all the accumulated exceptions!");
			int i = 1;
			for(JAXBException e : jaxbExceptions){
				logger.error("Exception number: " + i, e);
				i++;
			}
		}
	}

	public boolean isClientDisconnected() {
		return this.clientConnected;
	}

	public void setClientDisconnected(boolean clientDisconnected) {
		this.clientConnected = clientDisconnected;
	}
}
