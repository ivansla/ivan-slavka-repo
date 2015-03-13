package ivan.slavka.view.components.buttons;

import ivan.slavka.utils.beans.wrappers.CardBeanWrapper;
import ivan.slavka.utils.enums.PlayerEnum;
import ivan.slavka.view.panels.MTGClientPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

public class AddCardButton extends JButton{

	private final MTGClientPanel viewController;
	private PlayerEnum playerEnum;

	public AddCardButton(MTGClientPanel viewController, PlayerEnum playerEnum){

		this.viewController = viewController;
		this.playerEnum = playerEnum;

		this.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				switch(AddCardButton.this.playerEnum){
				case PLAYER:
					AddCardButton.this.viewController.addCard(PlayerEnum.PLAYER);
					break;
				case OPPONENT:
					CardBeanWrapper cardBean = AddCardButton.this.viewController.getController().addCard();
					CreatureButton creatureButton = new CreatureButton(AddCardButton.this.viewController, cardBean, PlayerEnum.OPPONENT);
					AddCardButton.this.viewController.getPlayTablePanel().getBattlefieldPanel().add(creatureButton);
					AddCardButton.this.viewController.getPlayTablePanel().getBattlefieldPanel().validate();
					break;
				}

			}
		});
	}
}
