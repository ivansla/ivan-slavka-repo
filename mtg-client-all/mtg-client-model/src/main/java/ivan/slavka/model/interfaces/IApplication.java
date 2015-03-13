package ivan.slavka.model.interfaces;

import ivan.slavka.utils.beans.CardBean;
import ivan.slavka.utils.interfaces.IControl;

public interface IApplication {

	public CardBean addCard(int cardNumber);

	public void playCard(CardBean cardbean);

	public void addObserver(IControl observer);

	public void removeObserver(IControl observer);
}
