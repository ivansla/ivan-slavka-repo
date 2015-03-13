package my.first.game.enums;

public enum InventoryFilterEnum {

	ALL(0),
	HELM(1),
	ARMOR(2),
	WEAPON(3),
	SHIELD(4),
	JEWELRY(5);

	private int value;

	private InventoryFilterEnum(int value){
		this.value = value;
	}

	@Override
	public String toString(){
		switch(this.value){
		case 0:
			return "All";
		case 1:
			return "Helms";
		case 2:
			return "Armors";
		case 3:
			return "Weapons";
		case 4:
			return "Shields";
		case 5:
			return "Jewelry";
		default:
			return "Incorrect LocationServiceEnum";
		}
	}

	public static InventoryFilterEnum toEnum(String stringToEnum){

		if(stringToEnum.equals("All")){
			return ALL;
		} else{
			return HELM;
		}
	}
}
