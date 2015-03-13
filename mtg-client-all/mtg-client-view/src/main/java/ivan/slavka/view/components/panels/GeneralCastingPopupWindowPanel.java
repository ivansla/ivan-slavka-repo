package ivan.slavka.view.components.panels;

import ivan.slavka.utils.beans.wrappers.CardBeanWrapper;
import ivan.slavka.view.components.buttons.StartStopTimerButton;
import ivan.slavka.view.components.listeners.StartStopTimerButtonListener;
import ivan.slavka.view.components.progressbars.InstantCastingProgressBar;
import ivan.slavka.view.components.window.CastingPopupWindow;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class GeneralCastingPopupWindowPanel extends JPanel{

	private final CastingPopupWindow popupWindow;

	private JLabel castingCard;
	private InstantCastingProgressBar progressBar;
	private StartStopTimerButton startStopTimerButton;

	public GeneralCastingPopupWindowPanel(CastingPopupWindow popupWindow) {
		this.setLayout(new MigLayout("", "[]", "[][][]"));

		this.popupWindow = popupWindow;

		this.initComponents();
	}

	private void initComponents(){

		this.progressBar = new InstantCastingProgressBar();
		this.add(this.progressBar, "cell 0 0");

		this.castingCard = new JLabel();
		this.add(this.castingCard, "cell 0 1");

		this.startStopTimerButton = new StartStopTimerButton(this.popupWindow.getInstantCastingTimer());
		this.startStopTimerButton.addActionListener(new StartStopTimerButtonListener(this.startStopTimerButton));
		this.add(this.startStopTimerButton, "cell 0 2");
	}

	public void castCard(CardBeanWrapper cardBean){
		this.castingCard.setIcon(cardBean.getIcon());
		this.validate();
	}

	public InstantCastingProgressBar getProgressBar() {
		return this.progressBar;
	}
}
