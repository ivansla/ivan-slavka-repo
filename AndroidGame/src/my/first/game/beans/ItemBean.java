package my.first.game.beans;

public class ItemBean {

	protected String itemName;
	protected int price;
	protected int qualityLevel;
	protected boolean isEquipable;

	public ItemBean(){}

	public ItemBean(int amount){
		this.itemName = "Gold";
		this.price = amount;
		this.qualityLevel = 1;
		this.isEquipable = false;
	}

	public String getItemName() {
		return this.itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public int getPrice() {
		return this.price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getQualityLevel() {
		return this.qualityLevel;
	}
	public void setQualityLevel(int qualityLevel) {
		this.qualityLevel = qualityLevel;
	}

	public ItemBean copy(ItemBean item){
		if(item == null){
			item = new ItemBean();
		}

		item.itemName = this.itemName;
		item.price = this.price;
		item.qualityLevel = this.qualityLevel;
		item.isEquipable = this.isEquipable;

		return item;
	}

	public boolean isEquipable() {
		return this.isEquipable;
	}

	public void setEquipable(boolean isEquipable) {
		this.isEquipable = isEquipable;
	}

}