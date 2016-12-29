package ivan.slavka.generators;

import ivan.slavka.beans.EventBean;
import ivan.slavka.constants.Constants;
import ivan.slavka.enums.EventTypeEnum;
import ivan.slavka.interfaces.IEconomyProgress;
import ivan.slavka.interfaces.IEvent;
import ivan.slavka.interfaces.IEventGenerator;

import java.util.Random;

public class EventGenerator implements IEventGenerator{

	private static int POOL_SIZE = 10;

	private Random randomizer = Randomizer.getInstance();
	private EventBean[] eventPool = new EventBean[POOL_SIZE];

	//private List<EventBean> eventPool = new ArrayList<EventBean>();

	private IEconomyProgress economyController;

	public EventGenerator(){}

	public EventGenerator(IEconomyProgress economyController){
		this.economyController = economyController;
	}

	@Override
	public IEvent generateEvent(int level){
		return this.generateEvent(level, null);
	}

	@Override
	public IEvent generateEvent(int level, EventTypeEnum eventType) {
		//boolean isEventAvailable = false;
		EventBean event = null;
		for(EventBean e : this.eventPool){
			if(e == null){
				e = new EventBean(this.economyController);
				event = e;
				break;
			}

			if(e.isReleased()){
				//isEventAvailable = true;
				e.setReleased(false);
				event = e;
				break;
			}
		}

		/*
		if(!isEventAvailable){
			event = new EventBean(this.economyController);
			this.eventPool.add(event);
		}
		 */

		if(eventType != null){
			event.generateEvent(level, eventType);
		} else {
			int roll = this.randomizer.nextInt(100);
			if(roll >= Constants.SPECIAL_EVENT_CHANCE_STARTS_AT){ // special event
				boolean success = event.generateSpecialEvent(level, roll);
				if(!success){
					event.generateEvent(level);
				}
			} else {
				event.generateEvent(level);
			}
		}
		return event;
	}
}
