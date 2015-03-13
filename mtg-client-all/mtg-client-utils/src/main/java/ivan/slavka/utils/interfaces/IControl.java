package ivan.slavka.utils.interfaces;

import ivan.slavka.utils.beans.CardBean;
import ivan.slavka.utils.beans.ManaPoolBean;
import ivan.slavka.utils.beans.wrappers.CardBeanWrapper;

import java.util.List;

public interface IControl extends IControlTurnManagement{

	public ManaPoolBean playCard(CardBeanWrapper cardBean);

	public List<CardBeanWrapper> getCardsInHand();

	public CardBeanWrapper addCard();

	public void addViewObserver(IMTGClientView observer);

	public void addModelObserver(IApplication observer);

	public int getAvailableMana();

	public void registerMessageController(IMessageControllerClient messageController);

	public void sendCard(CardBeanWrapper cardBean);

	public void receivePlayCard(CardBean cardBean);
}
