package ivan.slavka.utils.interfaces;

import ivan.slavka.utils.beans.InviteBean;
import ivan.slavka.utils.beans.ManaPoolBean;
import ivan.slavka.utils.beans.wrappers.CardBeanWrapper;
import ivan.slavka.utils.enums.PlayerEnum;

import java.util.List;

public interface IApplication extends IApplicationTurnManagement{

	public List<CardBeanWrapper> getCardsInHand();

	public CardBeanWrapper addCard();

	public ManaPoolBean playCard(CardBeanWrapper cardbean);

	public PlayerEnum getTurnOwner();

	public void setTurnOwner(PlayerEnum turnOwner);

	public int getAvailableMana();

	public void startGame(InviteBean inviteBean);

	public void playCardOpponent(CardBeanWrapper cardBeanWrapper);
}
