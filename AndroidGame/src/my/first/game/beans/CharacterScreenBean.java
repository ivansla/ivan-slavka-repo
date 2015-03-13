package my.first.game.beans;

import java.util.HashMap;
import java.util.StringTokenizer;

import my.first.game.enums.AbilityEnum;


public class CharacterScreenBean extends AbstractSheetBean{

	private CharacterBean characterBean;
	private int experienceGained;
	private int experienceNeeded;
	private int strength;
	private int dexterity;
	private int vitality;
	private int magic;
	private int chanceToHit;
	private int baseDamage;

	private ItemPropertyLayerBean itemPropertyLayer;

	private boolean bGainedLevel = false;

	public CharacterScreenBean(CharacterBean characterBean) {
		super();
		this.characterBean = characterBean;
		this.characterName = characterBean.getCharacterName();
		this.characterClass = characterBean.getCharacterType().toString();

		this.itemPropertyLayer = new ItemPropertyLayerBean();

		this.loadCharacterScreenData();

		this.currentHitPoints = this.totalHitPoints;
		this.currentMana = this.totalMana;
	}

	private void loadCharacterScreenData() {
		this.createItemPropertyLayer();

		this.level = this.characterBean.getLevel();
		this.experienceGained = this.characterBean.getExperienceGained();
		this.experienceNeeded = this.characterBean.getExperienceNeeded(this.level);

		// Base character attributes
		this.strength = this.characterBean.getStrength();
		this.dexterity = this.characterBean.getDexterity();
		this.vitality = this.characterBean.getVitality();
		this.magic = this.characterBean.getMagic();

		this.totalHitPoints = this.calculateTotalHealth();
		this.totalMana = this.calculateTotalMana();

		// Character attributes from items
		this.strength += this.itemPropertyLayer.getStrength();
		this.dexterity += this.itemPropertyLayer.getDexterity();
		this.vitality += this.itemPropertyLayer.getVitality();
		this.magic += this.itemPropertyLayer.getMagic();

		this.chanceToHit = this.calculateChanceToHitOnScreen();
		this.armorClass = this.calculateArmorClass();

		this.setDamage();
	}

	private void createItemPropertyLayer(){

		this.itemPropertyLayer.resetItemPropertyLayer();

		if((this.characterBean.getHelm() != null)){
			this.itemPropertyLayer.assignItemMagicAttributes(this.characterBean.getHelm());
		}

		if((this.characterBean.getArmor() != null)){
			this.itemPropertyLayer.assignItemMagicAttributes(this.characterBean.getArmor());
		}

		if((this.characterBean.getShield() != null)){
			this.itemPropertyLayer.assignItemMagicAttributes(this.characterBean.getShield());
		}

		if((this.characterBean.getWeapon() != null)){
			this.itemPropertyLayer.assignItemMagicAttributes(this.characterBean.getWeapon());
		}
	}

	private void setDamage(){
		this.minDamage = this.calculateBaseDamage() + this.itemPropertyLayer.getMinDamage();
		this.maxDamage = this.calculateBaseDamage() + this.itemPropertyLayer.getMaxDamage();
	}

	public int calculateBaseDamage(){
		switch(this.characterBean.characterType){
		case WARRIOR:
			this.baseDamage = ((WarriorBean)this.characterBean).calculateBaseDamage();
			break;
		default:
			this.baseDamage = ((WarriorBean)this.characterBean).calculateBaseDamage();
			break;
		}

		return this.baseDamage;
	}

	private int calculateDamage(){
		int finalDamage = 0;

		int damageInterval = this.characterBean.getWeapon().getMaxDamage() - this.characterBean.getWeapon().getMinDamage();
		int weaponDamage = (this.randomGenerator.nextInt(damageInterval) + 1) + this.characterBean.getWeapon().getMinDamage();

		switch(this.characterBean.characterType){
		case WARRIOR:
			finalDamage = weaponDamage + ((WarriorBean)this.characterBean).calculateBaseDamage();
			break;
		default:
			break;
		}

		return finalDamage;
	}

	private int calculateArmorClass() {
		return ((this.dexterity / 5) + this.itemPropertyLayer.getArmorClass());
	}

	private int calculateChanceToHitOnScreen() {
		return (50 + (this.dexterity / 2) + this.itemPropertyLayer.getToHit());
	}

	private int calculateTotalHealth() {
		int totalHealth = 0;
		switch (this.characterBean.characterType) {
		case WARRIOR:
			totalHealth = (2 * this.vitality) + (2 * this.level) + 18 + (2 * this.itemPropertyLayer.getVitality());
			break;
		default:
			break;
		}

		totalHealth += this.itemPropertyLayer.getLife();

		return totalHealth;
	}

