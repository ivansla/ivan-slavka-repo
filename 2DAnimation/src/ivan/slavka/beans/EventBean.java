package ivan.slavka.beans;

import ivan.slavka.enums.EventTypeEnum;
import ivan.slavka.interfaces.IEvent;

import java.util.Random;

public class EventBean implements IEvent{

	private boolean isReleased = false;
	private boolean isSpecial = false;
	private Random random = new Random();
	private EventEffectBean[] eventEffects = {
			new EventEffectBean(),
			new EventEffectBean()
	};

	public void generateSpecialEvent(int level, int roll){
		this.isSpecial = true;
		this.eventEffects[0].rollAttributes(EventTypeEnum.SPECIAL_EVENT, level, roll);
		this.eventEffects[1] = this.eventEffects[0];
	}

	public void generateEvent(int level, EventTypeEnum eventType){
		switch(eventType){
		case ATTACK:
			break;
		default:
			break;
		}
	}

	public void generateEvent(int level){
		this.isSpecial = false;
		for(int i = 0; i < 2; i++){
			boolean successfullRoll = false;
			while(!successfullRoll){
				int roll = this.random.nextInt(3);
				switch(roll){
				case 0:
					successfullRoll = this.eventEffects[i].rollAttributes(EventTypeEnum.WORKER, level, roll);
					break;
				case 1:
					successfullRoll = this.eventEffects[i].rollAttributes(EventTypeEnum.RESOURCE, level, roll);
					break;
				case 2:
					successfullRoll = this.eventEffects[i].rollAttributes(EventTypeEnum.RAID, level, roll);
					break;
				}
			}
		}

		while(this.eventEffects[0].getEventType().equals(this.eventEffects[1].getEventType()) &&
				this.eventEffects[0].getEventResources()[0].getResource().equals(this.eventEffects[1].getEventResources()[0].getResource())){

			boolean successfullRoll = false;
			while(!successfullRoll){
				int roll = this.random.nextInt(3);
				switch(roll){
				case 0:
					successfullRoll = this.eventEffects[1].rollAttributes(EventTypeEnum.WORKER, level, roll);
					break;
				case 1:
					successfullRoll = this.eventEffects[1].rollAttributes(EventTypeEnum.RESOURCE, level, roll);
					break;
				case 2:
					successfullRoll = this.eventEffects[1].rollAttributes(EventTypeEnum.RAID, level, roll);
					break;
				}
			}
		}
	}

	public boolean isReleased() {
		return this.isReleased;
	}

	public void setReleased(boolean isReleased) {
		this.isReleased = isReleased;
	}

	@Override
	public EventEffectBean getPrimaryEffect() {
		return this.eventEffects[0];
	}

	@Override
	public EventEffectBean getSecondaryEffect() {
		return this.eventEffects[1];
	}

	@Override
	public boolean isSpecialEvent() {
		return this.isSpecial;
	}
}
