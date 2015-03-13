package ivan.slavka.utils.enums;

import ivan.slavka.utils.exceptions.ParseEnumException;

import java.util.ArrayList;
import java.util.List;

public enum CardColorEnum {

	WHITE('W'),
	BLUE('U'),
	BLACK('B'),
	RED('R'),
	GREEN('G'),
	NEUTRAL('X');

	private char code;

	private CardColorEnum(char code){
		this.code = code;
	}

	public static CardColorEnum parse(char color){
		if(color == 'W'){
			return WHITE;
		} else if(color == 'U'){
			return BLUE;
		} else if(color == 'B'){
			return BLACK;
		}else if(color == 'R'){
			return RED;
		}else if(color == 'G'){
			return GREEN;
		} else {
			return NEUTRAL;
		}
	}

	public static CardColorEnum parse(String color) throws ParseEnumException{
		if(color.equalsIgnoreCase("WHITE")){
			return WHITE;
		} else if(color.equalsIgnoreCase("BLUE")){
			return BLUE;
		} else if(color.equalsIgnoreCase("BLACK")){
			return BLACK;
		}else if(color.equalsIgnoreCase("RED")){
			return RED;
		}else if(color.equalsIgnoreCase("GREEN")){
			return GREEN;
		}else if(color.equalsIgnoreCase("NEUTRAL")){
			return NEUTRAL;
		}else {
			throw new ParseEnumException(getEnumValues());
		}
	}

	private static List<String> getEnumValues(){
		List<String> permitedEnumValues = new ArrayList<String>();
		for(CardColorEnum cardColorEnum : CardColorEnum.values()){
			permitedEnumValues.add(cardColorEnum.toString());
		}
		return permitedEnumValues;
	}
}
