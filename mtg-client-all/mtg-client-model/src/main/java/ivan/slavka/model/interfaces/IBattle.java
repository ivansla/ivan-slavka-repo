package ivan.slavka.model.interfaces;

import ivan.slavka.utils.beans.CardBean;

public interface IBattle {

	public void addAttackCard(CardBean attackCard);

	public void addDefendCard(CardBean attackCard, CardBean defendCard);

	public void removeAttackCard(CardBean attackCard);

	public void removeDefendCard(CardBean attackCard, CardBean defendCard);

	public void startBattle();
}
