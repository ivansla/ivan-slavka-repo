package ivan.slavka.generators;

import ivan.slavka.beans.ArmyBean;
import ivan.slavka.beans.SoldierBean;
import ivan.slavka.constants.Constants;
import ivan.slavka.enums.CombatVictoryEnum;
import ivan.slavka.interfaces.IEconomyProgress;

import java.util.Random;

public class CombatSimulator {

	/*
	private static int PLAYER_SOLDIER_HITPOINTS = 45;
	private static int MAX_DAMAGE = 10;

	private static double DEFAULT_CHANCE_TO_HIT = 65;
	private static int DEFAULT_NUMBER_OF_TURNS = 20;
	 */

	private ArmyBean playerArmy, computerArmy;
	private Random random = Randomizer.getInstance();
	private CombatVictoryEnum victoryEnum;
	private boolean battleFinished = false;
	private SoldierBean[] s = new SoldierBean[1];

	private IEconomyProgress economyProgressController;

	public CombatSimulator(IEconomyProgress economyProgressController){
		this.economyProgressController = economyProgressController;
		this.playerArmy = new ArmyBean();//this.economyProgressController.getSoldiers(), PLAYER_SOLDIER_HITPOINTS);
		this.computerArmy = new ArmyBean();//numberOfEnemies);
	}

	private void initArmies(int numberOfEnemies){
		this.playerArmy.reinitializeArmy(this.economyProgressController.getSoldiers(), Constants.PLAYER_SOLDIER_HITPOINTS);
		this.computerArmy.reinitializeArmy(numberOfEnemies);
	}

	public CombatVictoryEnum performCombat(int numberOfEnemies){
		this.battleFinished = false;
		this.initArmies(numberOfEnemies);

		int currentTurn = 0;
		while(currentTurn < Constants.DEFAULT_NUMBER_OF_TURNS && !this.battleFinished){
			int initiative = this.random.nextInt(2);

			if(initiative == 0){
				this.performAttack(this.playerArmy, this.computerArmy);
				this.performAttack(this.computerArmy, this.playerArmy);

			} else {
				this.performAttack(this.computerArmy, this.playerArmy);
				this.performAttack(this.playerArmy, this.computerArmy);
			}

			if(this.playerArmy.numberOfLiveSoldiers() <= 0){
				this.victoryEnum = CombatVictoryEnum.COMPUTER;
				this.battleFinished = true;
			}

			if(this.computerArmy.numberOfLiveSoldiers() <= 0){
				this.victoryEnum = CombatVictoryEnum.PLAYER;
				this.battleFinished = true;
			}

			currentTurn++;
		}

		if(!this.battleFinished){
			this.victoryEnum = CombatVictoryEnum.EVEN;
		}

		//this.showBattleStatus();

		this.economyProgressController.increaseNumberOfSoldiers(this.playerArmy.numberOfLiveSoldiers() - this.economyProgressController.getSoldiers());
		return this.victoryEnum;
	}

	private void performAttack(ArmyBean primaryArmy, ArmyBean secondaryArmy){
		for(int i = 0; i < primaryArmy.numberOfLiveSoldiers(); i++){
			if(this.random.nextInt(100) < Constants.DEFAULT_CHANCE_TO_HIT){
				if(secondaryArmy.getNextLiveSoldier(this.s)){
					this.s[0].takeDamage(this.random.nextInt(Constants.MAX_DAMAGE) + 1);
				}
			}
		}
	}

	private void showBattleStatus(){
		System.out.println("Victorious: " + this.victoryEnum);
		System.out.println("My Army: " + this.playerArmy.numberOfLiveSoldiers());
		System.out.println("Enemy Army: " + this.computerArmy.numberOfLiveSoldiers());
	}

	public int getNumberOfEnemies(){
		return this.computerArmy.numberOfLiveSoldiers();
	}
}
