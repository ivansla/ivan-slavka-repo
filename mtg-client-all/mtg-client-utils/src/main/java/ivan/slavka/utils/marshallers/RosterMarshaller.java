package ivan.slavka.utils.marshallers;

import ivan.slavka.MTGSchemaFactory;
import ivan.slavka.utils.enums.MTGSchemaEnum;
import ivan.slavka.utils.jaxb.beans.roster.Roster;

import java.io.PrintWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class RosterMarshaller {

	private static final Log logger = LogFactory.getLog(RosterMarshaller.class);
	private final String rosterPackage  = "ivan.slavka.utils.jaxb.beans.roster";
	private final MTGSchemaFactory mtgSchemaFactory = MTGSchemaFactory.getInstance();

	private static RosterMarshaller rosterMarshaller = null;
	private Marshaller marshaller;

	private RosterMarshaller(){

		try{
			JAXBContext jaxbContext = JAXBContext.newInstance(this.rosterPackage);
			this.marshaller = jaxbContext.createMarshaller();
			this.marshaller.setSchema(this.mtgSchemaFactory.getSchema(MTGSchemaEnum.ROSTER));
		} catch (JAXBException e){
			logger.error("### ERROR ### - Error while creating JAXBContext from package: " + this.rosterPackage, e);
		}
	}

	public static RosterMarshaller getInstance(){
		if(rosterMarshaller == null){
			rosterMarshaller = new RosterMarshaller();
		}
		return rosterMarshaller;
	}

	public void marshal(Roster roster, PrintWriter writer) throws JAXBException{
		this.marshaller.marshal(roster, writer);
	}
}
