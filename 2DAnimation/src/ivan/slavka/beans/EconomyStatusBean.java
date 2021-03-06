package ivan.slavka.beans;

import ivan.slavka.interfaces.IWonderConstruction;
import android.util.Log;

public class EconomyStatusBean {

	private static float WOOD_MULTIPLICATOR = 0.5f;
	private static float STONE_MULTIPLICATOR = 0.25f;
	private static float FOOD_MULTIPLICATOR = 0.7f;
	private static float FOOD_CONSUMPTION = 0.2f;

	private static int FOOD_SHORTAGE_TOLERANCE = 3;
	private static int NUMBER_OF_WORKER_TYPES = 5;

	private int turnsWithoutFood = 0;

	private float coins = 0f;
	private float foodStorage = 10;
	private float woodIncome;
	private int woodWorkers = 1;
	private float stoneIncome;
	private int stoneWorkers = 1;
	private float foodIncome;
	private int foodWorkers = 1;
	private int soldiers = 0;
	private int builders = 0;

	private IWonderConstruction wonderConstructionController;

	public EconomyStatusBean(IWonderConstruction wonderConstructionController){
		this.wonderConstructionController = wonderConstructionController;
		this.updateEconomyStatus();
	}

	public float getWoodIncome() {
		return this.woodIncome;
	}

	public void setWoodIncome(float woodIncome) {
		this.woodIncome = woodIncome;
	}

	public int getWoodWorkers() {
		return this.woodWorkers;
	}

	public void setWoodWorkers(int woodWorkers) {
		this.woodWorkers = woodWorkers;
	}

	public float getStoneIncome() {
		return this.stoneIncome;
	}

	public void setStoneIncome(float stoneIncome) {
		this.stoneIncome = stoneIncome;
	}

	public int getStoneWorkers() {
		return this.stoneWorkers;
	}

	public void setStoneWorkers(int stoneWorkers) {
		this.stoneWorkers = stoneWorkers;
	}

	public float getFoodIncome() {
		return this.foodIncome;
	}

	public void setFoodIncome(float foodIncome) {
		this.foodIncome = foodIncome;
	}

	public int getFoodWorkers() {
		return this.foodWorkers;
	}

	public void setFoodWorkers(int foodWorkers) {
		this.foodWorkers = foodWorkers;
	}

	public int getSoldiers() {
		return this.soldiers;
	}

	public void setSoldiers(int soldiers) {
		this.soldiers = soldiers;
	}

	public int getBuilders() {
		return this.builders;
	}

	public void setBuilders(int builders) {
		this.builders = builders;
	}

	public void increaseWoodWorkersBy(int numberOfWorkers){
		this.woodWorkers += numberOfWorkers;
		if(this.woodWorkers < 0){
			this.woodWorkers = 0;
		}
	}

	public void decreaseWoodWorkersBy(int numberOfWorkers){
		this.woodWorkers -= numberOfWorkers;
	}

	public void increaseStoneWorkersBy(int numberOfWorkers){
		this.stoneWorkers += numberOfWorkers;
		if(this.stoneWorkers < 0){
			this.stoneWorkers = 0;
		}
	}

	public void decreaseStoneWorkersBy(int numberOfWorkers){
		this.stoneWorkers -= numberOfWorkers;
	}

	public void increaseFoodWorkersBy(int numberOfWorkers){
		this.foodWorkers += numberOfWorkers;
		if(this.foodWorkers < 0){
			this.foodWorkers = 0;
		}
	}

	public void decreaseFoodWorkersBy(int numberOfWorkers){
		this.foodWorkers -= numberOfWorkers;
	}

	public void increaseBuildersBy(int numberOfWorkers){
		this.builders += numberOfWorkers;
		if(this.builders < 0){
			this.builders = 0;
		}
	}

	public void increaseSoldiersBy(int numberOfWorkers){
		this.soldiers += numberOfWorkers;
		if(this.soldiers < 0){
			this.soldiers = 0;
		}
	}

	public float increaseCoinsBy(float coins){
		if(coins < 0){
			if((coins * (-1)) > this.coins){
				float difference = coins + this.coins;
				this.coins += difference;
				return difference;
			} else {
				this.coins -= coins;
			}
		} else {
			this.coins += coins;
		}

		return 0;
	}

	public float getCoins(){
		return this.coins;
	}

	public float increaseFoodStored(float food){
		if(food < 0){
			if((food * (-1)) > this.foodStorage){
				float difference = food + this.foodStorage;
				this.foodStorage += difference;
				return difference;
			} else {
				this.foodStorage -= food;
			}
		} else {
			this.foodStorage += food;
		}

		return 0;
	}

	public void sellFood(ResourceBean resource){
		float quantity = resource.getQuantity();
		if(quantity < this.foodStorage){
			this.foodStorage -= quantity;
			this.coins += quantity * resource.getPrice();
		} else {
			this.coins += (int) this.foodStorage * resource.getPrice();
			this.foodStorage -= (int) this.foodStorage;
		}
	}

	public void updateEconomyStatus(){
		this.calculateResourceIncome();
		this.consumeFood();
	}

	private void consumeFood(){

		int numberOfPeople = this.foodWorkers + this.woodWorkers + this.stoneWorkers + this.builders + this.soldiers;
		this.foodIncome = (FOOD_MULTIPLICATOR * this.foodWorkers) - (numberOfPeople * FOOD_CONSUMPTION);
		this.foodStorage += this.foodIncome;

		if(this.foodStorage <= 0){
			this.turnsWithoutFood++;
			if(this.turnsWithoutFood >= FOOD_SHORTAGE_TOLERANCE){
				int peoplePerished = (int) Math.floor(this.foodStorage / FOOD_CONSUMPTION) * -1;
				Log.v("consumeFood", "peoplePerished: " + peoplePerished);
				while(peoplePerished > 0){
					int choice = peoplePerished % NUMBER_OF_WORKER_TYPES;
					switch(choice){
					case 0:
						this.soldiers--;
						if(this.soldiers < 0){
							this.soldiers = 0;
						}
						break;
					case 1:
						this.foodWorkers--;
						if(this.foodWorkers < 0){
							this.foodWorkers = 1;
						}
						break;
					case 2:
						this.stoneWorkers--;
						if(this.stoneWorkers < 0){
							this.stoneWorkers = 0;
						}
						break;
					case 3:
						this.woodWorkers--;
						if(this.woodWorkers < 0){
							this.woodWorkers = 0;
						}
						break;
					case 4:
						this.builders--;
						if(this.builders < 0){
							this.builders = 0;
						}
						break;
					}

					peoplePerished--;
				}
			}
			this.foodStorage = 0;

		} else {
			this.turnsWithoutFood = 0;
		}
	}

	private void calculateResourceIncome(){
		this.woodIncome = WOOD_MULTIPLICATOR * this.woodWorkers;
		this.stoneIncome = STONE_MULTIPLICATOR * this.stoneWorkers;
	}

	public float getFoodStorage() {
		return this.foodStorage;
	}

	public void setFoodStorage(float foodStorage) {
		this.foodStorage = foodStorage;
	}

	public int getTurnsWithoutFood() {
		return this.turnsWithoutFood;
	}

	public void buySoldiers(ResourceBean resource){
		float totalPrice = resource.getPrice() * resource.getQuantity();
		if(totalPrice < this.coins){
			this.soldiers += resource.getQuantity();
			this.coins -= totalPrice;
		} else {
			while(this.coins >= resource.getPrice()){
				this.soldiers++;
				this.coins -= resource.getPrice();
			}
		}
	}
}
