package my.first.game.interfaces;

import my.first.game.enums.BattleControllerEventEnum;

public interface IBattleScreen {

	public void logBattleEvent(String message);

	public void processBattleControllerEvent(BattleControllerEventEnum battleControllerEvent);
}
