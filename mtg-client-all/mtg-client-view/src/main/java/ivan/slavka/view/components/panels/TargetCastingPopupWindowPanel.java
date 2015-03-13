package ivan.slavka.view.components.panels;

import ivan.slavka.utils.beans.wrappers.CardBeanWrapper;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class TargetCastingPopupWindowPanel extends JPanel{

	private final JLabel castingCard = new JLabel();
	private final JLabel targetCard = new JLabel();

	public TargetCastingPopupWindowPanel(){
		this.setLayout(new MigLayout("", "[][]", "[]"));

		this.add(this.castingCard, "cell 0 0");
		this.add(this.targetCard, "cell 1 0");
	}

	public void castCard(CardBeanWrapper castingCard, CardBeanWrapper targetCard){
		this.castingCard.setIcon(castingCard.getIcon());
		this.targetCard.setIcon(targetCard.getIcon());
		this.validate();
	}
}
