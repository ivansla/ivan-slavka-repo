package ivan.slavka.view.components.buttons;

import ivan.slavka.utils.beans.TurnStatusBean;
import ivan.slavka.view.components.panels.StatusPanel;
import ivan.slavka.view.panels.MTGClientPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class EndPhaseButton extends JButton{

	private final MTGClientPanel viewController;
	private final StatusPanel statusPanel;

	public EndPhaseButton(MTGClientPanel viewController, StatusPanel statusPanel){

		this.viewController = viewController;
		this.statusPanel = statusPanel;

		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {

				EndPhaseButton.this.viewController.nextStep();
				TurnStatusBean turnStatus = EndPhaseButton.this.viewController.getTurnStatus();
				EndPhaseButton.this.statusPanel.setTurnLabel(turnStatus.getTurnOwner().toString());
			}
		});
	}
}
