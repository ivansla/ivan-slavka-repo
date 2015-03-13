package my.first.game.utils.inventory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import my.first.game.beans.CharacterBean;
import my.first.game.beans.ItemBean;
import my.first.game.wrappers.InventoryItemWrapper;

public class InventoryManager {

	private final CharacterBean character;

	private List<String> tempInventoryList;
	private HashMap<String, InventoryItemWrapper> inventory;

	public InventoryManager(CharacterBean character){
		this.character = character;
		this.tempInventoryList = new ArrayList<String>();
		this.inventory = new HashMap<String, InventoryItemWrapper>();
	}

	public void addItemToInventory(ItemBean item){
		if(this.inventory.containsKey(item.getItemName())){
			this.inventory.get(item.getItemName()).increaseQuantity();
		} else {
			this.inventory.put(item.getItemName(), new InventoryItemWrapper(item));
		}
	}

	public void removeItemFromInventory(ItemBean item){
		if(this.inventory.containsKey(item.getItemName())){
			InventoryItemWrapper inventoryItem = this.inventory.get(item.getItemName());
			if(inventoryItem.getQuantity() > 0){
				inventoryItem.decreaseQuantity();
			}
		}
	}

	public List<String> getActualInventory(){
		this.tempInventoryList.clear();

		InventoryItemWrapper inventoryItem;
		for(String itemName : this.inventory.keySet()){
			inventoryItem = this.inventory.get(itemName);
			if(inventoryItem.getQuantity() > 0){
				this.tempInventoryList.add(itemName);
			}
		}

		return this.tempInventoryList;
	}

	public ItemBean getItemFromInventory(String itemName){
		InventoryItemWrapper inventoryItem = this.inventory.get(itemName);
		if(inventoryItem.getQuantity() > 0){
			return inventoryItem.getItem();
		}
		return null;
	}

	public void equipItemsInInventory(){
		for(InventoryItemWrapper item : this.inventory.values()){
			item.equipItem(this.character);
		}
	}

	public void resetTempEquipItemsInInventory(){
		for(InventoryItemWrapper item : this.inventory.values()){
			item.resetTempEquipItem();
		}
	}
}
