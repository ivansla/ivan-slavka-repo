package ivan.slavka.beans;

import ivan.slavka.enums.ResourcesEnum;

public class ResourceBean {

	private static int[] PRICE_ARRAY = null;

	private ResourcesEnum resource;
	private float quantity;
	private int price;
	private boolean isActivated;

	public ResourceBean(){
		if(PRICE_ARRAY == null){
			PRICE_ARRAY = new int[10];
			PRICE_ARRAY[ResourcesEnum.WOOD.getCode()] = 2;
			PRICE_ARRAY[ResourcesEnum.FOOD.getCode()] = 1;
			PRICE_ARRAY[ResourcesEnum.STONE.getCode()] = 4;
			PRICE_ARRAY[ResourcesEnum.SOLDIER.getCode()] = 7;
		}

		this.isActivated = false;
	}

	public ResourcesEnum getResource() {
		return this.resource;
	}
	public void setResource(ResourcesEnum resource) {
		this.resource = resource;
	}
	public float getQuantity() {
		return this.quantity;
	}
	public void setQuantity(float quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return this.price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public boolean isActivated(){
		return this.isActivated;
	}
	public void setIsActivated(boolean isActivated){
		this.isActivated = isActivated;
	}

	public void activateResource(ResourcesEnum resource, float quantity){
		this.isActivated = true;
		this.quantity = quantity;
		this.resource = resource;
		this.price = PRICE_ARRAY[resource.getCode()];
	}
}
