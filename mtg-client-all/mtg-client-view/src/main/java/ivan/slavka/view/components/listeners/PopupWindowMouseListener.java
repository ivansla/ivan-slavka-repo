package ivan.slavka.view.components.listeners;

import ivan.slavka.view.components.window.PopupWindow;

import java.awt.Point;
import java.awt.event.MouseListener;

public class PopupWindowMouseListener implements MouseListener{

	private final PopupWindow popupWindowController;

	public PopupWindowMouseListener(PopupWindow popupWindowController){
		this.popupWindowController = popupWindowController;
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		if(!this.isInsidePopupWindow(e.getLocationOnScreen())){
			this.popupWindowController.setAlreadyEntered(false);
			this.popupWindowController.hideWindow();
		}
	}

	private boolean isInsidePopupWindow(Point p){

		if((p.getX() > this.popupWindowController.getX() && p.getX() < this.popupWindowController.getX() + this.popupWindowController.getWidth()) &&
				(p.getY() > this.popupWindowController.getY() && p.getY() < this.popupWindowController.getY() + this.popupWindowController.getHeight())){
			return true;
		}
		return false;
	}
}
