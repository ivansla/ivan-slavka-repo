package ivan.slavka.utils.interfaces;

import ivan.slavka.utils.beans.InviteBean;
import ivan.slavka.utils.beans.LoginBean;
import ivan.slavka.utils.beans.RosterBean;

public interface IClientConnectionView extends IConfirmationSelection{

	public void onRoster(RosterBean roster);

	public String getOpponentName();

	public void doLogin(LoginBean login);

	public void doInvitation(InviteBean invite);

	public void onInvitation(InviteBean invite);

	public String getClientUsername();

	public void closeWindow();
}
