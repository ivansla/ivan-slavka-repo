package ivan.slavka.utils.interfaces;

import ivan.slavka.utils.beans.RosterBean;

public interface IRosterControl {

	public void updateRoster(RosterBean roster);

	public String getOpponentName();
}
