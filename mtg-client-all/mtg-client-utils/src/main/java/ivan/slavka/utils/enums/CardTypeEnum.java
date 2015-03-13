package ivan.slavka.utils.enums;

import ivan.slavka.utils.exceptions.ParseEnumException;

import java.util.ArrayList;
import java.util.List;

public enum CardTypeEnum {

	ARTIFACT(1),
	CREATURE(2),
	ENCHANTMENT(3),
	INSTANT(4),
	LAND(5),
	PLANESWALKER(6),
	SORCERY(7),
	TRIBAL(8),
	PLANE(9),
	VANGUARD(10),
	SCHEME(11);

	private int code;

	private CardTypeEnum(int code){
		this.code = code;
	}

	public String getCardTypeSymbol(){
		switch(this.code){
		case 1:
			return "A";
		case 2:
			return "C";
		case 3:
			return "E";
		case 4:
			return "I";
		case 5:
			return "L";
		case 6:
			return "PW";
		case 7:
			return "SC";
		case 8:
			return "T";
		case 9:
			return "PL";
		case 10:
			return "V";
		case 11:
			return "S";
		default:
			return "XXX";
		}
	}

	public static CardTypeEnum parse(String type) throws ParseEnumException{
		if(type.equalsIgnoreCase("ARTIFACT")){
			return ARTIFACT;
		} else if(type.equalsIgnoreCase("CREATURE")){
			return CREATURE;
		} else if(type.equalsIgnoreCase("ENCHANTMENT")){
			return ENCHANTMENT;
		}else if(type.equalsIgnoreCase("INSTANT")){
			return INSTANT;
		}else if(type.equalsIgnoreCase("LAND")){
			return LAND;
		}else if(type.equalsIgnoreCase("PLANESWALKER")){
			return PLANESWALKER;
		}else if(type.equalsIgnoreCase("SORCERY")){
			return SORCERY;
		}else if(type.equalsIgnoreCase("TRIBAL")){
			return TRIBAL;
		}else if(type.equalsIgnoreCase("PLANE")){
			return PLANE;
		}else if(type.equalsIgnoreCase("VANGUARD")){
			return VANGUARD;
		}else if(type.equalsIgnoreCase("SCHEME")){
			return SCHEME;
		}else {
			throw new ParseEnumException(getEnumValues());
		}
	}

	private static List<String> getEnumValues(){
		List<String> permitedEnumValues = new ArrayList<String>();
		for(CardTypeEnum cardTypeEnum : CardTypeEnum.values()){
			permitedEnumValues.add(cardTypeEnum.toString());
		}
		return permitedEnumValues;
	}
}
