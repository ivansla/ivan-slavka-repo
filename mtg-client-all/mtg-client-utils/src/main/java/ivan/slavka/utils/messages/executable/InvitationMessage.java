package ivan.slavka.utils.messages.executable;

import ivan.slavka.utils.beans.ClientBean;
import ivan.slavka.utils.beans.InviteBean;
import ivan.slavka.utils.interfaces.IMessage;
import ivan.slavka.utils.jaxb.beans.invitation.Invite;
import ivan.slavka.utils.marshallers.InviteMarshaller;
import ivan.slavka.utils.parsers.BeanParser;

import javax.xml.bind.JAXBException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InvitationMessage implements IMessage{

	private static final Log logger = LogFactory.getLog(InvitationMessage.class);

	private final ClientBean client;
	private final InviteBean inviteBean;
	private final InviteMarshaller marshaller = InviteMarshaller.getInstance();

	public InvitationMessage(InviteBean invite, ClientBean client){
		this.client = client;
		this.inviteBean = invite;
	}

	@Override
	public void execute() {
		try{
			Invite invite = BeanParser.beanToJaxb(this.inviteBean);
			this.marshaller.marshal(invite, this.client.getClientWriter());
			this.client.getClientWriter().println();
		} catch (JAXBException e){
			logger.error("### ERROR ### - Failed to marshal invitation", e);
		}
	}

}
