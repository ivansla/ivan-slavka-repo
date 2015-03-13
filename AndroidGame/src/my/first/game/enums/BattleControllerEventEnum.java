package my.first.game.enums;

public enum BattleControllerEventEnum {

	BATTLE_LOG(0),
	MONSTER_DIED(1);

	private int value;

	private BattleControllerEventEnum(int value){
		this.value = value;
	}
}
