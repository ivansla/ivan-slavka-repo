package my.first.game.beans;

import java.util.HashMap;

import my.first.game.enums.AbilityEnum;
import my.first.game.enums.CharacterClassEnum;
import my.first.game.utils.inventory.InventoryManager;

public abstract class CharacterBean {

	protected static int[] experienceTable = {
		2000,4620,8040,12489,18258
	};

	protected int level;
	protected int experienceGained;
	protected String characterName;
	protected CharacterClassEnum characterType;
	protected int strength;
	protected int dexterity;
	protected int vitality;
	protected int magic;
	protected HashMap<AbilityEnum, AbilityBean> characterAbilities;
	protected LocationBean currentLocation;

	protected WeaponItemBean weapon;
	protected ArmorItemBean armor;
	protected ArmorItemBean helm;
	protected ArmorItemBean shield;

	protected InventoryManager inventory;

	public CharacterBean(){};

	public CharacterBean(String characterName, int strength, int dexterity, int vitality, int magic, CharacterClassEnum characterClass, LocationBean location){

		this.inventory = new InventoryManager(this);
		this.characterName = characterName;
		this.strength = strength;
		this.dexterity = dexterity;
		this.vitality = vitality;
		this.magic = magic;
		this.characterType = characterClass;
		this.level = 1;
		this.experienceGained = 0;
		this.currentLocation = location;
		this.characterAbilities = new HashMap<AbilityEnum, AbilityBean>();
	}

	public abstract CharacterBean copy();

	public abstract int calculateBaseDamage();

	public void copy(CharacterBean tempCharacter){
		tempCharacter.level = this.level;
		tempCharacter.experienceGained = this.experienceGained;
		tempCharacter.characterName = this.characterName;
		tempCharacter.characterType = this.characterType;
		tempCharacter.strength = this.strength;
		tempCharacter.dexterity = this.dexterity;
		tempCharacter.vitality = this.vitality;
		tempCharacter.magic = this.magic;

		tempCharacter.currentLocation = this.currentLocation;
		tempCharacter.weapon = this.weapon;
		tempCharacter.armor = this.armor;
		tempCharacter.helm = this.helm;
		tempCharacter.shield = this.shield;
	}

	public int getExperienceNeeded(int level){
		return CharacterBean.experienceTable[level - 1];
	}

	public void increaseLevel(){
		this.level++;
	}

	public int getLevel() {
		return this.level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public String getCharacterName() {
		return this.characterName;
	}

	public void setCharacterName(String characterName) {
		this.characterName = characterName;
	}

	public CharacterClassEnum getCharacterType() {
		return this.characterType;
	}

	public void setCharacterType(CharacterClassEnum characterType) {
		this.characterType = characterType;
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

	public ArmorItemBean getArmor() {
		return this.armor;
	}

	public void setArmor(EquipableItemBean armor) {

		this.armor = (ArmorItemBean) armor;
	}

	public WeaponItemBean getWeapon() {
		return this.weapon;
	}

	public void setWeapon(EquipableItemBean weapon) {
		WeaponItemBean tempWeapon = (WeaponItemBean) weapon;
		if((tempWeapon != null) && tempWeapon.isTwoHanded()){
			this.shield.setEquiped(false);
			this.shield = null;
		}

		if(this.weapon != null){
			this.weapon.setEquiped(false);
			this.weapon = null;
		}

		this.weapon = tempWeapon;
	}

	public int getExperienceGained() {
		return this.experienceGained;
	}

	public void setExperienceGained(int experienceGained) {
		this.experienceGained = experienceGained;
	}

	public LocationBean getCurrentLocation() {
		return this.currentLocation;
	}

	public void setCurrentLocation(LocationBean currentLocation) {
		this.currentLocation = currentLocation;
	}

	public ArmorItemBean getHelm() {
		return this.helm;
	}

	public void setHelm(EquipableItemBean helm) {
		this.helm = (ArmorItemBean) helm;
	}

	public ArmorItemBean getShield() {
		return this.shield;
	}

	public void setShield(EquipableItemBean shield) {
		if((this.weapon != null) && this.weapon.isTwoHanded()){
			this.weapon.setEquiped(false);
			this.weapon = null;
		}
		this.shield = (ArmorItemBean) shield;
	}

	public InventoryManager getInventory() {
		return this.inventory;
	}

	public void unequipItem(EquipableItemBean item){
		switch(item.equipableItemType){
		case ARMOR:
			this.armor = null;
			break;
		case HELM:
			this.helm = null;
			break;
		case SHIELD:
			this.shield = null;
			break;
		case AXE:
		case BOW:
		case CLUB:
		case STAFF:
		case SWORD:
			this.weapon = null;
			break;
		}
		item.setTempEquiped(false);
	}

	public void equipItem(EquipableItemBean item){
		switch(item.equipableItemType){
		case ARMOR:
			this.setArmor(item);
			break;
		case HELM:
			this.setHelm(item);
			break;
		case SHIELD:
			this.setShield(item);
			break;
		case AXE:
		case BOW:
		case CLUB:
		case STAFF:
		case SWORD:
			this.setWeapon(item);
			break;
		}
		item.setTempEquiped(true);
	}
}
