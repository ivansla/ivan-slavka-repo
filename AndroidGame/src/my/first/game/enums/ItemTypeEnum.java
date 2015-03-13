package my.first.game.enums;

public enum ItemTypeEnum {

	HELM(0),
	AMULET(1),
	ARMOR(2),
	RING(3),
	WEAPON(4),
	SHIELD(5),
	MISC(7);

	private int value;

	private ItemTypeEnum(int value){
		this.value = value;
	}

	@Override
	public String toString(){
		switch(this.value){
		case 0:
			return "Helm";
		case 1:
			return "Amulet";
		case 2:
			return "Armor";
		case 3:
			return "Ring";
		case 4:
			return "Weapon";
		case 5:
			return "Shield";
		case 7:
			return "Misc.";
		default:
			return "Incorrect ItemTypeEnum";
		}
	}
}
