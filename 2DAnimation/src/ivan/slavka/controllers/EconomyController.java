package ivan.slavka.controllers;

import ivan.slavka.beans.EconomyStatusBean;
import ivan.slavka.beans.EventEffectBean;
import ivan.slavka.beans.ResourceBean;
import ivan.slavka.beans.WonderBean;
import ivan.slavka.enums.CombatVictoryEnum;
import ivan.slavka.enums.EventBehaviorEnum;
import ivan.slavka.enums.EventTypeEnum;
import ivan.slavka.enums.InputControlEnum;
import ivan.slavka.generators.CombatSimulator;
import ivan.slavka.interfaces.IEconomyProgress;
import ivan.slavka.interfaces.IEvent;

import java.util.Random;

public class EconomyController implements IEconomyProgress{

	private static int TOWN_LOSS_MAX = 70;
	private static int VILLAGE_LOSS_MAX = 30;

	private EconomyStatusBean economyStatus;
	private WonderBean wonder;
	private CombatSimulator combatSimulator;
	private Random random = new Random();

	private EventEffectBean invasionEvent = new EventEffectBean();

	private int turnsToInvasion;

	public EconomyController(){
		this.economyStatus = new EconomyStatusBean(this);
		this.wonder = new WonderBean(this);
		this.combatSimulator = new CombatSimulator(this);

		this.resetInvasion();
	}

	// IWonderConstruction

	@Override
	public float getStoneNeeded() {
		return this.wonder.getStoneRemaining();
	}

	@Override
	public float getWoodNeeded() {
		return this.wonder.getWoodRemaining();
	}

	@Override
	public float getCompletedPct() {
		return this.wonder.getCompleted();
	}

	@Override
	public int getBuilders() {
		return this.economyStatus.getBuilders();
	}

	@Override
	public void performConstruction() {
		this.wonder.updateWonderConstruction();
	}

	@Override
	public float getStoneStored() {
		return this.wonder.getStoneStored();
	}

	@Override
	public float getWoodStored() {
		return this.wonder.getWoodStored();
	}

	// IEconomyProgress
	@Override
	public float getFoodIncome() {
		return this.economyStatus.getFoodIncome();
	}

	@Override
	public float getWoodIncome() {
		return this.economyStatus.getWoodIncome();
	}

	@Override
	public float getStoneIncome() {
		return this.economyStatus.getStoneIncome();
	}

	@Override
	public int getFoodWorkers() {
		return this.economyStatus.getFoodWorkers();
	}

	@Override
	public int getWoodWorkers() {
		return this.economyStatus.getWoodWorkers();
	}

	@Override
	public int getStoneWorkers() {
		return this.economyStatus.getStoneWorkers();
	}

	@Override
	public int getSoldiers() {
		return this.economyStatus.getSoldiers();
	}

	@Override
	public void processEvent(IEvent event, InputControlEnum input) {
		switch(input){
		case LEFT:
			this.applyEvent(event.getPrimaryEffect());
			//Log.v("processEvent", "Resource: " + event.getPrimaryResource() + " Quantity: " + event.getPrimaryQuantity());
			break;
		case RIGHT:
			this.applyEvent(event.getSecondaryEffect());
			//Log.v("processEvent", "Resource: " + event.getSecondaryResource() + " Quantity: " + event.getSecondaryQuantity());
			break;
		}

		this.economyStatus.updateEconomyStatus();
		this.wonder.increaseStoneStored(this.economyStatus.getStoneIncome());
		this.wonder.increaseWoodStored(this.economyStatus.getWoodIncome());
		this.wonder.updateWonderConstruction();

		if(this.turnsToInvasion <= 0){
			this.invasionEvent.rollAttributes(EventTypeEnum.ATTACK, this.getLevel(), -1);
			this.resetInvasion();
			this.applyEvent(this.invasionEvent);
		} else {
			this.turnsToInvasion--;
		}
	}

	private void applyEvent(EventEffectBean eventEffect){
		switch(eventEffect.getEventType()){
		case RAID:
			this.handleRaid(eventEffect);
			break;
		case RESOURCE:
			this.handleResourceEvent(eventEffect);
			break;
		case SPECIAL_EVENT:
			this.handleSpecialEvent(eventEffect);
			break;
		case WORKER:
			this.handleWorkerEvent(eventEffect);
			break;
		case ATTACK:
			this.handleInvasionEvent(eventEffect);
			break;
		}
	}

