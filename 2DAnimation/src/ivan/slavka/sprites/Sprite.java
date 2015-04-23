package ivan.slavka.sprites;

import ivan.slavka.GameView;
import ivan.slavka.enums.EventSpriteEnum;
import ivan.slavka.enums.InputControlEnum;
import ivan.slavka.interfaces.IEvent;

import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;

public class Sprite {

	private IEvent event;

	private static float SWIPE_ANIMATION_TIME = 0.5f * GameView.FPS;
	private static float FALL_ANIMATION_TIME = 2f * GameView.FPS;
	public final static int WIDTH_SIZE = 128;
	public final static int HEIGHT_SIZE = 128;
	private static double GRAVITY = 0.981f;
	private static int TOLERANCE = 50;

	private Lock spriteLock = new ReentrantLock();

	//private static float SWIPE_ACCELERATION_ANIMATION_TIME = SWIPE_ANIMATION_TIME * SWIPE_ANIMATION_TIME * 30 * 30;


	//private float distanceY;
	private boolean isLowestSprite = false;
	private Bitmap[] bitmaps;
	private float deltaX;
	private int side;
	private boolean isPerformingAnimation = true;
	private boolean isInTolerance = true;
	private boolean isMoved = false;
	private double acceleration = 0d;
	private double velocityX = 0d;
	private float velocityY = 0f;
	private boolean isReleased;
	private int alphaIncrement;
	private int alpha;
	private Rect drawingRect;
	private boolean spriteActivated;

	private volatile float x = 100;
	private volatile float y = 100;
	private GameView gameView;
	private Bitmap bmp;
	private int currentFrame = 0;

	private Paint drawPaint;
	private Paint textPaint;

	public float referencePoint;
	private PointF position;// = new PointF(200f, 100f);

	public volatile boolean isPressed = false;
	private volatile boolean isClicked = false;
	private volatile float positionX;
	private volatile float positionY;
	private volatile float spriteDestinationX;
	private float spriteDestinationY;


	private long elapsedTime;
	private float distance;

	public Sprite() {

		this.bitmaps = new Bitmap[3];

		this.alpha = 255;
		this.isReleased = false;
		//this.spriteDestinationX = this.positionX;
		this.spriteActivated = false;
		this.referencePoint = this.x;

		this.alphaIncrement = (int) (255 / SWIPE_ANIMATION_TIME);
		//this.bmp = bmp;
		//this.width = bmp.getWidth() / BMP_COLUMNS;
		//this.height = bmp.getHeight() / BMP_ROWS;

		this.drawPaint = new Paint();
		this.drawPaint.setColor(Color.WHITE);

		this.drawPaint.setAntiAlias(true);
		//this.drawPaint.setStrokeWidth(20);
		this.drawPaint.setStyle(Paint.Style.FILL);

		this.textPaint = new Paint();
		this.textPaint.setColor(Color.YELLOW);
		this.textPaint.setAntiAlias(true);
		this.textPaint.setStyle(Paint.Style.FILL);
	}

	public void prepareSprite(GameView gameView, Bitmap barqueBitmap){
		if(this.gameView == null){

			this.gameView = gameView;
			this.drawingRect = new Rect();
			gameView.getWindowVisibleDisplayFrame(this.drawingRect);
			this.positionX = (this.drawingRect.exactCenterX() - (WIDTH_SIZE * 0.5f));
			this.positionY = 0f;

			//this.position = new PointF((this.drawingRect.exactCenterX() - (WIDTH_SIZE * 0.5f)), 0f);
			this.bitmaps[2] = barqueBitmap;
		}
	}

	public void prepareSpriteBitmaps(Map<EventSpriteEnum, Bitmap> eventSpriteBitmapMap){
		this.bitmaps[0] = eventSpriteBitmapMap.get(this.getEvent().getPrimaryEffect().getEventName());
		this.bitmaps[1] = eventSpriteBitmapMap.get(this.getEvent().getSecondaryEffect().getEventName());
	}

	private synchronized void update() {

		if(!this.isPressed && !this.isClicked){
			if(this.spriteDestinationY >= this.positionY + this.velocityY){
				this.positionY += this.velocityY;
				this.isPerformingAnimation = true;
			} else {
				this.positionY = this.spriteDestinationY;
				this.isPerformingAnimation = false;
			}
		}

		if(this.isPressed){
			this.positionX = this.spriteDestinationX;
		}

		if(!this.isPressed && this.isClicked){
			this.isPerformingAnimation = true;
			if(this.isInTolerance){
				//Log.v("Sprite.update", "isInTolerance");
				//Log.v("Sprite.update", "isLowestSprite: " + this.isLowestSprite);
				this.positionX -= this.velocityX;
				this.velocityX -= this.acceleration;

				if(this.side == -1 && this.positionX + this.deltaX >= this.referencePoint){
					this.isClicked = false;
					this.positionX = this.referencePoint - this.deltaX;
					this.spriteDestinationX = this.positionX;
					this.isPerformingAnimation = false;
					this.velocityX = 0d;
				}
				if(this.side == 1 && this.positionX + this.deltaX <= this.referencePoint){
					this.isClicked = false;
					this.positionX = this.referencePoint - this.deltaX;
					this.spriteDestinationX = this.positionX;
					this.isPerformingAnimation = false;
					this.velocityX = 0d;
				}
				if(this.side == 0){
					this.isClicked = false;
				}
			} else {
				this.alpha -= this.alphaIncrement;
				this.drawPaint.setAlpha(this.alpha);
				this.positionX += this.velocityX;
				this.velocityX += this.acceleration;
				//Log.v("Sprite.update", "else");
				//Log.v("Sprite.update", "isLowestSprite: " + this.isLowestSprite);
				if(this.alpha <= 0){
					this.isPerformingAnimation = false;
				}
			}
		}
	}

