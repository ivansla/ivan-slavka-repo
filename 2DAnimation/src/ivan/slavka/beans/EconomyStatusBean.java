package ivan.slavka.beans;

import ivan.slavka.constants.Constants;
import ivan.slavka.interfaces.IWonderConstruction;
import ivan.slavka.utils.LoggingUtils;

public class EconomyStatusBean {

	private static int NUMBER_OF_WORKER_TYPES = 5;

	private int turnsWithoutFood = 0;

	private boolean isCivilizationDead = false;

	private float coins = 0f;
	private volatile float foodStorage = Constants.STARTING_FOOD_AMOUNT;
	private float woodIncome;
	private int woodWorkers = 0;
	private float stoneIncome;
	private int stoneWorkers = 0;
	private volatile float foodIncome;
	private int foodWorkers = 0;
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
				this.foodStorage += food;
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

	public float getFoodConsumtion(){
		int numberOfPeople = this.getTotalPopulation();
		return (numberOfPeople * Constants.FOOD_CONSUMPTION);
	}

	private int getTotalPopulation(){
		return this.foodWorkers + this.woodWorkers + this.stoneWorkers + this.builders + this.soldiers;
	}

	private void consumeFood(){

		this.foodIncome = (Constants.FOOD_MULTIPLICATOR * this.foodWorkers) - this.getFoodConsumtion();
		this.foodStorage += this.foodIncome;
		if(this.foodStorage < 0){
			this.foodStorage = 0;
		}

		LoggingUtils.log("EconomyStatusBean.consumeFood", "Food Income: " + this.foodIncome + " Food Storage:" + this.foodStorage + " Food consumption: " + this.getFoodConsumtion());

		if(this.foodStorage <= 0){
			this.turnsWithoutFood++;
			if(this.turnsWithoutFood >= Constants.FOOD_SHORTAGE_TOLERANCE){
				int peoplePerished = 0;

				if(this.getTotalPopulation() <= 10){
					this.isCivilizationDead = true;
				} else {
					peoplePerished =(int) (this.getTotalPopulation() * Constants.FAMINE_PERISH);
				}

				LoggingUtils.log("EconomyStatusBean.consumeFood", "peoplePerished: " + peoplePerished);
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
							this.foodWorkers = 0;
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
		this.woodIncome = Constants.WOOD_MULTIPLICATOR * this.woodWorkers;
		this.stoneIncome = Constants.STONE_MULTIPLICATOR * this.stoneWorkers;

		LoggingUtils.log("EconomyStatusBean.calculateResourceIncome", "Wood Income: " + this.woodIncome + " Stone Income: " + this.stoneIncome);

		//LoggingUtils.log("EconomyStatusBean.calculateResourceIncome", "Wood Income: " + this.woodIncome + " Stone Income: " + this.stoneIncome);
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

	public void restartEconomyStatus(){
		this.woodWorkers = 0;
		this.foodWorkers = 1;
		this.stoneWorkers = 0;
		this.soldiers = 0;
		this.coins = 0;
		this.builders = 0;
		this.foodStorage = Constants.STARTING_FOOD_AMOUNT;
		this.turnsWithoutFood = 0;
		this.isCivilizationDead = false;
	}

	public boolean isCivilizationDead() {
		return this.isCivilizationDead;
	}
}
