package my.first.game.enums;

public enum CharacterClassEnum {

	WARRIOR(1),
	ROGUE(2),
	MAGE(3);

	private int value;

	private CharacterClassEnum(int value){
		this.value = value;
	}

	@Override
	public String toString(){

		switch (this.value) {
		case 1:
			return "Warrior";
		case 2:
			return "Rogue";
		case 3:
			return "Mage";
		default:
			return "no class";
		}
	}
}
