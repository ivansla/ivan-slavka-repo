package ivan.slavka.utils.enums;

import ivan.slavka.utils.exceptions.ParseEnumException;

import java.util.ArrayList;
import java.util.List;

public enum CastingPermissionEnum {

	NO_CASTING,
	INSTANTS_AND_ABILITIES,
	AT_THE_BEGINNING_OF_UPKEEP,
	AT_THE_BEGINNING_OF_END,
	TURN_OWNER_CASTING;

	public static CastingPermissionEnum parse(String phase) throws ParseEnumException{
		if(phase.equalsIgnoreCase("noCasting")){
			return NO_CASTING;
		} else if(phase.equalsIgnoreCase("instantsAndAbilities")){
			return INSTANTS_AND_ABILITIES;
		}else if(phase.equalsIgnoreCase("atTheBeginningOfUpkeep")){
			return AT_THE_BEGINNING_OF_UPKEEP;
		}else if(phase.equalsIgnoreCase("atTheBeginningOfEnd")){
			return AT_THE_BEGINNING_OF_END;
		}else if(phase.equalsIgnoreCase("turnOwnerCasting")){
			return TURN_OWNER_CASTING;
		}else {
			throw new ParseEnumException(getEnumValues());
		}
	}

	private static List<String> getEnumValues(){
		List<String> permitedEnumValues = new ArrayList<String>();
		for(CastingPermissionEnum castingPermissionEnum : CastingPermissionEnum.values()){
			permitedEnumValues.add(castingPermissionEnum.toString());
		}
		return permitedEnumValues;
	}
}
