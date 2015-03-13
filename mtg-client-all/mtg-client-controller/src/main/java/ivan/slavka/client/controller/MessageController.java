package ivan.slavka.client.controller;

import ivan.slavka.utils.beans.CardBean;
import ivan.slavka.utils.beans.ClientBean;
import ivan.slavka.utils.beans.InviteBean;
import ivan.slavka.utils.beans.LoginBean;
import ivan.slavka.utils.beans.RosterBean;
import ivan.slavka.utils.exceptions.MessageValidationException;
import ivan.slavka.utils.interfaces.IClientLoginInvitationControl;
import ivan.slavka.utils.interfaces.IControl;
import ivan.slavka.utils.interfaces.IMessageControllerClient;
import ivan.slavka.utils.interfaces.IMessageSender;
import ivan.slavka.utils.messages.executable.InvitationMessage;
import ivan.slavka.utils.messages.executable.LoginMessage;
import ivan.slavka.utils.messages.executable.PlayCardMessage;
import ivan.slavka.utils.properties.MessageConstants;
import ivan.slavka.utils.properties.MessageResourceBundle;

import java.io.IOException;
import java.net.Socket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class MessageController implements IMessageControllerClient{

	private static final Log logger = LogFactory.getLog(MessageController.class);
	private static final MessageResourceBundle messageResource = MessageResourceBundle.getInstance();

	private final IClientLoginInvitationControl controller;
	private ClientBean client;
	private IMessageSender messageSender;

	public MessageController(IClientLoginInvitationControl controller){
		this.controller = controller;
	}

	@Override
	public boolean onLogin(LoginBean login, Socket clientSocket) {
		// TODO: Add implementation for the case when user enters incorrect user credentials. Today it closes the socket.
		if(login != null){
			return true;
		}
		return false;
	}

	@Override
	public void doLogin(LoginBean loginBean) {
		this.messageSender.sendMessage(new LoginMessage(loginBean, this.client));
	}

	@Override
	public boolean onInvitation(InviteBean invite) {
		if(invite != null){
			switch(invite.getType()){
			case REQUEST:
				this.controller.onInvitation(invite);
				break;
			case RESPONSE:
				switch(invite.getAnswer()){
				case YES:
					this.controller.startGame(invite);
					this.controller.closeClientConnecetionView();
					break;
				case NO:
					break;
				}
				break;
			}
			return true;
		}
		return false;
	}

	@Override
	public void doInvitation(InviteBean invite) {
		// validation
		try{
			if(invite.getFrom().equalsIgnoreCase(invite.getTo())){
				throw new MessageValidationException(messageResource.getMessage(MessageConstants.INVALID_DESTINATION));
			}

			this.messageSender.sendMessage(new InvitationMessage(invite, this.client));

			switch(invite.getType()){
			case RESPONSE:
				switch(invite.getAnswer()){
				case YES:
					this.controller.startGame(invite);
					this.controller.closeClientConnecetionView();
					break;
				case NO:
					break;
				}
				break;
			case REQUEST:
				break;
			}
		} catch(MessageValidationException e){
			logger.error(e.getMessage());
		}
	}

	@Override
	public void registerMessageSender(IMessageSender messageSender) {
		this.messageSender = messageSender;
	}

	@Override
	public void createClient(Socket clientSocket) throws IOException {
		this.client = new ClientBean(clientSocket);
	}

	@Override
	public boolean onRoster(RosterBean roster) {
		if(roster != null){
			this.controller.onRoster(roster);
			return true;
		}
		return false;
	}

	@Override
	public void doPlayCard(CardBean cardBean) {
		this.messageSender.sendMessage(new PlayCardMessage(cardBean, this.client));
	}

	@Override
	public boolean onPlayCard(CardBean cardBean) {
		if(cardBean == null){
			return false;
		}
		IControl controller = (IControl) this.controller;
		controller.receivePlayCard(cardBean);
		return true;
	}
}
