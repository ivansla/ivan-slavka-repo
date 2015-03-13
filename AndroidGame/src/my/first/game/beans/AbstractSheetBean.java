package my.first.game.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import my.first.game.controller.BattleController;
import my.first.game.utils.GenericRandomGenerator;

public abstract class AbstractSheetBean {
	protected Random randomGenerator = GenericRandomGenerator.getInstance();

	protected Lock lock = new ReentrantLock();

	protected BattleController battleController = BattleController.getInstance();
	protected List<AbilityEffectBean> activeEffects = new ArrayList<AbilityEffectBean>();
	protected int currentHitPoints;
	protected int totalHitPoints;
	protected int minDamage;
	protected int maxDamage;
	protected int armorClass;
	protected int level;
	protected int totalMana = 0;
	protected int currentMana = 0;
	protected String characterName;
	protected String characterClass;
	protected boolean isAlive;

	public boolean sustainDamage(int damage){
		int actualHealth;
		this.lock.lock();
		this.currentHitPoints -= damage;
		if(this.currentHitPoints < 0){
			this.currentHitPoints = 0;
		}

		actualHealth = this.currentHitPoints;
		this.lock.unlock();
		this.isAlive = (actualHealth <= 0) ? false : true;
		return this.isAlive;
	}

	public void assignEffect(AbilityEffectBean abilityEffectBean){
		abilityEffectBean.setLastEffectOccurenceTime(System.currentTimeMillis());
		this.activeEffects.add(abilityEffectBean);
	}

	public void postMessage(String message){
		this.battleController.logBattleEvent(message);
	}

	public Lock getLock() {
		return this.lock;
	}
	public void setLock(Lock lock) {
		this.lock = lock;
	}
	public int getCurrentHitPoints() {
		return this.currentHitPoints;
	}
	public void setCurrentHitPoints(int currentHitPoints) {
		this.currentHitPoints = currentHitPoints;
	}
	public int getTotalHitPoints() {
		return this.totalHitPoints;
	}
	public void setTotalHitPoints(int totalHitPoints) {
		this.totalHitPoints = totalHitPoints;
	}
	public int getMinDamage() {
		return this.minDamage;
	}
	public void setMinDamage(int minDamage) {
		this.minDamage = minDamage;
	}
	public int getMaxDamage() {
		return this.maxDamage;
	}
	public void setMaxDamage(int maxDamage) {
		this.maxDamage = maxDamage;
	}
	public int getArmorClass() {
		return this.armorClass;
	}
	public void setArmorClass(int armorClass) {
		this.armorClass = armorClass;
	}
	public int getLevel() {
		return this.level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getTotalMana() {
		return this.totalMana;
	}
	public void setTotalMana(int totalMana) {
		this.totalMana = totalMana;
	}
	public int getCurrentMana() {
		return this.currentMana;
	}
	public void setCurrentMana(int currentMana) {
		this.currentMana = currentMana;
	}
	public String getCharacterName() {
		return this.characterName;
	}
	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}
	public String getCharacterClass() {
		return this.characterClass;
	}
	public void setCharacterClass(String characterClass) {
		this.characterClass = characterClass;
	}

	public boolean isAlive() {
		return this.isAlive;
	}

	public void setAlive(boolean isAlive) {
		this.isAlive = isAlive;
	}
}
