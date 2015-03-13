package ivan.slavka.utils.interfaces;

import ivan.slavka.utils.beans.ClientBean;
import ivan.slavka.utils.beans.InviteBean;
import ivan.slavka.utils.beans.LoginBean;
import ivan.slavka.utils.beans.RosterBean;

import java.net.Socket;

public interface IMessageControllerServer {

	public boolean onLogin(LoginBean login, Socket clientSocket);

	public void onInvitation(InviteBean invite);

	public void doInvitation(InviteBean invite, ClientBean client);

	public void sendRoster(RosterBean roster, ClientBean client);
}
