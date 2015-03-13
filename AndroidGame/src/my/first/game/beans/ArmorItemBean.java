package my.first.game.beans;

import java.util.Random;

import my.first.game.enums.ArmorTypeEnum;
import my.first.game.enums.EquipableItemEnum;
import my.first.game.utils.GenericRandomGenerator;

public class ArmorItemBean extends EquipableItemBean {

	private Random random = GenericRandomGenerator.getInstance() ;
	private int armorClass;
	private int minArmorClass;
	private int maxArmorClass;

	public ArmorItemBean(){}

	public ArmorItemBean(ArmorTypeEnum armorType){
		super();

		switch (armorType) {
		case RAGS:
			this.equipableItemType = EquipableItemEnum.ARMOR;
			this.qualityLevel = 1;
			this.price = 5;
			this.itemName = "Rags";
			//this.armorClass = this.random.nextInt(5) + 2;
			this.minArmorClass = 2;
			this.maxArmorClass = 6;
			this.maxDurability = 6;
			this.requirements = "";
			break;
		case CAPE:
			this.equipableItemType = EquipableItemEnum.ARMOR;
			this.qualityLevel = 1;
			this.price = 10;
			this.itemName = "Cape";
			//this.armorClass = this.random.nextInt(5) + 1;
			this.minArmorClass = 1;
			this.maxArmorClass = 5;
			this.maxDurability = 12;
			this.requirements = "";
			break;
		case CLOAK:
			this.equipableItemType = EquipableItemEnum.ARMOR;
			this.qualityLevel = 2;
			this.price = 40;
			this.itemName = "Cloak";
			//this.armorClass = this.random.nextInt(5) + 3;
			this.minArmorClass = 3;
			this.maxArmorClass = 7;
			this.maxDurability = 18;
			this.requirements = "";
			break;
		case ROBE:
			this.equipableItemType = EquipableItemEnum.ARMOR;
			this.qualityLevel = 3;
			this.price = 75;
			this.itemName = "Robe";
			this.minArmorClass = 4;
			this.maxArmorClass = 7;
			this.maxDurability = 24;
			this.requirements = "";
			break;
		case QUILTED_ARMOR:
			this.equipableItemType = EquipableItemEnum.ARMOR;
			this.qualityLevel = 4;
			this.price = 200;
			this.itemName = "Quilted Armor";
			this.minArmorClass = 7;
			this.maxArmorClass = 10;
			this.maxDurability = 30;
			this.requirements = "";
			break;
		case LEATHER_ARMOR:
			this.equipableItemType = EquipableItemEnum.ARMOR;
			this.qualityLevel = 6;
			this.price = 300;
			this.itemName = "Leather Armor";
			this.minArmorClass = 10;
			this.maxArmorClass = 13;
			this.maxDurability = 35;
			this.requirements = "";
			break;
		case HARD_LEATHER_ARMOR:
			this.equipableItemType = EquipableItemEnum.ARMOR;
			this.qualityLevel = 7;
			this.price = 450;
			this.itemName = "Hard Leather Armor";
			this.minArmorClass = 11;
			this.maxArmorClass = 14;
			this.maxDurability = 40;
			this.requirements = "";
			break;
		case STUDDED_LEATHER_ARMOR:
			this.equipableItemType = EquipableItemEnum.ARMOR;
			this.qualityLevel = 9;
			this.price = 700;
			this.itemName = "Studded Leather Armor";
			this.minArmorClass = 15;
			this.maxArmorClass = 17;
			this.maxDurability = 45;
			this.requirements = "20;STR";
			break;
		case RING_MAIL:
			this.equipableItemType = EquipableItemEnum.ARMOR;
			this.qualityLevel = 11;
			this.price = 900;
			this.itemName = "Ring Mail";
			this.minArmorClass = 17;
			this.maxArmorClass = 20;
			this.maxDurability = 50;
			this.requirements = "25;STR";
			break;
		case CHAIN_MAIL:
			this.equipableItemType = EquipableItemEnum.ARMOR;
			this.qualityLevel = 13;
			this.price = 1250;
			this.itemName = "Chain Mail";
			this.minArmorClass = 18;
			this.maxArmorClass = 22;
			this.maxDurability = 60;
			this.requirements = "30;STR";
			break;
		case SCALE_MAIL:
			this.equipableItemType = EquipableItemEnum.ARMOR;
			this.qualityLevel = 15;
			this.price = 2300;
			this.itemName = "Scale Mail";
			this.minArmorClass = 23;
			this.maxArmorClass = 28;
			this.maxDurability = 60;
			this.requirements = "35;STR";
			break;
		case SPLINT_MAIL:
			this.equipableItemType = EquipableItemEnum.ARMOR;
			this.qualityLevel = 17;
			this.price = 3250;
			this.itemName = "Splint Mail";
			this.minArmorClass = 30;
			this.maxArmorClass = 35;
			this.maxDurability = 65;
			this.requirements = "40;STR";
			break;
		case BREAST_PLATE:
			this.equipableItemType = EquipableItemEnum.ARMOR;
			this.qualityLevel = 16;
			this.price = 2800;
			this.itemName = "Breast Plate";
			this.minArmorClass = 20;
			this.maxArmorClass = 24;
			this.maxDurability = 80;
			this.requirements = "40;STR";
			break;
		case PLATE_MAIL:
			this.equipableItemType = EquipableItemEnum.ARMOR;
			this.qualityLevel = 19;
			this.price = 4600;
			this.itemName = "Plate Mail";
			this.minArmorClass = 42;
			this.maxArmorClass = 50;
			this.maxDurability = 75;
			this.requirements = "60;STR";
			break;
		case FIELD_PLATE:
			this.equipableItemType = EquipableItemEnum.ARMOR;
			this.qualityLevel = 21;
			this.price = 5800;
			this.itemName = "Field Plate";
			this.minArmorClass = 40;
			this.maxArmorClass = 45;
			this.maxDurability = 80;
			this.requirements = "65;STR";
			break;
		case GOTHIC_PLATE:
			this.equipableItemType = EquipableItemEnum.ARMOR;
			this.qualityLevel = 23;
			this.price = 8000;
			this.itemName = "Gothic Plate";
			this.minArmorClass = 50;
			this.maxArmorClass = 60;
			this.maxDurability = 100;
			this.requirements = "80;STR";
			break;
		case FULL_PLATE_MAIL:
			this.equipableItemType = EquipableItemEnum.ARMOR;
			this.qualityLevel = 25;
			this.price = 6500;
			this.itemName = "Full Plate Mail";
			this.minArmorClass = 60;
			this.maxArmorClass = 75;
			this.maxDurability = 90;
			this.requirements = "90;STR";
			break;
		case CAP:
			this.equipableItemType = EquipableItemEnum.HELM;
			this.qualityLevel = 1;
			this.price = 15;
			this.itemName = "Cap";
			this.minArmorClass = 1;
			this.maxArmorClass = 3;
			this.maxDurability = 15;
			this.requirements = "";
			break;
		case SKULL_CAP:
			this.equipableItemType = EquipableItemEnum.HELM;
			this.qualityLevel = 4;
			this.price = 25;
			this.itemName = "Skull Cap";
			this.minArmorClass = 2;
			this.maxArmorClass = 4;
			this.maxDurability = 20;
			this.requirements = "";
			break;
		case HELM:
			this.equipableItemType = EquipableItemEnum.HELM;
			this.qualityLevel = 8;
			this.price = 40;
			this.itemName = "Helm";
			this.minArmorClass = 4;
			this.maxArmorClass = 6;
			this.maxDurability = 30;
			this.requirements = "25;STR";
			break;
		case FULL_HELM:
			this.equipableItemType = EquipableItemEnum.HELM;
			this.qualityLevel = 12;
			this.price = 90;
			this.itemName = "Full Helm";
			this.minArmorClass = 6;
			this.maxArmorClass = 8;
			this.maxDurability = 35;
			this.requirements = "35;STR";
			break;
		case CROWN:
			this.equipableItemType = EquipableItemEnum.HELM;
			this.qualityLevel = 16;
			this.price = 200;
			this.itemName = "Crown";
			this.minArmorClass = 8;
			this.maxArmorClass = 12;
			this.maxDurability = 40;
			this.requirements = "";
			break;
		case GREAT_HELM:
			this.equipableItemType = EquipableItemEnum.HELM;
			this.qualityLevel = 20;
			this.price = 400;
			this.itemName = "Great Helm";
			this.minArmorClass = 10;
			this.maxArmorClass = 15;
			this.maxDurability = 60;
			this.requirements = "50;STR";
			break;
		case BUCKLER:
			this.equipableItemType = EquipableItemEnum.SHIELD;
			this.qualityLevel = 1;
			this.price = 30;
			this.itemName = "Buckler";
			this.minArmorClass = 1;
			this.maxArmorClass = 5;
			this.maxDurability = 16;
			this.requirements = "";
			break;
		case SMALL_SHIELD:
			this.equipableItemType = EquipableItemEnum.SHIELD;
			this.qualityLevel = 5;
			this.price = 90;
			this.itemName = "Small Shield";
			this.minArmorClass = 3;
			this.maxArmorClass = 8;
			this.maxDurability = 24;
			this.requirements = "25;STR";
			break;
		case LARGE_SHIELD:
			this.equipableItemType = EquipableItemEnum.SHIELD;
			this.qualityLevel = 9;
			this.price = 200;
			this.itemName = "Large Shield";
			this.minArmorClass = 5;
			this.maxArmorClass = 10;
			this.maxDurability = 32;
			this.requirements = "40;STR";
			break;
		case KITE_SHIELD:
			this.equipableItemType = EquipableItemEnum.SHIELD;
			this.qualityLevel = 14;
			this.price = 400;
			this.itemName = "Kite Shield";
			this.minArmorClass = 8;
			this.maxArmorClass = 15;
			this.maxDurability = 40;
			this.requirements = "50;STR";
			break;
		case GOTHIC_SHIELD:
			this.equipableItemType = EquipableItemEnum.SHIELD;
			this.qualityLevel = 23;
			this.price = 2300;
			this.itemName = "Gothic Shield";
			this.minArmorClass = 14;
			this.maxArmorClass = 18;
			this.maxDurability = 60;
			this.requirements = "80;STR";
			break;
		case TOWER_SHIELD:
			this.equipableItemType = EquipableItemEnum.SHIELD;
			this.qualityLevel = 20;
			this.price = 850;
			this.itemName = "Tower Shield";
			this.minArmorClass = 12;
			this.maxArmorClass = 20;
			this.maxDurability = 50;
			this.requirements = "60;STR";
			break;
			/*
		default:
			this.qualityLevel = 1;
			this.price = 5;
			this.itemName = "Rags";
			this.armorClass = this.random.nextInt(5) + 2;
			break;*/
		}
	}

	public int getArmorClass() {
		return this.armorClass;
	}

	public void setArmorClass(int armorClass) {
		this.armorClass = armorClass;
	}

	public int getMinArmorClass() {
		return this.minArmorClass;
	}

	public void setMinArmorClass(int minArmorClass) {
		this.minArmorClass = minArmorClass;
	}

	public int getMaxArmorClass() {
		return this.maxArmorClass;
	}

	public void setMaxArmorClass(int maxArmorClass) {
		this.maxArmorClass = maxArmorClass;
	}

	public ArmorItemBean copy(){
		ArmorItemBean item = new ArmorItemBean();
		item = (ArmorItemBean) super.copy(item);
		item.armorClass = this.random.nextInt(this.maxArmorClass - this.minArmorClass) + this.minArmorClass + 1;
		item.minArmorClass = this.minArmorClass;
		item.maxArmorClass = this.maxArmorClass;

		return item;
	}
}
