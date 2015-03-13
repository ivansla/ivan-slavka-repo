package ivan.slavka.view.components.panels;

import ivan.slavka.utils.beans.wrappers.CardBeanWrapper;
import ivan.slavka.view.components.buttons.MiniCardPanelCardButton;

import java.awt.Component;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class MiniCardPanel extends JPanel {

	public MiniCardPanel(){

		this.setLayout(new MigLayout("", "[]", "[]"));
	}

	public void addCard(MiniCardPanelCardButton cardButton) {
		this.add(cardButton);
		this.resortCards();
		this.validate();
	}

	public void removeCard(CardBeanWrapper cardBean) {
		this.remove(this.findCard(cardBean));
		this.resortCards();
		this.validate();
	}

	private MiniCardPanelCardButton findCard(CardBeanWrapper cardBean){

		MiniCardPanelCardButton cardButton = null;

		for(Component component : this.getComponents()){
			if(component instanceof MiniCardPanelCardButton){
				cardButton = (MiniCardPanelCardButton) component;
				if(cardButton.getCardBean().equals(cardBean)){
					return cardButton;
				}
			}
		}
		return cardButton;
	}

	private void resortCards(){
		List<MiniCardPanelCardButton> cards = new ArrayList<MiniCardPanelCardButton>();
		for(Component comp : this.getComponents()){
			if(comp instanceof MiniCardPanelCardButton){
				cards.add((MiniCardPanelCardButton)comp);
				this.remove(comp);
			}
		}

		int i = 0;
		for(MiniCardPanelCardButton card : cards){
			i++;
			this.add(card, "cell " + i + " 0");
		}
	}
}
