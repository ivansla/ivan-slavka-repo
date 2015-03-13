package ivan.slavka;

import ivan.slavka.abstracts.AbstractGameView;
import ivan.slavka.interfaces.IViewSwitcher;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.MotionEvent;

public class StartGameView extends AbstractGameView{
	public final static long FPS = 30;
	private Paint textPaint;
	private IViewSwitcher mainViewController;

	public StartGameView(Context context, IViewSwitcher controller) {
		super(context);

		this.mainViewController = controller;

		this.textPaint = new Paint();
		this.textPaint.setColor(Color.YELLOW);
		this.textPaint.setAntiAlias(true);
		this.textPaint.setStyle(Paint.Style.FILL);
	}

	@Override
	public void updateGame(){

	}

	@Override
	public void onDraw(Canvas canvas) {
		//canvas.drawColor(Color.BLACK);
		Log.v("StartGameView", "onDraw");
		canvas.drawText("Start Game", 100, 300, this.textPaint);
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {

		final int X = (int) event.getRawX();
		final int Y = (int) event.getRawY();
		switch (event.getAction() & MotionEvent.ACTION_MASK) {
		case MotionEvent.ACTION_DOWN:
			Log.v("StartGameView", "DOWN");
			this.mainViewController.startGame();
			break;
		case MotionEvent.ACTION_UP:
			break;
		case MotionEvent.ACTION_POINTER_DOWN:
			break;
		case MotionEvent.ACTION_POINTER_UP:
			break;
		case MotionEvent.ACTION_MOVE:
			break;
		}

		return true;
	}
}
