package my.first.game.enums;

public enum MonsterTypeEnum {

	UNDEAD(1),
	ANIMAL(2),
	DEMON(3);

	private int value;

	private MonsterTypeEnum(int value){
		this.value = value;
	}
}
