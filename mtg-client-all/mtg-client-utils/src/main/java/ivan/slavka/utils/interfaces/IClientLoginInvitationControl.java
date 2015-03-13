package ivan.slavka.utils.interfaces;

import ivan.slavka.utils.beans.InviteBean;
import ivan.slavka.utils.beans.LoginBean;
import ivan.slavka.utils.beans.RosterBean;


public interface IClientLoginInvitationControl {

	public void registerClientConnectionView(IClientConnectionView connectionView);

	public void closeClientConnecetionView();

	public void doLogin(LoginBean loginBean);

	public void doLogout(LoginBean loginBean);

	public void doInvitation(InviteBean inviteBean);

	public void onInvitation(InviteBean inviteBean);

	public void onRoster(RosterBean roster);

	public void startGame(InviteBean inviteBean);
}
