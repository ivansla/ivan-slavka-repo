package ivan.slavka.server.controller;

import ivan.slavka.utils.beans.ClientBean;
import ivan.slavka.utils.beans.InviteBean;
import ivan.slavka.utils.beans.LoginBean;
import ivan.slavka.utils.beans.RosterBean;
import ivan.slavka.utils.enums.LoginTypeEnum;
import ivan.slavka.utils.factories.MessageFactory;
import ivan.slavka.utils.interfaces.IApplicationServer;
import ivan.slavka.utils.interfaces.IMessageControllerServer;
import ivan.slavka.utils.interfaces.IMessageSender;

import java.net.Socket;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Controller implements IMessageControllerServer{

	private static final Log logger = LogFactory.getLog(Controller.class);

	private final MessageFactory messageFactory = MessageFactory.getInstance();

	private final IApplicationServer application;
	private final IMessageSender messageSender;

	public Controller(IApplicationServer application, IMessageSender messageSender){
		this.application = application;
		this.messageSender = messageSender;
	}

	@Override
	public boolean onLogin(LoginBean login, Socket clientSocket) {

		boolean loggedOn = false;

		if(login.getLoginType().equals(LoginTypeEnum.LOGIN)){
			loggedOn = this.application.doLogin(login, clientSocket);
		} else if (login.getLoginType().equals(LoginTypeEnum.LOGOUT)){
			loggedOn = this.application.doLogout(login);
		} else {
			loggedOn = false;
		}
		return loggedOn;
	}

	@Override
	public void onInvitation(InviteBean invite) {
		switch(invite.getType()){
		case REQUEST:
			this.application.doRequestInvitation(invite);
			break;
		case RESPONSE:
			this.application.doResponseInvitation(invite);
			break;
		}
	}

	@Override
	public void doInvitation(InviteBean invite, ClientBean client) {
		this.messageSender.sendMessage(this.messageFactory.createInvitationMessage(invite, client));
	}

	@Override
	public void sendRoster(RosterBean roster, ClientBean client) {
		this.messageSender.sendMessage(this.messageFactory.createRosterMessage(roster, client));
	}
}
