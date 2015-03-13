package ivan.slavka.utils.enums;

import ivan.slavka.utils.exceptions.ParseEnumException;

import java.util.ArrayList;
import java.util.List;

public enum PlayerEnum {

	PLAYER,
	OPPONENT;

	public static PlayerEnum parse(String player) throws ParseEnumException{
		if(player.equalsIgnoreCase("player")){
			return PLAYER;
		} else if(player.equalsIgnoreCase("opponent")){
			return OPPONENT;
		} else {
			throw new ParseEnumException(getEnumValues());
		}
	}

	private static List<String> getEnumValues(){
		List<String> permitedEnumValues = new ArrayList<String>();
		for(PlayerEnum playerEnum : PlayerEnum.values()){
			permitedEnumValues.add(playerEnum.toString());
		}
		return permitedEnumValues;
	}
}
