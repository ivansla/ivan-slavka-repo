package ivan.slavka.view.components.panels;

import ivan.slavka.view.components.buttons.MiniCardPanelCardButton;
import ivan.slavka.view.components.window.SpendManaPopupWindow;
import ivan.slavka.view.panels.MTGClientPanel;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

public class SpendManaPopupWindowPanel extends JPanel {

	private JButton subtractManaButton;
	private JButton addManaButton;
	private JButton okButton;
	private JButton cancelButton;
	private JLabel mana;
	private JLabel titleLabel;
	private int manaAmount = 0;
	private int maxManaAmount = 0;

	private final MiniCardPanelCardButton miniCardPanelCardButton;
	private final SpendManaPopupWindow popupWindow;
	private final MTGClientPanel viewController;

	public SpendManaPopupWindowPanel(SpendManaPopupWindow popupWindow, MiniCardPanelCardButton miniCardPanelCardButton, MTGClientPanel viewController) {
		this.setLayout(new MigLayout("", "[grow][][grow]", "[][][]"));
		this.miniCardPanelCardButton = miniCardPanelCardButton;
		this.popupWindow = popupWindow;
		this.viewController = viewController;
		this.maxManaAmount = this.viewController.getController().getAvailableMana();

		this.initComponents();
		this.setBorder(new TitledBorder(""));
	}

	private void initComponents(){

		this.titleLabel = new JLabel("Select amount of Mana to spend: ");
		this.add(this.titleLabel, "cell 0 0 3 1, alignx center");

		this.subtractManaButton = new JButton("-");
		this.subtractManaButton.addActionListener(new ManaSpendingActionListener(this, ManaAdditionEnum.SUBTRACT));
		this.add(this.subtractManaButton, "cell 0 1, alignx right");

		this.mana = new JLabel("0");
		this.add(this.mana, "cell 1 1, alignx center");

		this.addManaButton = new JButton("+");
		this.addManaButton.addActionListener(new ManaSpendingActionListener(this, ManaAdditionEnum.ADD));
		this.add(this.addManaButton, "cell 2 1, alignx left");

		this.okButton = new JButton("OK");
		this.okButton.setPreferredSize(new Dimension(100, 25));
		this.okButton.addActionListener(new ConfirmationActionListener(this, ButtonActionEnum.OK));
		this.add(this.okButton, "cell 0 2, alignx right");

		this.cancelButton = new JButton("Cancel");
		this.cancelButton.setPreferredSize(new Dimension(100, 25));
		this.cancelButton.addActionListener(new ConfirmationActionListener(this, ButtonActionEnum.CANCEL));
		this.add(this.cancelButton, "cell 2 2, alignx left");
	}

	public void addMana(){
		if(this.manaAmount < this.maxManaAmount){
			this.manaAmount++;
			this.mana.setText(String.valueOf(this.manaAmount));
			this.repaint();
		}
	}

	public void subtractMana(){
		if(this.manaAmount > 0){
			this.manaAmount--;
			this.mana.setText(String.valueOf(this.manaAmount));
			this.repaint();
		}
	}

	public void confirm(int mana){
		this.miniCardPanelCardButton.playCard(mana);
		this.popupWindow.setVisible(false);
		this.popupWindow.dispose();
	}

	public void cancel(){
		this.popupWindow.setVisible(false);
		this.popupWindow.dispose();
	}

	protected enum ButtonActionEnum{

		OK,
		CANCEL;
	}

	protected enum ManaAdditionEnum{

		ADD,
		SUBTRACT;
	}

	class ManaSpendingActionListener implements ActionListener{

		private final SpendManaPopupWindowPanel panel;
		private final ManaAdditionEnum manaAdditionEnum;

		public ManaSpendingActionListener(SpendManaPopupWindowPanel panel, ManaAdditionEnum manaAdditionEnum){
			this.panel = panel;
			this.manaAdditionEnum = manaAdditionEnum;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			switch(this.manaAdditionEnum){
			case ADD:
				this.panel.addMana();
				break;
			case SUBTRACT:
				this.panel.subtractMana();
				break;
			}
		}
	}

	class ConfirmationActionListener implements ActionListener{

		private final SpendManaPopupWindowPanel panel;
		private final ButtonActionEnum buttonActionEnum;

		public ConfirmationActionListener(SpendManaPopupWindowPanel panel, ButtonActionEnum buttonActionEnum){
			this.panel = panel;
			this.buttonActionEnum = buttonActionEnum;
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			switch(this.buttonActionEnum){
			case OK:
				this.panel.confirm(SpendManaPopupWindowPanel.this.manaAmount);
				break;
			case CANCEL:
				this.panel.cancel();
				break;
			}
		}
	}
}
