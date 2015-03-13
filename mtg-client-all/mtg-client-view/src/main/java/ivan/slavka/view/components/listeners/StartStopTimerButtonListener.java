package ivan.slavka.view.components.listeners;

import ivan.slavka.view.components.buttons.StartStopTimerButton;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StartStopTimerButtonListener implements ActionListener{

	private final StartStopTimerButton startStopTimerButton;

	public StartStopTimerButtonListener(StartStopTimerButton startStopTimerButton){
		this.startStopTimerButton = startStopTimerButton;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(this.startStopTimerButton.isRunning()){
			this.startStopTimerButton.stopTimer();
		} else {
			this.startStopTimerButton.restartTimer();
		}
	}

}
