package ivan.slavka.utils.interfaces;

import ivan.slavka.utils.beans.TurnStatusBean;
import ivan.slavka.utils.enums.PlayerEnum;

public interface IControlTurnManagement {

	public TurnStatusBean getTurnStatus();

	public void nextStep();

	public void setTurnOwner(PlayerEnum turnOwner);
}
