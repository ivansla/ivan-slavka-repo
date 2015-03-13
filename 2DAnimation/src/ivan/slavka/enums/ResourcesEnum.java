package ivan.slavka.enums;

public enum ResourcesEnum {

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
	NONE(100);

	private int code;

	private ResourcesEnum(int code){
		this.code = code;
	}

	public int getCode(){
		return this.code;
	}

	public static ResourcesEnum getEnum(int code){
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

	public String getAbbr(){
		switch(this.code){
		case 0:
			return "W";
		case 1:
			return "S";
		case 2:
			return "F";
		case 3:
			return "AM";
		case 4:
			return "WW";
		case 5:
			return "SW";
		case 6:
			return "FW";
		case 7:
			return "SR";
		case 8:
			return "BR";
		case 9:
			return "AW";
		default:
			return "NONE";
		}
	}
}
