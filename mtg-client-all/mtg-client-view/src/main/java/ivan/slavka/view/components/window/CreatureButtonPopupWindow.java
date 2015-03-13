package ivan.slavka.view.components.window;

import ivan.slavka.utils.beans.wrappers.CardBeanWrapper;
import ivan.slavka.utils.enums.CardPlayTypeEnum;
import ivan.slavka.view.components.buttons.CreatureButton;
import ivan.slavka.view.components.listeners.CreatureButtonPopupWindowMouseListener;
import ivan.slavka.view.components.panels.CreatureButtonPopupWindowPanel;
import ivan.slavka.view.panels.MTGClientPanel;
import ivan.slavka.view.panels.PlayTablePanel;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JWindow;

public class CreatureButtonPopupWindow extends JWindow{

	private final CreatureButton creaturePopupWindowButton;
	private final CreatureButtonPopupWindowPanel creaturePopupWindowPanel;
	private boolean alreadyEntered = false;
	private boolean popupWindowAbove = true;

	public CreatureButtonPopupWindow(CreatureButton creaturePopupWindowButton, MTGClientPanel viewController) {

		this.creaturePopupWindowButton = creaturePopupWindowButton;
		this.creaturePopupWindowPanel =  new CreatureButtonPopupWindowPanel(viewController);
		this.setSize(280, 100);

		this.getContentPane().add(this.creaturePopupWindowPanel);
		this.addMouseListener(new CreatureButtonPopupWindowMouseListener(this.creaturePopupWindowButton, this, viewController));
	}

	public void addItem(CardBeanWrapper cardBean, CardPlayTypeEnum playType) {
		this.creaturePopupWindowPanel.addItem(cardBean, playType);
	}

	public void removeItem(CardBeanWrapper cardBean, CardPlayTypeEnum playType) {
		this.creaturePopupWindowPanel.removeItem(cardBean, playType);
	}

	public void showWindow() {
		this.positionWindow();
		if(this.creaturePopupWindowPanel.hasPanelsToDisplay()){
			this.setVisible(true);
		}
	}

	public void hideWindow() {
		this.setVisible(false);
	}

	/**
	 * Positions popup window, based on position of the button, so that window is never out of the frame.
	 */
	private void positionWindow(){

		// Size of the popup window.
		Dimension windowDimension = this.getSize();

		Rectangle containingPanel = this.getContainingPanel(this.creaturePopupWindowButton);
		Rectangle buttonBoundsInContainingPanel = this.getPopupWindowButtonBoundsInContainingPanel(this.creaturePopupWindowButton, containingPanel);

		// Location where the window should appear.
		double windowPositionX = (buttonBoundsInContainingPanel.getX() + (buttonBoundsInContainingPanel.getWidth() / 2)) - (windowDimension.getWidth() / 2);
		double windowPositionY;

		// These constraints are used in order to discover the position of button, and when and where to display popup window.
		double leftBorderConstraint = (windowDimension.getWidth() / 2) - (buttonBoundsInContainingPanel.getWidth() / 2) + containingPanel.getX();
		double rightBorderConstraint = buttonBoundsInContainingPanel.getX() + (windowDimension.getWidth() / 2) + (buttonBoundsInContainingPanel.getWidth() / 2);
		double rightBorderPosition = containingPanel.getX() + containingPanel.getWidth();
		double bottomBorderConstraint = buttonBoundsInContainingPanel.getY() + buttonBoundsInContainingPanel.getHeight() + windowDimension.getHeight();

		// If button is too near the left border.
		if(buttonBoundsInContainingPanel.getX() < leftBorderConstraint) {
			windowPositionX = containingPanel.getX();

			// If button is too low.
			if(bottomBorderConstraint > containingPanel.getHeight()){
				windowPositionY = buttonBoundsInContainingPanel.getY() - windowDimension.getHeight();
				this.popupWindowAbove = true;
			} else {
				windowPositionY = buttonBoundsInContainingPanel.getY() + buttonBoundsInContainingPanel.getHeight();
				this.popupWindowAbove = false;
			}

			// If the button is too near the buttonBoundsInContainingPanel right border.
		} else if(rightBorderConstraint > rightBorderPosition){
			windowPositionX = rightBorderPosition - windowDimension.getWidth();

			//If button is too low.
			if(bottomBorderConstraint > containingPanel.getHeight()){
				windowPositionY = buttonBoundsInContainingPanel.getY() - windowDimension.getHeight();
				this.popupWindowAbove = true;
			} else {
				windowPositionY = buttonBoundsInContainingPanel.getY() + buttonBoundsInContainingPanel.getHeight();
				this.popupWindowAbove = false;
			}
		} else {

			//If button is too low.
			if(bottomBorderConstraint > containingPanel.getHeight()){
				windowPositionY = buttonBoundsInContainingPanel.getY() - windowDimension.getHeight();
				this.popupWindowAbove = true;
			} else {
				windowPositionY = buttonBoundsInContainingPanel.getY() + buttonBoundsInContainingPanel.getHeight();
				this.popupWindowAbove = false;
			}
		}

		// Window rectangle with size and location where should it be shown.		Rectangle windowRectangle = new Rectangle();
		windowRectangle.setLocation((int)windowPositionX, (int)windowPositionY);
		windowRectangle.setSize(windowDimension);

		this.setBounds(windowRectangle);
	}

	public boolean isPopupWindowAbove() {
		return this.popupWindowAbove;
	}

	public boolean isAlreadyEntered() {
		return this.alreadyEntered;
	}

	public void setAlreadyEntered(boolean alreadyEntered) {
		this.alreadyEntered = alreadyEntered;
	}

	/**
	 * Returns a Container where the component is held.
	 * 
	 * @param c - Component whose container we are looking for.
	 * @return
	 */
	private Container getComponentContainer(Component c){
		return c.getParent();
	}

	/**
	 * Gets container bounds in relation to the desktop
	 * 
	 * @param c
	 * @return
	 */
	private Rectangle getContainingPanel(Component c){

		Component component = this.getComponentContainer(c);
		while(!(component instanceof PlayTablePanel)){
			component = this.getComponentContainer(component);
		}

		Rectangle r = new Rectangle(component.getLocation(), component.getSize());

		component = this.getComponentContainer(component);
		Point p;
		while(!(component instanceof JFrame)){
			p = new Point();
			p.setLocation(r.getX() + component.getX(), r.getY() + component.getY());
			r.setLocation(p);
			component = this.getComponentContainer(component);
		}

		p = new Point();
		p.setLocation(r.getX() + component.getX(), r.getY() + component.getY());
		r.setLocation(p);

		return r;
	}

	private Rectangle getPopupWindowButtonBoundsInContainingPanel(Component c, Rectangle containingPanelBounds){

		int x = 0;
		int y = 0;

		while(!(c instanceof PlayTablePanel)){
			x += c.getX();
			y += c.getY();
			c = this.getComponentContainer(c);
		}

		Point p = new Point((int)containingPanelBounds.getX() + x, (int)containingPanelBounds.getY() + y);

		return new Rectangle(p, this.creaturePopupWindowButton.getSize());
	}
}
