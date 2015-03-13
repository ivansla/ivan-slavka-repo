package my.first.game.wrappers;

import my.first.game.beans.CharacterBean;
import my.first.game.beans.EquipableItemBean;
import my.first.game.beans.ItemBean;

public class InventoryItemWrapper {

	private final ItemBean item;
	private int quantity;

	public InventoryItemWrapper(ItemBean item){
		this.item = item;
		this.quantity = 1;
	}

	public void increaseQuantity(){
		this.quantity++;
	}

	public void decreaseQuantity(){
		this.quantity--;
	}

	public int getQuantity(){
		return this.quantity;
	}

	public ItemBean getItem(){
		return this.item;
	}

	public void resetTempEquipItem(){
		EquipableItemBean equipableItem = (EquipableItemBean) this.item;
		equipableItem.setTempEquiped(equipableItem.isEquiped());
	}

	public void equipItem(CharacterBean character){
		if(this.item.isEquipable()){
			EquipableItemBean equipableItem = (EquipableItemBean) this.item;
			equipableItem.setEquiped(equipableItem.isTempEquiped());

			switch(equipableItem.getEquipableItemType()){
			case ARMOR:
				if(equipableItem.isEquiped()){
					character.setArmor(equipableItem);
				} else {
					character.setArmor(null);
				}
				break;
			case HELM:
				if(equipableItem.isEquiped()){
					character.setHelm(equipableItem);
				} else {
					character.setHelm(null);
				}
				break;
			case SHIELD:
				if(equipableItem.isEquiped()){
					character.setShield(equipableItem);
				} else {
					character.setShield(null);
				}
				break;
			case AXE:
			case BOW:
			case CLUB:
			case STAFF:
			case SWORD:
				if(equipableItem.isEquiped()){
					character.setWeapon(equipableItem);
				} else {
					character.setWeapon(null);
				}
				break;

			default:
				break;
			}
		}
	}
}
