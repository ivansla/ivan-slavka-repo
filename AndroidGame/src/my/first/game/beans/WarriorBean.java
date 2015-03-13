package my.first.game.beans;

import my.first.game.enums.AbilityEnum;
import my.first.game.enums.ArmorTypeEnum;
import my.first.game.enums.CharacterClassEnum;
import my.first.game.enums.WeaponTypeEnum;
import my.first.game.utils.ItemGenerator;


public class WarriorBean extends CharacterBean {

	private ItemGenerator itemGenerator = ItemGenerator.getInstance();

	public WarriorBean(){}

	public WarriorBean(String characterName, LocationBean location){
		super(characterName, 30, 20, 25, 10, CharacterClassEnum.WARRIOR, location);

		this.armor = (ArmorItemBean)this.itemGenerator.generateBaseItem(ArmorTypeEnum.RAGS.toString());
		this.armor.setEquiped(true);

		this.weapon = (WeaponItemBean)this.itemGenerator.generateBaseItem(WeaponTypeEnum.SHORT_SWORD.toString());
		this.weapon.setEquiped(true);

		this.inventory.addItemToInventory(this.armor);
		this.inventory.addItemToInventory(this.weapon);

		// TODO: remove this when go to production
		this.inventory.addItemToInventory(this.itemGenerator.generateBaseItem(WeaponTypeEnum.BROAD_AXE.toString()));
		//this.inventory.addItemToInventory(this.itemGenerator.generateBaseItem(ArmorTypeEnum.CAP.toString()));
		//this.inventory.addItemToInventory(this.itemGenerator.generateBaseItem(ArmorTypeEnum.BUCKLER.toString()));
		//this.inventory.addItemToInventory(this.itemGenerator.generateTestItem("Titans", EquipableItemEnum.ARMOR));

		this.characterAbilities.put(AbilityEnum.FIREBALL, new AbilityBean(AbilityEnum.FIREBALL, this.magic));
		this.characterAbilities.put(AbilityEnum.FROSTBOLT, new AbilityBean(AbilityEnum.FROSTBOLT, this.magic));
		this.characterAbilities.put(AbilityEnum.IMMOLATION, new AbilityBean(AbilityEnum.IMMOLATION, this.magic));
		this.characterAbilities.put(AbilityEnum.METEOR_SHOWER, new AbilityBean(AbilityEnum.METEOR_SHOWER, this.magic));
		this.characterAbilities.put(AbilityEnum.HEAL, new AbilityBean(AbilityEnum.HEAL, this.magic));
		this.characterAbilities.put(AbilityEnum.CRIPPLE, new AbilityBean(AbilityEnum.CRIPPLE, this.magic));
	}

	@Override
	public int calculateBaseDamage() {
		int baseDamage = (this.strength * this.level)/100;
		if(baseDamage <= 0){
			baseDamage = 1;
		}
		return baseDamage;
	}

	@Override
	public WarriorBean copy() {
		WarriorBean tempWarriorBean = new WarriorBean();
		super.copy(tempWarriorBean);
		return tempWarriorBean;
	}
}
