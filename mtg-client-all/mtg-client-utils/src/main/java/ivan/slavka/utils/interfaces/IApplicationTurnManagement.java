package ivan.slavka.utils.interfaces;

import ivan.slavka.utils.beans.TurnStatusBean;

public interface IApplicationTurnManagement {

	public void changeTurnOwner();

	public void nextStep();

	public TurnStatusBean getTurnStatus();
}
