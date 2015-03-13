package ivan.slavka.view.components.panels.window;

import ivan.slavka.utils.enums.ConfirmationTypeEnum;
import ivan.slavka.utils.interfaces.IConfirmationSelection;
import ivan.slavka.view.components.buttons.ConfirmationButton;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

public class InvitationRequestPanel extends JPanel implements IConfirmationSelection{

	private final IConfirmationSelection confirmationController;
	private JLabel text;

	public InvitationRequestPanel(IConfirmationSelection confirmationController) {
		this.confirmationController = confirmationController;

		this.initComponents();
	}

	private void initComponents(){

		this.setLayout(new MigLayout("", "[grow][grow][grow]", "[grow][]"));
		this.setBorder(new TitledBorder("Invitation Request"));

		this.text = new JLabel();
		this.add(this.text, "cell 0 0 2 1, aligny center, alignx center");

		ConfirmationButton okConfirmationButton = new ConfirmationButton(this, ConfirmationTypeEnum.OK);
		this.add(okConfirmationButton, "cell 0 1, aligny bottom");

		ConfirmationButton cancelConfirmationButton = new ConfirmationButton(this, ConfirmationTypeEnum.CANCEL);
		this.add(cancelConfirmationButton, "cell 1 1, aligny bottom");
	}

	public void setPlayerName(String name){
		this.text.setText(name + " invited you for a game!");
	}

	//////////////////////////////////////////////////////////////
	//	implementation (IConfirmationSelection)
	//////////////////////////////////////////////////////////////

	@Override
	public void doOk() {
		this.confirmationController.doOk();
	}

	@Override
	public void doCancel() {
		this.confirmationController.doCancel();
	}

}
