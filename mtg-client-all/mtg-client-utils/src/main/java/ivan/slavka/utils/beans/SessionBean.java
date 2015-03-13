package ivan.slavka.utils.beans;

import java.util.HashMap;
import java.util.Map;

public class SessionBean {

	private final Map<String, ClientBean> session = new HashMap<String, ClientBean>(2);

	public Map<String, ClientBean> getSession() {
		return this.session;
	}

	public ClientBean getClient(String username){
		return this.session.get(username);
	}
}
