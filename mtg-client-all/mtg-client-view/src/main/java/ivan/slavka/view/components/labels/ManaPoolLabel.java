package ivan.slavka.view.components.labels;

import javax.swing.JLabel;

public class ManaPoolLabel extends JLabel{

	public ManaPoolLabel(){
		this.setText("0 / 0");
	}

	public void setManaPool(int availableMana, int totalMana) {
		this.setText(availableMana + " / " + totalMana);
		this.repaint();
	}
}
