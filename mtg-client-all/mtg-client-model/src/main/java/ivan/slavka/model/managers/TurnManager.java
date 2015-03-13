package ivan.slavka.model.managers;

import ivan.slavka.utils.enums.CastingPermissionEnum;
import ivan.slavka.utils.enums.GamePhaseEnum;
import ivan.slavka.utils.enums.GamePhaseStepEnum;
import ivan.slavka.utils.interfaces.IApplicationTurnManagement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TurnManager {

	private static TurnManager turnManager = null;

	private GamePhaseEnum gamePhase;
	private GamePhaseStepEnum gamePhaseStep;
	private final Map<GamePhaseStepEnum, List<CastingPermissionEnum>> permissions;
	private final IApplicationTurnManagement turnManagement;

	private TurnManager(IApplicationTurnManagement turnManagement){
		this.turnManagement = turnManagement;
		this.permissions = new HashMap<GamePhaseStepEnum, List<CastingPermissionEnum>>();
		this.gamePhase = GamePhaseEnum.BEGINNING_PHASE;
		this.gamePhaseStep = GamePhaseStepEnum.UNTAP_STEP;

		this.fillPermissions();
	}

	public static TurnManager getInstance(IApplicationTurnManagement turnManagement){
		if(turnManager == null){
			turnManager = new TurnManager(turnManagement);
		}
		return turnManager;
	}

	public GamePhaseEnum getGamePhase() {
		return this.gamePhase;
	}

	public GamePhaseStepEnum getGamePhaseStep() {
		return this.gamePhaseStep;
	}

	public List<CastingPermissionEnum> getStepPermissions(){
		return this.permissions.get(this.gamePhaseStep);
	}

	public void nextStep(){
		switch(this.gamePhaseStep){
		case UNTAP_STEP:
			this.gamePhaseStep = GamePhaseStepEnum.UPKEEP_STEP;
			break;
		case UPKEEP_STEP:
			this.gamePhaseStep = GamePhaseStepEnum.DRAW_STEP;
			break;
		case DRAW_STEP:
			this.gamePhase = GamePhaseEnum.PRE_COMBAT_MAIN_PHASE;
			this.gamePhaseStep = GamePhaseStepEnum.PRE_COMBAT_MAIN_PHASE_STEP;
			break;
		case PRE_COMBAT_MAIN_PHASE_STEP:
			this.gamePhase = GamePhaseEnum.COMBAT_PHASE;
			this.gamePhaseStep = GamePhaseStepEnum.BEGINNING_OF_COMBAT_STEP;
			break;
		case BEGINNING_OF_COMBAT_STEP:
			this.gamePhaseStep = GamePhaseStepEnum.DECLARE_ATTACKERS_STEP;
			break;
		case DECLARE_ATTACKERS_STEP:
			this.gamePhaseStep = GamePhaseStepEnum.DECLARE_BLOCKERS_STEP;
			break;
		case DECLARE_BLOCKERS_STEP:
			this.gamePhaseStep = GamePhaseStepEnum.COMBAT_DAMAGE_STEP;
			break;
		case COMBAT_DAMAGE_STEP:
			this.gamePhaseStep = GamePhaseStepEnum.END_COMBAT_STEP;
			break;
		case END_COMBAT_STEP:
			this.gamePhase = GamePhaseEnum.POST_COMBAT_MAIN_PHASE;
			this.gamePhaseStep = GamePhaseStepEnum.POST_COMBAT_MAIN_PHASE_STEP;
			break;
		case POST_COMBAT_MAIN_PHASE_STEP:
			this.gamePhase = GamePhaseEnum.ENDING_PHASE;
			this.gamePhaseStep = GamePhaseStepEnum.END_STEP;
			break;
		case END_STEP:
			this.gamePhaseStep = GamePhaseStepEnum.CLEANUP_STEP;
			break;
		case CLEANUP_STEP:
			this.gamePhase = GamePhaseEnum.BEGINNING_PHASE;
			this.gamePhaseStep = GamePhaseStepEnum.UNTAP_STEP;
			this.turnManagement.changeTurnOwner();
			break;
		}
	}

	private void fillPermissions(){
		List<CastingPermissionEnum> castingPermissions;

		for(GamePhaseStepEnum stepEnum : GamePhaseStepEnum.values()){
			switch(stepEnum){
			case BEGINNING_OF_COMBAT_STEP:
				castingPermissions = new ArrayList<CastingPermissionEnum>();
				castingPermissions.add(CastingPermissionEnum.INSTANTS_AND_ABILITIES);
				this.permissions.put(stepEnum, castingPermissions);
				break;
			case CLEANUP_STEP:
				castingPermissions = new ArrayList<CastingPermissionEnum>();
				castingPermissions.add(CastingPermissionEnum.NO_CASTING);
				this.permissions.put(stepEnum, castingPermissions);
				break;
			case COMBAT_DAMAGE_STEP:
				castingPermissions = new ArrayList<CastingPermissionEnum>();
				castingPermissions.add(CastingPermissionEnum.INSTANTS_AND_ABILITIES);
				this.permissions.put(stepEnum, castingPermissions);
				break;
			case DECLARE_ATTACKERS_STEP:
				castingPermissions = new ArrayList<CastingPermissionEnum>();
				castingPermissions.add(CastingPermissionEnum.INSTANTS_AND_ABILITIES);
				this.permissions.put(stepEnum, castingPermissions);
				break;
			case DECLARE_BLOCKERS_STEP:
				castingPermissions = new ArrayList<CastingPermissionEnum>();
				castingPermissions.add(CastingPermissionEnum.INSTANTS_AND_ABILITIES);
				this.permissions.put(stepEnum, castingPermissions);
				break;
			case DRAW_STEP:
				castingPermissions = new ArrayList<CastingPermissionEnum>();
				castingPermissions.add(CastingPermissionEnum.INSTANTS_AND_ABILITIES);
				this.permissions.put(stepEnum, castingPermissions);
				break;
			case END_COMBAT_STEP:
				castingPermissions = new ArrayList<CastingPermissionEnum>();
				castingPermissions.add(CastingPermissionEnum.INSTANTS_AND_ABILITIES);
				this.permissions.put(stepEnum, castingPermissions);
				break;
			case END_STEP:
				castingPermissions = new ArrayList<CastingPermissionEnum>();
				castingPermissions.add(CastingPermissionEnum.INSTANTS_AND_ABILITIES);
				castingPermissions.add(CastingPermissionEnum.AT_THE_BEGINNING_OF_END);
				this.permissions.put(stepEnum, castingPermissions);
				break;
			case POST_COMBAT_MAIN_PHASE_STEP:
				castingPermissions = new ArrayList<CastingPermissionEnum>();
				castingPermissions.add(CastingPermissionEnum.INSTANTS_AND_ABILITIES);
				castingPermissions.add(CastingPermissionEnum.TURN_OWNER_CASTING);
				this.permissions.put(stepEnum, castingPermissions);
				break;
			case PRE_COMBAT_MAIN_PHASE_STEP:
				castingPermissions = new ArrayList<CastingPermissionEnum>();
				castingPermissions.add(CastingPermissionEnum.INSTANTS_AND_ABILITIES);
				castingPermissions.add(CastingPermissionEnum.TURN_OWNER_CASTING);
				this.permissions.put(stepEnum, castingPermissions);
				break;
			case UNTAP_STEP:
				castingPermissions = new ArrayList<CastingPermissionEnum>();
				castingPermissions.add(CastingPermissionEnum.NO_CASTING);
				this.permissions.put(stepEnum, castingPermissions);
				break;
			case UPKEEP_STEP:
				castingPermissions = new ArrayList<CastingPermissionEnum>();
				castingPermissions.add(CastingPermissionEnum.INSTANTS_AND_ABILITIES);
				castingPermissions.add(CastingPermissionEnum.AT_THE_BEGINNING_OF_UPKEEP);
				this.permissions.put(stepEnum, castingPermissions);
				break;
			}
		}
	}
}
