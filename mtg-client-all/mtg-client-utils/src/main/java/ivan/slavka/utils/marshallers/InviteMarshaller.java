package ivan.slavka.utils.marshallers;

import ivan.slavka.MTGSchemaFactory;
import ivan.slavka.utils.enums.MTGSchemaEnum;
import ivan.slavka.utils.jaxb.beans.invitation.Invite;

import java.io.PrintWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InviteMarshaller {

	private static final Log logger = LogFactory.getLog(InviteMarshaller.class);
	private final String invitePackage  = "ivan.slavka.utils.jaxb.beans.invitation";
	private final MTGSchemaFactory mtgSchemaFactory = MTGSchemaFactory.getInstance();

	private static InviteMarshaller inviteMarshaller = null;
	private Marshaller marshaller;

	private InviteMarshaller(){

		try{
			JAXBContext jaxbContext = JAXBContext.newInstance(this.invitePackage);
			this.marshaller = jaxbContext.createMarshaller();
			this.marshaller.setSchema(this.mtgSchemaFactory.getSchema(MTGSchemaEnum.INVITATION));
		} catch (JAXBException e){
			logger.error("### ERROR ### - Error while creating JAXBContext from package: " + this.invitePackage, e);
		}
	}

	public static InviteMarshaller getInstance(){
		if(inviteMarshaller == null){
			inviteMarshaller = new InviteMarshaller();
		}
		return inviteMarshaller;
	}

	public void marshal(Invite invite, PrintWriter writer) throws JAXBException{
		this.marshaller.marshal(invite, writer);
	}
}
