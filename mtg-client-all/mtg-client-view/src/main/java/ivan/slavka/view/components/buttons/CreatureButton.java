package ivan.slavka.view.components.buttons;

import ivan.slavka.utils.beans.wrappers.CardBeanWrapper;
import ivan.slavka.utils.enums.CardPlayTypeEnum;
import ivan.slavka.utils.enums.PlayerEnum;
import ivan.slavka.view.components.listeners.CreatureButtonMouseListener;
import ivan.slavka.view.components.window.CreatureButtonPopupWindow;
import ivan.slavka.view.panels.MTGClientPanel;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Rectangle;

import javax.swing.JButton;
import javax.swing.JFrame;

public class CreatureButton extends JButton{

	private final CreatureButtonPopupWindow popupWindow;
	private final CardBeanWrapper cardBean;
	private boolean isAttacking = false;
	private boolean isBlocking = false;
	private final PlayerEnum cardOwner;

	private int cardRow;
	private int cardColumn;

	public CreatureButton(MTGClientPanel viewController, CardBeanWrapper cardBean, PlayerEnum cardOwner){
		this.popupWindow = new CreatureButtonPopupWindow(this, viewController);
		this.cardBean = cardBean;
		this.cardOwner = cardOwner;

		this.setText("C");
		this.setPreferredSize(new Dimension(30, 60));

		this.addMouseListener(new CreatureButtonMouseListener(this, this.popupWindow, viewController));
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

	public void removeItem(CardBeanWrapper cardBean, CardPlayTypeEnum playType){
		this.popupWindow.removeItem(cardBean, playType);
	}

	public CardBeanWrapper getCardBeanWrapper() {
		return this.cardBean;
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
	 * Retrieves bounds of the button in relation to the desktop, and sets the flag if the popupWindow
	 * is above or below the button.
	 * 
	 * @return
	 */
	public Rectangle getButtonPosition(){
		Rectangle r = new Rectangle(this.getLocation(), this.getSize());

		Component component = this.getComponentContainer(this);
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

	public boolean isAttacking() {
		return this.isAttacking;
	}

	public void setAttacking(boolean isAttacking) {
		this.isAttacking = isAttacking;
	}

	public int getCardRow() {
		return this.cardRow;
	}

	public void setCardRow(int cardRow) {
		this.cardRow = cardRow;
	}

	public int getCardColumn() {
		return this.cardColumn;
	}

	public void setCardColumn(int cardColumn) {
		this.cardColumn = cardColumn;
	}

	public PlayerEnum getCardOwner() {
		return this.cardOwner;
	}

	public boolean isBlocking() {
		return this.isBlocking;
	}

	public void setBlocking(boolean isBlocking) {
		this.isBlocking = isBlocking;
	}
}
