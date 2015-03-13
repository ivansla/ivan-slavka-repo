package my.first.game.beans;

import my.first.game.controller.BattleController;

public class MonsterSheetBean extends AbstractSheetBean implements Runnable{

	private static int CYCLE_SPEED = 100;

	private MonsterBean monster;

	public void loadMonster(MonsterBean monster){
		this.monster = monster;
		int hitPointsInterval = monster.getMaxHitPoints() - monster.getMinHitPoints();
		this.totalHitPoints = this.randomGenerator.nextInt(hitPointsInterval) + monster.getMinHitPoints();
		this.currentHitPoints = this.totalHitPoints;
		this.minDamage = monster.getMinDamage();
		this.maxDamage = monster.getMaxDamage();
		this.armorClass = monster.getArmorClass();
		this.characterName = monster.getMonsterName().toString();
		this.characterClass = monster.getMonsterType().toString();
	}

	@Override
	public int getCurrentHitPoints() {
		return this.currentHitPoints;
	}

	@Override
	public void setCurrentHitPoints(int currentHitPoints) {
		this.currentHitPoints = currentHitPoints;
	}

	@Override
	public int getTotalHitPoints() {
		return this.totalHitPoints;
	}

	@Override
	public void setTotalHitPoints(int totalHitPoints) {
		this.totalHitPoints = totalHitPoints;
	}

	@Override
	public int getMinDamage() {
		return this.minDamage;
	}

	@Override
	public void setMinDamage(int minDamage) {
		this.minDamage = minDamage;
	}

	@Override
	public int getMaxDamage() {
		return this.maxDamage;
	}

	@Override
	public void setMaxDamage(int maxDamage) {
		this.maxDamage = maxDamage;
	}

	@Override
	public int getArmorClass() {
		return this.armorClass;
	}

	@Override
	public void setArmorClass(int armorClass) {
		this.armorClass = armorClass;
	}

	public void setBattleController(BattleController battleController) {
		this.battleController = battleController;
	}

	public MonsterBean getMonster() {
		return this.monster;
	}

	public int calculateMonsterChanceToHit(){
		return (30 + this.monster.getChanceToHit() + (2 * this.monster.getMonsterLevel()));
	}

	@Override
	public void run() {
		boolean isAliveMainCharacter = true;
		long lastAttackTimeElapsed = System.currentTimeMillis();

		int actualHitPoints = this.currentHitPoints;
		while((actualHitPoints > 0) && isAliveMainCharacter){
			try {
				Thread.sleep(MonsterSheetBean.CYCLE_SPEED);
				this.lock.lock();
				actualHitPoints = this.currentHitPoints;
				this.lock.unlock();
				if(actualHitPoints > 0){

					long currentTime = System.currentTimeMillis();
					for(AbilityEffectBean abilityEffect : this.activeEffects){
						if((currentTime - abilityEffect.getLastEffectOccurenceTime()) >= abilityEffect.getAbility().getDuration()){
							abilityEffect.setLastEffectOccurenceTime(currentTime);
							abilityEffect.decreaseTicks();
							abilityEffect.applyEffect(this);

							if(abilityEffect.getNumberOfTicks() <= 0){
								this.activeEffects.remove(abilityEffect);
							}
						}
					}

					if((currentTime - lastAttackTimeElapsed) >= this.getMonster().getAttackSpeed()){
						lastAttackTimeElapsed = System.currentTimeMillis();
						isAliveMainCharacter = this.battleController.performMonsterAttack(this);
					}
				}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
