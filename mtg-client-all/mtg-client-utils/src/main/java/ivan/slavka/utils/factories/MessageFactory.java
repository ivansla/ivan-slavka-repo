package ivan.slavka.utils.factories;

import ivan.slavka.utils.beans.ClientBean;
import ivan.slavka.utils.beans.InviteBean;
import ivan.slavka.utils.beans.RosterBean;
import ivan.slavka.utils.interfaces.IMessage;
import ivan.slavka.utils.messages.executable.InvitationMessage;
import ivan.slavka.utils.messages.executable.RosterMessage;

public class MessageFactory {

	private static MessageFactory messageFactory = null;

	private MessageFactory(){}

	public static MessageFactory getInstance(){
		if(messageFactory == null){
			messageFactory = new MessageFactory();
		}
		return messageFactory;
	}

	public IMessage createInvitationMessage(InviteBean invite, ClientBean clientBean) {
		return new InvitationMessage(invite, clientBean);
	}

	public IMessage createRosterMessage(RosterBean roster, ClientBean clientBean) {
		return new RosterMessage(roster, clientBean);
	}
}
