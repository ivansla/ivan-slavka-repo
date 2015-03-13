package ivan.slavka.utils.beans;

import ivan.slavka.utils.enums.CastingPermissionEnum;
import ivan.slavka.utils.enums.GamePhaseEnum;
import ivan.slavka.utils.enums.GamePhaseStepEnum;
import ivan.slavka.utils.enums.PlayerEnum;

import java.util.ArrayList;
import java.util.List;

public class TurnStatusBean {

	private PlayerEnum turnOwner;
	private GamePhaseEnum gamePhase;
	private GamePhaseStepEnum gamePhaseStep;
	private final List<CastingPermissionEnum> permissions = new ArrayList<CastingPermissionEnum>();

	public PlayerEnum getTurnOwner() {
		return this.turnOwner;
	}
	public void setTurnOwner(PlayerEnum turnOwner) {
		this.turnOwner = turnOwner;
	}
	public GamePhaseEnum getGamePhase() {
		return this.gamePhase;
	}
	public void setGamePhase(GamePhaseEnum gamePhase) {
		this.gamePhase = gamePhase;
	}
	public GamePhaseStepEnum getGamePhaseStep() {
		return this.gamePhaseStep;
	}
	public void setGamePhaseStep(GamePhaseStepEnum gamePhaseStep) {
		this.gamePhaseStep = gamePhaseStep;
	}
	public List<CastingPermissionEnum> getPermissions() {
		return this.permissions;
	}
}