	private int calculateTotalMana() {
		int totalMana = 0;
		switch (this.characterBean.characterType) {
		case WARRIOR:
			totalMana = (((1 * this.magic) + (1 * this.level)) - 1) + (1 * this.itemPropertyLayer.getMagic());
			break;
		default:
			break;
		}

		totalMana += this.itemPropertyLayer.getMana();

		return totalMana;
	}

	public int calculateCharacterDefenseRating(){
		return ((this.dexterity / 5) + this.calculateArmorClass() + (2 * this.level));
	}

	public void addExperience(int experience){
		this.experienceGained += experience;
		this.characterBean.setExperienceGained(this.experienceGained);
		if(this.experienceGained >= this.experienceNeeded){
			this.characterBean.increaseLevel();
			this.bGainedLevel = true;
		}
	}

	public CharacterScreenBean copy(){
		CharacterScreenBean tempCharacterScreen = new CharacterScreenBean(this.getCharacterBean().copy());
		return tempCharacterScreen;
	}

	public void changeLocation(LocationBean location){
		this.characterBean.setCurrentLocation(location);
	}

	public HashMap<AbilityEnum, AbilityBean> getCharacterAbilities(){
		return this.characterBean.characterAbilities;
	}

	public AbilityBean getAbility(AbilityEnum abilityName){
		// TODO: this is the place where bonuses for this ability will apply
		return this.characterBean.characterAbilities.get(abilityName);
	}

	public CharacterBean getCharacterBean() {
		return this.characterBean;
	}

	public void setCharacterBean(CharacterBean characterBean) {
		this.characterBean = characterBean;
	}

	public int getExperienceGained() {
		return this.experienceGained;
	}

	public void setExperienceGained(int experienceGained) {
		this.experienceGained = experienceGained;
	}

	public int getExperienceNeeded() {
		return this.experienceNeeded;
	}

	public void setExperienceNeeded(int experienceNeeded) {
		this.experienceNeeded = experienceNeeded;
	}

	public int getStrength() {
		return this.strength;
	}

	public void setStrength(int strength) {
		this.strength = strength;
	}

	public int getDexterity() {
		return this.dexterity;
	}

	public void setDexterity(int dexterity) {
		this.dexterity = dexterity;
	}

	public int getVitality() {
		return this.vitality;
	}

	public void setVitality(int vitality) {
		this.vitality = vitality;
	}

	public int getMagic() {
		return this.magic;
	}

	public void setMagic(int magic) {
		this.magic = magic;
	}

	public int getChanceToHit() {
		return this.chanceToHit;
	}

	public void setChanceToHit(int chanceToHit) {
		this.chanceToHit = chanceToHit;
	}

	public int getBaseDamage() {
		return this.baseDamage;
	}

	public void setBaseDamage(int baseDamage) {
		this.baseDamage = baseDamage;
	}

	public boolean isbGainedLevel() {
		return this.bGainedLevel;
	}

	public void setbGainedLevel(boolean bGainedLevel) {
		this.bGainedLevel = bGainedLevel;
	}

	/* Deprecated
	public void equipItem(EquipableItemBean item){
		item.setEquiped(true);
		this.characterBean.equipItem(item);
		this.loadCharacterScreenData();
	}

	public void unequipItem(EquipableItemBean item){
		item.setEquiped(false);
		this.characterBean.unequipItem(item);
		this.loadCharacterScreenData();
	}

	 */
	public void tempEquipItem(EquipableItemBean item){
		if(this.areItemRequirementsMet(item)){
			this.characterBean.equipItem(item);
		}
		this.loadCharacterScreenData();
	}

	public void tempUnequipItem(EquipableItemBean item){
		this.characterBean.unequipItem(item);
		this.loadCharacterScreenData();
	}
	public void equipItems(){
		this.characterBean.getInventory().equipItemsInInventory();
		this.loadCharacterScreenData();
	}
	public void resetTempEquipItems(){
		this.characterBean.getInventory().resetTempEquipItemsInInventory();
	}

	private boolean areItemRequirementsMet(EquipableItemBean item){
		StringTokenizer tokenizer = new StringTokenizer(item.getRequirements(), ";");
		while(tokenizer.hasMoreTokens()){
			String characterAttribute = tokenizer.nextToken();
			int characterAttributeValue = Integer.valueOf(tokenizer.nextToken());

			if(characterAttribute.equals("STR")){
				if(this.getStrength() < characterAttributeValue){
					return false;
				}
			}

			if(characterAttribute.equals("DEX")){
				if(this.getDexterity() < characterAttributeValue){
					return false;
				}
			}

			if(characterAttribute.equals("MAG")){
				if(this.getMagic() < characterAttributeValue){
					return false;
				}
			}

		}
		return true;
	}
}
