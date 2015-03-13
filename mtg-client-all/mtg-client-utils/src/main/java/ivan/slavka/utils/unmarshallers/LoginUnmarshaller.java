package ivan.slavka.utils.unmarshallers;

import ivan.slavka.MTGSchemaFactory;
import ivan.slavka.utils.enums.MTGSchemaEnum;
import ivan.slavka.utils.jaxb.beans.login.Login;

import java.io.StringReader;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoginUnmarshaller {

	private static final Log logger = LogFactory.getLog(LoginUnmarshaller.class);
	private final String loginPackage  = "ivan.slavka.utils.jaxb.beans.login";
	private final MTGSchemaFactory mtgSchemaFactory = MTGSchemaFactory.getInstance();

	private static LoginUnmarshaller loginUnmarshaller = null;
	private Unmarshaller unmarshaller;

	private LoginUnmarshaller(){

		try{
			JAXBContext jaxbContext = JAXBContext.newInstance(this.loginPackage);
			this.unmarshaller = jaxbContext.createUnmarshaller();
			this.unmarshaller.setSchema(this.mtgSchemaFactory.getSchema(MTGSchemaEnum.LOGIN));
		} catch (JAXBException e){
			logger.error("### ERROR ### - Error while creating JAXBContext from package: " + this.loginPackage, e);
		}
	}

	public static LoginUnmarshaller getInstance(){
		if(loginUnmarshaller == null){
			loginUnmarshaller = new LoginUnmarshaller();
		}
		return loginUnmarshaller;
	}

	public Login unmarshal(StringReader reader) throws JAXBException{
		return (Login)this.unmarshaller.unmarshal(reader);
	}
}
