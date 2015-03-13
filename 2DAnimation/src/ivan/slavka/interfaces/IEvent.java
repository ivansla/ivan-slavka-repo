package ivan.slavka.interfaces;

import ivan.slavka.beans.EventEffectBean;

public interface IEvent {

	public EventEffectBean getPrimaryEffect();
	public EventEffectBean getSecondaryEffect();

	public boolean isSpecialEvent();
}