	private void handleInvasionEvent(EventEffectBean eventEffect){
		int executionMax = 80;
		int executionMin = 30;
		int enslaveMax = 50;
		int enslaveMin = 20;
		int ransackMax = 50;
		int ransackMin = 30;

		CombatVictoryEnum combatEnum = this.combatSimulator.performCombat((int) eventEffect.getEventResources()[0].getQuantity());
		switch(combatEnum){
		case COMPUTER:
			int roll = this.random.nextInt(10);
			// TODO: Implementation needs to be improved
			if(roll < 2){	// Execution
				float executionPct = (this.random.nextInt(executionMax - executionMin) + executionMin) * 0.01f;
				int execution = (int) (this.getTotalPopulation() * executionPct);
				int decrementalValue = (int) (execution * 0.25);
				this.economyStatus.increaseBuildersBy(-1 * decrementalValue);
				this.economyStatus.increaseFoodWorkersBy(-1 * decrementalValue);
				this.economyStatus.increaseWoodWorkersBy(-1 * decrementalValue);
				this.economyStatus.increaseStoneWorkersBy(-1 * decrementalValue);
			} else if(roll >= 2 && roll < 5){ // Enslavement
				float enslavePct = (this.random.nextInt(enslaveMax - enslaveMin) + enslaveMin) * 0.01f;
				int enslave = (int) (this.getTotalPopulation() * enslavePct);
				int decrementalValue = (int) (enslave * 0.25);
				this.economyStatus.increaseBuildersBy(-1 * decrementalValue);
				this.economyStatus.increaseFoodWorkersBy(-1 * decrementalValue);
				this.economyStatus.increaseWoodWorkersBy(-1 * decrementalValue);
				this.economyStatus.increaseStoneWorkersBy(-1 * decrementalValue);
			} else { // Ransack
				float ransackPct = (this.random.nextInt(ransackMax - ransackMin) + ransackMin) * 0.01f;
				float woodRansack = this.getWoodStored() * ransackPct;
				this.wonder.increaseWoodStored(-woodRansack);
				float stoneRansack = this.getStoneStored() * ransackPct;
				this.wonder.increaseStoneStored(-stoneRansack);
				float foodRansack = this.getFoodStored() * ransackPct;
				this.economyStatus.increaseFoodStored(-foodRansack);
				float coinsRansack = this.getCoins() * ransackPct;
				this.economyStatus.increaseCoinsBy(-coinsRansack);
			}
			break;
		case EVEN:
		case PLAYER:
			break;
		}
	}

	private void handleWorkerEvent(EventEffectBean eventEffect){
		ResourceBean resource = eventEffect.getEventResources()[0];
		float quantity = resource.getQuantity();

		switch(resource.getResource()){
		case WOOD_WORKER:
			this.economyStatus.increaseWoodWorkersBy((int) quantity);
			break;
		case FOOD_WORKER:
			this.economyStatus.increaseFoodWorkersBy((int) quantity);
			break;
		case STONE_WORKER:
			this.economyStatus.increaseStoneWorkersBy((int) quantity);
			break;
		case BUILDER:
			this.economyStatus.increaseBuildersBy((int) quantity);
			break;
		case SOLDIER:
			this.economyStatus.buySoldiers(resource);
			break;
		}
	}

	private void handleResourceEvent(EventEffectBean eventEffect){
		ResourceBean resource = eventEffect.getEventResources()[0];
		float quantity = resource.getQuantity();

		switch(resource.getResource()){
		case WOOD:
			if(eventEffect.getBehavior().equals(EventBehaviorEnum.SELL)){
				this.sellResource(resource);
			} else {
				this.wonder.increaseWoodStored(quantity);
			}
			break;
		case STONE:
			if(eventEffect.getBehavior().equals(EventBehaviorEnum.SELL)){
				this.sellResource(resource);
			} else {
				this.wonder.increaseStoneStored(quantity);
			}
			break;
		case FOOD:
			if(eventEffect.getBehavior().equals(EventBehaviorEnum.SELL)){
				this.economyStatus.sellFood(resource);
			} else {
				this.economyStatus.increaseFoodStored(quantity);
			}
			break;
		}
	}

