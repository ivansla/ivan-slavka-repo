package ivan.slavka.view.components.window;

import ivan.slavka.utils.beans.CardBean;
import ivan.slavka.utils.beans.wrappers.CardBeanWrapper;
import ivan.slavka.utils.enums.CardPlayTypeEnum;
import ivan.slavka.view.components.buttons.PopupWindowButton;
import ivan.slavka.view.components.listeners.PopupWindowMouseListener;
import ivan.slavka.view.components.panels.PopupWindowPanel;
import ivan.slavka.view.panels.MTGClientPanel;
import ivan.slavka.view.panels.PlayTablePanel;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;
import javax.swing.JRootPane;
import javax.swing.JWindow;

public class PopupWindow extends JWindow{

	private final PopupWindowButton popupWindowButton;
	private boolean alreadyEntered = false;
	private final PopupWindowPanel popupWindowPanel;

	public PopupWindow(PopupWindowButton popupWindowButton, MTGClientPanel viewController) {

		this.popupWindowButton = popupWindowButton;
		this.popupWindowPanel =  new PopupWindowPanel(viewController);
		this.setSize(280, 100);

		this.getContentPane().add(this.popupWindowPanel);

		this.addMouseListener(new PopupWindowMouseListener(this));
	}

	public void addItem(CardBeanWrapper cardBean, CardPlayTypeEnum playType) {
		this.popupWindowPanel.addItem(cardBean, playType);
	}

	public void removeItem(CardBean cardBean, CardPlayTypeEnum playType) {
		this.popupWindowPanel.removeItem(cardBean, playType);
	}

	public void showWindow() {
		this.positionWindow();
		if(this.popupWindowPanel.hasPanelsToDisplay()){
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

		Rectangle containingPanel = this.getContainingPanel(this.popupWindowButton);
		Rectangle buttonBoundsInContainingPanel = this.getPopupWindowButtonBoundsInContainingPanel(this.popupWindowButton, containingPanel);

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
			} else {
				windowPositionY = buttonBoundsInContainingPanel.getY() + buttonBoundsInContainingPanel.getHeight();
			}

			// If the button is too near the right border.
		} else if(rightBorderConstraint > rightBorderPosition){
			windowPositionX = rightBorderPosition - windowDimension.getWidth();

			//If button is too low.
			if(bottomBorderConstraint > containingPanel.getHeight()){
				windowPositionY = buttonBoundsInContainingPanel.getY() - windowDimension.getHeight();
			} else {
				windowPositionY = buttonBoundsInContainingPanel.getY() + buttonBoundsInContainingPanel.getHeight();
			}
		} else {

			//If button is too low.
			if(bottomBorderConstraint > containingPanel.getHeight()){
				windowPositionY = buttonBoundsInContainingPanel.getY() - windowDimension.getHeight();
			} else {
				windowPositionY = buttonBoundsInContainingPanel.getY() + buttonBoundsInContainingPanel.getHeight();
			}
		}

		// Window rectangle with size and location where should it be shown.		Rectangle windowRectangle = new Rectangle();
		windowRectangle.setLocation((int)windowPositionX, (int)windowPositionY);
		windowRectangle.setSize(windowDimension);

		this.setBounds(windowRectangle);
	}

	public boolean isAlreadyEntered() {
		return this.alreadyEntered;
	}

	public void setAlreadyEntered(boolean alreadyEntered) {
		this.alreadyEntered = alreadyEntered;
	}

	/**
	 * Returns Rectangle which holds size and position of the Frame on Desktop.
	 * 
	 * @param c - Component whose Frame we are looking for.
	 * @return
	 */
	@Deprecated
	private Rectangle getFrameBounds(Component c){

		Component component = this.getComponentContainer(c);
		while(!(component instanceof JFrame)){
			component = this.getComponentContainer(component);
		}

		return component.getBounds();
	}

	/**
	 * Returns a location (position), where the rootPane is. This location can vary because the Frame that
	 * hold this rootPane can have different size of Title Bar and Borders. The return value is used to
	 * perform corrections when trying to show component based on desktop position.
	 * 
	 * @param c - Component whose RootPane we are looking for.
	 * @return
	 */
	@Deprecated
	private Point getRootPaneLocation(Component c){

		Component component = this.getComponentContainer(c);
		while(!(component instanceof JRootPane)){
			component = this.getComponentContainer(component);
		}

		return component.getLocation();
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

	@Deprecated
	private Point getPositionThroughPanels(Component c){
		Point positionThroughPanels = new Point();
		double x = c.getBounds().getX();
		double y = c.getBounds().getY();

		Component component = this.getComponentContainer(c);
		while(!(component instanceof JLayeredPane)){
			x += component.getBounds().getX();
			y += component.getBounds().getY();
			component = this.getComponentContainer(component);
		}
		positionThroughPanels.setLocation(x, y);
		return positionThroughPanels;
	}

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

		return new Rectangle(p, this.popupWindowButton.getSize());
	}
}
