package ivan.slavka.abstracts;

import ivan.slavka.threads.GameLoopThread;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public abstract class AbstractGameView extends SurfaceView implements SurfaceHolder.Callback{

	public final static long FPS = 30;
	private Paint textPaint;
	private SurfaceHolder holder;
	private GameLoopThread gameLoopThread;

	public AbstractGameView(Context context) {
		super(context);


		this.getHolder().addCallback(this);
	}

	public abstract void updateGame();

	@Override
	public abstract void onDraw(Canvas canvas);

	@Override
	public void onSizeChanged (int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,	int height) {
		// TODO Auto-generated method stub

	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		this.gameLoopThread.setRunning(false);
		while (retry) {
			try {
				this.gameLoopThread.join();
				retry = false;
			} catch (InterruptedException e) {
			}
		}
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		this.gameLoopThread = new GameLoopThread(this, this.getHolder());
		this.gameLoopThread.setRunning(true);
		this.gameLoopThread.start();

		//AbstractGameView.this.gameLoopRunnable.setRunning(true);
		//AbstractGameView.this.gameLoopRunnable.start();
	}
}
