package ivan.slavka.sprites;

import ivan.slavka.GameView;
import ivan.slavka.generators.EventGenerator;
import ivan.slavka.interfaces.IEconomyProgress;
import ivan.slavka.interfaces.IEventGenerator;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Bitmap;

public class SpriteManager {

	private List<Sprite> spritePool = new ArrayList<Sprite>();
	private IEventGenerator eventGenerator = new EventGenerator();
	private IEconomyProgress economyController;

	private Bitmap bmp;
	private GameView gameView;

	public SpriteManager(){}

	public SpriteManager(GameView gameView, Bitmap bmp){
		this.bmp = bmp;
		this.gameView = gameView;
	}

	public SpriteManager(GameView gameView, Bitmap bmp, IEconomyProgress economyController){
		this.bmp = bmp;
		this.gameView = gameView;
		this.economyController = economyController;
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
			sprite.prepareSprite(this.gameView, this.bmp);
		}

		sprite.setEvent(this.eventGenerator.generateEvent(this.economyController.getLevel()));

		return sprite;
	}
}
