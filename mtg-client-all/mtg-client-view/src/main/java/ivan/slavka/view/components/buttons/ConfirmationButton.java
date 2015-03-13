package ivan.slavka.view.components.buttons;

import ivan.slavka.utils.enums.ConfirmationTypeEnum;
import ivan.slavka.utils.interfaces.IConfirmationSelection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class ConfirmationButton extends JButton{

	private final IConfirmationSelection confirmationController;
	private final ConfirmationTypeEnum confirmationType;

	public ConfirmationButton(IConfirmationSelection confirmationController, ConfirmationTypeEnum confirmationEnum){
		this.confirmationController = confirmationController;
		this.confirmationType = confirmationEnum;

		this.initComponents(confirmationEnum);
	}

	private void initComponents(ConfirmationTypeEnum confirmationEnum){
		this.setText(confirmationEnum.toString());
		this.setSize(100, 25);

		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switch(ConfirmationButton.this.confirmationType){
				case OK:
					ConfirmationButton.this.confirmationController.doOk();
					break;
				case CANCEL:
					ConfirmationButton.this.confirmationController.doCancel();
					break;
				}
			}
		});
	}
}
