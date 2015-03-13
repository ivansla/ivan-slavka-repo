package ivan.slavka.server.application.singleton;

import ivan.slavka.utils.enums.PlayerEnum;

import java.util.Random;

public class TurnDecider {

	private static TurnDecider turnDecider = null;

	private final Random randomGenerator;

	private TurnDecider(){
		this.randomGenerator = new Random();
	}

	public static TurnDecider getInstance(){
		if(turnDecider == null){
			turnDecider = new TurnDecider();
		}
		return turnDecider;
	}

	public PlayerEnum generateTurnOwner(){
		int randomNumber = this.randomGenerator.nextInt();
		return (randomNumber % 2 == 0 ? PlayerEnum.PLAYER : PlayerEnum.OPPONENT);
	}
}
