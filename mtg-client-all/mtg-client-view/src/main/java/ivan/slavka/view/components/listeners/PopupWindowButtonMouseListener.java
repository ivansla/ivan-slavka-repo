package ivan.slavka.view.components.listeners;

import ivan.slavka.view.components.buttons.PopupWindowButton;
import ivan.slavka.view.components.window.PopupWindow;

import java.awt.event.MouseListener;

public class PopupWindowButtonMouseListener implements MouseListener{

	private final PopupWindowButton popupWindowButton;
	private final PopupWindow popupWindow;

	public PopupWindowButtonMouseListener(PopupWindowButton popupWindowButton, PopupWindow popupWindow){
		this.popupWindowButton = popupWindowButton;
		this.popupWindow = popupWindow;
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		this.popupWindow.setAlreadyEntered(true);
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		this.popupWindowButton.showWindow();
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		this.popupWindowButton.hideWindow();
	}

}
