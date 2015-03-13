package ivan.slavka.view.panels;

import ivan.slavka.utils.beans.wrappers.CardBeanWrapper;
import ivan.slavka.utils.enums.CardTypeEnum;
import ivan.slavka.utils.enums.PlayerEnum;
import ivan.slavka.view.components.panels.BattlefieldPanel;
import ivan.slavka.view.components.panels.ChatPanel;
import ivan.slavka.view.components.panels.CreaturePanel;
import ivan.slavka.view.components.panels.LandPanel;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

public class PlayTablePanel extends JPanel{

	private final CreaturePanel opponentCreaturePanel;
	private final CreaturePanel playerCreaturePanel;
	private final LandPanel opponentLandPanel;
	private final LandPanel playerLandPanel;
	private final BattlefieldPanel battlefieldPanel;
	private final ChatPanel chatPanel;

	private CardBeanWrapper selectedCard;

	public PlayTablePanel(MTGClientPanel controller) {
		this.setLayout(new MigLayout("", "[grow][grow]", "[15%][30%][30%][15%][10%]"));

		this.opponentCreaturePanel = new CreaturePanel(controller, PlayerEnum.OPPONENT, this.battlefieldPanel);
		this.opponentCreaturePanel.setBorder(new TitledBorder("Opponent Creature Panel"));
		this.add(this.opponentCreaturePanel, "cell 0 0 1 2,grow");

		this.opponentLandPanel = new LandPanel(controller, PlayerEnum.OPPONENT);
		this.opponentLandPanel.setBorder(new TitledBorder("Opponent Land Panel"));
		this.add(this.opponentLandPanel, "cell 1 0,grow");

		this.battlefieldPanel = new BattlefieldPanel(controller);
		this.add(this.battlefieldPanel, "cell 1 1 1 2,grow");

		this.playerCreaturePanel = new CreaturePanel(controller, PlayerEnum.PLAYER, this.battlefieldPanel);
		this.playerCreaturePanel.setBorder(new TitledBorder("Player Creature Panel"));
		this.add(this.playerCreaturePanel, "cell 0 2 1 2,grow");

		this.playerLandPanel = new LandPanel(controller, PlayerEnum.PLAYER);
		this.playerLandPanel.setBorder(new TitledBorder("Player Land Panel"));
		this.add(this.playerLandPanel, "cell 1 3,grow");

		this.chatPanel = new ChatPanel();
		this.add(this.chatPanel, "cell 0 4 2 1,grow");
	}

	public void playCard(CardBeanWrapper cardBean, PlayerEnum player) {

		cardBean.setCardOwner(player);
		switch(player){
		case PLAYER:
			if(cardBean.getCardTypeToDisplay().equals(CardTypeEnum.CREATURE)){
				this.playerCreaturePanel.addCardToGame(cardBean, player);
			}else if(cardBean.getCardTypeToDisplay().equals(CardTypeEnum.ENCHANTMENT)){
				this.selectedCard = cardBean;
			} else {
				this.playerLandPanel.addCardToGame(cardBean, player);
			}
			break;
		case OPPONENT:
			if(cardBean.getCardTypeToDisplay().equals(CardTypeEnum.CREATURE)){
				this.opponentCreaturePanel.addCardToGame(cardBean, player);
			} else {
				this.opponentLandPanel.addCardToGame(cardBean, player);
			}
			break;
		}
	}

	public CardBeanWrapper selectedCard() {
		return this.selectedCard;
	}

	public void deselectCard(){
		this.selectedCard = null;
	}

	public CreaturePanel getOpponentCreaturePanel() {
		return this.opponentCreaturePanel;
	}

	public CreaturePanel getPlayerCreaturePanel() {
		return this.playerCreaturePanel;
	}

	public LandPanel getOpponentLandPanel() {
		return this.opponentLandPanel;
	}

	public LandPanel getPlayerLandPanel() {
		return this.playerLandPanel;
	}

	public BattlefieldPanel getBattlefieldPanel() {
		return this.battlefieldPanel;
	}

	public ChatPanel getChatPanel() {
		return this.chatPanel;
	}
}
