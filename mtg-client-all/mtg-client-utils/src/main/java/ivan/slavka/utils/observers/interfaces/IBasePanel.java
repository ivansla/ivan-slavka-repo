package ivan.slavka.utils.observers.interfaces;

import ivan.slavka.utils.beans.CardBean;

public interface IBasePanel {

	public void addCard(CardBean cardBean);

	public void removeCard(CardBean cardBean);

	public void tapCard(CardBean cardBean);

	public void unTapCard(CardBean cardBean);
}
