package ivan.slavka.view.components.panels.connection;

import ivan.slavka.utils.beans.InviteBean;
import ivan.slavka.utils.beans.LoginBean;
import ivan.slavka.utils.enums.InvitationTypeEnum;
import ivan.slavka.utils.enums.LoginTypeEnum;
import ivan.slavka.utils.interfaces.IClientConnectionView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

public class ConnectionPanel extends JPanel {

	private final IClientConnectionView control;

	private JTextField usernameTextField;
	private JButton connectButton;
	private JButton inviteButton;

	public ConnectionPanel(IClientConnectionView control) {
		this.control = control;

		this.initComponents();
	}

	private void initComponents(){
		this.setLayout(new MigLayout("", "[][grow][]", "[][]"));

		this.setBorder(new TitledBorder("Connection Panel"));

		JLabel usernameLabel = new JLabel("Username: ");
		this.add(usernameLabel, "cell 0 0,alignx trailing");

		this.usernameTextField = new JTextField();
		this.add(this.usernameTextField, "cell 1 0 2 1,growx");
		this.usernameTextField.setColumns(10);

		this.connectButton = new JButton("Connect");
		this.add(this.connectButton, "cell 1 1");

		this.inviteButton = new JButton("Invite");
		this.add(this.inviteButton, "cell 2 1");

		/////////////////////////////////////////////
		//			Button Action Listeners
		/////////////////////////////////////////////

		this.connectButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ConnectionPanel.this.control.doLogin(ConnectionPanel.this.createLogin());
			}
		});

		this.inviteButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				ConnectionPanel.this.control.doInvitation(ConnectionPanel.this.createInvitation());
			}
		});
	}

	private LoginBean createLogin(){
		LoginBean login = new LoginBean();
		login.setLoginType(LoginTypeEnum.LOGIN);
		login.setUsername(this.usernameTextField.getText());
		return login;
	}

	private LoginBean createLogout(){
		LoginBean login = new LoginBean();
		login.setLoginType(LoginTypeEnum.LOGOUT);
		login.setUsername(this.usernameTextField.getText());
		return login;
	}

	public InviteBean createInvitation(){
		InviteBean invite = new InviteBean();
		invite.setFrom(this.control.getClientUsername());
		invite.setTo(this.control.getOpponentName());
		invite.setType(InvitationTypeEnum.REQUEST);
		return invite;
	}
}
