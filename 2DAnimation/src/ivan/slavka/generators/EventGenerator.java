package ivan.slavka.generators;

import ivan.slavka.beans.EventBean;
import ivan.slavka.enums.EventTypeEnum;
import ivan.slavka.interfaces.IEvent;
import ivan.slavka.interfaces.IEventGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class EventGenerator implements IEventGenerator{

	private Random randomizer = new Random();
	private List<EventBean> eventPool = new ArrayList<EventBean>();

	@Override
	public IEvent generateEvent(int level){
		return this.generateEvent(level, null);
	}

	@Override
	public IEvent generateEvent(int level, EventTypeEnum eventType) {
		boolean isEventAvailable = false;
		EventBean event = null;
		for(EventBean e : this.eventPool){
			if(e.isReleased()){
				isEventAvailable = true;
				e.setReleased(false);
				event = e;
			}
		}

		if(!isEventAvailable){
			event = new EventBean();
			this.eventPool.add(event);
		}

		if(eventType != null){
			event.generateEvent(level, eventType);
		} else {
			int roll = this.randomizer.nextInt(100);
			if(roll >= 95){ // special event
				event.generateSpecialEvent(level, roll);
			} else {
				event.generateEvent(level);
			}
		}
		return event;
	}
}
