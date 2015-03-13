package ivan.slavka.utils.enums;

import ivan.slavka.utils.exceptions.ParseEnumException;

import java.util.ArrayList;
import java.util.List;

public enum GamePhaseEnum {

	BEGINNING_PHASE,
	PRE_COMBAT_MAIN_PHASE,
	COMBAT_PHASE,
	POST_COMBAT_MAIN_PHASE,
	ENDING_PHASE;

	public static GamePhaseEnum parse(String phase) throws ParseEnumException{
		if(phase.equalsIgnoreCase("beginningPhase")){
			return BEGINNING_PHASE;
		} else if(phase.equalsIgnoreCase("preCombatMainPhase")){
			return PRE_COMBAT_MAIN_PHASE;
		}else if(phase.equalsIgnoreCase("combatPhase")){
			return COMBAT_PHASE;
		}else if(phase.equalsIgnoreCase("postCombatMainPhase")){
			return POST_COMBAT_MAIN_PHASE;
		}else if(phase.equalsIgnoreCase("endingPhase")){
			return ENDING_PHASE;
		}else {
			throw new ParseEnumException(getEnumValues());
		}
	}

	private static List<String> getEnumValues(){
		List<String> permitedEnumValues = new ArrayList<String>();
		for(GamePhaseEnum gamePhaseEnum : GamePhaseEnum.values()){
			permitedEnumValues.add(gamePhaseEnum.toString());
		}
		return permitedEnumValues;
	}
}
