package ivan.slavka.panel;

import ivan.slavka.utils.beans.CardBean;
import ivan.slavka.utils.beans.wrappers.CardBeanWrapper;
import ivan.slavka.utils.enums.CardPlayTypeEnum;
import ivan.slavka.view.components.buttons.PopupWindowButton;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class PopupWindowButtonTestPanel extends JPanel {

	/**
	 * Create the panel.
	 */
	public PopupWindowButtonTestPanel() {
		this.setLayout(new MigLayout("", "[][grow][]", "[][grow][]"));

		PopupWindowButton popupWindowButton = new PopupWindowButton("Popup", null);
		this.add(popupWindowButton, "cell 0 0,grow");

		popupWindowButton.addItem(new CardBeanWrapper(new CardBean()), CardPlayTypeEnum.GLOBAL_ARTIFACT);

		popupWindowButton.addItem(new CardBeanWrapper(new CardBean()), CardPlayTypeEnum.GLOBAL_ARTIFACT);

		popupWindowButton.addItem(new CardBeanWrapper(new CardBean()), CardPlayTypeEnum.GLOBAL_ENCHANTMENT);

		popupWindowButton.addItem(new CardBeanWrapper(new CardBean()), CardPlayTypeEnum.ENCHANTMENT);

		popupWindowButton.addItem(new CardBeanWrapper(new CardBean()), CardPlayTypeEnum.LAND);
	}
}
