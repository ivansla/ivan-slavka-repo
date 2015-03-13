package my.first.game.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import my.first.game.beans.AbilityBean;
import my.first.game.beans.AbilityEffectBean;
import my.first.game.beans.AbstractSheetBean;
import my.first.game.beans.CharacterScreenBean;
import my.first.game.beans.MonsterSheetBean;
import my.first.game.enums.AbilityEnum;
import my.first.game.enums.BattleControllerEventEnum;
import my.first.game.interfaces.IBattleScreen;
import my.first.game.utils.GenericRandomGenerator;
import my.first.game.utils.MonsterGenerator;

public class BattleController {

	private static final int MAX_MONSTERS = 5;

	private Random randomGenerator = GenericRandomGenerator.getInstance();
	private MonsterGenerator monsterGenerator = MonsterGenerator.getInstance();
	private List<AbstractSheetBean> monsterList = new ArrayList<AbstractSheetBean>();

	private static BattleController battleController = null;

	private CharacterScreenBean mainCharacter;
	//private MonsterSheetBean monster;
	private IBattleScreen battleScreen;

	private boolean isMainCharacterAlive;

	private BattleController(){}

	public static BattleController getInstance(){
		if(BattleController.battleController == null){
			BattleController.battleController = new BattleController();
		}
		return BattleController.battleController;
	}

	public void startBattle(){

		this.mainCharacter.setAlive(true);
		this.isMainCharacterAlive = true;

		int numberOfMonsters = this.randomGenerator.nextInt(BattleController.MAX_MONSTERS) + 1;
		for(int i = 0; i < numberOfMonsters; i++){

			// TODO: Toto cele treba prepravit, aby sa zbytocne nevytvaral novy objekt, mozno pouzit nejaky pool.
			MonsterSheetBean monster = new MonsterSheetBean();
			monster.loadMonster(this.monsterGenerator.generateMonster(this.mainCharacter.getLevel()));
			// end TODO

			monster.setAlive(true);
			this.monsterList.add(monster);

			// TODO: Toto tiez treba prerobit lepsie, aby sa nevytvaral zakazdym Thread, mozno vytvorit Thread pool.
			Thread monsterThread = new Thread(monster);
			monsterThread.start();
			// end TODO
		}
	}

	public synchronized boolean performMonsterAttack(MonsterSheetBean monster){

		if(this.isMainCharacterAlive){
			String battleLogMessage = "";

			// Calculate monster chance to hit
			int monsterChanceToHit = monster.calculateMonsterChanceToHit();
			int characterDefenseRating = this.mainCharacter.calculateCharacterDefenseRating();
			int finalChanceToHit = monsterChanceToHit - characterDefenseRating;

			if(finalChanceToHit < 15){
				finalChanceToHit = 15;
			}

			boolean successfullHit = (this.randomGenerator.nextInt(100) < finalChanceToHit) ? true : false;

			// TODO: Tato synchronizovana metoda ktora vracia nejaku hodnotu nie je zrovna najstastnejsie riesenie. Treba vymysliet nejaku alternativu. Zaroven bude trochu problem pri zisteny a odovzdavani info o smrti.
			// Calculate and perform damage to character

			if(successfullHit){
				int monsterDamageInterval = monster.getMaxDamage() - monster.getMinDamage();
				int monsterDamage = (this.randomGenerator.nextInt(monsterDamageInterval) + 1) + monster.getMinDamage();

				battleLogMessage = "Monster made successfull hit for " + monsterDamage + " HP";
				this.isMainCharacterAlive = this.mainCharacter.sustainDamage(monsterDamage);

				if(!this.isMainCharacterAlive){
					battleLogMessage = "You were killed!";
				}

				this.battleScreen.logBattleEvent(battleLogMessage);

				return this.isMainCharacterAlive;
			}

			battleLogMessage = "Monster has missed!";
			this.battleScreen.logBattleEvent(battleLogMessage);
		}
		return this.isMainCharacterAlive;
	}