	private void handleRaid(EventEffectBean eventEffect){
		int roll = this.random.nextInt(10);
		if(roll < 3){
			switch(eventEffect.getSpecialEventName()){
			case RAID_TOWN:
				roll = this.random.nextInt(TOWN_LOSS_MAX);
				break;
			case RAID_VILLAGE:
				roll = this.random.nextInt(VILLAGE_LOSS_MAX);
				break;
			}

			for(ResourceBean resource : eventEffect.getEventResources()){
				switch(resource.getResource()){
				case STONE:
					this.wonder.increaseStoneStored(resource.getQuantity());
					break;
				case COINS:
					this.economyStatus.increaseCoinsBy(resource.getQuantity());
					break;
				case WOOD:
					this.wonder.increaseWoodStored(resource.getQuantity());
					break;
				case FOOD:
					this.economyStatus.increaseFoodStored(resource.getQuantity());
					break;
				}
			}

			int soldiersLost = (int) (this.economyStatus.getSoldiers() * roll * 0.01f);
			this.economyStatus.increaseSoldiersBy(-1 * soldiersLost);
		}
	}

	private void handleSpecialEvent(EventEffectBean eventEffect){

		float quantity = eventEffect.getEventResources()[0].getQuantity();

		switch(eventEffect.getSpecialEventName()){
		case BABY_BOOM:
			int newborns = (int) (this.getTotalPopulation() * quantity * 0.01f * 0.5f); // percentage; half of population are women
			int incrementalValue = (int) (newborns * 0.25);
			this.economyStatus.increaseBuildersBy(incrementalValue);
			this.economyStatus.increaseFoodWorkersBy(incrementalValue);
			this.economyStatus.increaseWoodWorkersBy(incrementalValue);
			this.economyStatus.increaseStoneWorkersBy(incrementalValue);
			break;
		case BAD_ROCK:
			float stoneLost = (this.wonder.getStoneStored() * quantity * 0.01f);
			this.wonder.increaseStoneStored(-1 * stoneLost);
			break;
		case EARTHQUAKE:
			this.wonder.damageWonder(quantity);
			break;
		case FIRE:
			float woodLost = (this.wonder.getWoodStored() * quantity * 0.01f);
			this.wonder.increaseWoodStored(-1 * woodLost);
			break;
		case PLAGUE:
		case DISEASE:
			int deaths = (int) (this.getTotalPopulation() * quantity * 0.01f);
			int decrementalValue = (int) (deaths * 0.2);
			this.economyStatus.increaseBuildersBy(-1 * decrementalValue);
			this.economyStatus.increaseFoodWorkersBy(-1 * decrementalValue);
			this.economyStatus.increaseWoodWorkersBy(-1 * decrementalValue);
			this.economyStatus.increaseStoneWorkersBy(-1 * decrementalValue);
			this.economyStatus.increaseSoldiersBy(-1 * decrementalValue);
			break;
		case VERMIN:
			float foodLost = (this.economyStatus.getFoodStorage() * quantity * 0.01f);
			this.economyStatus.increaseFoodStored(-1 * foodLost);
			break;
		}
	}

	private void sellResource(ResourceBean resource){
		int difference = 0;
		switch(resource.getResource()){
		case WOOD:
			difference = this.wonder.increaseWoodStored(resource.getQuantity());
			break;
		case STONE:
			difference = this.wonder.increaseStoneStored(resource.getQuantity());
			break;
		}

		if(difference == 0){
			this.economyStatus.increaseCoinsBy(resource.getQuantity() * resource.getPrice());
		} else {
			this.economyStatus.increaseCoinsBy((resource.getQuantity() - difference) * resource.getPrice());
		}
	}

	private int getTotalPopulation(){
		return this.getBuilders() + this.getStoneWorkers() + this.getWoodWorkers() + this.getFoodWorkers() + this.getSoldiers();
	}

	@Override
	public float getFoodStored() {
		return this.economyStatus.getFoodStorage();
	}

	@Override
	public int getTurnsWithoutFood() {
		return this.economyStatus.getTurnsWithoutFood();
	}

	@Override
	public float getCoins() {
		return this.economyStatus.getCoins();
	}

	@Override
	public void increaseNumberOfSoldiers(int numberOfSoldiers) {
		this.economyStatus.increaseSoldiersBy(numberOfSoldiers);
	}

	private void resetInvasion(){
		this.turnsToInvasion = this.random.nextInt(5) + 20;
	}

	@Override
	public int getLevel() {
		return (int) (this.wonder.getCompleted() * 0.1);
	}
}
