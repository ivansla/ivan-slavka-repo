package ivan.slavka.threads;

import ivan.slavka.abstracts.AbstractGameView;
import android.annotation.SuppressLint;
import android.graphics.Canvas;

@SuppressLint("WrongCall")
public class GameLoopThread extends Thread {

	//private static GameLoopThread INSTANCE = null;

	private long fps;// = 30;
	private AbstractGameView view;
	private boolean running = false;

	public GameLoopThread(AbstractGameView view, long fps) {
		this.view = view;
		this.fps = fps;
	}

	public void setRunning(boolean run) {
		this.running = run;
	}

	@Override
	public void run() {
		long ticksPS = 1000 / this.fps;
		long startTime;
		long sleepTime;
		while (this.running) {
			Canvas c = null;
			startTime = System.currentTimeMillis();
			try {
				c = this.view.getHolder().lockCanvas();
				synchronized (this.view.getHolder()) {
					this.view.updateGame();
					this.view.onDraw(c);
				}
			} finally {
				if (c != null) {
					this.view.getHolder().unlockCanvasAndPost(c);
				}
			}
			sleepTime = ticksPS-(System.currentTimeMillis() - startTime);
			try {
				if (sleepTime > 0)
					sleep(sleepTime);
				else
					sleep(10);
			} catch (Exception e) {}
		}
	}
}