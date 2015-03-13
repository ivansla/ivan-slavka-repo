package ivan.slavka.utils.observers.interfaces;

import ivan.slavka.utils.beans.wrappers.CardBeanWrapper;

import javax.swing.ImageIcon;

public interface ICardsPanel {

	public void showCard(ImageIcon image);

	public void hideCard();

	public void playCard(CardBeanWrapper cardBean);

	public void addCard(CardBeanWrapper cardBean);
}
