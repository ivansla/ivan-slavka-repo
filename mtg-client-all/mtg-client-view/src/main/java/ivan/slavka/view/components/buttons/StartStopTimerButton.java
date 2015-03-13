package ivan.slavka.view.components.buttons;

import javax.swing.JButton;
import javax.swing.Timer;

public class StartStopTimerButton extends JButton{

	private final Timer instantCastingTimer;

	private boolean running = true;

	public StartStopTimerButton(Timer instantCastingTimer){
		this.setText("Stop Timer");

		this.instantCastingTimer = instantCastingTimer;
	}

	public Timer getInstantCastingTimer() {
		return this.instantCastingTimer;
	}

	public void stopTimer(){
		this.setText("Continue");
		this.running = false;
		this.instantCastingTimer.stop();
	}

	public void restartTimer(){
		this.setText("Stop Time");
		this.running = true;
		this.instantCastingTimer.restart();
	}

	public boolean isRunning() {
		return this.running;
	}
}
