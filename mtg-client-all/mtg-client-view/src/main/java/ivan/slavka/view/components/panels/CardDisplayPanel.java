package ivan.slavka.view.components.panels;

import ivan.slavka.view.components.labels.CardDisplayLabel;

import java.awt.BorderLayout;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class CardDisplayPanel extends JPanel {

	private CardDisplayLabel pictureLabel;

	public CardDisplayPanel() {

		this.setLayout(new BorderLayout());
		this.pictureLabel = new CardDisplayLabel();
		this.add(this.pictureLabel, BorderLayout.CENTER);
	}

	public void setPicture(ImageIcon icon){

		this.pictureLabel.setIcon(icon);
		this.validate();
	}

	public void removePicture(){

		this.pictureLabel.setIcon(null);
		this.validate();
	}
}
