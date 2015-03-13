package my.first.game.beans;

import java.util.Random;

import my.first.game.utils.GenericRandomGenerator;

public class AbilityEffectBean {

	private Random random = GenericRandomGenerator.getInstance();

	private AbilityBean ability;
	private int magic;
	private int characterLevel;
	private long lastEffectOccurenceTime;
	private int numberOfTicks;

	public AbilityEffectBean(AbilityBean ability, int magic, int characterLevel) {
		this.ability = ability;
		this.magic = magic;
		this.characterLevel = characterLevel;
		this.numberOfTicks = ability.getTicks();
	}

	public void applyEffect(AbstractSheetBean sheetBean){
		switch(this.ability.getAbilityName()){
		case IMMOLATION:
			int damage = this.ability.calculateRecursiveDamage(2 * (this.random.nextInt(10) + this.random.nextInt(10) + this.characterLevel + 4));
			sheetBean.sustainDamage(damage);
			sheetBean.postMessage("Monster sustained " + damage + " HP damage from immolation.");
			break;
		default:
			break;
		}
	}

	public long getLastEffectOccurenceTime() {
		return this.lastEffectOccurenceTime;
	}

	public void setLastEffectOccurenceTime(long lastEffectOccurenceTime) {
		this.lastEffectOccurenceTime = lastEffectOccurenceTime;
	}

	public AbilityBean getAbility() {
		return this.ability;
	}

	public void setAbility(AbilityBean ability) {
		this.ability = ability;
	}

	public int getMagic() {
		return this.magic;
	}

	public void setMagic(int magic) {
		this.magic = magic;
	}

	public int getCharacterLevel() {
		return this.characterLevel;
	}

	public void setCharacterLevel(int characterLevel) {
		this.characterLevel = characterLevel;
	}

	public void decreaseTicks(){
		this.numberOfTicks--;
	}

	public int getNumberOfTicks(){
		return this.numberOfTicks;
	}
}
