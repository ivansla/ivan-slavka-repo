package ivan.slavka.view.components.panels;

import ivan.slavka.utils.beans.wrappers.CardBeanWrapper;
import ivan.slavka.utils.enums.CardPlayTypeEnum;
import ivan.slavka.view.panels.MTGClientPanel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

public class CreatureButtonPopupWindowPanel extends JPanel {

	private final List<CardBeanWrapper> listOfEnchantments = new ArrayList<CardBeanWrapper>();
	private final List<CardBeanWrapper> listOfArtifacts = new ArrayList<CardBeanWrapper>();
	private final List<CardBeanWrapper> listOfBlockers = new ArrayList<CardBeanWrapper>();

	private final PopupWindowCardsPanel enchantmentsPanel;
	private final PopupWindowCardsPanel artifactsPanel;
	private final PopupWindowCardsPanel blockersPanel;

	public CreatureButtonPopupWindowPanel(MTGClientPanel viewController) {
		this.enchantmentsPanel  = new PopupWindowCardsPanel("Enchantments", viewController);
		this.artifactsPanel = new PopupWindowCardsPanel("Artifacts", viewController);
		this.blockersPanel = new PopupWindowCardsPanel("Blockers", viewController);

		this.setBorder(new TitledBorder("Popup Window"));
	}

	public void addItem(CardBeanWrapper cardBean, CardPlayTypeEnum playTypeEnum) {
		switch(playTypeEnum){
		case ENCHANTMENT:
			this.listOfEnchantments.add(cardBean);
			this.enchantmentsPanel.addItem(cardBean);
			break;
		case ARTIFACT:
			this.listOfArtifacts.add(cardBean);
			this.artifactsPanel.addItem(cardBean);
			break;
		case BLOCKER:
			this.listOfBlockers.add(cardBean);
			this.blockersPanel.addItem(cardBean);
		}
		this.displayPanels();
	}

	public void removeItem(CardBeanWrapper cardBean, CardPlayTypeEnum playType) {
	}

	private List<PopupWindowCardsPanel> getPanelsToDisplay(){
		List<PopupWindowCardsPanel> panelsToDisplay = new ArrayList<PopupWindowCardsPanel>();

		if(!this.listOfEnchantments.isEmpty()) {
			panelsToDisplay.add(this.enchantmentsPanel);
		}
		if(!this.listOfArtifacts.isEmpty()) {
			panelsToDisplay.add(this.artifactsPanel);
		}
		if(!this.listOfBlockers.isEmpty()) {
			panelsToDisplay.add(this.blockersPanel);
		}
		return panelsToDisplay;
	}

	private void displayPanels(){

		List<PopupWindowCardsPanel> listOfPanelsToDisplay = this.getPanelsToDisplay();

		String rowConstraint = "";
		for(int i = 0; i < listOfPanelsToDisplay.size(); i++){
			rowConstraint += "[grow]";
		}

		this.setLayout(new MigLayout("", "[]", rowConstraint));

		int i = 0;
		for(PopupWindowCardsPanel panel : listOfPanelsToDisplay){

			this.add(panel, "cell 0 " + i);
			i++;
		}
	}

	public boolean hasPanelsToDisplay(){
		if(this.getPanelsToDisplay().isEmpty()){
			return false;
		}
		return true;
	}
}
