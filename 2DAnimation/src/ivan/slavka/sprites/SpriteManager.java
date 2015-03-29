package ivan.slavka.sprites;

import ivan.slavka.GameView;
import ivan.slavka.R;
import ivan.slavka.enums.EventSpriteEnum;
import ivan.slavka.generators.EventGenerator;
import ivan.slavka.interfaces.IEconomyProgress;
import ivan.slavka.interfaces.IEventGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class SpriteManager {

	private List<Sprite> spritePool = new ArrayList<Sprite>();
	private IEventGenerator eventGenerator = new EventGenerator();
	private IEconomyProgress economyController;

	private Map<EventSpriteEnum, Bitmap> eventSpriteBitmapMap = new HashMap<>();

	private Bitmap bmp;
	private GameView gameView;

	public SpriteManager(){}

	public SpriteManager(GameView gameView, Bitmap bmp){
		this.bmp = bmp;
		this.gameView = gameView;
	}

	public SpriteManager(GameView gameView, IEconomyProgress economyController){
		this.gameView = gameView;
		this.economyController = economyController;
		this.loadResources();
	}

	public Sprite retrieveSprite(){
		boolean isSpriteAvailable = false;
		Sprite sprite = null;
		for(Sprite s : this.spritePool){
			if(s.isReleased()){
				isSpriteAvailable = true;
				s.setReleased(false);
				s.deactivateSprite();
				//s.prepareSprite(this.gameView, this.bmp);
				sprite = s;
			}
		}

		if(!isSpriteAvailable){
			sprite = new Sprite();
			this.spritePool.add(sprite);
			sprite.prepareSprite(this.gameView);
		}

		sprite.setEvent(this.eventGenerator.generateEvent(this.economyController.getLevel()));
		sprite.prepareSpriteBitmaps(this.eventSpriteBitmapMap);

		return sprite;
	}

	private void loadResources(){

		this.eventSpriteBitmapMap.put(EventSpriteEnum.WOOD, BitmapFactory.decodeResource(this.gameView.getResources(), R.drawable.wood));
		this.eventSpriteBitmapMap.put(EventSpriteEnum.STONE, BitmapFactory.decodeResource(this.gameView.getResources(), R.drawable.stone));
		this.eventSpriteBitmapMap.put(EventSpriteEnum.FOOD, BitmapFactory.decodeResource(this.gameView.getResources(), R.drawable.food));
		this.eventSpriteBitmapMap.put(EventSpriteEnum.WOOD_WORKER, BitmapFactory.decodeResource(this.gameView.getResources(), R.drawable.axe));
		this.eventSpriteBitmapMap.put(EventSpriteEnum.STONE_WORKER, BitmapFactory.decodeResource(this.gameView.getResources(), R.drawable.pickaxe));
		this.eventSpriteBitmapMap.put(EventSpriteEnum.FOOD_WORKER, BitmapFactory.decodeResource(this.gameView.getResources(), R.drawable.scythe));
		this.eventSpriteBitmapMap.put(EventSpriteEnum.BUILDER, BitmapFactory.decodeResource(this.gameView.getResources(), R.drawable.hammer));
		this.eventSpriteBitmapMap.put(EventSpriteEnum.SOLDIER, BitmapFactory.decodeResource(this.gameView.getResources(), R.drawable.sword));
		this.eventSpriteBitmapMap.put(EventSpriteEnum.BABY_BOOM, BitmapFactory.decodeResource(this.gameView.getResources(), R.drawable.baby_boom));
		this.eventSpriteBitmapMap.put(EventSpriteEnum.BAD_ROCK, BitmapFactory.decodeResource(this.gameView.getResources(), R.drawable.bad_rock));
		this.eventSpriteBitmapMap.put(EventSpriteEnum.DISEASE, BitmapFactory.decodeResource(this.gameView.getResources(), R.drawable.disease));
		this.eventSpriteBitmapMap.put(EventSpriteEnum.EARTHQUAKE, BitmapFactory.decodeResource(this.gameView.getResources(), R.drawable.earthquake));
		//this.eventSpriteBitmapMap.put(EventSpriteEnum.FIRE, BitmapFactory.decodeResource(this.gameView.getResources(), R.drawable.wood));
		//this.eventSpriteBitmapMap.put(EventSpriteEnum.PLAGUE, BitmapFactory.decodeResource(this.gameView.getResources(), R.drawable.wood));
		this.eventSpriteBitmapMap.put(EventSpriteEnum.RAID_TOWN, BitmapFactory.decodeResource(this.gameView.getResources(), R.drawable.city));
		this.eventSpriteBitmapMap.put(EventSpriteEnum.RAID_VILLAGE, BitmapFactory.decodeResource(this.gameView.getResources(), R.drawable.village));
		this.eventSpriteBitmapMap.put(EventSpriteEnum.VERMIN, BitmapFactory.decodeResource(this.gameView.getResources(), R.drawable.vermin));
	}
}
