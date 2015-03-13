package ivan.slavka.view.components.window;

import ivan.slavka.view.components.buttons.MiniCardPanelCardButton;
import ivan.slavka.view.components.panels.SpendManaPopupWindowPanel;
import ivan.slavka.view.panels.MTGClientPanel;
import ivan.slavka.view.panels.PlayTablePanel;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JWindow;

public class SpendManaPopupWindow extends JWindow{

	private final SpendManaPopupWindowPanel popupWindowPanel;
	private final MTGClientPanel viewController;
	private final MiniCardPanelCardButton miniCardPanelCardButton;

	public SpendManaPopupWindow(MTGClientPanel viewController, MiniCardPanelCardButton miniCardPanelCardButton){
		this.popupWindowPanel = new SpendManaPopupWindowPanel(this, miniCardPanelCardButton, viewController);
		this.viewController = viewController;
		this.miniCardPanelCardButton = miniCardPanelCardButton;

		this.setSize(300, 300);
		this.setContentPane(this.popupWindowPanel);
	}

	public void showWindow() {
		this.positionWindow();
		this.setVisible(true);
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

		Rectangle containingPanel = this.getContainingPanel(this.viewController.getPlayTablePanel());
		Rectangle buttonBoundsInContainingPanel = this.getPopupWindowBoundsInContainingPanel(this.viewController.getPlayTablePanel(), containingPanel);

		// Location where the window should appear.
		double windowPositionX = buttonBoundsInContainingPanel.getX() + ((buttonBoundsInContainingPanel.getWidth() / 2) - (this.getWidth() / 2));
		double windowPositionY = buttonBoundsInContainingPanel.getY() + ((buttonBoundsInContainingPanel.getHeight() / 2) - (this.getHeight() / 2));

		// Window rectangle with size and location where should it be shown.
		Rectangle windowRectangle = new Rectangle();
		windowRectangle.setLocation((int)windowPositionX, (int)windowPositionY);
		windowRectangle.setSize(windowDimension);

		this.setBounds(windowRectangle);
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

	private Rectangle getContainingPanel(Component component){

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

	private Rectangle getPopupWindowBoundsInContainingPanel(Component c, Rectangle containingPanelBounds){

		int x = 0;
		int y = 0;

		while(!(c instanceof PlayTablePanel)){
			x += c.getX();
			y += c.getY();
			c = this.getComponentContainer(c);
		}

		Point p = new Point((int)containingPanelBounds.getX() + x, (int)containingPanelBounds.getY() + y);

		return new Rectangle(p, this.viewController.getPlayTablePanel().getSize());
	}
}
