package ivan.slavka.view.components.listeners;

import ivan.slavka.utils.beans.wrappers.CardBeanWrapper;
import ivan.slavka.utils.enums.CardPlayTypeEnum;
import ivan.slavka.view.components.buttons.CreatureButton;
import ivan.slavka.view.components.window.CreatureButtonPopupWindow;
import ivan.slavka.view.panels.MTGClientPanel;

import java.awt.Color;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.event.MouseListener;

import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class CreatureButtonMouseListener implements MouseListener{

	private final CreatureButton creatureButton;
	private final CreatureButtonPopupWindow popupWindow;
	private final MTGClientPanel viewController;

	private final Border systemBorder = LineBorder.createGrayLineBorder();

	public CreatureButtonMouseListener(CreatureButton creatureButton, CreatureButtonPopupWindow popupWindow, MTGClientPanel viewController){
		this.viewController = viewController;
		this.creatureButton = creatureButton;
		this.popupWindow = popupWindow;
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		//		CardBean cardBean = this.viewController.getPlayTablePanel().selectedCard();
		//		if(cardBean != null){
		//			this.popupWindow.addItem(cardBean, CardPlayTypeEnum.ENCHANTMENT);
		//			this.viewController.getPlayTablePanel().deselectCard();
		//		}else if(!this.creatureButton.isAttacking() && this.viewController.getController().getTurnOwner() == PlayerEnum.PLAYER){
		//		}else if(!this.creatureButton.isAttacking() && this.viewController.getController().getTurnOwner() == PlayerEnum.OPPONENT){
		//			this.viewController.getSelectedButton().addItem(this.creatureButton.getCardBean(), CardPlayTypeEnum.BLOCKER);
		//			this.viewController.getPlayTablePanel().getBattlefieldPanel().validate();
		//			this.creatureButton.setAttacking(true);
		//		}

		switch(this.viewController.getController().getTurnStatus().getTurnOwner()){
		case PLAYER:
			if(this.creatureButton.isAttacking()){
				this.viewController.getPlayTablePanel().getBattlefieldPanel().remove(this.creatureButton);
				this.viewController.getPlayTablePanel().getPlayerCreaturePanel().add(this.creatureButton, "cell "
						+ this.creatureButton.getCardColumn()
						+ " "
						+ this.creatureButton.getCardRow());

				this.creatureButton.setAttacking(false);
			} else {
				this.viewController.getPlayTablePanel().getBattlefieldPanel().add(this.creatureButton);
				this.creatureButton.setAttacking(true);
			}

			//this.viewController.getPlayTablePanel().validate();
			break;
		case OPPONENT:
			switch(this.creatureButton.getCardOwner()){
			case OPPONENT:
				if(this.viewController.getSelectedButton() != null && this.viewController.getSelectedButton().equals(this.creatureButton)){
					this.viewController.setSelectedButton(null);
					this.creatureButton.setBorder(this.systemBorder);
				} else {
					this.viewController.setSelectedButton(this.creatureButton);
					this.creatureButton.setBorder(new LineBorder(Color.BLUE, 2));
				}
				return;
			case PLAYER:
				if(this.creatureButton.isBlocking()){
					this.viewController.getSelectedButton().removeItem(this.creatureButton.getCardBeanWrapper(), CardPlayTypeEnum.BLOCKER);
					this.viewController.getPlayTablePanel().getPlayerCreaturePanel().add(this.creatureButton, "cell "
							+ this.creatureButton.getCardColumn()
							+ " "
							+ this.creatureButton.getCardRow());
				} else {
					if(this.viewController.getSelectedButton() != null){
						this.viewController.getSelectedButton().addItem(this.creatureButton.getCardBeanWrapper(), CardPlayTypeEnum.BLOCKER);
						this.viewController.getPlayTablePanel().getPlayerCreaturePanel().remove(this.creatureButton);
					}
				}
				break;
			}
			break;
		}
		this.viewController.getPlayTablePanel().validate();
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		CardBeanWrapper cardBean = this.creatureButton.getCardBeanWrapper();
		this.viewController.getCardsPanel().showCard(cardBean.getIcon());
		this.creatureButton.showWindow();
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		Rectangle buttonBounds = this.creatureButton.getButtonPosition();
		Point mousePosition = e.getLocationOnScreen();

		if(mousePosition.getX() < buttonBounds.getX() + buttonBounds.getWidth()
				&& mousePosition.getX() > buttonBounds.getX()
				&& mousePosition.getY() > buttonBounds.getY()
				&& !this.popupWindow.isPopupWindowAbove()){
			return;
		}

		this.viewController.getCardsPanel().hideCard();
		this.creatureButton.hideWindow();
	}
}