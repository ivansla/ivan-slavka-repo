package ivan.slavka.view.panels;

import ivan.slavka.utils.beans.wrappers.CardBeanWrapper;
import ivan.slavka.utils.enums.PlayerEnum;
import ivan.slavka.view.components.panels.CardsPanel;
import ivan.slavka.view.components.panels.StatusPanel;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class StatusAndCardDisplayPanel extends JPanel {

	private final StatusPanel statusPanel;
	private final CardsPanel cardsPanel;

	private MTGClientPanel controller;

	public StatusAndCardDisplayPanel(MTGClientPanel controller) {
		this.controller = controller;

		this.setLayout(new MigLayout("", "[grow]", "[grow][520]"));

		this.statusPanel = new StatusPanel(this.controller);
		this.add(this.statusPanel, "cell 0 0,grow");

		this.cardsPanel = new CardsPanel(controller);
		this.add(this.cardsPanel, "cell 0 1,grow");
	}

	public void addCardToMiniCardPanel(CardBeanWrapper cardBean){
		this.cardsPanel.addCard(cardBean);
	}

	public CardsPanel getCardsPanel() {
		return this.cardsPanel;
	}

	public StatusPanel getStatusPanel() {
		return this.statusPanel;
	}

	public void setTurnOwner(PlayerEnum turnOwner){
		this.statusPanel.setTurnLabel(turnOwner.toString());
	}
}
