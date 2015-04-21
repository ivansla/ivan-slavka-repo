package ivan.slavka.interfaces;

import ivan.slavka.enums.InputControlEnum;


public interface IEconomyProgress extends IWonderConstruction{

	public float getFoodIncome();
	public float getFoodStored();
	public int getTurnsWithoutFood();
	public float getWoodIncome();
	public float getStoneIncome();
	public int getFoodWorkers();
	public int getWoodWorkers();
	public int getStoneWorkers();
	public int getSoldiers();
	public float getCoins();
	public void increaseNumberOfSoldiers(int numberOfSoldiers);
	public int getLevel();
	public int getTotalPopulation();
	public float getFoodConsumption();

	public void processEvent(IEvent event, InputControlEnum input);
}