	public synchronized boolean performPlayerAttack(AbstractSheetBean enemyBean){

		// Calculate player chancce to hit
		int finalChanceToHit = ((50 + (this.mainCharacter.getDexterity() / 2) + this.mainCharacter.getCharacterBean().getWeapon().getToHit() + this.mainCharacter.getLevel()) - enemyBean.getArmorClass());

		boolean successfullHit = (this.randomGenerator.nextInt(100) < finalChanceToHit) ? true : false;

		// Calculate and perform damage to monster
		if(successfullHit){
			int weaponDamageInterval = this.mainCharacter.getCharacterBean().getWeapon().getMaxDamage() - this.mainCharacter.getCharacterBean().getWeapon().getMinDamage();
			int characterDamage = ((this.randomGenerator.nextInt(weaponDamageInterval) + 1) + this.mainCharacter.getCharacterBean().getWeapon().getMinDamage());
			characterDamage += this.mainCharacter.calculateBaseDamage();

			this.performMonsterDamage(characterDamage, enemyBean);
			return this.isAllMonstersDead();
		}

		this.battleScreen.logBattleEvent("You missed!");
		return this.isAllMonstersDead();
	}

	public synchronized void performPlayerAbility(String abilityName, AbstractSheetBean sheetBean){
		AbilityBean ability = this.mainCharacter.getAbility(AbilityEnum.toEnum(abilityName));
		switch (ability.getType()) {
		case DEBUFF:
			this.performDebuffAbility(ability, sheetBean);
			break;
		default:
			this.performBuffAbility();
			break;
		}
	}

	private void performBuffAbility(){

	}

	private void performDebuffAbility(AbilityBean ability, AbstractSheetBean sheetBean){

		int playerBonus = 0;
		switch(this.mainCharacter.getCharacterBean().getCharacterType()){
		case MAGE:
			playerBonus = 20;
			break;
		default:
			break;
		}

		int finalChanceToHit = 50 + this.mainCharacter.getMagic() + playerBonus;
		finalChanceToHit -= 2 * sheetBean.getLevel();
		if(finalChanceToHit < 5){
			finalChanceToHit = 5;
		} else if (finalChanceToHit > 95){
			finalChanceToHit = 95;
		}

		boolean successfullHit = (this.randomGenerator.nextInt(100) < finalChanceToHit) ? true : false;

		if(!successfullHit){
			this.battleScreen.logBattleEvent("You missed!");
			return;
		}

		int damage = 0;
		switch (ability.getAbilityName()) {
		case FROSTBOLT:
			damage = this.randomGenerator.nextInt(10) + ability.getLevel() + (this.mainCharacter.getMagic() / 8) + 1;
			this.performMonsterDamage(damage, sheetBean);
			break;
		case FIREBALL:
			damage = ((this.randomGenerator.nextInt(10) + this.randomGenerator.nextInt(10) + this.mainCharacter.getLevel()) * 2) + 4;
			damage = ability.calculateRecursiveDamage(damage);
			this.performMonsterDamage(damage, sheetBean);
			break;
		case IMMOLATION:
			sheetBean.assignEffect(new AbilityEffectBean(ability, this.mainCharacter.getMagic(), this.mainCharacter.getLevel()));
			break;
		default:
			break;
		}
	}

	private void performMonsterDamage(int damage, AbstractSheetBean sheetBean){
		boolean isMonsterAlive = true;

		this.battleScreen.logBattleEvent("You hit monster for " + damage + " HP");
		isMonsterAlive = sheetBean.sustainDamage(damage);
		if(!isMonsterAlive){
			this.battleScreen.logBattleEvent("You successfully killed monster! Rejoice!");
			this.gainExperience();
			this.monsterList.remove(sheetBean);
			this.battleScreen.processBattleControllerEvent(BattleControllerEventEnum.MONSTER_DIED);
		}
	}

	public boolean isAllMonstersDead(){
		return this.monsterList.isEmpty() ? true : false;
	}

	private void gainExperience(){
		int experienceGained = (int) (((MonsterSheetBean) this.monsterList.get(0)).getMonster().getBaseExperience() * (1.0f + (0.1f * (((MonsterSheetBean) this.monsterList.get(0)).getMonster().getMonsterLevel() - this.mainCharacter.getLevel()))));
		this.mainCharacter.addExperience(experienceGained);
	}

	public void logBattleEvent(String message){
		this.battleScreen.logBattleEvent(message);
	}

	public CharacterScreenBean getMainCharacter() {
		return this.mainCharacter;
	}

	public void setMainCharacter(CharacterScreenBean mainCharacter) {
		this.mainCharacter = mainCharacter;
	}

	public IBattleScreen getBattleScreen() {
		return this.battleScreen;
	}

	public void setBattleScreen(IBattleScreen battleScreen) {
		this.battleScreen = battleScreen;
	}

	public List<AbstractSheetBean> getMonsterList() {
		return this.monsterList;
	}

	public void setMonsterList(List<AbstractSheetBean> monsterList) {
		this.monsterList = monsterList;
	}
}
