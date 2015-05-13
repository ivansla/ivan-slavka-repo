package ivan.slavka.beans;

import ivan.slavka.constants.Constants;
import ivan.slavka.enums.ResourcesEnum;
import ivan.slavka.interfaces.IWonderConstruction;
import ivan.slavka.utils.LoggingUtils;

public class WonderBean {

	private static float MAINTENANCE_WOOD_REQUIRED_BASE = Constants.BUILDER_WOOD_USAGE * Constants.TURNS_NEEDED_FOR_BUILDER * Constants.BASE_MAINTENANCE_QUOTIENT;
	private static float MAINTENANCE_STONE_REQUIRED_BASE = Constants.BUILDER_STONE_USAGE * Constants.TURNS_NEEDED_FOR_BUILDER * Constants.BASE_MAINTENANCE_QUOTIENT;

	private float completed = 0f;
	private float woodStored = 0f;
	private float stoneStored = 0f;
	private float woodRemaining = Constants.BUILDER_WOOD_USAGE * Constants.TURNS_NEEDED_FOR_BUILDER;
	private float stoneRemaining = Constants.BUILDER_STONE_USAGE * Constants.TURNS_NEEDED_FOR_BUILDER;

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
		LoggingUtils.log(WonderBean.class, "updateWonderConstruction", "Resources before using. Wood stored: " + this.woodStored + " Stone stored: " + this.stoneStored);
		LoggingUtils.log(WonderBean.class, "updateWonderConstruction", "Resources needed. Wood needed: " + this.woodRemaining + " Stone needed: " + this.stoneRemaining);

		if(this.maintainWonderConstruction()){
			int totalBuilders = this.wonderConstructionController.getBuilders();

			this.woodBuilding = 0f;
			this.stoneBuilding = 0f;

			if(this.woodStored > 0 || this.stoneStored > 0){
				LoggingUtils.log(WonderBean.class, "updateWonderConstruction", "Constructing Wonder");

				int woodBuilders = (int) (this.woodStored / Constants.BUILDER_WOOD_USAGE);
				int stoneBuilders = (int) (this.stoneStored / Constants.BUILDER_STONE_USAGE);

				int buildersUsingAllResources = 0;
				if(woodBuilders < stoneBuilders){
					buildersUsingAllResources = woodBuilders;
				} else {
					buildersUsingAllResources = stoneBuilders;
				}

				if(buildersUsingAllResources > totalBuilders){
					buildersUsingAllResources = totalBuilders;
				}

				this.constructWonderWithAllMaterials(buildersUsingAllResources);

				int buildersUsingMissingResources = totalBuilders - buildersUsingAllResources;
				this.constructWonderWithMissingMaterials(buildersUsingMissingResources);
			}
		}
	}

	private boolean maintainWonderConstruction(){
		LoggingUtils.log(WonderBean.class, "maintainWonderConstruction", "Maintaining Wonder Construction");

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

		this.woodMaintenace = MAINTENANCE_WOOD_REQUIRED_BASE * level;// * quotient;
		this.stoneMaintenace = MAINTENANCE_STONE_REQUIRED_BASE * level;// * quotient;

		if(this.woodMaintenace > this.woodStored || this.stoneMaintenace > this.stoneStored){
			//this.calculateWonderMaintenanceDamage(woodMissing, ResourcesEnum.WOOD, level);
			//this.calculateWonderMaintenanceDamage(stoneMissing, ResourcesEnum.STONE, level);
			this.completed -= level;
			return false;
		}

		LoggingUtils.log(WonderBean.class, "maintainWonderConstruction", "Wood Maintenance: " + this.woodMaintenace + " Stone Maintenance: " + this.stoneMaintenace);

		woodMissing = this.increaseWoodStored(-this.woodMaintenace);
		stoneMissing = this.increaseStoneStored(-this.stoneMaintenace);

		return true;
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

		LoggingUtils.log(WonderBean.class, "calculateWonderMaintenanceDamage", "Damaging Wonder due to missing resource: " + resource + " missing quantity: " + missingQuantity);
		LoggingUtils.log(WonderBean.class, "calculateWonderMaintenanceDamage", "Wonder percent: " + this.completed);
	}

	private void constructWonderWithAllMaterials(int usedBuilders){

		this.woodBuilding += Constants.BUILDER_WOOD_USAGE * usedBuilders;
		this.stoneBuilding += Constants.BUILDER_STONE_USAGE * usedBuilders;

		this.completed += (Constants.CONSTRUCTION_MULTIPLICATOR * usedBuilders);
		this.woodStored -= (Constants.BUILDER_WOOD_USAGE * usedBuilders);
		this.stoneStored -= (Constants.BUILDER_STONE_USAGE * usedBuilders);
		LoggingUtils.log(WonderBean.class, "constructWonderWithAllMaterials", "Wood used: " + this.woodBuilding + " Stone used: " + this.stoneBuilding);
		LoggingUtils.log(WonderBean.class, "constructWonderWithAllMaterials", "Wonder percent: " + this.completed);
	}

	private void constructWonderWithMissingMaterials(int builders){
		if(builders == 0){
			return;
		}

		int usingBuilders = 0;
		for(int i = 0; i < builders; i++){
			boolean hasResourcesForBuilder = false;
			if(this.woodStored >= Constants.BUILDER_WOOD_USAGE &&
					this.woodStored >= Constants.BUILDER_WOOD_USAGE * usingBuilders){
				hasResourcesForBuilder = true;
			}

			if(this.stoneStored >= Constants.BUILDER_STONE_USAGE &&
					this.stoneStored >= Constants.BUILDER_STONE_USAGE * usingBuilders){
				hasResourcesForBuilder = true;
			}

			if(!hasResourcesForBuilder){
				break;
			}
			usingBuilders++;
		}

		this.woodBuilding += Constants.BUILDER_WOOD_USAGE * usingBuilders;
		this.stoneBuilding += Constants.BUILDER_STONE_USAGE * usingBuilders;

		float woodNeeded = (Constants.BUILDER_WOOD_USAGE * usingBuilders);
		float stoneNeeded = (Constants.BUILDER_STONE_USAGE * usingBuilders);
		if(woodNeeded <= this.woodStored){
			this.woodStored -= woodNeeded;
			LoggingUtils.log(WonderBean.class, "constructWonderWithMissingMaterials", "Wood used: " + woodNeeded);
		}

		if(stoneNeeded <= this.stoneStored){
			this.stoneStored -= stoneNeeded;
			LoggingUtils.log(WonderBean.class, "constructWonderWithMissingMaterials", "Stone used: " + stoneNeeded);
		}
		this.completed += (0.5 * Constants.CONSTRUCTION_MULTIPLICATOR * usingBuilders);
		LoggingUtils.log(WonderBean.class, "constructWonderWithMissingMaterials", "Wonder percent: " + this.completed + " constructed with: " + usingBuilders + " builders");
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
		this.woodRemaining = Constants.BUILDER_WOOD_USAGE * Constants.TURNS_NEEDED_FOR_BUILDER;
		this.stoneRemaining = Constants.BUILDER_STONE_USAGE * Constants.TURNS_NEEDED_FOR_BUILDER;
	}
}

