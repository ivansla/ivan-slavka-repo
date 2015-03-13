package ivan.slavka.utils.interfaces;

import ivan.slavka.utils.beans.InviteBean;
import ivan.slavka.utils.beans.LoginBean;

import java.net.Socket;

public interface IApplicationServer {

	public void registerMessageController(IMessageControllerServer messageController);

	public boolean doLogin(LoginBean login, Socket clientSocket);

	public boolean doLogout(LoginBean login);

	public void doRequestInvitation(InviteBean invite);

	public void doResponseInvitation(InviteBean invite);
}
