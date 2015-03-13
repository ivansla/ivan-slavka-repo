package ivan.slavka.utils.unmarshallers;

import ivan.slavka.MTGSchemaFactory;
import ivan.slavka.utils.enums.MTGSchemaEnum;
import ivan.slavka.utils.jaxb.beans.roster.Roster;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RosterUnmarshaller {

	private static final Log logger = LogFactory.getLog(RosterUnmarshaller.class);
	private final String rosterPackage  = "ivan.slavka.utils.jaxb.beans.roster";
	private final MTGSchemaFactory mtgSchemaFactory = MTGSchemaFactory.getInstance();

	private static RosterUnmarshaller rosterUnmarshaller = null;
	private Unmarshaller unmarshaller;

	private RosterUnmarshaller(){

		try{
			JAXBContext jaxbContext = JAXBContext.newInstance(this.rosterPackage);
			this.unmarshaller = jaxbContext.createUnmarshaller();
			this.unmarshaller.setSchema(this.mtgSchemaFactory.getSchema(MTGSchemaEnum.ROSTER));
		} catch (JAXBException e){
			logger.error("### ERROR ### - Error while creating JAXBContext from package: " + this.rosterPackage, e);
		}
	}

	public static RosterUnmarshaller getInstance(){
		if(rosterUnmarshaller == null){
			rosterUnmarshaller = new RosterUnmarshaller();
		}
		return rosterUnmarshaller;
	}

	public Roster unmarshal(StringReader reader) throws JAXBException{
		return (Roster)this.unmarshaller.unmarshal(reader);
	}
}
