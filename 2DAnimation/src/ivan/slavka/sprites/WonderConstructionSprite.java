package ivan.slavka.sprites;

import ivan.slavka.GameView;
import ivan.slavka.interfaces.IWonderConstruction;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Log;

public class WonderConstructionSprite {

	private static float SWIPE_ANIMATION_TIME = 0.5f * GameView.FPS;
	private static float FALL_ANIMATION_TIME = 3f * GameView.FPS;
	public final static int SIZE = 50;
	private static double GRAVITY = 0.981f;
	private static int TEXT_SPACE = 20;

	private Lock spriteLock = new ReentrantLock();

	//private static float SWIPE_ACCELERATION_ANIMATION_TIME = SWIPE_ANIMATION_TIME * SWIPE_ANIMATION_TIME * 30 * 30;

	private IWonderConstruction wonderConstructionController;


	//private float distanceY;
	private PointF position;// = new PointF(200f, 100f);
	private float deltaX;
	private boolean isClicked = false;
	private int side;
	private boolean isPerformingAnimation = true;
	private boolean isInTolerance = true;
	private double acceleration = 0d;
	public volatile boolean isPressed = false;
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
	private float spriteDestinationX;
	private float spriteDestinationY;


	private long elapsedTime;
	private float distance;

	public WonderConstructionSprite(IWonderConstruction wonderConstructionController) {

		this.wonderConstructionController = wonderConstructionController;

		this.alpha = 255;
		this.isReleased = false;
		//this.spriteDestinationX = this.position.x;
		this.spriteActivated = true;
		this.referencePoint = this.x;

		this.alphaIncrement = (int) (255 / SWIPE_ANIMATION_TIME);
		//this.bmp = bmp;
		//this.width = bmp.getWidth() / BMP_COLUMNS;
		//this.height = bmp.getHeight() / BMP_ROWS;

		this.drawPaint = new Paint();
		this.drawPaint.setColor(Color.BLUE);

		this.drawPaint.setAntiAlias(true);
		//this.drawPaint.setStrokeWidth(20);
		this.drawPaint.setStyle(Paint.Style.FILL);

		this.textPaint = new Paint();
		this.textPaint.setColor(Color.YELLOW);
		this.textPaint.setAntiAlias(true);
		this.textPaint.setStyle(Paint.Style.FILL);
	}

	public void prepareSprite(GameView gameView, Bitmap bmp){
		if(this.gameView == null){

			this.gameView = gameView;
			this.drawingRect = new Rect();
			gameView.getWindowVisibleDisplayFrame(this.drawingRect);
			this.position = new PointF((this.drawingRect.exactCenterX() - (SIZE * 0.5f)), 0f);
			Log.v("prepareSprite", "this.position.x :" + this.position.x);
		}
	}

	private void update() {
		this.spriteActivated = false;
	}

	public void onDraw(Canvas canvas) {
		this.update();
		canvas.drawRect(this.drawingRect.exactCenterX() + 50, 0, this.drawingRect.right , this.drawingRect.bottom - 200, this.drawPaint);
		canvas.drawText("Wonder Completed", this.drawingRect.exactCenterX() + 50, TEXT_SPACE, this.textPaint);
		canvas.drawText("" + this.wonderConstructionController.getCompletedPct() + " %", this.drawingRect.exactCenterX() + 50, TEXT_SPACE * 2, this.textPaint);
		canvas.drawText("Resources Needed", this.drawingRect.exactCenterX() + 50, TEXT_SPACE * 3, this.textPaint);
		canvas.drawText("Wood", this.drawingRect.exactCenterX() + 50, TEXT_SPACE * 4, this.textPaint);
		canvas.drawText("Stone", this.drawingRect.exactCenterX() + 50, TEXT_SPACE * 5, this.textPaint);
		canvas.drawText("" + this.wonderConstructionController.getWoodNeeded(), this.drawingRect.exactCenterX() + 100, TEXT_SPACE * 4, this.textPaint);
		canvas.drawText("" + this.wonderConstructionController.getStoneNeeded(), this.drawingRect.exactCenterX() + 100, TEXT_SPACE * 5, this.textPaint);

		canvas.drawText("Resources Stored", this.drawingRect.exactCenterX() + 50, TEXT_SPACE * 6, this.textPaint);
		canvas.drawText("Wood", this.drawingRect.exactCenterX() + 50, TEXT_SPACE * 7, this.textPaint);
		canvas.drawText("Stone", this.drawingRect.exactCenterX() + 50, TEXT_SPACE * 8, this.textPaint);

		canvas.drawText("" + this.wonderConstructionController.getWoodStored(), this.drawingRect.exactCenterX() + 100, TEXT_SPACE * 7, this.textPaint);
		canvas.drawText("" + this.wonderConstructionController.getStoneStored(), this.drawingRect.exactCenterX() + 100, TEXT_SPACE * 8, this.textPaint);

	}

	public boolean isCollition(double x2, double y2) {
		boolean isCollition = x2 > this.position.x && x2 < this.position.x + SIZE && y2 > this.position.y && y2 < this.position .y + SIZE;
		return isCollition;
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
}