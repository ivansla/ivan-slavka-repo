package ivan.slavka.beans;

import ivan.slavka.constants.Constants;
import ivan.slavka.enums.ResourcesEnum;
import ivan.slavka.interfaces.IWonderConstruction;
import ivan.slavka.utils.LoggingUtils;

public class WonderBean {

	private static float MAINTENANCE_WOOD_REQUIRED_BASE = Constants.WOOD_NEEDED * Constants.MAINTENANCE_QUOTIENT;
	private static float MAINTENANCE_STONE_REQUIRED_BASE = Constants.STONE_NEEDED * Constants.MAINTENANCE_QUOTIENT;

	private float completed = 0f;
	private float woodStored = 0f;
	private float stoneStored = 0f;
	private float woodRemaining = Constants.WOOD_NEEDED;
	private float stoneRemaining = Constants.STONE_NEEDED;

	private volatile float woodMaintenace;
	private volatile float stoneMaintenace;
	private volatile float woodBuilding;
	private volatile float stoneBuilding;

	private IWonderConstruction wonderConstructionController;

	public WonderBean(IWonderConstruction wonderConstructionController){
		this.wonderConstructionController = wonderConstructionController;
	}

	public float getCompleted() {
		return this.completed;
	}

	public void setCompleted(float completed) {
		this.completed = completed;
	}

	public float getWoodStored() {
		return this.woodStored;
	}

	public void setWoodStored(float woodStored) {
		this.woodStored = woodStored;
	}

	public float getStoneStored() {
		return this.stoneStored;
	}

	public void setStoneStored(float stoneStored) {
		this.stoneStored = stoneStored;
	}

	public float getWoodRemaining() {
		return this.woodRemaining;
	}

	public void setWoodRemaining(float woodRemaining) {
		this.woodRemaining = woodRemaining;
	}

	public float getStoneRemaining() {
		return this.stoneRemaining;
	}

	public void setStoneRemaining(float stoneRemaining) {
		this.stoneRemaining = stoneRemaining;
	}

	public int increaseWoodStored(float wood){
		if(wood < 0){
			if((wood * (-1)) > this.woodStored){
				int difference = (int) wood + (int) this.woodStored;
				this.woodStored = 0;
				this.woodRemaining -= (wood - difference);
				return difference;
			} else {
				this.woodStored += wood;
				this.woodRemaining -= wood;
			}
		} else {
			this.woodStored += wood;
			this.woodRemaining -= wood;

			if(this.woodRemaining <= 0){
				this.woodRemaining = 0;
			}
		}

		return 0;
	}

	public int increaseStoneStored(float stone){
		if(stone < 0){
			if((stone * (-1)) > this.stoneStored){
				int difference = (int) stone + (int) this.stoneStored;
				this.stoneStored = 0;
				this.stoneRemaining -= (stone + difference);
				return difference;
			} else {
				this.stoneStored += stone;
				this.stoneRemaining -= stone;
			}
		} else {
			this.stoneStored += stone;
			this.stoneRemaining -= stone;

			if(this.stoneRemaining <= 0){
				this.stoneRemaining = 0;
			}
		}

		return 0;
	}

	public void damageWonder(float damage){
		this.completed -= damage;
		if(this.completed < 0){
			this.completed = 0;
		}
	}

	public void updateWonderConstruction(){
		LoggingUtils.log("WonderBean.updateWonderConstruction", "Resources before using. Wood stored: " + this.woodStored + " Stone stored: " + this.stoneStored);
		LoggingUtils.log("WonderBean.updateWonderConstruction", "Resources needed. Wood needed: " + this.woodRemaining + " Stone needed: " + this.stoneRemaining);

		this.maintainWonderConstruction();

		int totalBuilders = this.wonderConstructionController.getBuilders();

		this.woodBuilding = 0f;
		this.stoneBuilding = 0f;

		if(this.woodStored > 0 || this.stoneStored > 0){
			LoggingUtils.log("WonderBean.updateWonderConstruction", "Constructing Wonder");

			int woodBuilders = (int) (this.woodStored / (Constants.WOOD_NEEDED * Constants.CONSTRUCTION_MULTIPLICATOR));
			int stoneBuilders = (int) (this.stoneStored / (Constants.STONE_NEEDED * Constants.CONSTRUCTION_MULTIPLICATOR));

			if(woodBuilders >= totalBuilders && stoneBuilders >= totalBuilders){
				this.constructWonderWithAllMaterials(totalBuilders);

			} else if((woodBuilders >= totalBuilders && stoneBuilders < totalBuilders) || (woodBuilders < totalBuilders && stoneBuilders >= totalBuilders)){
				int usedBuilders = Math.min(woodBuilders, stoneBuilders);
				int remainingBuilders = totalBuilders - usedBuilders;

				this.constructWonderWithAllMaterials(usedBuilders);
				this.constructWonderWithMissingMaterials(remainingBuilders);
			} else {
				int usedBuilders = Math.min(woodBuilders, stoneBuilders);
				int remainingBuilders = Math.max(woodBuilders, stoneBuilders) - usedBuilders;

				this.constructWonderWithAllMaterials(usedBuilders);
				this.constructWonderWithMissingMaterials(remainingBuilders);
			}
		}
	}

