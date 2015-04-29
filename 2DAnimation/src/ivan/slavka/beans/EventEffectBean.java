package ivan.slavka.beans;

import ivan.slavka.enums.EventBehaviorEnum;
import ivan.slavka.enums.EventSpriteEnum;
import ivan.slavka.enums.EventTypeEnum;
import ivan.slavka.enums.SpecialEventEnum;
import ivan.slavka.interfaces.IEconomyProgress;

import java.util.Random;

public class EventEffectBean {

	private IEconomyProgress economyController;
	private static int NUMBER_OF_RESOURCES = 4;
	private ResourceBean[] resourceArray = new ResourceBean[NUMBER_OF_RESOURCES];

	private AttributeBean[] possibleAttributes = new AttributeBean[7];

	private AttributeBean[] attributes = {
			new AttributeBean(-10, -6, 5),
			new AttributeBean(-5, -1, 2),
			new AttributeBean(1, 3, 1),
			new AttributeBean(4, 7, 2),
			new AttributeBean(8, 11, 4),
			new AttributeBean(12, 15, 6),
			new AttributeBean(16, 20, 8)
	};

	private AttributeBean[] specialEventAttributes = {
			new AttributeBean(EventSpriteEnum.VERMIN, 20, 80, 1, EventSpriteEnum.FOOD),
			new AttributeBean(EventSpriteEnum.PLAGUE, 20, 80, 7, EventSpriteEnum.ALL_WORKERS),
			new AttributeBean(EventSpriteEnum.EARTHQUAKE, 10, 30, 9, EventSpriteEnum.CONSTRUCTION),
			new AttributeBean(EventSpriteEnum.BAD_ROCK, 20, 80, 5, EventSpriteEnum.STONE),
			new AttributeBean(EventSpriteEnum.FIRE, 20, 80, 5, EventSpriteEnum.WOOD),
			new AttributeBean(EventSpriteEnum.BABY_BOOM, 20, 50, 5, EventSpriteEnum.ALL_WORKERS),
	};

	private AttributeBean[] raidEventAttributes = {
			new AttributeBean(EventSpriteEnum.RAID_VILLAGE, 100, 300, 2, EventSpriteEnum.FOOD),
			new AttributeBean(EventSpriteEnum.RAID_VILLAGE, 100, 300, 2, EventSpriteEnum.WOOD),
			new AttributeBean(EventSpriteEnum.RAID_TOWN, 50, 150, 2, EventSpriteEnum.STONE),
			new AttributeBean(EventSpriteEnum.RAID_TOWN, 20, 50, 2, EventSpriteEnum.COINS)
	};

	private AttributeBean[] invasionAttributes = {
			new AttributeBean(10, 30, 1),
			new AttributeBean(40, 70, 2),
			new AttributeBean(80, 110, 4),
			new AttributeBean(120, 150, 6),
			new AttributeBean(160, 200, 8)
	};

	private Random random = new Random();

	private EventBehaviorEnum behavior;
	private SpecialEventEnum specialEventName;
	private EventTypeEnum eventType;
	private EventSpriteEnum eventName;

	private int lastPossibleAttributeIndex = 0;

	public EventEffectBean(){
		for(int i = 0; i < NUMBER_OF_RESOURCES; i++){
			this.resourceArray[i] = new ResourceBean();
		}
	}

	public EventEffectBean(IEconomyProgress economyController){
		this();
		this.economyController = economyController;
	}

	public EventBehaviorEnum getBehavior() {
		return this.behavior;
	}
	public void setBehavior(EventBehaviorEnum behavior) {
		this.behavior = behavior;
	}

