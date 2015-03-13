package ivan.slavka.beans;

public class SoldierBean {

	private static int DEFAULT_NUMBER_OF_HITPOINTS = 30;

	private int maxNumberOfHitPoints;
	private int currentNumberOfHitPoints;
	private boolean isAlive;

	public SoldierBean(){
		this.maxNumberOfHitPoints = DEFAULT_NUMBER_OF_HITPOINTS;
		this.currentNumberOfHitPoints = DEFAULT_NUMBER_OF_HITPOINTS;

		this.isAlive = true;
	}

	public SoldierBean(int maxNumberOfHitPoints){
		this.maxNumberOfHitPoints = maxNumberOfHitPoints;
		this.currentNumberOfHitPoints = maxNumberOfHitPoints;

		this.isAlive = true;
	}

	public void takeDamage(){
		this.currentNumberOfHitPoints--;
		if(this.currentNumberOfHitPoints <= 0){
			this.isAlive = false;
		}
	}

	public void takeDamage(int damage){
		this.currentNumberOfHitPoints -= damage;
		if(this.currentNumberOfHitPoints <= 0){
			this.isAlive = false;
		}
	}

	public boolean isAlive(){
		return this.isAlive;
	}
}
