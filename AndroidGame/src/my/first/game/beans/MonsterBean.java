package my.first.game.beans;

import my.first.game.enums.MagicResistenceEnum;
import my.first.game.enums.MonsterEnum;
import my.first.game.enums.MonsterTypeEnum;

public class MonsterBean {

	private MonsterEnum monsterName;
	private MonsterTypeEnum monsterType;
	private int minDungLvl;
	private int maxDungLvl;
	private int monsterLevel;
	private int minHitPoints;
	private int maxHitPoints;
	private int armorClass;
	private int chanceToHit;
	private int minDamage;
	private int maxDamage;
	private MagicResistenceEnum frostResistence;
	private MagicResistenceEnum fireResistence;
	private MagicResistenceEnum magicResistence;
	private int baseExperience;
	private int attackSpeed;
	private int monsterSize;

	public MonsterBean(){};

	public MonsterBean(MonsterEnum monsterName, MonsterTypeEnum monsterType,
			int minDungLvl, int maxDungLvl, int monsterLvl,
			int minHitPoints, int maxHitpoints, int armorClass,
			int chanceToHit, int minDamage, int maxDamage,
			MagicResistenceEnum frostResistence, MagicResistenceEnum fireResistence,
			MagicResistenceEnum magicResistence, int baseExperience, int attackSpeed,
			int monsterSize){

		this.monsterName = monsterName;
		this.monsterType = monsterType;
		this.minDungLvl = minDungLvl;
		this.maxDungLvl = maxDungLvl;
		this.monsterLevel = monsterLvl;
		this.minHitPoints = minHitPoints;
		this.maxHitPoints = maxHitpoints;
		this.armorClass = armorClass;
		this.chanceToHit = chanceToHit;
		this.minDamage = minDamage;
		this.maxDamage = maxDamage;
		this.frostResistence = frostResistence;
		this.fireResistence = fireResistence;
		this.magicResistence = magicResistence;
		this.baseExperience = baseExperience;
		this.attackSpeed = attackSpeed;
		this.monsterSize = monsterSize;
	}

	public MonsterBean copyMonster(){
		MonsterBean newMonster = new MonsterBean();

		newMonster.monsterName = this.monsterName;
		newMonster.monsterType = this.monsterType;
		newMonster.minDungLvl = this.minDungLvl;
		newMonster.maxDungLvl = this.maxDungLvl;
		newMonster.monsterLevel = this.monsterLevel;
		newMonster.minHitPoints = this.minHitPoints;
		newMonster.maxHitPoints = this.maxHitPoints;
		newMonster.armorClass = this.armorClass;
		newMonster.chanceToHit = this.chanceToHit;
		newMonster.minDamage = this.minDamage;
		newMonster.maxDamage = this.maxDamage;
		newMonster.frostResistence = this.frostResistence;
		newMonster.fireResistence = this.fireResistence;
		newMonster.magicResistence = this.magicResistence;
		newMonster.baseExperience = this.baseExperience;
		newMonster.attackSpeed = this.attackSpeed;
		newMonster.monsterSize = this.monsterSize;

		return newMonster;
	}

	public MonsterEnum getMonsterName() {
		return this.monsterName;
	}
	public void setMonsterName(MonsterEnum monsterName) {
		this.monsterName = monsterName;
	}
	public int getMinDungLvl() {
		return this.minDungLvl;
	}
	public void setMinDungLvl(int minDungLvl) {
		this.minDungLvl = minDungLvl;
	}
	public int getMaxDungLvl() {
		return this.maxDungLvl;
	}
	public void setMaxDungLvl(int maxDungLvl) {
		this.maxDungLvl = maxDungLvl;
	}
	public int getMonsterLevel() {
		return this.monsterLevel;
	}
	public void setMonsterLevel(int monsterLevel) {
		this.monsterLevel = monsterLevel;
	}
	public int getMinHitPoints() {
		return this.minHitPoints;
	}
	public void setMinHitPoints(int minHitPoints) {
		this.minHitPoints = minHitPoints;
	}
	public int getMaxHitPoints() {
		return this.maxHitPoints;
	}
	public void setMaxHitPoints(int maxHitPoints) {
		this.maxHitPoints = maxHitPoints;
	}
	public int getArmorClass() {
		return this.armorClass;
	}
	public void setArmorClass(int armorClass) {
		this.armorClass = armorClass;
	}
	public int getChanceToHit() {
		return this.chanceToHit;
	}
	public void setChanceToHit(int chanceToHit) {
		this.chanceToHit = chanceToHit;
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
	public MagicResistenceEnum getFrostResistence() {
		return this.frostResistence;
	}
	public void setFrostResistence(MagicResistenceEnum frostResistence) {
		this.frostResistence = frostResistence;
	}
	public MagicResistenceEnum getFireResistence() {
		return this.fireResistence;
	}
	public void setFireResistence(MagicResistenceEnum fireResistence) {
		this.fireResistence = fireResistence;
	}
	public MagicResistenceEnum getMagicResistence() {
		return this.magicResistence;
	}
	public void setMagicResistence(MagicResistenceEnum magicResistence) {
		this.magicResistence = magicResistence;
	}
	public int getBaseExperience() {
		return this.baseExperience;
	}
	public void setBaseExperience(int baseExperience) {
		this.baseExperience = baseExperience;
	}

	public MonsterTypeEnum getMonsterType() {
		return this.monsterType;
	}

	public void setMonsterType(MonsterTypeEnum monsterType) {
		this.monsterType = monsterType;
	}

	public int getAttackSpeed() {
		return this.attackSpeed;
	}

	public void setAttackSpeed(int attackSpeed) {
		this.attackSpeed = attackSpeed;
	}

	public int getMonsterSize() {
		return this.monsterSize;
	}

	public void setMonsterSize(int monsterSize) {
		this.monsterSize = monsterSize;
	}
}
