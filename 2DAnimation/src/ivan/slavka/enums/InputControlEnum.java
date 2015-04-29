package ivan.slavka.enums;

public enum InputControlEnum {

	LEFT(0),
	RIGHT(1),
	CLICK(2),
	NONE(3);

	private int code;

	private InputControlEnum(int code){
		this.code = code;
	}

	public static InputControlEnum getEnum(int code){
		switch(code){
		case 0:
			return LEFT;
		case 1:
			return RIGHT;
		case 2:
			return CLICK;
		default:
			return NONE;
		}
	}
}
