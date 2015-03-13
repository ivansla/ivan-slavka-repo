package ivan.slavka.utils.marshallers;

import ivan.slavka.MTGSchemaFactory;
import ivan.slavka.utils.enums.MTGSchemaEnum;
import ivan.slavka.utils.jaxb.beans.play.card.Card;

import java.io.PrintWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PlayCardMarshaller {

	private static final Log logger = LogFactory.getLog(PlayCardMarshaller.class);
	private final String playCardPackage  = "ivan.slavka.utils.jaxb.beans.play.card";
	private final MTGSchemaFactory mtgSchemaFactory = MTGSchemaFactory.getInstance();

	private static PlayCardMarshaller playCardMarshaller = null;
	private Marshaller marshaller;

	private PlayCardMarshaller(){

		try{
			JAXBContext jaxbContext = JAXBContext.newInstance(this.playCardPackage);
			this.marshaller = jaxbContext.createMarshaller();
			this.marshaller.setSchema(this.mtgSchemaFactory.getSchema(MTGSchemaEnum.PLAY_CARD));
		} catch (JAXBException e){
			logger.error("### ERROR ### - Error while creating JAXBContext from package: " + this.playCardPackage, e);
		}
	}

	public static PlayCardMarshaller getInstance(){
		if(playCardMarshaller == null){
			playCardMarshaller = new PlayCardMarshaller();
		}
		return playCardMarshaller;
	}

	public void marshal(Card card, PrintWriter writer) throws JAXBException{
		this.marshaller.marshal(card, writer);
	}
}
