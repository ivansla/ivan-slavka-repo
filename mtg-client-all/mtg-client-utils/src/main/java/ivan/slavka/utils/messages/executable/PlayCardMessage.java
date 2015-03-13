package ivan.slavka.utils.messages.executable;

import ivan.slavka.utils.beans.CardBean;
import ivan.slavka.utils.beans.ClientBean;
import ivan.slavka.utils.interfaces.IMessage;
import ivan.slavka.utils.jaxb.beans.play.card.Card;
import ivan.slavka.utils.marshallers.PlayCardMarshaller;
import ivan.slavka.utils.parsers.BeanParser;

import javax.xml.bind.JAXBException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PlayCardMessage implements IMessage{

	private static final Log logger = LogFactory.getLog(PlayCardMessage.class);

	private final ClientBean client;
	private final CardBean cardBean;
	private final PlayCardMarshaller marshaller = PlayCardMarshaller.getInstance();

	public PlayCardMessage(CardBean card, ClientBean client){
		this.client = client;
		this.cardBean = card;
	}

	@Override
	public void execute() {
		try{
			Card card = BeanParser.beanToJaxb(this.cardBean);
			this.marshaller.marshal(card, this.client.getClientWriter());
			this.client.getClientWriter().println();
		} catch (JAXBException e){
			logger.error("### ERROR ### - Failed to marshal playcard", e);
		}
	}

}
