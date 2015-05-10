package ivan.slavka.beans;

import ivan.slavka.constants.Constants;

public class SoldierBean {

	private int maxNumberOfHitPoints;
	private int currentNumberOfHitPoints;
	private boolean isAlive;

	public SoldierBean(){
		this.maxNumberOfHitPoints = Constants.DEFAULT_NUMBER_OF_HITPOINTS;
		this.currentNumberOfHitPoints = Constants.DEFAULT_NUMBER_OF_HITPOINTS;

		this.isAlive = false;
	}

	public SoldierBean(int maxNumberOfHitPoints){
		this.maxNumberOfHitPoints = maxNumberOfHitPoints;
		this.currentNumberOfHitPoints = maxNumberOfHitPoints;

		this.isAlive = false;
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

	public void setAlive(boolean isAlive){
		this.isAlive = isAlive;
	}

	public void setMaxNumberOfHitPoints(int maxNumberOfHitPoints){
		this.maxNumberOfHitPoints = maxNumberOfHitPoints;
	}
}
