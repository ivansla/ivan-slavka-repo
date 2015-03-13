package ivan.slavka.utils.messages.executable;

import ivan.slavka.utils.beans.ClientBean;
import ivan.slavka.utils.beans.RosterBean;
import ivan.slavka.utils.interfaces.IMessage;
import ivan.slavka.utils.jaxb.beans.roster.Roster;
import ivan.slavka.utils.marshallers.RosterMarshaller;
import ivan.slavka.utils.parsers.BeanParser;

import javax.xml.bind.JAXBException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RosterMessage implements IMessage{

	private static final Log logger = LogFactory.getLog(RosterMessage.class);

	private final ClientBean client;
	private final RosterBean rosterBean;
	private final RosterMarshaller marshaller = RosterMarshaller.getInstance();

	public RosterMessage(RosterBean roster, ClientBean client){
		this.client = client;
		this.rosterBean = roster;
	}

	@Override
	public void execute() {
		try{
			Roster roster = BeanParser.beanToJaxb(this.rosterBean);
			this.marshaller.marshal(roster, this.client.getClientWriter());
			this.client.getClientWriter().println();
		} catch (JAXBException e){
			logger.error("### ERROR ### - Failed to marshal invitation", e);
		}
	}

}
