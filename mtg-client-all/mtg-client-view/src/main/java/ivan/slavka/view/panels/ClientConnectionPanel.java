package ivan.slavka.view.panels;

import ivan.slavka.utils.beans.InviteBean;
import ivan.slavka.utils.beans.LoginBean;
import ivan.slavka.utils.beans.RosterBean;
import ivan.slavka.utils.enums.InvitationAnswerEnum;
import ivan.slavka.utils.enums.InvitationTypeEnum;
import ivan.slavka.utils.interfaces.IClientConnectionView;
import ivan.slavka.utils.interfaces.IClientLoginInvitationControl;
import ivan.slavka.utils.interfaces.IRosterControl;
import ivan.slavka.view.components.panels.connection.ConnectionPanel;
import ivan.slavka.view.components.panels.connection.RosterPanel;
import ivan.slavka.view.components.window.InvitationRequestPopupWindow;

import javax.swing.JFrame;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class ClientConnectionPanel extends JPanel implements IClientConnectionView{

	private final IClientLoginInvitationControl controller;
	private InvitationRequestPopupWindow requestPopupWindow;
	private JFrame clientFrame;

	// Context
	private String username;
	private String opponentName;
	private String session;

	private ConnectionPanel connectionPanel;
	private IRosterControl rosterPanel;

	public ClientConnectionPanel(IClientLoginInvitationControl controller) {
		this.controller = controller;

		this.initComponents();
	}

	private void initComponents(){

		this.setLayout(new MigLayout("", "[grow][grow]", "[100][grow]"));

		this.connectionPanel = new ConnectionPanel(this);
		this.add(this.connectionPanel, "cell 0 0,grow");

		this.rosterPanel = new RosterPanel();
		this.add((RosterPanel)this.rosterPanel, "cell 1 0 1 2,grow");
	}


	public void initWindow(){

		if(this.clientFrame == null){
			this.clientFrame = new JFrame();
		}

		this.clientFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.clientFrame.setBounds(100, 100, 1000, 500);
		this.clientFrame.setContentPane(this);
		this.clientFrame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.clientFrame.setResizable(true);
		this.clientFrame.setVisible(true);
	}

	@Override
	public void closeWindow(){
		this.clientFrame.dispose();
	}

	/////////////////////////////////////////////////////////
	//	implementation (IClientConnectionView)
	/////////////////////////////////////////////////////////

	@Override
	public void onRoster(RosterBean roster) {
		this.rosterPanel.updateRoster(roster);
	}

	@Override
	public String getOpponentName() {
		return this.rosterPanel.getOpponentName();
	}

	@Override
	public void doLogin(LoginBean login) {
		this.username = login.getUsername();
		this.controller.doLogin(login);
	}

	@Override
	public void doInvitation(InviteBean invite) {
		this.controller.doInvitation(invite);
	}

	@Override
	public String getClientUsername() {
		return this.username;
	}

	@Override
	public void onInvitation(InviteBean invite) {
		if(this.requestPopupWindow == null){
			this.requestPopupWindow = InvitationRequestPopupWindow.getInstance(this);
		}

		this.opponentName = invite.getFrom();
		this.session = invite.getSessionId();
		this.requestPopupWindow.showWindow(this.opponentName);
	}

	/////////////////////////////////////////////////////////
	//	implementation (IConfirmationSelection)
	/////////////////////////////////////////////////////////

	@Override
	public void doOk() {

		InviteBean inviteBean = new InviteBean();
		inviteBean.setAnswer(InvitationAnswerEnum.YES);
		inviteBean.setType(InvitationTypeEnum.RESPONSE);
		inviteBean.setTo(this.opponentName);
		inviteBean.setFrom(this.username);
		inviteBean.setSessionId(this.session);
		this.controller.doInvitation(inviteBean);
	}

	@Override
	public void doCancel() {
		InviteBean inviteBean = new InviteBean();
		inviteBean.setAnswer(InvitationAnswerEnum.NO);
		inviteBean.setType(InvitationTypeEnum.RESPONSE);
		inviteBean.setTo(this.opponentName);
		inviteBean.setFrom(this.username);
		this.controller.doInvitation(inviteBean);
	}

}
