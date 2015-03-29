package ivan.slavka.beans;

import ivan.slavka.enums.EventSpriteEnum;

public class ResourceBean {

	private static int[] PRICE_ARRAY = null;

	private EventSpriteEnum resource;
	private float quantity;
	private int price;
	private boolean isActivated;

	public ResourceBean(){
		if(PRICE_ARRAY == null){
			PRICE_ARRAY = new int[10];
			PRICE_ARRAY[EventSpriteEnum.WOOD.getCode()] = 2;
			PRICE_ARRAY[EventSpriteEnum.FOOD.getCode()] = 1;
			PRICE_ARRAY[EventSpriteEnum.STONE.getCode()] = 4;
			PRICE_ARRAY[EventSpriteEnum.SOLDIER.getCode()] = 7;
		}

		this.isActivated = false;
	}

	public EventSpriteEnum getResource() {
		return this.resource;
	}
	public void setResource(EventSpriteEnum resource) {
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

	public void activateResource(EventSpriteEnum resource, float quantity){
		this.isActivated = true;
		this.quantity = quantity;
		this.resource = resource;
		this.price = PRICE_ARRAY[resource.getCode()];
	}
}
