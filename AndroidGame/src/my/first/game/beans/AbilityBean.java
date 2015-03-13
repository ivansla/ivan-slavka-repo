package my.first.game.beans;

import my.first.game.enums.AbilityDamageTypeCalculationEnum;
import my.first.game.enums.AbilityEffectEnum;
import my.first.game.enums.AbilityEnum;
import my.first.game.enums.AbilitySourceEnum;
import my.first.game.enums.AbilityTargetEnum;
import my.first.game.enums.AbilityTypeEnum;

public class AbilityBean {

	private static int ITERATION = 1;

	private int level;
	private AbilityEnum abilityName;
	private int duration;
	private int minDamage;
	private int maxDamage;
	private AbilitySourceEnum source;
	private AbilityTargetEnum target;
	private AbilityTypeEnum type;
	private int ticks;
	private int cooldown;
	private int baseManaCost;
	private AbilityDamageTypeCalculationEnum damageTypeCalculation;
	private AbilityEffectEnum effect;
	private boolean isBlockable;

	public AbilityBean(AbilityEnum ability, int magic){
		this.abilityName = ability;

		switch(ability){
		case FIREBALL:
			this.duration = 0;
			this.level = 1;
			this.minDamage = this.calculateRecursiveDamage(AbilityBean.ITERATION, 6);
			this.maxDamage = this.calculateRecursiveDamage(AbilityBean.ITERATION, 42);
			this.source = AbilitySourceEnum.FIRE;
			this.target = AbilityTargetEnum.TARGET;
			this.type = AbilityTypeEnum.DEBUFF;
			this.ticks = 0;
			this.cooldown = 2000;
			this.baseManaCost = 15;
			this.damageTypeCalculation = AbilityDamageTypeCalculationEnum.POINTS;
			this.effect = AbilityEffectEnum.HEALTH;
			this.isBlockable = true;
			break;
		case FROSTBOLT:
			this.duration = 0;
			this.level = 1;
			this.minDamage = 1 + this.level + (magic / 8);
			this.maxDamage = 10 + this.level + (magic / 8);
			this.source = AbilitySourceEnum.FROST;
			this.target = AbilityTargetEnum.TARGET;
			this.type = AbilityTypeEnum.DEBUFF;
			this.ticks = 0;
			this.cooldown = 1000;
			this.baseManaCost = 10;
			this.damageTypeCalculation = AbilityDamageTypeCalculationEnum.POINTS;
			this.effect = AbilityEffectEnum.HEALTH;
			this.isBlockable = true;
			break;
		case IMMOLATION:
			this.duration = 3000;
			this.level = 1;
			this.minDamage = 6;
			this.maxDamage = 42;
			this.source = AbilitySourceEnum.FIRE;
			this.target = AbilityTargetEnum.TARGET;
			this.type = AbilityTypeEnum.DEBUFF;
			this.ticks = 4;
			this.cooldown = 3000;
			this.baseManaCost = 20;
			this.damageTypeCalculation = AbilityDamageTypeCalculationEnum.POINTS;
			this.effect = AbilityEffectEnum.HEALTH;
			this.isBlockable = false;
			break;
		case METEOR_SHOWER:
			this.duration = 15000;
			this.level = 1;
			this.minDamage = 6;
			this.maxDamage = 42;
			this.source = AbilitySourceEnum.FIRE;
			this.target = AbilityTargetEnum.MASS;
			this.type = AbilityTypeEnum.DEBUFF;
			this.ticks = 5;
			this.cooldown = 10000;
			this.baseManaCost = 35;
			this.damageTypeCalculation = AbilityDamageTypeCalculationEnum.POINTS;
			this.effect = AbilityEffectEnum.HEALTH;
			this.isBlockable = false;
			break;
		case HEAL:
			this.duration = 0;
			this.level = 1;
			this.minDamage = 2 + this.level;
			this.maxDamage = 14 + (6 * this.level);
			this.source = AbilitySourceEnum.HOLY;
			this.target = AbilityTargetEnum.SELF;
			this.type = AbilityTypeEnum.BUFF;
			this.ticks = 0;
			this.cooldown = 10000;
			this.baseManaCost = 20;
			this.damageTypeCalculation = AbilityDamageTypeCalculationEnum.POINTS;
			this.effect = AbilityEffectEnum.HEALTH;
			this.isBlockable = false;
			break;
		case CRIPPLE:
			this.duration = 10000;
			this.level = 1;
			this.minDamage = 20;
			this.maxDamage = 20;
			this.source = AbilitySourceEnum.CURSE;
			this.target = AbilityTargetEnum.SELF;
			this.type = AbilityTypeEnum.DEBUFF;
			this.ticks = 0;
			this.cooldown = 10000;
			this.baseManaCost = 10;
			this.damageTypeCalculation = AbilityDamageTypeCalculationEnum.PERCENT;
			this.effect = AbilityEffectEnum.ARMOR;
			this.isBlockable = false;
			break;
		default:
			break;
		}
	}

	public int calculateRecursiveDamage(int param){
		return this.calculateRecursiveDamage(AbilityBean.ITERATION, param);
	}

	private int calculateRecursiveDamage(int iteration, int param){
		while(iteration <= this.level){
			param *= (9f / 8f);
			iteration++;
			this.calculateRecursiveDamage(iteration, param);
		}
		return param;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public AbilityEnum getAbilityName() {
		return this.abilityName;
	}

	public void setAbilityName(AbilityEnum abilityName) {
		this.abilityName = abilityName;
	}

	public int getDuration() {
		return this.duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
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

	public AbilitySourceEnum getSource() {
		return this.source;
	}

	public void setSource(AbilitySourceEnum source) {
		this.source = source;
	}

	public AbilityTargetEnum getTarget() {
		return this.target;
	}

	public void setTarget(AbilityTargetEnum target) {
		this.target = target;
	}

	public AbilityTypeEnum getType() {
		return this.type;
	}

	public void setType(AbilityTypeEnum type) {
		this.type = type;
	}

	public int getTicks() {
		return this.ticks;
	}

	public void setTicks(int ticks) {
		this.ticks = ticks;
	}

	public int getCooldown() {
		return this.cooldown;
	}

	public void setCooldown(int cooldown) {
		this.cooldown = cooldown;
	}

	public int getBaseManaCost() {
		return this.baseManaCost;
	}

	public void setBaseManaCost(int baseManaCost) {
		this.baseManaCost = baseManaCost;
	}

	public AbilityDamageTypeCalculationEnum getDamageTypeCalculation() {
		return this.damageTypeCalculation;
	}

	public void setDamageTypeCalculation(
			AbilityDamageTypeCalculationEnum damageTypeCalculation) {
		this.damageTypeCalculation = damageTypeCalculation;
	}

	public AbilityEffectEnum getEffect() {
		return this.effect;
	}

	public void setEffect(AbilityEffectEnum effect) {
		this.effect = effect;
	}

	public boolean isBlockable() {
		return this.isBlockable;
	}

	public void setBlockable(boolean isBlockable) {
		this.isBlockable = isBlockable;
	}
}
