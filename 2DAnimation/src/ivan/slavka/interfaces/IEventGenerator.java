package ivan.slavka.interfaces;

import ivan.slavka.enums.EventTypeEnum;


public interface IEventGenerator {

	public IEvent generateEvent(int level);
	public IEvent generateEvent(int level, EventTypeEnum eventType);
}
