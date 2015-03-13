package ivan.slavka.view.components.window;

import ivan.slavka.utils.beans.wrappers.CardBeanWrapper;
import ivan.slavka.view.components.listeners.InstantCastingProgressBarListener;
import ivan.slavka.view.components.panels.GeneralCastingPopupWindowPanel;
import ivan.slavka.view.components.panels.TargetCastingPopupWindowPanel;
import ivan.slavka.view.panels.PlayTablePanel;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JFrame;
import javax.swing.JWindow;
import javax.swing.Timer;

public class CastingPopupWindow extends JWindow{

	private final GeneralCastingPopupWindowPanel generalCastingPanel;
	private final TargetCastingPopupWindowPanel targetCastingPanel = new TargetCastingPopupWindowPanel();
	private final PlayTablePanel playTablePanel;
	private final Timer instantCastingTimer;

	private static CastingPopupWindow castingPopupWindow = null;

	private CastingPopupWindow(PlayTablePanel playTablePanel) {

		this.setSize(312, 445);
		this.playTablePanel = playTablePanel;
		this.generalCastingPanel = new GeneralCastingPopupWindowPanel(this);
		this.instantCastingTimer = new Timer(50, new InstantCastingProgressBarListener(this.generalCastingPanel.getProgressBar(), this));
	}

	public static CastingPopupWindow getInstance(PlayTablePanel playTablePanel){
		if(castingPopupWindow == null){
			castingPopupWindow = new CastingPopupWindow(playTablePanel);
		}
		return castingPopupWindow;
	}

	public void generalCasting(CardBeanWrapper castingCard){

		this.generalCastingPanel.castCard(castingCard);
		this.setContentPane(this.generalCastingPanel);
		this.validate();

		this.showWindow();
	}

	public void targetCasting(CardBeanWrapper castingCard, CardBeanWrapper targetCard){
		this.targetCastingPanel.castCard(castingCard, targetCard);
		this.setContentPane(this.targetCastingPanel);
		this.validate();

		this.showWindow();
	}

	private void showWindow() {
		this.positionWindow();
		this.instantCastingTimer.restart();
		this.setVisible(true);
	}

	public void hideWindow() {
		this.instantCastingTimer.stop();
		this.setVisible(false);
	}

	/**
	 * Positions popup window, based on position of the button, so that window is never out of the frame.
	 */
	private void positionWindow(){

		// Size of the popup window.
		Dimension windowDimension = this.getSize();

		Rectangle containingPanel = this.getContainingPanel(this.playTablePanel);
		Rectangle buttonBoundsInContainingPanel = this.getPopupWindowBoundsInContainingPanel(this.playTablePanel, containingPanel);

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

		return new Rectangle(p, this.playTablePanel.getSize());
	}

	public Timer getInstantCastingTimer() {
		return this.instantCastingTimer;
	}
}
