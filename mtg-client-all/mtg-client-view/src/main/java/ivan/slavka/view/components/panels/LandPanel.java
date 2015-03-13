package ivan.slavka.view.components.panels;

import ivan.slavka.utils.beans.CardBean;
import ivan.slavka.utils.beans.wrappers.CardBeanWrapper;
import ivan.slavka.utils.enums.CardPlayTypeEnum;
import ivan.slavka.utils.enums.PlayerEnum;
import ivan.slavka.view.components.buttons.CardButton;
import ivan.slavka.view.components.buttons.MiniCardPanelCardButton;
import ivan.slavka.view.components.buttons.PopupWindowButton;
import ivan.slavka.view.panels.MTGClientPanel;

import java.awt.Color;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class LandPanel extends JPanel{

	private final MTGClientPanel controller;
	private int numberOfPlayerLands = 0;
	private int numberOfOpponentLands = 0;
	private final PlayerEnum playerEnum;

	private final PopupWindowButton artifactsButton;
	private final PopupWindowButton enchantmentsButton;
	private final PopupWindowButton greenLandsButton;
	private final PopupWindowButton redLandsButton;
	private final PopupWindowButton blackLandsButton;
	private final PopupWindowButton whiteLandsButton;
	private final PopupWindowButton blueLandsButton;

	public LandPanel(MTGClientPanel controller, PlayerEnum playerEnum){

		this.playerEnum = playerEnum;
		this.controller = controller;

		this.greenLandsButton = new PopupWindowButton("L", controller);
		this.redLandsButton = new PopupWindowButton("L", controller);
		this.blackLandsButton = new PopupWindowButton("L", controller);
		this.whiteLandsButton = new PopupWindowButton("L", controller);
		this.blueLandsButton = new PopupWindowButton("L", controller);
		this.artifactsButton = new PopupWindowButton("A", controller);
		this.enchantmentsButton = new PopupWindowButton("E", controller);

		this.setLayout(new MigLayout("", "[30][30][100][30][30][30][30][30]", "[60]"));
		this.initComponents();
	}

	private void initComponents(){

		this.add(this.enchantmentsButton, "cell 0 0");
		this.add(this.artifactsButton, "cell 1 0");

		this.greenLandsButton.setForeground(Color.GREEN);
		this.add(this.greenLandsButton, "cell 3 0");

		this.redLandsButton.setForeground(Color.RED);
		this.add(this.redLandsButton, "cell 4 0");

		this.blackLandsButton.setForeground(Color.BLACK);
		this.add(this.blackLandsButton, "cell 5 0");

		this.whiteLandsButton.setForeground(Color.WHITE);
		this.add(this.whiteLandsButton, "cell 6 0");

		this.blueLandsButton.setForeground(Color.BLUE);
		this.add(this.blueLandsButton, "cell 7 0");
	}

	public void discard(CardBean cardBean) {
		// TODO Auto-generated method stub

	}

	public void tapCard(CardButton cardButton) {

		CardButton card = cardButton;
		card.setBackground(Color.GRAY);
	}

	public void unTapCard(CardButton cardButton) {
		CardButton card = cardButton;
		card.setBackground(card.getDefaultColor());
	}

	public void playCard(CardBean cardBean, PlayerEnum playerEnum){
	}

	public void addCardToGame(CardBeanWrapper cardBean, PlayerEnum playerEnum){

		switch(this.playerEnum){
		case PLAYER:
			this.numberOfPlayerLands++;
			this.greenLandsButton.addItem(cardBean, CardPlayTypeEnum.LAND);
			//this.add(new CardButton(this.controller.getCardsPanel(), cardBean), "cell " + this.numberOfPlayerLands + " 1");
			break;
		case OPPONENT:
			this.numberOfOpponentLands++;
			this.add(new MiniCardPanelCardButton(this.controller, cardBean), "cell " + this.numberOfOpponentLands + " 0");
			break;
		}
		this.validate();
	}
}
