package ivan.slavka.threads;

import ivan.slavka.abstracts.AbstractGameView;
import android.annotation.SuppressLint;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

@SuppressLint("WrongCall")
public class GameLoopThread extends Thread {

	private static long FPS = 30;

	private AbstractGameView view;
	private SurfaceHolder surfaceHolder;
	private boolean running = false;

	public GameLoopThread(AbstractGameView view, SurfaceHolder surfaceHolder) {
		this.view = view;
		this.surfaceHolder = surfaceHolder;
	}

	public void setRunning(boolean run) {
		this.running = run;
	}

	@Override
	public void run() {
		long startTime;
		long sleepTime;
		long timePassed = 0;
		while (this.running) {
			Canvas c = null;
			//limit frame rate to max 60fps
			startTime = System.currentTimeMillis();
			sleepTime = startTime - timePassed;
			if (sleepTime < 16) {
				try {
					Thread.sleep(16 - sleepTime);
				}
				catch(InterruptedException e) {

				}
			}
			timePassed = System.currentTimeMillis();

			try {
				c = this.surfaceHolder.lockCanvas();
				synchronized (this.surfaceHolder) {
					if(c != null){
						this.view.updateGame();
						this.view.onDraw(c);
					}
				}
			} finally {
				if (c != null) {
					this.surfaceHolder.unlockCanvasAndPost(c);
				}
			}
		}
	}
}