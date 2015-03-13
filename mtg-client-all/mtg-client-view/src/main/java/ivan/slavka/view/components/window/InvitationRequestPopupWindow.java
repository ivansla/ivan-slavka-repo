package ivan.slavka.view.components.window;

import ivan.slavka.utils.interfaces.IConfirmationSelection;
import ivan.slavka.view.components.panels.window.InvitationRequestPanel;
import ivan.slavka.view.panels.ClientConnectionPanel;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JWindow;

public class InvitationRequestPopupWindow extends JWindow implements IConfirmationSelection{

	private static InvitationRequestPopupWindow popupWindow = null;
	private final ClientConnectionPanel clientConnectionPanel;
	private final InvitationRequestPanel requestPanel;

	private InvitationRequestPopupWindow(ClientConnectionPanel clientConnectionPanel) {
		this.clientConnectionPanel = clientConnectionPanel;
		this.requestPanel = new InvitationRequestPanel(this);

		this.setSize(312, 445);
		this.setContentPane(this.requestPanel);
	}

	public static InvitationRequestPopupWindow getInstance(ClientConnectionPanel clientConnectionPanel){
		if(popupWindow == null){
			popupWindow = new InvitationRequestPopupWindow(clientConnectionPanel);
		}
		return popupWindow;
	}

	public void showWindow(String playerName) {
		this.requestPanel.setPlayerName(playerName);
		this.positionWindow();
		this.setVisible(true);
		this.setAlwaysOnTop(true);
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

		Rectangle containingPanel = this.getContainingPanel(this.clientConnectionPanel);
		Rectangle buttonBoundsInContainingPanel = this.getPopupWindowBoundsInContainingPanel(this.clientConnectionPanel, containingPanel);

		// Location where the window should appear.
		double windowPositionX = buttonBoundsInContainingPanel.getX() + ((buttonBoundsInContainingPanel.getWidth() / 2) - (this.getWidth() / 2));
		double windowPositionY = buttonBoundsInContainingPanel.getY() + ((buttonBoundsInContainingPanel.getHeight() / 2) - (this.getHeight() / 2));

		// Window rectangle with size and location where should it be shown.		Rectangle windowRectangle = new Rectangle();
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

		// While it's not main panel.
		while(!(component instanceof ClientConnectionPanel)){
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

		while(!(c instanceof ClientConnectionPanel)){
			x += c.getX();
			y += c.getY();
			c = this.getComponentContainer(c);
		}

		Point p = new Point((int)containingPanelBounds.getX() + x, (int)containingPanelBounds.getY() + y);

		return new Rectangle(p, this.clientConnectionPanel.getSize());
	}

	@Override
	public void doOk() {
		this.clientConnectionPanel.doOk();
		this.hideWindow();
	}

	@Override
	public void doCancel() {
		this.clientConnectionPanel.doCancel();
		this.hideWindow();
	}
}
