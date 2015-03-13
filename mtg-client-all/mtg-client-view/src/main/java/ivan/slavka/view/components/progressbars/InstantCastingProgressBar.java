package ivan.slavka.view.components.progressbars;

import javax.swing.JProgressBar;

public class InstantCastingProgressBar extends JProgressBar{

	private int progress = 0;

	public InstantCastingProgressBar(){
		this.setMinimum(0);
		this.setMaximum(60);
	}

	public void incrementProgressBar(){
		this.setValue(this.progress++);
		this.repaint();
	}

	public void resetProgressBar(){
		this.resetProgressBar();
		this.progress = 0;
	}

	public boolean isFinished(){
		if(this.progress >= 60){
			this.progress = 0;
			return true;
		}
		return false;
	}
}
