package my.first.game.enums;

public enum LocationServiceEnum {

	WEAPON_MERCHANT(0),
	ARMOR_MERCHANT(1);

	private int value;

	private LocationServiceEnum(int value){
		this.value = value;
	}

	@Override
	public String toString(){
		switch(this.value){
		case 0:
			return "Weapon Merchant";
		case 1:
			return "Armor Merchant";
		default:
			return "Incorrect LocationServiceEnum";
		}
	}

	public static LocationServiceEnum toEnum(String stringToEnum){

		if(stringToEnum.equals("Weapon Merchant")){
			return WEAPON_MERCHANT;
		} else{
			return ARMOR_MERCHANT;
		}
	}
}