package ivan.slavka.view.components.panels;

import ivan.slavka.utils.beans.CardBean;
import ivan.slavka.utils.beans.wrappers.CardBeanWrapper;
import ivan.slavka.view.components.buttons.CardButton;
import ivan.slavka.view.panels.MTGClientPanel;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

public class BattlefieldPanel extends JPanel{

	private final MTGClientPanel controller;

	public BattlefieldPanel(MTGClientPanel controller) {

		this.controller = controller;

		this.setLayout(new MigLayout("", "[30,grow][30][30][30][30]", "[80][80,grow]"));
		this.setBorder(new TitledBorder("Battlefield"));
	}

	public void discard(CardBean cardBean) {
		// TODO Auto-generated method stub

	}

	public void tapCard(CardButton cardButton) {
		// TODO Auto-generated method stub

	}

	public void block(CardBean cardBean) {
		// TODO Auto-generated method stub

	}

	public void attack(CardBeanWrapper cardBean) {
		// TODO Auto-generated method stub

	}
}
