package ivan.slavka.utils.marshallers;

import ivan.slavka.MTGSchemaFactory;
import ivan.slavka.utils.enums.MTGSchemaEnum;
import ivan.slavka.utils.jaxb.beans.login.Login;

import java.io.PrintWriter;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoginMarshaller {

	private static final Log logger = LogFactory.getLog(LoginMarshaller.class);
	private final String loginPackage  = "ivan.slavka.utils.jaxb.beans.login";
	private final MTGSchemaFactory mtgSchemaFactory = MTGSchemaFactory.getInstance();

	private static LoginMarshaller loginMarshaller = null;
	private Marshaller marshaller;

	private LoginMarshaller(){

		try{
			JAXBContext jaxbContext = JAXBContext.newInstance(this.loginPackage);
			this.marshaller = jaxbContext.createMarshaller();
			this.marshaller.setSchema(this.mtgSchemaFactory.getSchema(MTGSchemaEnum.LOGIN));
		} catch (JAXBException e){
			logger.error("### ERROR ### - Error while creating JAXBContext from package: " + this.loginPackage, e);
		}
	}

	public static LoginMarshaller getInstance(){
		if(loginMarshaller == null){
			loginMarshaller = new LoginMarshaller();
		}
		return loginMarshaller;
	}

	public void marshal(Login login, PrintWriter writer) throws JAXBException{
		this.marshaller.marshal(login, writer);
	}
}
