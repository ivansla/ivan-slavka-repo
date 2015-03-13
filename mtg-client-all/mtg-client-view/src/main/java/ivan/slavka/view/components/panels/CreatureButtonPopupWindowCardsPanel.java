package ivan.slavka.view.components.panels;

import ivan.slavka.utils.beans.wrappers.CardBeanWrapper;
import ivan.slavka.view.components.buttons.CardButton;
import ivan.slavka.view.components.buttons.MiniCardPanelCardButton;
import ivan.slavka.view.panels.MTGClientPanel;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

public class CreatureButtonPopupWindowCardsPanel extends JPanel{

	private final MTGClientPanel viewController;

	private final String rowConstraint = "[60]";

	private int numberOfCardsInRow = 0;
	private int numberOfCards = 0;
	private int numberOfrows = 0;

	public CreatureButtonPopupWindowCardsPanel(String borderName, MTGClientPanel viewController) {
		this.viewController = viewController;

		this.setLayout(new MigLayout("", "[30][30][30][30][30]", "[60]"));

		this.setBorder(new TitledBorder(borderName));
	}

	public void addItem(CardBeanWrapper cardBean){
		this.numberOfCards++;

		if(this.numberOfCards % 5 == 1){
			this.setNewLayout(((this.numberOfCards - 1) / 5) + 1);
			this.numberOfrows++;
		}

		this.add(new MiniCardPanelCardButton(this.viewController, cardBean), "cell " + this.numberOfCardsInRow + " " + this.numberOfrows);
		this.numberOfCardsInRow++;
	}

	public void removeItem(CardButton cardButton){
		this.numberOfCards--;

		if(this.numberOfCards % 5 == 0){
			this.setNewLayout(this.numberOfCards / 5);
			this.numberOfrows--;
		}

		this.remove(cardButton);
		this.numberOfCardsInRow--;
	}

	private void setNewLayout(int numberOfRows){

		String layoutRowConstraints = "";
		for(int i = 0; i < numberOfRows; i++){
			layoutRowConstraints += this.rowConstraint;
		}

		this.setLayout(new MigLayout("", "[30][30][30][30][30]", layoutRowConstraints));
	}
}
