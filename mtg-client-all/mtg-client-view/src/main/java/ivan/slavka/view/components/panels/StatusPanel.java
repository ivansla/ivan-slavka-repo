package ivan.slavka.view.components.panels;

import ivan.slavka.utils.beans.ManaPoolBean;
import ivan.slavka.utils.enums.PlayerEnum;
import ivan.slavka.view.components.buttons.AddCardButton;
import ivan.slavka.view.components.buttons.EndPhaseButton;
import ivan.slavka.view.components.labels.ManaPoolLabel;
import ivan.slavka.view.components.labels.ManaSymbolLabel;
import ivan.slavka.view.panels.MTGClientPanel;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import net.miginfocom.swing.MigLayout;

public class StatusPanel extends JPanel{

	private final MTGClientPanel controller;
	private JLabel turnLabel = new JLabel();

	private ManaPoolLabel blackManaAmountLabel;
	private ManaPoolLabel blueManaAmountLabel;
	private ManaPoolLabel whiteManaAmountLabel;
	private ManaPoolLabel greenManaAmountLabel;
	private ManaPoolLabel redManaAmountLabel;

	public StatusPanel(MTGClientPanel controller) {

		this.controller = controller;

		this.setLayout(new MigLayout("", "[][][][]", "[][][][][]"));
		this.initComponents();
	}

	private void initComponents(){

		AddCardButton addPlayerCardButton = new AddCardButton(this.controller, PlayerEnum.PLAYER);
		addPlayerCardButton.setText("Add Player Card");
		this.add(addPlayerCardButton, "cell 0 0,grow");

		ManaSymbolLabel blackManaSymbolLabel = new ManaSymbolLabel(ManaSymbolLabel.BLACK_MANA_SYMBOL);
		this.add(blackManaSymbolLabel, "cell 2 0");

		this.blackManaAmountLabel = new ManaPoolLabel();
		this.blackManaAmountLabel.setHorizontalAlignment(SwingConstants.CENTER);
		this.add(this.blackManaAmountLabel, "cell 3 0");

		ManaSymbolLabel redManaSymbolLabel = new ManaSymbolLabel(ManaSymbolLabel.RED_MANA_SYMBOL);
		this.add(redManaSymbolLabel, "cell 2 1");

		this.redManaAmountLabel = new ManaPoolLabel();
		this.add(this.redManaAmountLabel, "cell 3 1");

		ManaSymbolLabel blueManaSymbolLabel = new ManaSymbolLabel(ManaSymbolLabel.BLUE_MANA_SYMBOL);
		this.add(blueManaSymbolLabel, "cell 2 2");

		this.blueManaAmountLabel = new ManaPoolLabel();
		this.add(this.blueManaAmountLabel, "cell 3 2");

		ManaSymbolLabel whiteManaSymbolLabel = new ManaSymbolLabel(ManaSymbolLabel.WHITE_MANA_SYMBOL);
		this.add(whiteManaSymbolLabel, "cell 2 3");

		this.whiteManaAmountLabel = new ManaPoolLabel();
		this.add(this.whiteManaAmountLabel, "cell 3 3");

		ManaSymbolLabel greenManaSymbolLabel = new ManaSymbolLabel(ManaSymbolLabel.GREEN_MANA_SYMBOL);
		this.add(greenManaSymbolLabel, "cell 2 4");

		AddCardButton addOpponentCardButton = new AddCardButton(this.controller, PlayerEnum.OPPONENT);
		addOpponentCardButton.setText("Add Opponent Card");
		this.add(addOpponentCardButton, "cell 0 1,grow");

		EndPhaseButton endPhaseButton = new EndPhaseButton(this.controller, this);
		endPhaseButton.setText("Continue");
		this.add(endPhaseButton, "cell 0 2,grow");

		this.add(this.turnLabel, "cell 1 2");

		this.greenManaAmountLabel = new ManaPoolLabel();
		this.add(this.greenManaAmountLabel, "cell 3 4");
	}

	public JLabel getTurnLabel() {
		return this.turnLabel;
	}

	public void setTurnLabel(String turnLabel) {
		this.turnLabel.setText(turnLabel);
	}

	public void updateManaPool(ManaPoolBean manaPool){
		this.blackManaAmountLabel.setManaPool(manaPool.getAvailableBlackMana(), manaPool.getTotalBlackMana());
		this.blueManaAmountLabel.setManaPool(manaPool.getAvailableBlueMana(), manaPool.getTotalBlueMana());
		this.greenManaAmountLabel.setManaPool(manaPool.getAvailableGreenMana(), manaPool.getTotalGreenMana());
		this.redManaAmountLabel.setManaPool(manaPool.getAvailableRedMana(), manaPool.getTotalRedMana());
		this.whiteManaAmountLabel.setManaPool(manaPool.getAvailableWhiteMana(), manaPool.getTotalWhiteMana());
	}
}