	private void maintainWonderConstruction(){
		LoggingUtils.log("WonderBean.maintainWonderConstruction", "Maintaining Wonder Construction");

		float woodMissing = 0f;
		float stoneMissing = 0f;
		int level = 0;
		float quotient = 0f;
		if(this.completed >= 20 && this.completed < 40){
			level = 2;
			quotient = 0.1f;
		} else if(this.completed >= 40 && this.completed < 60){
			level = 3;
			quotient = 0.2f;
		} else if(this.completed >= 60 && this.completed < 80){
			level = 4;
			quotient = 0.4f;
		} else if(this.completed >= 80 && this.completed < 100){
			level = 5;
			quotient = 0.5f;
		}

		this.woodMaintenace = MAINTENANCE_WOOD_REQUIRED_BASE * level * quotient;
		this.stoneMaintenace = MAINTENANCE_STONE_REQUIRED_BASE * level * quotient;

		LoggingUtils.log("WonderBean.maintainWonderConstruction", "Wood Maintenance: " + this.woodMaintenace + " Stone Maintenance: " + this.stoneMaintenace);

		woodMissing = this.increaseWoodStored(-this.woodMaintenace);
		stoneMissing = this.increaseStoneStored(-this.stoneMaintenace);

		this.calculateWonderMaintenanceDamage(woodMissing, ResourcesEnum.WOOD, level);
		this.calculateWonderMaintenanceDamage(stoneMissing, ResourcesEnum.STONE, level);
	}

	private void calculateWonderMaintenanceDamage(float missingQuantity, ResourcesEnum resource, int level){
		if(missingQuantity >= 0){
			return;
		}

		float percentageMissing = 0f;
		switch(resource){
		case WOOD:
			percentageMissing = ((missingQuantity * 100) / (MAINTENANCE_WOOD_REQUIRED_BASE * level));
			percentageMissing = (percentageMissing * this.completed) * 0.01f * Constants.RESOURCE_IMPORTANCE_QUOTIENT;
			break;
		case STONE:
			percentageMissing = ((missingQuantity * 100) / (MAINTENANCE_STONE_REQUIRED_BASE * level));
			percentageMissing = (percentageMissing * this.completed) * 0.01f * Constants.RESOURCE_IMPORTANCE_QUOTIENT;
			break;
		}

		this.completed += percentageMissing;

		LoggingUtils.log("WonderBean.calculateWonderMaintenanceDamage", "Damaging Wonder due to missing resource: " + resource + " missing quantity: " + missingQuantity);
		LoggingUtils.log("WonderBean.calculateWonderMaintenanceDamage", "Wonder percent: " + this.completed);
	}

	private void constructWonderWithAllMaterials(int usedBuilders){

		this.woodBuilding += Constants.WOOD_NEEDED * Constants.CONSTRUCTION_MULTIPLICATOR * usedBuilders;
		this.stoneBuilding += Constants.STONE_NEEDED * Constants.CONSTRUCTION_MULTIPLICATOR * usedBuilders;

		this.completed += (Constants.CONSTRUCTION_MULTIPLICATOR * usedBuilders);
		this.woodStored -= (Constants.WOOD_NEEDED * Constants.CONSTRUCTION_MULTIPLICATOR * usedBuilders);
		this.stoneStored -= (Constants.STONE_NEEDED * Constants.CONSTRUCTION_MULTIPLICATOR * usedBuilders);
		LoggingUtils.log("WonderBean.constructWonderWithAllMaterials", "Wood used: " + this.woodBuilding + " Stone used: " + this.stoneBuilding);
		LoggingUtils.log("WonderBean.constructWonderWithAllMaterials", "Wonder percent: " + this.completed);
	}

	private void constructWonderWithMissingMaterials(int usedBuilders){
		this.woodBuilding += Constants.WOOD_NEEDED * Constants.CONSTRUCTION_MULTIPLICATOR * usedBuilders;
		this.stoneBuilding += Constants.STONE_NEEDED * Constants.CONSTRUCTION_MULTIPLICATOR * usedBuilders;

		float woodNeeded = (Constants.WOOD_NEEDED * Constants.CONSTRUCTION_MULTIPLICATOR * usedBuilders);
		float stoneNeeded = (Constants.STONE_NEEDED * Constants.CONSTRUCTION_MULTIPLICATOR * usedBuilders);
		this.completed += (0.5 * Constants.CONSTRUCTION_MULTIPLICATOR * usedBuilders);
		if(woodNeeded <= this.woodStored){
			this.woodStored -= woodNeeded;
		}

		if(stoneNeeded <= this.stoneStored){
			this.stoneStored -= stoneNeeded;
		}

		LoggingUtils.log("WonderBean.constructWonderWithMissingMaterials", "Wood used: " + this.woodBuilding + " Stone used: " + this.stoneBuilding);
		LoggingUtils.log("WonderBean.constructWonderWithMissingMaterials", "Wonder percent: " + this.completed);
	}

	public float getWoodMaintenace() {
		return this.woodMaintenace;
	}

	public float getStoneMaintenace() {
		return this.stoneMaintenace;
	}

	public float getWoodBuilding() {
		return this.woodBuilding;
	}

	public float getStoneBuilding() {
		return this.stoneBuilding;
	}

	public void restartWonder(){
		this.woodStored = 0;
		this.stoneStored = 0;
		this.completed = 0;
		this.woodRemaining = Constants.WOOD_NEEDED;
		this.stoneRemaining = Constants.STONE_NEEDED;
	}
}

