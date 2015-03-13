package ivan.slavka.view.components.listeners;

import ivan.slavka.view.components.buttons.CreatureButton;
import ivan.slavka.view.components.window.CreatureButtonPopupWindow;
import ivan.slavka.view.panels.MTGClientPanel;

import java.awt.Point;
import java.awt.event.MouseListener;

public class CreatureButtonPopupWindowMouseListener implements MouseListener{

	private final CreatureButtonPopupWindow creatureButtonPopupWindow;
	private final CreatureButton creatureButton;
	private final MTGClientPanel viewControler;

	public CreatureButtonPopupWindowMouseListener(CreatureButton creatureButton, CreatureButtonPopupWindow creatureButtonPopupWindow, MTGClientPanel viewControler){
		this.creatureButtonPopupWindow = creatureButtonPopupWindow;
		this.creatureButton = creatureButton;
		this.viewControler = viewControler;
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
			this.creatureButtonPopupWindow.setAlreadyEntered(false);
			this.creatureButtonPopupWindow.hideWindow();
		}
	}

	private boolean isInsidePopupWindow(Point p){

		if((p.getX() > this.creatureButtonPopupWindow.getX() && p.getX() < this.creatureButtonPopupWindow.getX() + this.creatureButtonPopupWindow.getWidth()) &&
				(p.getY() > this.creatureButtonPopupWindow.getY() && p.getY() < this.creatureButtonPopupWindow.getY() + this.creatureButtonPopupWindow.getHeight())){
			return true;
		}
		return false;
	}
}
