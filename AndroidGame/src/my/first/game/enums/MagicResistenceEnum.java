package my.first.game.enums;

public enum MagicResistenceEnum {

	IMMUNITY(1),
	RESISTENCE(2),
	NONE(3);

	private int value;

	private MagicResistenceEnum(int value){
		this.value = value;
	}
}
