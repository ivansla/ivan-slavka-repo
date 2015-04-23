package ivan.slavka;

import ivan.slavka.abstracts.AbstractGameView;
import ivan.slavka.controllers.EconomyController;
import ivan.slavka.enums.GeneralSpriteEnum;
import ivan.slavka.enums.InputControlEnum;
import ivan.slavka.interfaces.IEconomyProgress;
import ivan.slavka.interfaces.IEvent;
import ivan.slavka.sprites.AnimationSprite;
import ivan.slavka.sprites.EconomyStatusSprite;
import ivan.slavka.sprites.GameOverSprite;
import ivan.slavka.sprites.Sprite;
import ivan.slavka.sprites.SpriteManager;
import ivan.slavka.sprites.WonderConstructionSprite;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;

@SuppressLint("WrongCall")
public class GameView extends AbstractGameView {
	public final static long FPS = 30;
	private final static int SPRITE_DELAY = 2000;
	private final static int NUMBER_OF_SPRITES = 7;
	private static float LOWEST_POINT;
	private static float SPRITE_PADDING;
	private static int MAX_NUMBER_OF_SPRITES;

	private long spriteStartTime;
	private Sprite[] activeSpriteArray = new Sprite[NUMBER_OF_SPRITES];
	private int activeSpriteIndex = 0;
	private int selectedSpriteIndex = 0;
	private volatile int numberOfActiveSprites = 0;

	//private Rect displaySize;
	private Point displaySize = new Point();
	//private Display display;

	private SpriteManager spriteManager;// = new SpriteManager();
	private IEconomyProgress economyProgressController = new EconomyController();


	private AnimationSprite[] generalSprites = new AnimationSprite[10];
	private GameOverSprite gameOverSprite;

	private EconomyStatusSprite economySprite;// = new EconomyStatusSprite();
	private WonderConstructionSprite wonderSprite;

	private Paint drawPaint;

	private volatile Sprite activeSprite = null;
	private Bitmap worldBitmap = null;

	public GameView(Context context) {
		super(context);
		WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
		Display display = wm.getDefaultDisplay();
		display.getSize(this.displaySize);

		LOWEST_POINT = this.displaySize.y * 0.8f;
		SPRITE_PADDING = this.displaySize.y * 0.02f;
		MAX_NUMBER_OF_SPRITES =(int) (LOWEST_POINT / (Sprite.HEIGHT_SIZE + SPRITE_PADDING));

		this.economySprite = new EconomyStatusSprite(this.economyProgressController);
		this.wonderSprite = new WonderConstructionSprite(this.economyProgressController);

		this.spriteManager = new SpriteManager(this, this.economyProgressController);

		this.economySprite.prepareSprite(this, null);
		this.wonderSprite.prepareSprite(this, null);
		this.startGame();

		this.drawPaint = new Paint();
		this.drawPaint.setColor(Color.RED);

		this.drawPaint.setAntiAlias(true);
		//this.drawPaint.setStrokeWidth(20);
		this.drawPaint.setStyle(Paint.Style.FILL);

		this.prepareGeneralSprites();

		this.gameOverSprite = new GameOverSprite();
		this.gameOverSprite.setSpritePosition(200, 200);
	}

	private void prepareGeneralSprites(){

		this.generalSprites[0] = new AnimationSprite(this.economyProgressController);
		this.generalSprites[0].prepareSprite(this.spriteManager.getGeneralSprite(GeneralSpriteEnum.BUILDER_CAMP), GeneralSpriteEnum.BUILDER_CAMP);
		this.generalSprites[0].setCoordinates(350, 250);

		this.generalSprites[1] = new AnimationSprite(this.economyProgressController);
		this.generalSprites[1].prepareSprite(this.spriteManager.getGeneralSprite(GeneralSpriteEnum.GRANARY), GeneralSpriteEnum.GRANARY);
		this.generalSprites[1].setCoordinates(100, 200);

		this.generalSprites[2] = new AnimationSprite(this.economyProgressController);
		this.generalSprites[2].prepareSprite(this.spriteManager.getGeneralSprite(GeneralSpriteEnum.LUMBER_CAMP), GeneralSpriteEnum.LUMBER_CAMP);
		this.generalSprites[2].setCoordinates(30, 500);

		this.generalSprites[3] = new AnimationSprite(this.economyProgressController);
		this.generalSprites[3].prepareSprite(this.spriteManager.getGeneralSprite(GeneralSpriteEnum.SOLDIER_CAMP), GeneralSpriteEnum.SOLDIER_CAMP);
		this.generalSprites[3].setCoordinates(20, 300);

		this.generalSprites[4] = new AnimationSprite(this.economyProgressController);
		this.generalSprites[4].prepareSprite(this.spriteManager.getGeneralSprite(GeneralSpriteEnum.STONE_WAREHOUSE), GeneralSpriteEnum.STONE_WAREHOUSE);
		this.generalSprites[4].setCoordinates(340, 450);

		this.generalSprites[5] = new AnimationSprite(this.economyProgressController);
		this.generalSprites[5].prepareSprite(this.spriteManager.getGeneralSprite(GeneralSpriteEnum.WOOD_WAREHOUSE), GeneralSpriteEnum.WOOD_WAREHOUSE);
		this.generalSprites[5].setCoordinates(410, 450);

		this.generalSprites[6] = new AnimationSprite(this.economyProgressController, 128);
		this.generalSprites[6].prepareSprite(this.spriteManager.getGeneralSprite(GeneralSpriteEnum.WONDER), GeneralSpriteEnum.WONDER);
		this.generalSprites[6].setCoordinates(310, 100);

		this.generalSprites[7] = new AnimationSprite(this.economyProgressController, 64);
		this.generalSprites[7].prepareSprite(this.spriteManager.getGeneralSprite(GeneralSpriteEnum.FARM_CAMP), GeneralSpriteEnum.FARM_CAMP);
		this.generalSprites[7].setCoordinates(30, 190);

		this.generalSprites[8] = new AnimationSprite(this.economyProgressController, 64);
		this.generalSprites[8].prepareSprite(this.spriteManager.getGeneralSprite(GeneralSpriteEnum.QUARRY_CAMP), GeneralSpriteEnum.QUARRY_CAMP);
		this.generalSprites[8].setCoordinates(350, 350);

		this.worldBitmap = this.spriteManager.getGeneralSprite(GeneralSpriteEnum.WORLD);
	}

