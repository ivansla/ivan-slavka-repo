package ivan.slavka.sprites;

import ivan.slavka.GameView;
import ivan.slavka.interfaces.IEconomyProgress;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.Rect;
import android.util.Log;

public class EconomyStatusSprite {

	public final static int SIZE = 50;
	private static int TEXT_SPACE = 20;
	private IEconomyProgress economyProgressController;

	private PointF position;// = new PointF(200f, 100f);
	private boolean isPerformingAnimation = true;
	public volatile boolean isPressed = false;
	private boolean isReleased;
	private Rect drawingRect;
	private boolean spriteActivated;

	private volatile float x = 100;
	private volatile float y = 100;
	private GameView gameView;

	private Paint drawPaint;
	private Paint textPaint;

	public float referencePoint;

	public EconomyStatusSprite(IEconomyProgress economyProgressController) {

		this.economyProgressController = economyProgressController;

		this.isReleased = false;
		this.spriteActivated = true;
		this.referencePoint = this.x;

		this.drawPaint = new Paint();
		this.drawPaint.setColor(Color.GREEN);

		this.drawPaint.setAntiAlias(true);
		this.drawPaint.setStyle(Paint.Style.FILL);

		this.textPaint = new Paint();
		this.textPaint.setColor(Color.BLACK);
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
		canvas.drawRect(0, 0, this.drawingRect.exactCenterX() - 50, this.drawingRect.bottom - 200, this.drawPaint);
		canvas.drawText("Type", 0, TEXT_SPACE, this.textPaint);
		canvas.drawText("WOOD", 0, TEXT_SPACE * 2, this.textPaint);
		canvas.drawText("STONE", 0, TEXT_SPACE * 3, this.textPaint);
		canvas.drawText("FOOD", 0, TEXT_SPACE * 4, this.textPaint);
		canvas.drawText("BUILDERS", 0, TEXT_SPACE * 5, this.textPaint);
		canvas.drawText("SOLDIERS", 0, TEXT_SPACE * 6, this.textPaint);

		canvas.drawText("Income", 50, TEXT_SPACE, this.textPaint);
		canvas.drawText("" + this.economyProgressController.getWoodIncome(), 50, TEXT_SPACE * 2, this.textPaint);
		canvas.drawText("" + this.economyProgressController.getStoneIncome(), 50, TEXT_SPACE * 3, this.textPaint);
		canvas.drawText("" + this.economyProgressController.getFoodIncome(), 50, TEXT_SPACE * 4, this.textPaint);

		canvas.drawText("Workers", 100, TEXT_SPACE, this.textPaint);
		canvas.drawText("" + this.economyProgressController.getWoodWorkers(), 100, TEXT_SPACE * 2, this.textPaint);
		canvas.drawText("" + this.economyProgressController.getStoneWorkers(), 100, TEXT_SPACE * 3, this.textPaint);
		canvas.drawText("" + this.economyProgressController.getFoodWorkers(), 100, TEXT_SPACE * 4, this.textPaint);
		canvas.drawText("" + this.economyProgressController.getBuilders(), 100, TEXT_SPACE * 5, this.textPaint);
		canvas.drawText("" + this.economyProgressController.getSoldiers(), 100, TEXT_SPACE * 6, this.textPaint);

		canvas.drawText("Food Stored", 0, TEXT_SPACE * 7, this.textPaint);
		canvas.drawText("" + this.economyProgressController.getFoodStored(), 100, TEXT_SPACE * 7, this.textPaint);
		canvas.drawText("Turns WO food", 0, TEXT_SPACE * 8, this.textPaint);
		canvas.drawText("" + this.economyProgressController.getTurnsWithoutFood(), 100, TEXT_SPACE * 8, this.textPaint);
		canvas.drawText("Coins", 0, TEXT_SPACE * 9, this.textPaint);
		canvas.drawText("" + this.economyProgressController.getCoins(), 100, TEXT_SPACE * 9, this.textPaint);
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