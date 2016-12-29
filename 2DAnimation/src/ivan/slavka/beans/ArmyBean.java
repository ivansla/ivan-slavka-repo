package ivan.slavka.beans;

public class ArmyBean {

	private SoldierBean[] soldierArray;
	private int currentSoldierIndex = 0;

	public ArmyBean(){}

	@Deprecated
	public ArmyBean(int numberOfSoldiers){
		this.soldierArray = new SoldierBean[numberOfSoldiers];
		for(int i = 0; i < numberOfSoldiers; i++){
			this.soldierArray[i] = new SoldierBean();
		}
	}

	@Deprecated
	public ArmyBean(int numberOfSoldiers, int defaultNumberOfHitpoints){
		this.soldierArray = new SoldierBean[numberOfSoldiers];
		for(int i = 0; i < numberOfSoldiers; i++){
			this.soldierArray[i] = new SoldierBean(defaultNumberOfHitpoints);
		}
	}

	public void reinitializeArmy(int numberOfSoldiers, int defaultNumberOfHitpoints){
		this.reinitializeArmy(numberOfSoldiers);
		for(SoldierBean soldier : this.soldierArray){
			if(soldier.isAlive()){
				soldier.setMaxNumberOfHitPoints(defaultNumberOfHitpoints);
			}
		}
	}

	public void reinitializeArmy(int numberOfSoldiers){
		if(this.soldierArray == null || this.soldierArray.length < numberOfSoldiers){
			this.soldierArray = new SoldierBean[numberOfSoldiers * 2];


			for(int i = 0; i < this.soldierArray.length; i++){
				this.soldierArray[i] = new SoldierBean();
			}
		}

		for(SoldierBean soldier : this.soldierArray){
			soldier.setAlive(false);
		}

		for(int i = 0; i < numberOfSoldiers; i++){
			this.soldierArray[i].setAlive(true);
		}
	}

	public boolean getNextLiveSoldier(SoldierBean[] soldier){
		for(int i = 0; i < this.soldierArray.length; i++){
			int index = this.getNextIndex(i, this.currentSoldierIndex);
			if(this.soldierArray[index].isAlive()){
				this.currentSoldierIndex = index;
				soldier[0] = this.soldierArray[index];
				return true;
			}
		}

		return false;
	}

	private int getNextIndex(int currentIndex, int selectedIndex){
		int sucet = currentIndex + selectedIndex;
		if(sucet >= this.soldierArray.length){
			return (sucet - this.soldierArray.length);
		}
		return sucet;
	}

	public int numberOfLiveSoldiers(){
		int liveSoldiers = 0;
		for(int i = 0; i < this.soldierArray.length; i++){
			if(this.soldierArray[i].isAlive()){
				liveSoldiers++;
			}
		}
		return liveSoldiers;
	}
}
