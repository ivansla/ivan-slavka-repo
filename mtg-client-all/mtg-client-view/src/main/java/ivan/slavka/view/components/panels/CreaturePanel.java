package ivan.slavka.view.components.panels;

import ivan.slavka.utils.beans.wrappers.CardBeanWrapper;
import ivan.slavka.utils.enums.PlayerEnum;
import ivan.slavka.view.components.buttons.CreatureButton;
import ivan.slavka.view.panels.MTGClientPanel;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class CreaturePanel extends JPanel {

	private final MTGClientPanel controller;
	private int numberOfPlayerCreatures = 0;
	private int numberOfOpponentCreatures = 0;
	private final int assignCardRowPlayer = 0;
	private final int assignCardRowOpponent = 1;
	private final PlayerEnum playerEnum;
	private final BattlefieldPanel battlefield;

	public CreaturePanel(MTGClientPanel controller, PlayerEnum playerEnum, BattlefieldPanel battlefield){

		this.playerEnum = playerEnum;
		this.controller = controller;
		this.battlefield = battlefield;

		this.setLayout(new MigLayout("", "[30][30][30][30][30]", "[80][80]"));
	}

	public void addCardToGame(CardBeanWrapper cardBean, PlayerEnum playerEnum){

		CreatureButton creatureButton;

		switch(this.playerEnum){
		case PLAYER:
			this.numberOfPlayerCreatures++;
			creatureButton = new CreatureButton(this.controller, cardBean, PlayerEnum.PLAYER);
			creatureButton.setCardRow(this.assignCardRowPlayer);
			creatureButton.setCardColumn(this.numberOfPlayerCreatures);
			this.add(creatureButton, "cell " + this.numberOfPlayerCreatures + " " + this.assignCardRowPlayer);
			break;
		case OPPONENT:
			this.numberOfOpponentCreatures++;
			creatureButton = new CreatureButton(this.controller, cardBean, PlayerEnum.OPPONENT);
			creatureButton.setCardRow(this.assignCardRowOpponent);
			creatureButton.setCardColumn(this.numberOfOpponentCreatures);
			this.add(creatureButton, "cell " + this.numberOfOpponentCreatures + " " + this.assignCardRowOpponent);
			break;
		}
		this.validate();
	}

	public PlayerEnum getPlayerEnum() {
		return this.playerEnum;
	}
}
