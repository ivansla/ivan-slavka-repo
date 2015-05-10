package ivan.slavka.constants;

import ivan.slavka.enums.EventSpriteEnum;

public class Constants {

	public static int SPECIAL_EVENT_CHANCE_STARTS_AT = 98;

	// CombatSimulator
	public static int PLAYER_SOLDIER_HITPOINTS = 45;
	public static int MAX_DAMAGE = 10;

	// SoldierBean
	public static int DEFAULT_NUMBER_OF_HITPOINTS = 30;

	public static double DEFAULT_CHANCE_TO_HIT = 65;
	public static int DEFAULT_NUMBER_OF_TURNS = 20;

	// EconomyStatusBean
	public static float WOOD_MULTIPLICATOR = 0.5f;
	public static float STONE_MULTIPLICATOR = 0.25f;
	public static float FOOD_MULTIPLICATOR = 0.7f;
	public static float FOOD_CONSUMPTION = 0.2f;
	public static int FOOD_SHORTAGE_TOLERANCE = 3;

	// ResourceBean
	public static int WOOD_PRICE = 2;
	public static int FOOD_PRICE = 1;
	public static int STONE_PRICE = 4;
	public static int SOLDIER_PRICE = 7;

	// WonderBean
	public static int WOOD_NEEDED = 1000;
	public static int STONE_NEEDED = 500;
	public static float CONSTRUCTION_MULTIPLICATOR = 0.1f;
	public static float RESOURCE_IMPORTANCE_QUOTIENT = 0.5f;
	public static float MAINTENANCE_QUOTIENT = 0.2f;

	public final static AttributeBean[] ATTRIBUTES = {
		new AttributeBean(-10, -6, 5),
		new AttributeBean(-5, -1, 2),
		new AttributeBean(1, 3, 1),
		new AttributeBean(4, 7, 2),
		new AttributeBean(8, 11, 4),
		new AttributeBean(12, 15, 6),
		new AttributeBean(16, 20, 8)
	};

	public final static AttributeBean[] SPECIAL_EVENT_ATTRIBUTES = {
		new AttributeBean(EventSpriteEnum.VERMIN, 20, 50, 1, EventSpriteEnum.FOOD),
		new AttributeBean(EventSpriteEnum.PLAGUE, 20, 50, 7, EventSpriteEnum.ALL_WORKERS),
		new AttributeBean(EventSpriteEnum.EARTHQUAKE, 10, 20, 9, EventSpriteEnum.CONSTRUCTION),
		new AttributeBean(EventSpriteEnum.BAD_ROCK, 20, 50, 5, EventSpriteEnum.STONE),
		new AttributeBean(EventSpriteEnum.FIRE, 20, 50, 5, EventSpriteEnum.WOOD),
		new AttributeBean(EventSpriteEnum.BABY_BOOM, 20, 50, 5, EventSpriteEnum.ALL_WORKERS),
	};

	public final static AttributeBean[] RAID_EVENT_ATTRIBUTES = {
		new AttributeBean(EventSpriteEnum.RAID_VILLAGE, 100, 300, 2, EventSpriteEnum.FOOD),
		new AttributeBean(EventSpriteEnum.RAID_VILLAGE, 100, 300, 2, EventSpriteEnum.WOOD),
		new AttributeBean(EventSpriteEnum.RAID_TOWN, 50, 150, 2, EventSpriteEnum.STONE),
		new AttributeBean(EventSpriteEnum.RAID_TOWN, 20, 50, 2, EventSpriteEnum.COINS),
		new AttributeBean(EventSpriteEnum.RAID_VILLAGE, 300, 900, 5, EventSpriteEnum.FOOD),
		new AttributeBean(EventSpriteEnum.RAID_VILLAGE, 300, 900, 5, EventSpriteEnum.WOOD),
		new AttributeBean(EventSpriteEnum.RAID_TOWN, 200, 450, 5, EventSpriteEnum.STONE),
		new AttributeBean(EventSpriteEnum.RAID_TOWN, 20, 50, 5, EventSpriteEnum.COINS),
		new AttributeBean(EventSpriteEnum.RAID_VILLAGE, 1000, 3000, 8, EventSpriteEnum.FOOD),
		new AttributeBean(EventSpriteEnum.RAID_VILLAGE, 1000, 3000, 8, EventSpriteEnum.WOOD),
		new AttributeBean(EventSpriteEnum.RAID_TOWN, 500, 1500, 8, EventSpriteEnum.STONE),
		new AttributeBean(EventSpriteEnum.RAID_TOWN, 20, 50, 8, EventSpriteEnum.COINS)
	};

	public final static AttributeBean[] INVASION_ATTRIBUTES = {
		new AttributeBean(10, 30, 1),
		new AttributeBean(40, 70, 2),
		new AttributeBean(80, 110, 4),
		new AttributeBean(120, 150, 6),
		new AttributeBean(160, 200, 8)
	};

	public static class AttributeBean{
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

}
