package ivan.slavka.sprites;

import ivan.slavka.constants.Constants;
import ivan.slavka.enums.GeneralSpriteEnum;
import ivan.slavka.interfaces.IEconomyProgress;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Rect;

public class AnimationSprite {

	private static int BASIC_SIZE = 64;
	private static float BASE_POPULATION = 100f;

	private static int MINIMUM_WAREHOUSE = 10;

	private int size;
	private Bitmap bitmap;
	private float x;
	private float y;
	private int numberOfTransitions;
	private int actualTransition;
	private Rect src, dst;
	private GeneralSpriteEnum spriteName;

	private IEconomyProgress economyController;
	private SpriteManager spriteManager;

	public AnimationSprite(){}

	public AnimationSprite(IEconomyProgress economyController){
		this.economyController = economyController;
		//this.spriteManager = spriteManager;
		//this.bitmap = this.spriteManager.getGeneralSprite(animationSpriteEnum);
		this.src = new Rect();
		this.dst = new Rect();
		this.size = BASIC_SIZE;
	}

	public AnimationSprite(IEconomyProgress economyController, int size){
		this.economyController = economyController;
		//this.spriteManager = spriteManager;
		//this.bitmap = this.spriteManager.getGeneralSprite(animationSpriteEnum);
		this.src = new Rect();
		this.dst = new Rect();

		this.size = size;
	}

	public void prepareSprite(Bitmap bmp, GeneralSpriteEnum spriteName){
		this.spriteName = spriteName;
		this.bitmap = bmp;
		this.actualTransition = 1;
		this.numberOfTransitions = (this.bitmap.getWidth() / this.size);
	}

	public void setCoordinates(int x, int y){
		this.x = x;
		this.y = y;

		this.dst.left = x;
		this.dst.top = y;
		this.dst.right = x + this.size;
		this.dst.bottom = y + this.size;
	}

	private void update(){
		this.actualTransition = this.calculateSpriteTransition();

		this.src.left = this.actualTransition * this.size;
		this.src.top = 0;
		this.src.right = this.src.left + this.size;
		this.src.bottom = this.src.top + this.size;
	}

	public void onDraw(Canvas canvas) {
		this.update();
		//canvas.drawRect(this.position.x, this.position.y, this.position.x + WIDTH_SIZE, this.position.y + HEIGHT_SIZE, this.drawPaint);
		canvas.drawBitmap(this.bitmap, this.src, this.dst, null);
	}

	private int calculateSpriteTransition(){
		switch(this.spriteName){
		case WOOD_WAREHOUSE:
			float woodRequirement = this.economyController.getWoodBuilding() + this.economyController.getWoodMaintenace();

			if(this.economyController.getWoodStored() <= 0){
				//if(this.economyController.getWoodIncome() < woodRequirement){ // Income is lower than necessity
				return 0;
				//}
			} else {

				/*if(woodRequirement <= 0){
					return 2;
				}
				 */
				if((woodRequirement < MINIMUM_WAREHOUSE && this.economyController.getWoodStored() <= MINIMUM_WAREHOUSE) ||
						(woodRequirement >= MINIMUM_WAREHOUSE && this.economyController.getWoodStored() < woodRequirement * 2)){ // Income is lower than necessity
					return 1;
				}

				if(woodRequirement >= MINIMUM_WAREHOUSE && this.economyController.getWoodStored() >= woodRequirement * 5){ // Income is bigger than necessity and resource stored is more than the necessity
					return 3;
				}
			}
			return 2; // Balanced

		case STONE_WAREHOUSE:
			float stoneRequirement = this.economyController.getStoneBuilding() + this.economyController.getStoneMaintenace();

			if(this.economyController.getStoneStored() <= 0){
				//if(this.economyController.getStoneIncome() < stoneRequirement){ // Income is lower than necessity
				return 0;
				//}
			} else {

				if((stoneRequirement < MINIMUM_WAREHOUSE && this.economyController.getStoneStored() <= MINIMUM_WAREHOUSE) ||
						(stoneRequirement >= MINIMUM_WAREHOUSE && this.economyController.getStoneStored() < stoneRequirement * 2)){ // Income is lower than necessity
					return 1;
				}

				if(stoneRequirement >= MINIMUM_WAREHOUSE &&	this.economyController.getStoneStored() >= stoneRequirement * 5){ // Income is bigger than necessity and resource stored is more than the necessity
					return 3;
				}
			}
			return 2; // Balanced

		case GOLD_WAREHOUSE:
			//float stoneRequirement = this.economyController.getStoneBuilding() + this.economyController.getStoneMaintenace();

			if(this.economyController.getCoins() <= 0){
				return 0;
			} else {
				if(this.economyController.getCoins() < Constants.SOLDIER_PRICE * 2){ // Income is lower than necessity
					return 1;
				}

				if(	this.economyController.getCoins() >= Constants.SOLDIER_PRICE * 10){ // Income is bigger than necessity and resource stored is more than the necessity
					return 3;
				}
			}
			return 2; // Balanced

		case GRANARY:
			if(this.economyController.getFoodStored() <= 0){
				if(this.economyController.getFoodIncome() < 0){ // Income is lower than necessity
					return 0;
				}
			} else {
				if(this.economyController.getFoodStored() < (this.economyController.getFoodConsumption() * 2)){ // Income is lower than necessity
					return 1;
				}

				if(	this.economyController.getFoodStored() > (this.economyController.getFoodConsumption() * 5)){ // Income is bigger than necessity and resource stored is more than the necessity
					return 3;
				}
			}
			return 2; // Balanced

		case BUILDER_CAMP:
			return this.calculateCampSiteTransition(this.economyController.getBuilders(), this.economyController.getTotalPopulation());
		case FARM_CAMP:
			return this.calculateCampSiteTransition(this.economyController.getFoodWorkers(), this.economyController.getTotalPopulation());
		case LUMBER_CAMP:
			return this.calculateCampSiteTransition(this.economyController.getWoodWorkers(), this.economyController.getTotalPopulation());
		case SOLDIER_CAMP:
			return this.calculateCampSiteTransition(this.economyController.getSoldiers(), this.economyController.getTotalPopulation());
		case QUARRY_CAMP:
			return this.calculateCampSiteTransition(this.economyController.getStoneWorkers(), this.economyController.getTotalPopulation());
		case WONDER:
			return ((int) this.economyController.getCompletedPct() / 10);
		}

		return 0;
	}

	private int calculateCampSiteTransition(float peopleInCamp, float totalPeople){
		float percentage = 0f;
		if(totalPeople <= BASE_POPULATION){
			percentage = peopleInCamp / BASE_POPULATION;
		} else {
			percentage = peopleInCamp / totalPeople;
		}

		if(percentage <= 0.05f){
			return 0;
		}

		if(percentage > 0.05f && percentage <= 0.17f){
			return 1;
		}

		if(percentage > 0.17f && percentage <= 0.30f){
			return 2;
		}
		return 3;
	}
}
