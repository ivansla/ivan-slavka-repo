package ivan.slavka.utils.enums;

import ivan.slavka.utils.exceptions.ParseEnumException;

import java.util.ArrayList;
import java.util.List;

public enum GamePhaseStepEnum {

	UNTAP_STEP,
	UPKEEP_STEP,
	DRAW_STEP,
	BEGINNING_OF_COMBAT_STEP,
	DECLARE_ATTACKERS_STEP,
	DECLARE_BLOCKERS_STEP,
	COMBAT_DAMAGE_STEP,
	END_COMBAT_STEP,
	END_STEP,
	CLEANUP_STEP,
	PRE_COMBAT_MAIN_PHASE_STEP,
	POST_COMBAT_MAIN_PHASE_STEP;

	public static GamePhaseStepEnum parse(String phase) throws ParseEnumException{
		if(phase.equalsIgnoreCase("untapStep")){
			return UNTAP_STEP;
		} else if(phase.equalsIgnoreCase("upkeepStep")){
			return UPKEEP_STEP;
		}else if(phase.equalsIgnoreCase("drawStep")){
			return DRAW_STEP;
		}else if(phase.equalsIgnoreCase("beginningOfCombatStep")){
			return BEGINNING_OF_COMBAT_STEP;
		}else if(phase.equalsIgnoreCase("declareAttackersStep")){
			return DECLARE_ATTACKERS_STEP;
		}else if(phase.equalsIgnoreCase("declareBlockers")){
			return DECLARE_BLOCKERS_STEP;
		}else if(phase.equalsIgnoreCase("combatDamageStep")){
			return COMBAT_DAMAGE_STEP;
		}else if(phase.equalsIgnoreCase("endCombatStep")){
			return END_COMBAT_STEP;
		}else if(phase.equalsIgnoreCase("endStep")){
			return END_STEP;
		}else if(phase.equalsIgnoreCase("cleanupStep")){
			return CLEANUP_STEP;
		}else if(phase.equalsIgnoreCase("preCombatMainPhaseStep")){
			return PRE_COMBAT_MAIN_PHASE_STEP;
		}else if(phase.equalsIgnoreCase("postCombatMainPhaseStep")){
			return POST_COMBAT_MAIN_PHASE_STEP;
		}else {
			throw new ParseEnumException(getEnumValues());
		}
	}

	private static List<String> getEnumValues(){
		List<String> permitedEnumValues = new ArrayList<String>();
		for(GamePhaseStepEnum gamePhaseStepEnum : GamePhaseStepEnum.values()){
			permitedEnumValues.add(gamePhaseStepEnum.toString());
		}
		return permitedEnumValues;
	}
}
