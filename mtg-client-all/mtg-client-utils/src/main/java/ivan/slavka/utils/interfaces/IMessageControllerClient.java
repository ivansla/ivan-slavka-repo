package ivan.slavka.utils.interfaces;

import ivan.slavka.utils.beans.CardBean;
import ivan.slavka.utils.beans.InviteBean;
import ivan.slavka.utils.beans.LoginBean;
import ivan.slavka.utils.beans.RosterBean;

import java.io.IOException;
import java.net.Socket;

public interface IMessageControllerClient {

	public void registerMessageSender(IMessageSender messageSender);

	public void createClient(Socket clientSocket) throws IOException;

	public boolean onLogin(LoginBean login, Socket clientSocket);

	public boolean onInvitation(InviteBean invite);

	public void doLogin(LoginBean loginBean);

	public void doInvitation(InviteBean invite);

	public boolean onRoster(RosterBean roster);

	public void doPlayCard(CardBean cardBean);

	public boolean onPlayCard(CardBean cardBean);
}
