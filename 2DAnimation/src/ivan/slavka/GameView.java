package ivan.slavka;

import ivan.slavka.abstracts.AbstractGameView;
import ivan.slavka.controllers.EconomyController;
import ivan.slavka.enums.InputControlEnum;
import ivan.slavka.interfaces.IEconomyProgress;
import ivan.slavka.interfaces.IEvent;
import ivan.slavka.sprites.EconomyStatusSprite;
import ivan.slavka.sprites.Sprite;
import ivan.slavka.sprites.SpriteManager;
import ivan.slavka.sprites.WonderConstructionSprite;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.Display;
import android.view.MotionEvent;
import android.view.WindowManager;

@SuppressLint("WrongCall")
public class GameView extends AbstractGameView {
	public final static long FPS = 30;
	private final static int SPRITE_DELAY = 3000;
	private final static int NUMBER_OF_SPRITES = 30;
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
	private EconomyStatusSprite economySprite;// = new EconomyStatusSprite();
	private WonderConstructionSprite wonderSprite;

	private Paint drawPaint;

	private Sprite activeSprite = null;

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
	}

	private void startGame(){
		this.spriteStartTime = System.currentTimeMillis();
	}

	@Override
	public void updateGame(){
		long timePassed = System.currentTimeMillis() - this.spriteStartTime;
		if(timePassed > SPRITE_DELAY && this.numberOfActiveSprites < MAX_NUMBER_OF_SPRITES){// && this.activeSpriteIndex < 0){
			Sprite sprite = this.spriteManager.retrieveSprite();
			float lowerTo = LOWEST_POINT - ((this.numberOfActiveSprites) * (Sprite.HEIGHT_SIZE + SPRITE_PADDING));
			sprite.setSpriteYDestination(lowerTo);
			this.spriteStartTime = System.currentTimeMillis();

			this.activeSpriteArray[this.activeSpriteIndex] = sprite;
			this.numberOfActiveSprites++;

			this.incrementActiveSpritesIndex();
		}

		if(this.activeSprite != null){
			if(!this.activeSprite.isPerformingAnimation() && this.activeSprite.isActivated()){
				this.activeSpriteArray[this.selectedSpriteIndex] = null;
				this.numberOfActiveSprites--;

				this.processTurn(this.activeSprite.getEvent(), this.activeSprite.getSide());

				this.lowerRemainingSprites();
				this.activeSprite = null;
			}
		}
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		final int X = (int) event.getRawX();
		final int Y = (int) event.getRawY();
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
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

			break;
		case MotionEvent.ACTION_UP:
			if(this.activeSprite != null){
				this.activeSprite.spriteActionUp(X);
			}
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			break;
		case MotionEvent.ACTION_POINTER_UP:
			break;
		case MotionEvent.ACTION_MOVE:
			if(this.activeSprite != null){
				this.activeSprite.spriteActionMove(X);
			}

			break;
		}

		return true;
	}

	private void lowerRemainingSprites(){
		Sprite s;
		int spriteIndex = 0;
		for(int i = 0; i < NUMBER_OF_SPRITES; i++){
			s = this.activeSpriteArray[i];
			if(s != null){
				float lowerTo = LOWEST_POINT - (spriteIndex * (Sprite.HEIGHT_SIZE + SPRITE_PADDING));
				s.setSpriteYDestination(lowerTo);
				spriteIndex++;
			}
		}
	}

	@Override
	public void onDraw(Canvas canvas) {
		canvas.drawColor(Color.BLACK);
		this.economySprite.onDraw(canvas);
		this.wonderSprite.onDraw(canvas);
		Sprite s;
		for(int i = 0; i < NUMBER_OF_SPRITES; i++){
			s = this.activeSpriteArray[i];
			if(s != null){
				s.onDraw(canvas);
			}
		}
	}

	private void incrementActiveSpritesIndex(){
		this.activeSpriteIndex++;
		if(this.activeSpriteIndex >= NUMBER_OF_SPRITES){
			this.activeSpriteIndex = 0;
		}
	}

	private void processTurn(IEvent event, InputControlEnum input){
		this.economyProgressController.processEvent(event, input);// performConstruction();
	}
}