	public void setSpritePath(float x, int y){
		this.spriteDestinationX = x - this.deltaX;
	}

	public synchronized void spriteActionDown(float x){
		if(!this.isLowestSprite){
			return;
		}

		if(!this.isPerformingAnimation){
			this.side = 0;
			this.isPressed = true;
			this.deltaX = x - this.positionX;
			this.referencePoint = x;
			//this.isClicked = true;
			this.setSpritePath(x, 0);
		}
	}

	public synchronized void spriteActionUp(float x){
		if(!this.isLowestSprite){
			return;
		}

		if(!this.isPerformingAnimation){
			this.isClicked = true;
			this.isPressed = false;
			if(!this.isMoved){
				this.isClicked = false;
			}

			if(this.isInTolerance){
				this.distance = this.positionX + this.deltaX - this.referencePoint;
				this.acceleration = (2 * this.distance) / (SWIPE_ANIMATION_TIME * SWIPE_ANIMATION_TIME);  //(this.distance / (ANIMATION_TIME * ANIMATION_TIME * 30 * 30));
				this.velocityX = this.acceleration * (SWIPE_ANIMATION_TIME);

				if(this.velocityX < 0){
					this.side = -1;
				} else if(this.velocityX > 0){
					this.side = 1;
				}
			} else {

				if(this.isGoingLeft(x)){
					this.distance = this.positionX + this.deltaX;
					this.acceleration = (2 * (-this.distance)) / (SWIPE_ANIMATION_TIME * SWIPE_ANIMATION_TIME);  //(this.distance / (ANIMATION_TIME * ANIMATION_TIME * 30 * 30));
				}

				if(this.isGoingRight(x)){
					this.distance = this.drawingRect.width() - (this.positionX + this.deltaX);
					this.acceleration = (2 * this.distance) / (SWIPE_ANIMATION_TIME * SWIPE_ANIMATION_TIME);  //(this.distance / (ANIMATION_TIME * ANIMATION_TIME * 30 * 30));
				}

				this.spriteActivated = true;
				this.isMoved = false;
			}

			this.isPerformingAnimation = true;
		}

	}

	public synchronized void spriteActionMove(float x){
		if(!this.isPerformingAnimation && this.isLowestSprite){
			this.isMoved = true;
			this.setSpritePath(x, 0);
			this.isInTolerance = this.calculateIsInTolerance(x);
		}
	}

	public void onDraw(Canvas canvas) {
		this.update();
		//canvas.drawRect(this.positionX, this.positionY, this.positionX + WIDTH_SIZE, this.positionY + HEIGHT_SIZE, this.drawPaint);
		canvas.drawBitmap(this.bitmaps[2], this.positionX, this.positionY, null);
		if(this.event.isSpecialEvent()){
			canvas.drawBitmap(this.bitmaps[0], this.positionX, this.positionY, null);
		} else {
			canvas.drawBitmap(this.bitmaps[0], this.positionX, this.positionY, null);
			canvas.drawBitmap(this.bitmaps[1], (this.positionX + (WIDTH_SIZE * 0.5f)), this.positionY, null);
		}
	}

	public boolean isCollition(double x2, double y2) {
		boolean isCollition = x2 > this.positionX && x2 < this.positionX + WIDTH_SIZE && y2 > this.positionY && y2 < this.positionY + HEIGHT_SIZE;
		return isCollition;
	}

	/*
	public void spritePerformAction(){
		this.input = InputControlEnum.RIGHT;
	}

	public boolean isInTolerance(){
		return this.isInTolerance;
	}
	 */

	private boolean calculateIsInTolerance(float actualPoint){
		int subtraction = Math.abs((int)(this.referencePoint - actualPoint));
		if(subtraction <= TOLERANCE){
			return true;
		}
		return false;
	}

	private boolean isGoingLeft(float actualPoint){
		if(this.referencePoint > actualPoint){
			this.side = -1;
			return true;
		}
		return false;
	}

	private boolean isGoingRight(float actualPoint){
		if(this.referencePoint < actualPoint){
			this.side = 1;
			return true;
		}
		return false;
	}

	private void resetSpriteAlpha(){
		this.alpha = 255;
	}

	public void setSpriteYDestination(float y){
		float distanceY = y;// - this.positionY;
		this.spriteLock.lock();
		this.velocityY = distanceY / FALL_ANIMATION_TIME;
		this.spriteDestinationY = y;
		this.spriteLock.unlock();
	}

	public boolean isReleased(){
		return this.isReleased;
	}

	public void setReleased(boolean released){
		this.isReleased = released;
	}

	public boolean isActivated(){
		return this.spriteActivated;
	}

	public void deactivateSprite(){
		this.spriteActivated = false;
	}

	public boolean isPerformingAnimation(){
		return this.isPerformingAnimation;
	}

	public IEvent getEvent() {
		return this.event;
	}

	public void setEvent(IEvent event) {
		this.event = event;
	}

	public InputControlEnum getSide(){
		if(this.side == -1){
			return InputControlEnum.LEFT;
		} else {
			return InputControlEnum.RIGHT;
		}
	}

	public boolean isLowestSprite() {
		return this.isLowestSprite;
	}

	public void setLowestSprite(boolean isLowestSprite) {
		this.isLowestSprite = isLowestSprite;
	}
}