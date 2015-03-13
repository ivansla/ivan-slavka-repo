package ivan.slavka.server.application;

import ivan.slavka.server.application.singleton.TurnDecider;
import ivan.slavka.utils.beans.ClientBean;
import ivan.slavka.utils.beans.InviteBean;
import ivan.slavka.utils.beans.LoginBean;
import ivan.slavka.utils.beans.RosterBean;
import ivan.slavka.utils.beans.SessionBean;
import ivan.slavka.utils.interfaces.IApplicationServer;
import ivan.slavka.utils.interfaces.IMessageControllerServer;

import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class Application implements IApplicationServer{

	// Logger
	private static final Log logger = LogFactory.getLog(Application.class);

	// Controller
	private IMessageControllerServer messageController;

	// Context of users
	private Lock rosterLock = new ReentrantLock();
	private final Map<String, ClientBean> roster = new HashMap<String, ClientBean>();

	// Context of sessions
	private final Map<String, SessionBean> sessions = new HashMap<String, SessionBean>();

	// Generators
	private final AtomicInteger sessionIdGenerator = new AtomicInteger();
	private final TurnDecider turnDecider = TurnDecider.getInstance();

	@Override
	public boolean doLogin(LoginBean login, Socket clientSocket) {
		try{
			this.rosterLock.lock();
			if(!this.roster.containsKey(login.getUsername())){
				ClientBean client = new ClientBean(clientSocket);
				this.roster.put(login.getUsername(), client);
				for(ClientBean clientInRoster : this.roster.values()){
					this.sendRoster(clientInRoster);
				}
				return true;
			} else {
				this.roster.remove(login.getUsername());
				return false;
			}
		} catch(IOException e){
			logger.error("### ERROR ### - Failed to create Client", e);
			return false;
		} finally {
			this.rosterLock.unlock();
		}
	}

	@Override
	public boolean doLogout(LoginBean login) {
		try{
			this.rosterLock.lock();
			if(this.roster.containsKey(login.getUsername())){
				this.roster.remove(login.getUsername());
			}
			return false;
		} finally {
			this.rosterLock.unlock();
		}
	}

	@Override
	public void registerMessageController(IMessageControllerServer messageController){
		this.messageController = messageController;
	}

	@Override
	public void doRequestInvitation(InviteBean invite) {
		String sessionId = String.valueOf(this.sessionIdGenerator.getAndIncrement());
		SessionBean session = new SessionBean();
		session.getSession().put(invite.getFrom(), this.roster.get(invite.getFrom()));
		this.sessions.put(sessionId, session);
		invite.setSessionId(sessionId);
		invite.setTurnOwner(this.turnDecider.generateTurnOwner());

		this.messageController.doInvitation(invite, this.roster.get(invite.getTo()));
	}

	@Override
	public void doResponseInvitation(InviteBean invite) {
		this.messageController.doInvitation(invite, this.roster.get(invite.getTo()));

		switch(invite.getAnswer()){
		case YES:
			SessionBean session = this.sessions.get(invite.getSessionId());
			session.getSession().put(invite.getFrom(), this.roster.get(invite.getFrom()));

			// Removing users from roster after successful creation of session
			this.roster.remove(invite.getFrom());
			this.roster.remove(invite.getTo());
			break;
		case NO:
			this.sessions.remove(invite.getSessionId());
			break;
		}
	}

	private void sendRoster(ClientBean client) {
		RosterBean roster = new RosterBean();
		try{
			this.rosterLock.lock();
			for(String username : this.roster.keySet()){
				roster.getRoster().add(username);
			}
		} finally {
			this.rosterLock.unlock();
		}

		this.messageController.sendRoster(roster, client);
	}
}
