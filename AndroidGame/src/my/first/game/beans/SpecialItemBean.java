package my.first.game.beans;

import my.first.game.enums.SpecialItemTypeEnum;

public class SpecialItemBean extends ItemBean{

	public SpecialItemBean(){}

	public SpecialItemBean(SpecialItemTypeEnum itemType){
		switch(itemType){
		case HEALTH_POTION:
			this.itemName = "Potion of Healing";
			this.price = 50;
			this.qualityLevel = 1;
			break;
		case FULL_HEALTH_POTION:
			this.itemName = "Potion of Full Healing";
			this.price = 150;
			this.qualityLevel = 1;
			break;
		case MANA_POTION:
			this.itemName = "Potion of Mana";
			this.price = 50;
			this.qualityLevel = 1;
			break;
		case FULL_MANA_POTION:
			this.itemName = "Potion of Full Mana";
			this.price = 150;
			this.qualityLevel = 1;
			break;
		case REJUVENATION_POTION:
			this.itemName = "Potion of Rejuvenation";
			this.price = 120;
			this.qualityLevel = 3;
			break;
		case FULL_REJUVENATION:
			this.itemName = "Potion of Full Rejuvenation";
			this.price = 600;
			this.qualityLevel = 7;
			break;
		}
	}

	public SpecialItemBean copy(){
		SpecialItemBean item = new SpecialItemBean();
		item.itemName = this.getItemName();
		item.price = this.getPrice();
		item.qualityLevel = this.getQualityLevel();
		return item;
	}
}
