package ivan.slavka.sprites;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.util.Log;

public class GameOverSprite {

	private static int GAME_OVER_SPRITE_SIZE = 200;
	private static int RESTART_BUTTON_POSITION_X = 50;
	private static int RESTART_BUTTON_POSITION_Y = 100;
	private static float TEXT_SIZE = 16f;

	private Paint textPaint;
	private Paint drawPaint;

	private Point position;

	public GameOverSprite(){
		this.textPaint = new Paint();
		this.textPaint.setColor(Color.BLACK);
		this.textPaint.setAntiAlias(true);
		this.textPaint.setStyle(Paint.Style.FILL);
		this.textPaint.setTextSize(TEXT_SIZE);

		this.drawPaint = new Paint();
		this.drawPaint.setColor(Color.WHITE);
		this.drawPaint.setAntiAlias(true);
		this.drawPaint.setStyle(Paint.Style.FILL);

		this.position = new Point();
		this.position.x = 0;
		this.position.y = 0;
	}

	private void update() {

	}

	public void onDraw(Canvas canvas) {
		this.update();

		canvas.drawRect(this.position.x, this.position.y, this.position.x + GAME_OVER_SPRITE_SIZE, this.position.y + GAME_OVER_SPRITE_SIZE, this.drawPaint);
		canvas.drawText("GAME OVER", this.position.x + 50, this.position.y + 50, this.textPaint);

		canvas.drawText("RESTART", this.position.x + RESTART_BUTTON_POSITION_X, this.position.y + RESTART_BUTTON_POSITION_Y, this.textPaint);
	}

	public boolean isCollition(double x2, double y2) {
		Log.v("GameOverSprite.isCollition", "x2: " + x2);
		Log.v("GameOverSprite.isCollition", "y2: " + y2);
		boolean isCollition = (x2 > this.position.x) && (x2 < this.position.x + 100) && (y2 > this.position.y + RESTART_BUTTON_POSITION_X) && (y2 < this.position.y + RESTART_BUTTON_POSITION_Y + TEXT_SIZE);
		Log.v("GameOverSprite.isCollition", "isCollition: " + isCollition);
		return isCollition;
	}

	public void setSpritePosition(int x, int y){
		this.position.x = x;
		this.position.y = y;
	}
}
