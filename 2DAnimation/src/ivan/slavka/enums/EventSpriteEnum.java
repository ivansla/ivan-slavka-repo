package ivan.slavka.enums;

public enum EventSpriteEnum {

	WOOD(0),
	STONE(1),
	FOOD(2),
	ALL_MATERIALS(3),
	WOOD_WORKER(4),
	STONE_WORKER(5),
	FOOD_WORKER(6),
	SOLDIER(7),
	BUILDER(8),
	ALL_WORKERS(9),
	COINS(10),
	CONSTRUCTION(11),

	VERMIN(20),
	PLAGUE(21),
	EARTHQUAKE(22),
	DISEASE(23),
	BAD_ROCK(24),
	FIRE(25),
	BABY_BOOM(26),
	RAID_TOWN(27),
	RAID_VILLAGE(28),

	NONE(100);

	private int code;

	private EventSpriteEnum(int code){
		this.code = code;
	}

	public int getCode(){
		return this.code;
	}

	public static EventSpriteEnum getEnum(int code){
		switch(code){
		case 0:
			return WOOD;
		case 1:
			return STONE;
		case 2:
			return FOOD;
		case 3:
			return ALL_MATERIALS;
		case 4:
			return WOOD_WORKER;
		case 5:
			return STONE_WORKER;
		case 6:
			return FOOD_WORKER;
		case 7:
			return SOLDIER;
		case 8:
			return BUILDER;
		case 9:
			return ALL_WORKERS;
		default:
			return NONE;
		}
	}
}
