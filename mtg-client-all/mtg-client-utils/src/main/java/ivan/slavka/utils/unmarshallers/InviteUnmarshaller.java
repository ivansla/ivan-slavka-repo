package ivan.slavka.utils.unmarshallers;

import ivan.slavka.MTGSchemaFactory;
import ivan.slavka.utils.enums.MTGSchemaEnum;
import ivan.slavka.utils.jaxb.beans.invitation.Invite;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class InviteUnmarshaller {

	private static final Log logger = LogFactory.getLog(InviteUnmarshaller.class);
	private final String invitePackage  = "ivan.slavka.utils.jaxb.beans.invitation";
	private final MTGSchemaFactory mtgSchemaFactory = MTGSchemaFactory.getInstance();

	private static InviteUnmarshaller inviteUnmarshaller = null;
	private Unmarshaller unmarshaller;

	private InviteUnmarshaller(){

		try{
			JAXBContext jaxbContext = JAXBContext.newInstance(this.invitePackage);
			this.unmarshaller = jaxbContext.createUnmarshaller();
			this.unmarshaller.setSchema(this.mtgSchemaFactory.getSchema(MTGSchemaEnum.INVITATION));
		} catch (JAXBException e){
			logger.error("### ERROR ### - Error while creating JAXBContext from package: " + this.invitePackage, e);
		}
	}

	public static InviteUnmarshaller getInstance(){
		if(inviteUnmarshaller == null){
			inviteUnmarshaller = new InviteUnmarshaller();
		}
		return inviteUnmarshaller;
	}

	public Invite unmarshal(StringReader reader) throws JAXBException{
		return (Invite)this.unmarshaller.unmarshal(reader);
	}
}
