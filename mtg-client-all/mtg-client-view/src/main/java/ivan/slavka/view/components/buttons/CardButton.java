package ivan.slavka.view.components.buttons;

import ivan.slavka.utils.beans.wrappers.CardBeanWrapper;
import ivan.slavka.utils.enums.PlayerEnum;
import ivan.slavka.view.components.panels.BattlefieldPanel;
import ivan.slavka.view.panels.MTGClientPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;
import javax.swing.JPanel;

public class CardButton extends JButton {

	private final MTGClientPanel controller;
	private final BattlefieldPanel battlefield;
	private final CardBeanWrapper cardBean;
	private boolean tapped = false;

	private final Color defaultColor;

	public CardButton(MTGClientPanel controller, CardBeanWrapper cardBean, BattlefieldPanel battlefield){

		this.defaultColor = this.getBackground();

		this.controller = controller;
		this.battlefield = battlefield;
		this.cardBean = cardBean;

		this.setText(this.cardBean.getCardTypeToDisplay().getCardTypeSymbol());
		this.setHorizontalAlignment(CENTER);
		this.setVerticalAlignment(CENTER);
		this.setPreferredSize(new Dimension(30, 60));

		switch(this.cardBean.getCardColor()){
		case WHITE:
			this.setForeground(Color.WHITE);
			break;
		case BLACK:
			this.setForeground(Color.BLACK);
			break;
		case BLUE:
			this.setForeground(Color.BLUE);
			break;
		case GREEN:
			this.setForeground(Color.GREEN);
			break;
		case NEUTRAL:
			this.setForeground(Color.GRAY);
			break;
		case RED:
			this.setForeground(Color.RED);
			break;
		}

		this.addMouseListener(new MouseListener() {

			@Override
			public void mouseReleased(MouseEvent e) {
			}

			@Override
			public void mousePressed(MouseEvent e) {
			}

			@Override
			public void mouseExited(MouseEvent e) {
				CardButton.this.controller.getCardsPanel().hideCard();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				CardButton.this.controller.getCardsPanel().showCard(CardButton.this.cardBean.getIcon());
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if(CardButton.this.cardBean.getCardOwner() == PlayerEnum.OPPONENT){
					return;
				}
				switch(CardButton.this.cardBean.getCardTypeToDisplay()){
				case CREATURE:
					JPanel containingPanel = (JPanel)((CardButton)e.getSource()).getParent();
					containingPanel.remove(CardButton.this);
					CardButton.this.battlefield.attack(CardButton.this.getCardBean());
					CardButton.this.controller.attack(CardButton.this.getCardBean());
					break;
				}
			}
		});
	}

	public CardBeanWrapper getCardBean() {
		return this.cardBean;
	}

	public Color getDefaultColor() {
		return this.defaultColor;
	}
}
