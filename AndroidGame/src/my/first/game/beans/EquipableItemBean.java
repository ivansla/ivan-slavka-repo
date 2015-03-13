package my.first.game.beans;

import my.first.game.enums.EquipableItemEnum;

public class EquipableItemBean extends ItemBean{

	protected EquipableItemEnum equipableItemType;
	protected int actualDurability;
	protected int maxDurability;
	protected String requirements;
	protected ItemPropertyBean prefix;
	protected ItemPropertyBean suffix;
	protected boolean isMagical;
	protected boolean isEquiped;
	protected boolean isTempEquiped;

	public EquipableItemBean(){
		super();
		this.isEquipable = true;
		this.isMagical = false;
		this.isEquiped = false;
		this.isTempEquiped = false;
	}

	@Override
	public ItemBean copy(ItemBean item){
		if(item == null){
			item = new EquipableItemBean();
		}
		EquipableItemBean equipableItem = (EquipableItemBean) super.copy(item);
		equipableItem.equipableItemType = this.equipableItemType;
		equipableItem.actualDurability = this.actualDurability;
		equipableItem.maxDurability = this.maxDurability;
		equipableItem.requirements = this.requirements;
		equipableItem.prefix = this.prefix;
		equipableItem.suffix = this.suffix;

		return equipableItem;
	}

	public int getActualDurability() {
		return this.actualDurability;
	}
	public void setActualDurability(int actualDurability) {
		this.actualDurability = actualDurability;
	}
	public int getMaxDurability() {
		return this.maxDurability;
	}
	public void setMaxDurability(int maxDurability) {
		this.maxDurability = maxDurability;
	}
	public String getRequirements() {
		return this.requirements;
	}
	public void setRequirements(String requirements) {
		this.requirements = requirements;
	}
	public EquipableItemEnum getEquipableItemType() {
		return this.equipableItemType;
	}
	public void setEquipableItemType(EquipableItemEnum equipableItemType) {
		this.equipableItemType = equipableItemType;
	}

	public ItemPropertyBean getPrefix() {
		return this.prefix;
	}

	public void setPrefix(ItemPropertyBean prefix) {
		this.prefix = prefix;
	}

	public ItemPropertyBean getSuffix() {
		return this.suffix;
	}

	public void setSuffix(ItemPropertyBean suffix) {
		this.suffix = suffix;
	}

	public boolean isMagical() {
		return this.isMagical;
	}

	public void setMagical(boolean isMagical) {
		this.isMagical = isMagical;
	}

	public boolean isEquiped() {
		return this.isEquiped;
	}

	public void setEquiped(boolean isEquiped) {
		this.isTempEquiped = isEquiped;
		this.isEquiped = isEquiped;
	}

	public boolean isTempEquiped() {
		return this.isTempEquiped;
	}

	public void setTempEquiped(boolean isTempEquiped) {
		this.isTempEquiped = isTempEquiped;
	}

}
