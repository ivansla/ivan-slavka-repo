package ivan.slavka.utils.unmarshallers;

import ivan.slavka.MTGSchemaFactory;
import ivan.slavka.utils.enums.MTGSchemaEnum;
import ivan.slavka.utils.jaxb.beans.play.card.Card;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PlayCardUnmarshaller {

	private static final Log logger = LogFactory.getLog(PlayCardUnmarshaller.class);
	private final String playCardPackage  = "ivan.slavka.utils.jaxb.beans.play.card";
	private final MTGSchemaFactory mtgSchemaFactory = MTGSchemaFactory.getInstance();

	private static PlayCardUnmarshaller playCardUnmarshaller = null;
	private Unmarshaller unmarshaller;

	private PlayCardUnmarshaller(){

		try{
			JAXBContext jaxbContext = JAXBContext.newInstance(this.playCardPackage);
			this.unmarshaller = jaxbContext.createUnmarshaller();
			this.unmarshaller.setSchema(this.mtgSchemaFactory.getSchema(MTGSchemaEnum.PLAY_CARD));
		} catch (JAXBException e){
			logger.error("### ERROR ### - Error while creating JAXBContext from package: " + this.playCardPackage, e);
		}
	}

	public static PlayCardUnmarshaller getInstance(){
		if(playCardUnmarshaller == null){
			playCardUnmarshaller = new PlayCardUnmarshaller();
		}
		return playCardUnmarshaller;
	}

	public Card unmarshal(StringReader reader) throws JAXBException{
		return (Card)this.unmarshaller.unmarshal(reader);
	}
}
