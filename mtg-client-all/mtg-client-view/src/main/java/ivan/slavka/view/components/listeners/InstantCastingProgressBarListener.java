package ivan.slavka.view.components.listeners;

import ivan.slavka.view.components.progressbars.InstantCastingProgressBar;
import ivan.slavka.view.components.window.CastingPopupWindow;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InstantCastingProgressBarListener implements ActionListener{

	private final InstantCastingProgressBar progressBar;
	private final CastingPopupWindow popupWindow;

	public InstantCastingProgressBarListener(InstantCastingProgressBar progressBar, CastingPopupWindow popupWindow){
		this.progressBar = progressBar;
		this.popupWindow = popupWindow;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		this.progressBar.incrementProgressBar();
		if(this.progressBar.isFinished()){
			this.popupWindow.hideWindow();
		}
	}

}
