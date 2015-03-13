package ivan.slavka.view.components.buttons;

import ivan.slavka.utils.beans.CardBean;
import ivan.slavka.utils.beans.wrappers.CardBeanWrapper;
import ivan.slavka.utils.enums.CardPlayTypeEnum;
import ivan.slavka.view.components.listeners.PopupWindowButtonMouseListener;
import ivan.slavka.view.components.window.PopupWindow;
import ivan.slavka.view.panels.MTGClientPanel;

import java.awt.Dimension;

import javax.swing.JButton;

public class PopupWindowButton extends JButton{

	private final PopupWindow popupWindow;

	public PopupWindowButton(String buttonText, MTGClientPanel viewController){
		this.popupWindow = new PopupWindow(this, viewController);

		this.setText(buttonText);
		this.setPreferredSize(new Dimension(30, 60));

		this.addMouseListener(new PopupWindowButtonMouseListener(this, this.popupWindow));
	}

	public void showWindow() {
		this.popupWindow.showWindow();
	}

	public void hideWindow() {
		if(!this.popupWindow.isAlreadyEntered()){
			this.popupWindow.hideWindow();
		}
	}

	public void addItem(CardBeanWrapper cardBean, CardPlayTypeEnum playType){
		this.popupWindow.addItem(cardBean, playType);
	}

	public void removeItem(CardBean cardBean, CardPlayTypeEnum playType){
		this.popupWindow.removeItem(cardBean, playType);
	}
}
