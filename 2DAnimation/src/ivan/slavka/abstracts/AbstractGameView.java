package ivan.slavka.abstracts;

import ivan.slavka.threads.GameLoopThread;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public abstract class AbstractGameView extends SurfaceView{

	public final static long FPS = 30;
	private Paint textPaint;
	private SurfaceHolder holder;
	private GameLoopThread gameLoopThread;

	public AbstractGameView(Context context) {
		super(context);

		this.gameLoopThread = new GameLoopThread(this, FPS);

		this.holder = this.getHolder();
		this.holder.addCallback(new SurfaceHolder.Callback() {

			@Override
			public void surfaceDestroyed(SurfaceHolder holder) {
				boolean retry = true;
				AbstractGameView.this.gameLoopThread.setRunning(false);
				while (retry) {
					try {
						AbstractGameView.this.gameLoopThread.join();
						retry = false;
					} catch (InterruptedException e) {
					}
				}
			}

			@Override
			public void surfaceCreated(SurfaceHolder holder) {
				AbstractGameView.this.gameLoopThread.setRunning(true);
				AbstractGameView.this.gameLoopThread.start();
			}

			@Override
			public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
				Log.v("surfaceChanged", "surface has changed");
				//GameView.this.canvasBitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
				//GameView.this.drawCanvas = new Canvas(GameView.this.canvasBitmap);
				//GameView.this.drawCanvas.drawColor(Color.TRANSPARENT);
				//GameView.this.bmp = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
			}
		});
	}

	public abstract void updateGame();

	@Override
	public abstract void onDraw(Canvas canvas);
}
