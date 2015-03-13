package ivan.slavka.view.components.panels;

import ivan.slavka.utils.beans.CardBean;
import ivan.slavka.utils.beans.wrappers.CardBeanWrapper;
import ivan.slavka.utils.enums.CardPlayTypeEnum;
import ivan.slavka.view.panels.MTGClientPanel;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

public class PopupWindowPanel extends JPanel {

	private final List<CardBeanWrapper> listOfGlobalEnchantments = new ArrayList<CardBeanWrapper>();
	private final List<CardBeanWrapper> listOfEnchantments = new ArrayList<CardBeanWrapper>();
	private final List<CardBeanWrapper> listOfBlockers = new ArrayList<CardBeanWrapper>();
	private final List<CardBeanWrapper> listOfArtifacts = new ArrayList<CardBeanWrapper>();
	private final List<CardBeanWrapper> listOfGlobalArtifacts = new ArrayList<CardBeanWrapper>();
	private final List<CardBeanWrapper> listOfLands = new ArrayList<CardBeanWrapper>();

	private final PopupWindowCardsPanel globalEnchantmentsPanel;
	private final PopupWindowCardsPanel enchantmentsPanel;
	private final PopupWindowCardsPanel blockersPanel;
	private final PopupWindowCardsPanel artifactsPanel;
	private final PopupWindowCardsPanel globalArtifactsPanel;
	private final PopupWindowCardsPanel landsPanel;

	public PopupWindowPanel(MTGClientPanel viewController) {
		this.globalEnchantmentsPanel = new PopupWindowCardsPanel("Global Enchantments", viewController);
		this.enchantmentsPanel  = new PopupWindowCardsPanel("Enchantments", viewController);
		this.blockersPanel  = new PopupWindowCardsPanel("Blocked By", viewController);
		this.artifactsPanel = new PopupWindowCardsPanel("Artifacts", viewController);
		this.globalArtifactsPanel  = new PopupWindowCardsPanel("Global Artifacts", viewController);
		this.landsPanel  = new PopupWindowCardsPanel("Lands", viewController);

		this.setBorder(new TitledBorder("Popup Window"));
	}

	public void addItem(CardBeanWrapper cardBean, CardPlayTypeEnum playTypeEnum) {
		switch(playTypeEnum){
		case GLOBAL_ENCHANTMENT:
			this.listOfGlobalEnchantments.add(cardBean);
			this.globalEnchantmentsPanel.addItem(cardBean);
			break;
		case ENCHANTMENT:
			this.listOfEnchantments.add(cardBean);
			this.enchantmentsPanel.addItem(cardBean);
			break;
		case GLOBAL_ARTIFACT:
			this.listOfGlobalArtifacts.add(cardBean);
			this.globalArtifactsPanel.addItem(cardBean);
			break;
		case ARTIFACT:
			this.listOfArtifacts.add(cardBean);
			this.artifactsPanel.addItem(cardBean);
			break;
		case LAND:
			this.listOfLands.add(cardBean);
			this.landsPanel.addItem(cardBean);
			break;
		case BLOCKER:
			this.listOfBlockers.add(cardBean);
			this.blockersPanel.addItem(cardBean);
			break;
		}
		this.displayPanels();
	}

	public void removeItem(CardBean cardBean, CardPlayTypeEnum playType) {
	}

	private List<PopupWindowCardsPanel> getPanelsToDisplay(){
		List<PopupWindowCardsPanel> panelsToDisplay = new ArrayList<PopupWindowCardsPanel>();

		if(!this.listOfGlobalEnchantments.isEmpty()) {
			panelsToDisplay.add(this.globalEnchantmentsPanel);
		}
		if(!this.listOfGlobalArtifacts.isEmpty()) {
			panelsToDisplay.add(this.globalArtifactsPanel);
		}
		if(!this.listOfEnchantments.isEmpty()) {
			panelsToDisplay.add(this.enchantmentsPanel);
		}
		if(!this.listOfArtifacts.isEmpty()) {
			panelsToDisplay.add(this.artifactsPanel);
		}
		if(!this.listOfBlockers.isEmpty()) {
			panelsToDisplay.add(this.blockersPanel);
		}
		if(!this.listOfLands.isEmpty()) {
			panelsToDisplay.add(this.landsPanel);
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