	private void startGame(){
		this.spriteStartTime = System.currentTimeMillis();
	}

	@Override
	public void updateGame(){
		if(!this.economyProgressController.isGameOver()){

			long timePassed = System.currentTimeMillis() - this.spriteStartTime;
			if(timePassed > SPRITE_DELAY && this.numberOfActiveSprites < MAX_NUMBER_OF_SPRITES){// && this.activeSpriteIndex < 0){
				Sprite sprite = this.spriteManager.retrieveSprite();
				float lowerTo = LOWEST_POINT - ((this.numberOfActiveSprites) * (Sprite.HEIGHT_SIZE + SPRITE_PADDING));
				sprite.setSpriteYDestination(lowerTo);
				this.spriteStartTime = System.currentTimeMillis();

				if(this.numberOfActiveSprites == 0){
					sprite.setLowestSprite(true);
				}

				this.activeSpriteArray[this.activeSpriteIndex] = sprite;
				this.numberOfActiveSprites++;

				this.incrementActiveSpritesIndex();
			}

			if(this.activeSprite != null){
				if(!this.activeSprite.isPerformingAnimation() && this.activeSprite.isActivated()){
					this.activeSpriteArray[this.selectedSpriteIndex] = null;
					this.numberOfActiveSprites--;

					this.processTurn(this.activeSprite.getEvent(), this.activeSprite.getSide());
					this.activeSprite.setLowestSprite(false);

					this.lowerRemainingSprites();
					this.activeSprite = null;
				}
			}
		}
	}

	private void lowerRemainingSprites(){
		Sprite s;
		int spriteIndex = 0;

		boolean isFirstOccurence = true;

		for(int i = 0; i < NUMBER_OF_SPRITES; i++){
			s = this.activeSpriteArray[i];

			if(s != null){
				if(isFirstOccurence){
					s.setLowestSprite(true);
					isFirstOccurence = false;
				}

				float lowerTo = LOWEST_POINT - (spriteIndex * (Sprite.HEIGHT_SIZE + SPRITE_PADDING));
				s.setSpriteYDestination(lowerTo);
				spriteIndex++;
			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		final int X = (int) event.getRawX();
		final int Y = (int) event.getRawY();
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:

			Log.v("GameView.onTouchEvent", "ACTION_DOWN");

			if(this.activeSprite != null && this.activeSprite.isPerformingAnimation()){
				return true;
			}

			if(!this.economyProgressController.isGameOver()){
				Sprite s;
				for(int i = 0; i < NUMBER_OF_SPRITES; i++){
					s = this.activeSpriteArray[i];
					if(s != null){
						if(s.isCollition(event.getX(), event.getY())){
							this.activeSprite = s;
							this.activeSprite.spriteActionDown(X);
							this.selectedSpriteIndex = i;
						}
					}
				}
			} else {
				if(this.gameOverSprite.isCollition(event.getX(), event.getY())){
					Log.v("GameView.onTouchEvent", "restarting game");
					this.restartGame();
				}
			}

			break;
		case MotionEvent.ACTION_UP:
			Log.v("GameView.onTouchEvent", "ACTION_UP");
			if(this.activeSprite != null){
				this.activeSprite.spriteActionUp(X);
			}
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			break;
		case MotionEvent.ACTION_POINTER_UP:
			break;
		case MotionEvent.ACTION_MOVE:
			Log.v("GameView.onTouchEvent", "ACTION_MOVE");
			if(this.activeSprite != null){
				this.activeSprite.spriteActionMove(X);
			}

			break;
		}

		return true;
	}

	@Override
	public void onDraw(Canvas canvas) {
		if(!this.economyProgressController.isGameOver()){
			canvas.drawBitmap(this.worldBitmap, 0f, 0f, null);
			for(int i = 0; i < this.generalSprites.length; i++){
				if(this.generalSprites[i] != null){
					this.generalSprites[i].onDraw(canvas);
				}
			}

			//this.economySprite.onDraw(canvas);
			//this.wonderSprite.onDraw(canvas);
			Sprite s;
			for(int i = 0; i < NUMBER_OF_SPRITES; i++){
				s = this.activeSpriteArray[i];
				if(s != null){
					s.onDraw(canvas);
				}
			}
		} else {
			this.gameOverSprite.onDraw(canvas);
		}
	}

	private void incrementActiveSpritesIndex(){
		this.activeSpriteIndex++;
		if(this.activeSpriteIndex >= NUMBER_OF_SPRITES){
			this.activeSpriteIndex = 0;
		}
	}

	private void processTurn(IEvent event, InputControlEnum input){
		//this.economyProgressController.processEvent(event, input);// performConstruction();
	}

	private void restartGame(){
		this.economyProgressController.restartGame();
		this.numberOfActiveSprites = 0;

		for(int i = 0; i < this.activeSpriteArray.length; i++){
			this.activeSpriteArray[i] = null;
		}

		this.spriteManager.restartSprites();
	}
}