	public boolean rollAttributes(EventTypeEnum eventType, int level, int roll){
		float quantity = 0f;
		int resourceRoll;
		this.eventType = eventType;
		boolean filterSuccessfull = false;
		boolean isRollSuccessfull = false;

		this.resetResources();

		if(level == 0){
			level = 1;
		}

		AttributeBean attr;
		switch(eventType){
		case RESOURCE:
			int max = 15 * level;
			int min = 5 * level;
			quantity = (this.random.nextInt(max - min - 1)) + min;
			if((roll % 2) == 0){
				this.behavior = EventBehaviorEnum.ADDITION;
			} else {
				this.behavior = EventBehaviorEnum.SELL;
			}
			resourceRoll = this.random.nextInt(3);
			this.resourceArray[0].activateResource(EventSpriteEnum.getEnum(resourceRoll), quantity);

			switch(EventSpriteEnum.getEnum(resourceRoll)){
			case STONE:
				if(this.economyController.getStoneStored() <= 0){
					return false;
				}
				break;
			case WOOD:
				if(this.economyController.getWoodStored() <= 0){
					return false;
				}
				break;
			case FOOD:
				if(this.economyController.getFoodStored() <= 0){
					return false;
				}
				break;
			}


			this.eventName = EventSpriteEnum.getEnum(resourceRoll);
			isRollSuccessfull = true;
			break;
		case WORKER:
			filterSuccessfull = this.filterPossibleAttributes(level);
			if(filterSuccessfull){
				attr = this.possibleAttributes[this.random.nextInt(this.lastPossibleAttributeIndex)];
				quantity = this.random.nextInt(attr.maxValue - attr.minValue + 1) + attr.minValue;
				this.behavior = EventBehaviorEnum.ADDITION;
				resourceRoll = this.random.nextInt(5) + 4;
				this.resourceArray[0].activateResource(EventSpriteEnum.getEnum(resourceRoll), quantity);
				if(EventSpriteEnum.getEnum(resourceRoll).equals(EventSpriteEnum.SOLDIER) && this.economyController.getCoins() < this.resourceArray[0].getPrice()){
					while(resourceRoll == EventSpriteEnum.SOLDIER.getCode()){
						resourceRoll = this.random.nextInt(5) + 4;
						this.resourceArray[0].activateResource(EventSpriteEnum.getEnum(resourceRoll), quantity);
					}
				}

				isRollSuccessfull = true;
				this.eventName = EventSpriteEnum.getEnum(resourceRoll);
			}
			break;
		case SPECIAL_EVENT:
			filterSuccessfull = this.filterPossibleAttributes(this.specialEventAttributes, level);
			if(filterSuccessfull){
				attr = this.possibleAttributes[this.random.nextInt(this.lastPossibleAttributeIndex)];
				quantity = this.random.nextInt(attr.maxValue - attr.minValue + 1) + attr.minValue;
				this.behavior = EventBehaviorEnum.SUBTRACTION;
				this.resourceArray[0].activateResource(attr.resource, quantity);
				isRollSuccessfull = true;
				this.eventName = attr.name;
			}
			break;
		case RAID:
			filterSuccessfull = this.filterPossibleAttributes(this.raidEventAttributes, level);
			if(filterSuccessfull){
				attr = this.possibleAttributes[this.random.nextInt(this.lastPossibleAttributeIndex)];
				quantity = this.random.nextInt(attr.maxValue - attr.minValue + 1) + attr.minValue;
				this.behavior = EventBehaviorEnum.ADDITION;
				this.resourceArray[0].activateResource(attr.resource, quantity);

				attr = this.findComplement(attr, this.raidEventAttributes);
				quantity = this.random.nextInt(attr.maxValue - attr.minValue + 1) + attr.minValue;
				this.resourceArray[1].activateResource(attr.resource, quantity);
				isRollSuccessfull = true;
				this.eventName = attr.name;
			}
			break;
		case ATTACK:
			filterSuccessfull = this.filterPossibleAttributes(this.invasionAttributes, level);
			if(filterSuccessfull){
				attr = this.possibleAttributes[this.random.nextInt(this.lastPossibleAttributeIndex)];
				quantity = this.random.nextInt(attr.maxValue - attr.minValue + 1) + attr.minValue;
				this.behavior = EventBehaviorEnum.SUBTRACTION;
				this.resourceArray[0].activateResource(EventSpriteEnum.SOLDIER, quantity);
				isRollSuccessfull = true;
			}
			break;
		}
		return isRollSuccessfull;
	}

	private AttributeBean findComplement(AttributeBean attributeBean, AttributeBean[] attributesArray){
		for(AttributeBean attr : attributesArray){
			if(attributeBean.qLvl == attr.qLvl && attributeBean.name.equals(attr.name) && !attributeBean.resource.equals(attr.resource)){
				return attr;
			}
		}
		return null;
	}

	// TODO: These two methods need to be optimized
	private boolean filterPossibleAttributes(int level){
		boolean isFilterSuccessfull = false;
		this.resetPossibleAttributes();
		for(AttributeBean attr : this.attributes){
			if(attr.qLvl <= level && attr.qLvl >= (level * 0.5)){
				this.possibleAttributes[this.lastPossibleAttributeIndex] = attr;
				this.lastPossibleAttributeIndex++;
				isFilterSuccessfull = true;
			}
		}
		return isFilterSuccessfull;
	}

	private boolean filterPossibleAttributes(AttributeBean[] attributesArray, int level){
		boolean isFilterSuccessfull = false;
		this.resetPossibleAttributes();
		for(AttributeBean attr : attributesArray){
			if(attr.qLvl <= level && attr.qLvl >= (level * 0.5)){
				this.possibleAttributes[this.lastPossibleAttributeIndex] = attr;
				this.lastPossibleAttributeIndex++;
				isFilterSuccessfull = true;
			}
		}
		return isFilterSuccessfull;
	}

	private void resetPossibleAttributes(){
		this.lastPossibleAttributeIndex = 0;

		for(int i = 0; i < this.possibleAttributes.length; i++){
			this.possibleAttributes[i] = null;
		}
	}

	private class AttributeBean{
		public final EventSpriteEnum resource;
		public final int minValue;
		public final int maxValue;
		public final int qLvl;
		public EventSpriteEnum name;

		public AttributeBean(int minValue, int maxValue, int qLvl){
			this.minValue = minValue;
			this.maxValue = maxValue;
			this.qLvl = qLvl;
			this.resource = EventSpriteEnum.ALL_WORKERS;
		}

		public AttributeBean(EventSpriteEnum name, int minValue, int maxValue, int qLvl, EventSpriteEnum resourceEnum){
			this.name = name;
			this.minValue = minValue;
			this.maxValue = maxValue;
			this.qLvl = qLvl;
			this.resource = resourceEnum;
		}
	}

	public SpecialEventEnum getSpecialEventName() {
		return this.specialEventName;
	}

	private void resetResources(){
		for(int i = 0; i < NUMBER_OF_RESOURCES; i++){
			this.resourceArray[i].setIsActivated(false);
		}
	}

	public ResourceBean[] getEventResources(){
		return this.resourceArray;
	}

	public EventTypeEnum getEventType(){
		return this.eventType;
	}
	public EventSpriteEnum getEventName(){
		return this.eventName;
	}
}
