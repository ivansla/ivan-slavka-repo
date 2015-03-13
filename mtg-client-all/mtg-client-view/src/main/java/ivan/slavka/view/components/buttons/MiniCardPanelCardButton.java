package ivan.slavka.view.components.buttons;

import ivan.slavka.utils.beans.wrappers.CardBeanWrapper;
import ivan.slavka.utils.enums.PlayerEnum;
import ivan.slavka.view.components.window.SpendManaPopupWindow;
import ivan.slavka.view.panels.MTGClientPanel;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JButton;

public class MiniCardPanelCardButton extends JButton{

	private final CardBeanWrapper cardBean;
	private final MTGClientPanel controller;
	private final Color defaultColor;

	public MiniCardPanelCardButton(MTGClientPanel controller, CardBeanWrapper cardBean){

		this.defaultColor = this.getBackground();

		this.controller = controller;
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
				MiniCardPanelCardButton.this.controller.getCardsPanel().hideCard();
			}

			@Override
			public void mouseEntered(MouseEvent e) {
				MiniCardPanelCardButton.this.controller.getCardsPanel().showCard(MiniCardPanelCardButton.this.cardBean.getIcon());
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				if(MiniCardPanelCardButton.this.controller.getTurnStatus().getTurnOwner() != PlayerEnum.PLAYER){
					return;
				}

				if(MiniCardPanelCardButton.this.cardBean.isCustomManaSpend()){
					SpendManaPopupWindow spendManaPopupWindow = new SpendManaPopupWindow(MiniCardPanelCardButton.this.controller, MiniCardPanelCardButton.this);
					spendManaPopupWindow.showWindow();
				} else {
					MiniCardPanelCardButton.this.playCard();
				}
			}
		});
	}

	public void playCard(){
		MiniCardPanelCardButton.this.controller.getCardsPanel().playCard(MiniCardPanelCardButton.this.cardBean);
		MiniCardPanelCardButton.this.controller.getCastingPopupWindow().generalCasting(MiniCardPanelCardButton.this.cardBean);
	}

	public void playCard(int mana){
		this.cardBean.getManaCostBean().setGeneralManaCost(mana);
		this.playCard();
	}

	public CardBeanWrapper getCardBean() {
		return this.cardBean;
	}

	public Color getDefaultColor() {
		return this.defaultColor;
	}
}
