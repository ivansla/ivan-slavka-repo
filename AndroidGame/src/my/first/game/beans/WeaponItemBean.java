package my.first.game.beans;

import my.first.game.enums.EquipableItemEnum;
import my.first.game.enums.WeaponTypeEnum;

public class WeaponItemBean extends EquipableItemBean{

	private int minDamage;
	private int maxDamage;
	private int attackSpeed;
	private int toHit;
	private boolean isTwoHanded;

	public WeaponItemBean(){}

	public WeaponItemBean(WeaponTypeEnum weaponType){
		super();

		this.isTwoHanded = false;

		switch (weaponType) {
		case DAGGER:
			this.equipableItemType = EquipableItemEnum.SWORD;
			this.itemName = "Dagger";
			this.qualityLevel = 1;
			this.price = 60;
			this.minDamage = 1;
			this.maxDamage = 4;
			this.toHit = 0;
			this.maxDurability = 16;
			this.requirements = "";
			break;
		case SABRE:
			this.equipableItemType = EquipableItemEnum.SWORD;
			this.itemName = "Sabre";
			this.qualityLevel = 1;
			this.price = 170;
			this.minDamage = 1;
			this.maxDamage = 8;
			this.toHit = 0;
			this.maxDurability = 45;
			this.requirements = "STR;17";
			break;
		case SHORT_SWORD:
			this.equipableItemType = EquipableItemEnum.SWORD;
			this.itemName = "Short Sword";
			this.qualityLevel = 1;
			this.price = 120;
			this.minDamage = 2;
			this.maxDamage = 6;
			this.toHit = 0;
			this.maxDurability = 24;
			this.requirements = "STR;18";
			break;
		case SCIMITAR:
			this.equipableItemType = EquipableItemEnum.SWORD;
			this.itemName = "Scimitar";
			this.qualityLevel = 4;
			this.price = 200;
			this.minDamage = 3;
			this.maxDamage = 7;
			this.toHit = 0;
			this.maxDurability = 28;
			this.requirements = "STR;23;DEX;23";
			break;
		case BLADE:
			this.equipableItemType = EquipableItemEnum.SWORD;
			this.itemName = "Blade";
			this.qualityLevel = 4;
			this.price = 280;
			this.minDamage = 3;
			this.maxDamage = 8;
			this.toHit = 0;
			this.maxDurability = 30;
			this.requirements = "STR;25;DEX;30";
			break;
		case FALCHION:
			this.equipableItemType = EquipableItemEnum.SWORD;
			this.itemName = "Falchion";
			this.qualityLevel = 2;
			this.price = 250;
			this.minDamage = 4;
			this.maxDamage = 8;
			this.toHit = 0;
			this.maxDurability = 20;
			this.requirements = "STR;30";
			break;
		case LONG_SWORD:
			this.equipableItemType = EquipableItemEnum.SWORD;
			this.itemName = "Long Sword";
			this.qualityLevel = 6;
			this.price = 350;
			this.minDamage = 2;
			this.maxDamage = 10;
			this.toHit = 0;
			this.maxDurability = 40;
			this.requirements = "STR;30;DEX;30";
			break;
		case CLAYMORE:
			this.equipableItemType = EquipableItemEnum.SWORD;
			this.itemName = "Claymore";
			this.qualityLevel = 5;
			this.price = 450;
			this.minDamage = 1;
			this.maxDamage = 12;
			this.toHit = 0;
			this.maxDurability = 36;
			this.requirements = "STR;35";
			break;
		case BROAD_SWORD:
			this.equipableItemType = EquipableItemEnum.SWORD;
			this.itemName = "Broad Sword";
			this.qualityLevel = 8;
			this.price = 750;
			this.minDamage = 4;
			this.maxDamage = 12;
			this.toHit = 0;
			this.maxDurability = 50;
			this.requirements = "STR;40";
			break;
		case BASTARD_SWORD:
			this.equipableItemType = EquipableItemEnum.SWORD;
			this.itemName = "Bastard Sword";
			this.qualityLevel = 10;
			this.price = 1000;
			this.minDamage = 6;
			this.maxDamage = 15;
			this.toHit = 0;
			this.maxDurability = 60;
			this.requirements = "STR;50";
			break;
		case TWO_HANDED_SWORD:
			this.equipableItemType = EquipableItemEnum.SWORD;
			this.itemName = "Two-Handed Sword";
			this.qualityLevel = 14;
			this.price = 1800;
			this.minDamage = 8;
			this.maxDamage = 16;
			this.toHit = 0;
			this.maxDurability = 75;
			this.requirements = "STR;65";
			this.isTwoHanded = true;
			break;
		case GREAT_SWORD:
			this.equipableItemType = EquipableItemEnum.SWORD;
			this.itemName = "Great Sword";
			this.qualityLevel = 17;
			this.price = 3000;
			this.minDamage = 10;
			this.maxDamage = 20;
			this.toHit = 0;
			this.maxDurability = 100;
			this.requirements = "STR;75";
			this.isTwoHanded = true;
			break;
		case SMALL_AXE:
			this.equipableItemType = EquipableItemEnum.AXE;
			this.isTwoHanded = true;
			this.itemName = "Small Axe";
			this.qualityLevel = 2;
			this.price = 150;
			this.minDamage = 2;
			this.maxDamage = 10;
			this.toHit = 0;
			this.maxDurability = 24;
			this.requirements = "";
			break;
		case AXE:
			this.equipableItemType = EquipableItemEnum.AXE;
			this.isTwoHanded = true;
			this.itemName = "Axe";
			this.qualityLevel = 4;
			this.price = 450;
			this.minDamage = 4;
			this.maxDamage = 12;
			this.toHit = 0;
			this.maxDurability = 32;
			this.requirements = "STR;22";
			break;
		case LARGE_AXE:
			this.equipableItemType = EquipableItemEnum.AXE;
			this.isTwoHanded = true;
			this.itemName = "Large Axe";
			this.qualityLevel = 6;
			this.price = 750;
			this.minDamage = 6;
			this.maxDamage = 16;
			this.toHit = 0;
			this.maxDurability = 40;
			this.requirements = "STR;30";
			break;
		case BROAD_AXE:
			this.equipableItemType = EquipableItemEnum.AXE;
			this.isTwoHanded = true;
			this.itemName = "Broad Axe";
			this.qualityLevel = 8;
			this.price = 1000;
			this.minDamage = 8;
			this.maxDamage = 20;
			this.toHit = 0;
			this.maxDurability = 50;
			this.requirements = "STR;50";
			break;
		case BATTLE_AXE:
			this.equipableItemType = EquipableItemEnum.AXE;
			this.isTwoHanded = true;
			this.itemName = "Battle Axe";
			this.qualityLevel = 10;
			this.price = 1500;
			this.minDamage = 10;
			this.maxDamage = 25;
			this.toHit = 0;
			this.maxDurability = 60;
			this.requirements = "STR;65";
			break;
		case GREAT_AXE:
			this.equipableItemType = EquipableItemEnum.AXE;
			this.isTwoHanded = true;
			this.itemName = "Great Axe";
			this.qualityLevel = 12;
			this.price = 2500;
			this.minDamage = 12;
			this.maxDamage = 30;
			this.toHit = 0;
			this.maxDurability = 75;
			this.requirements = "STR;80";
			break;
		case CLUB:
			this.equipableItemType = EquipableItemEnum.CLUB;
			this.itemName = "Club";
			this.qualityLevel = 1;
			this.price = 20;
			this.minDamage = 1;
			this.maxDamage = 6;
			this.toHit = 0;
			this.maxDurability = 20;
			this.requirements = "";
			break;
		case SPIKED_CLUB:
			this.equipableItemType = EquipableItemEnum.CLUB;
			this.itemName = "Spiked Club";
			this.qualityLevel = 4;
			this.price = 225;
			this.minDamage = 3;
			this.maxDamage = 6;
			this.toHit = 0;
			this.maxDurability = 20;
			this.requirements = "STR;18";
			break;
		case MACE:
			this.equipableItemType = EquipableItemEnum.CLUB;
			this.itemName = "Mace";
			this.qualityLevel = 2;
			this.price = 200;
			this.minDamage = 1;
			this.maxDamage = 8;
			this.toHit = 0;
			this.maxDurability = 32;
			this.requirements = "STR;16";
			break;
		case MORNING_STAR:
			this.equipableItemType = EquipableItemEnum.CLUB;
			this.itemName = "Morning Star";
			this.qualityLevel = 3;
			this.price = 300;
			this.minDamage = 1;
			this.maxDamage = 10;
			this.toHit = 0;
			this.maxDurability = 40;
			this.requirements = "STR;26";
			break;
		case FLAIL:
			this.equipableItemType = EquipableItemEnum.CLUB;
			this.itemName = "Flail";
			this.qualityLevel = 7;
			this.price = 500;
			this.minDamage = 2;
			this.maxDamage = 12;
			this.toHit = 0;
			this.maxDurability = 36;
			this.requirements = "STR;30";
			break;
		case WAR_HAMMER:
			this.equipableItemType = EquipableItemEnum.CLUB;
			this.itemName = "War Hammer";
			this.qualityLevel = 5;
			this.price = 600;
			this.minDamage = 5;
			this.maxDamage = 9;
			this.toHit = 0;
			this.maxDurability = 50;
			this.requirements = "STR;40";
			break;
		case MAUL:
			this.equipableItemType = EquipableItemEnum.CLUB;
			this.isTwoHanded = true;
			this.itemName = "Maul";
			this.qualityLevel = 10;
			this.price = 900;
			this.minDamage = 6;
			this.maxDamage = 20;
			this.toHit = 0;
			this.maxDurability = 50;
			this.requirements = "STR;55";
			break;
		case SHORT_STAFF:
			this.equipableItemType = EquipableItemEnum.STAFF;
			this.isTwoHanded = true;
			this.itemName = "Short Staff";
			this.qualityLevel = 1;
			this.price = 30;
			this.minDamage = 2;
			this.maxDamage = 4;
			this.toHit = 0;
			this.maxDurability = 25;
			this.requirements = "";
			break;
		case LONG_STAFF:
			this.equipableItemType = EquipableItemEnum.STAFF;
			this.isTwoHanded = true;
			this.itemName = "Long Staff";
			this.qualityLevel = 4;
			this.price = 100;
			this.minDamage = 4;
			this.maxDamage = 8;
			this.toHit = 0;
			this.maxDurability = 35;
			this.requirements = "";
			break;
		case COMPOSITE_STAFF:
			this.equipableItemType = EquipableItemEnum.STAFF;
			this.isTwoHanded = true;
			this.itemName = "Composite Staff";
			this.qualityLevel = 6;
			this.price = 500;
			this.minDamage = 5;
			this.maxDamage = 10;
			this.toHit = 0;
			this.maxDurability = 45;
			this.requirements = "";
			break;
		case QUARTER_STAFF:
			this.equipableItemType = EquipableItemEnum.STAFF;
			this.isTwoHanded = true;
			this.itemName = "Quarter Staff";
			this.qualityLevel = 9;
			this.price = 1000;
			this.minDamage = 6;
			this.maxDamage = 12;
			this.toHit = 0;
			this.maxDurability = 55;
			this.requirements = "STR;20";
			break;
		case WAR_STAFF:
			this.equipableItemType = EquipableItemEnum.STAFF;
			this.isTwoHanded = true;
			this.itemName = "War Staff";
			this.qualityLevel = 12;
			this.price = 1500;
			this.minDamage = 8;
			this.maxDamage = 16;
			this.toHit = 0;
			this.maxDurability = 75;
			this.requirements = "STR;30";
			break;
			/*
		default:
			this.itemName = "Dagger";
			this.qualityLevel = 1;
			this.price = 60;
			this.minDamage = 1;
			this.maxDamage = 4;
			this.toHit = 0;
			break;
			 */
		}
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

	public int getAttackSpeed() {
		return this.attackSpeed;
	}

	public void setAttackSpeed(int attackSpeed) {
		this.attackSpeed = attackSpeed;
	}

	public int getToHit() {
		return this.toHit;
	}

	public void setToHit(int toHit) {
		this.toHit = toHit;
	}

	public WeaponItemBean copy(){
		WeaponItemBean item = new WeaponItemBean();
		item = (WeaponItemBean) super.copy(item);
		item.minDamage = this.minDamage;
		item.maxDamage = this.maxDamage;
		item.attackSpeed = this.attackSpeed;
		item.toHit = this.toHit;
		item.isTwoHanded = this.isTwoHanded;

		return item;
	}

	public boolean isTwoHanded() {
		return this.isTwoHanded;
	}

	public void setTwoHanded(boolean isTwoHanded) {
		this.isTwoHanded = isTwoHanded;
	}
}
