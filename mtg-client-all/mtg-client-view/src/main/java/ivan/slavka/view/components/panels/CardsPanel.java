package ivan.slavka.view.components.panels;

import ivan.slavka.utils.beans.wrappers.CardBeanWrapper;
import ivan.slavka.utils.observers.interfaces.ICardsPanel;
import ivan.slavka.view.components.buttons.MiniCardPanelCardButton;
import ivan.slavka.view.panels.MTGClientPanel;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class CardsPanel extends JPanel implements ICardsPanel{

	private final CardDisplayPanel cardDisplayPanel;
	private final MiniCardPanel miniCardPanel;

	private final MTGClientPanel controller;

	public CardsPanel(MTGClientPanel controller) {
		this.controller = controller;

		this.setLayout(new BorderLayout(0, 0));

		this.miniCardPanel = new MiniCardPanel();
		this.miniCardPanel.setPreferredSize(new Dimension(350, 70));
		this.cardDisplayPanel = new CardDisplayPanel();
		this.cardDisplayPanel.setPreferredSize(new Dimension(350, 450));

		this.add(this.miniCardPanel, BorderLayout.NORTH);
		this.add(this.cardDisplayPanel, BorderLayout.CENTER);
	}

	@Override
	public void showCard(ImageIcon image) {
		this.cardDisplayPanel.setPicture(image);
	}

	@Override
	public void hideCard() {
		this.cardDisplayPanel.removePicture();
	}

	@Override
	public void addCard(CardBeanWrapper cardBean) {
		this.miniCardPanel.addCard(new MiniCardPanelCardButton(this.controller, cardBean));
	}

	@Override
	public void playCard(CardBeanWrapper cardBean) {
		this.miniCardPanel.removeCard(cardBean);
		this.controller.playCard(cardBean);
	}
}
