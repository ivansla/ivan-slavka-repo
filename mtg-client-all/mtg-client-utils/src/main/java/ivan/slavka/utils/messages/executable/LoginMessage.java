package ivan.slavka.utils.messages.executable;

import ivan.slavka.utils.beans.ClientBean;
import ivan.slavka.utils.beans.LoginBean;
import ivan.slavka.utils.interfaces.IMessage;
import ivan.slavka.utils.jaxb.beans.login.Login;
import ivan.slavka.utils.marshallers.LoginMarshaller;
import ivan.slavka.utils.parsers.BeanParser;

import javax.xml.bind.JAXBException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class LoginMessage implements IMessage{

	private static final Log logger = LogFactory.getLog(LoginMessage.class);

	private final ClientBean client;
	private final LoginBean loginBean;
	private final LoginMarshaller marshaller = LoginMarshaller.getInstance();

	public LoginMessage(LoginBean loginBean, ClientBean client){
		this.client = client;
		this.loginBean = loginBean;
	}

	@Override
	public void execute() {
		try{
			Login login = BeanParser.beanToJaxb(this.loginBean);
			this.marshaller.marshal(login, this.client.getClientWriter());
			this.client.getClientWriter().println();
		} catch (JAXBException e){
			logger.error("### ERROR ### - Failed to marshal invitation", e);
		}
	}

}
