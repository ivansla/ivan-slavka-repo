package ivan.slavka.utils.interfaces;

import ivan.slavka.utils.beans.wrappers.CardBeanWrapper;
import ivan.slavka.utils.enums.PlayerEnum;

public interface IMTGClientView {

	public void playCard(CardBeanWrapper cardBean);

	public void addCard(PlayerEnum playerEnum);

	public void attack(CardBeanWrapper cardBean);

	public void defend(CardBeanWrapper cardBean);

	public void useAbility(CardBeanWrapper cardBean);

	public void initWindow();
}
