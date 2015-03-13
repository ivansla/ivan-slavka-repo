package ivan.slavka.view.panels;


import ivan.slavka.utils.beans.TurnStatusBean;
import ivan.slavka.utils.beans.wrappers.CardBeanWrapper;
import ivan.slavka.utils.enums.PlayerEnum;
import ivan.slavka.utils.interfaces.IControl;
import ivan.slavka.utils.interfaces.IMTGClientView;
import ivan.slavka.view.components.buttons.CreatureButton;
import ivan.slavka.view.components.panels.CardsPanel;
import ivan.slavka.view.components.window.CastingPopupWindow;

import javax.swing.JFrame;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class MTGClientPanel extends JPanel implements IMTGClientView{

	private PlayerEnum turnOwner = PlayerEnum.PLAYER;
	private CreatureButton selectedButton = null;

	private final StatusAndCardDisplayPanel statusAndCardDisplayPanel;
	private final PlayTablePanel playTablePanel;
	private final CastingPopupWindow castingPopupWindow;

	private final IControl controller;

	public MTGClientPanel(IControl controller) {
		this.controller = controller;

		this.setLayout(new MigLayout("", "[350][grow]", "[grow]"));

		this.statusAndCardDisplayPanel = new StatusAndCardDisplayPanel(this);
		this.add(this.statusAndCardDisplayPanel, "cell 0 0,grow");

		this.playTablePanel = new PlayTablePanel(this);
		this.add(this.playTablePanel, "cell 1 0,grow");

		this.castingPopupWindow = CastingPopupWindow.getInstance(this.playTablePanel);
	}

	@Override
	public void playCard(CardBeanWrapper cardBean) {
		this.statusAndCardDisplayPanel.getStatusPanel().updateManaPool(this.controller.playCard(cardBean));
		this.playTablePanel.playCard(cardBean, PlayerEnum.PLAYER);
	}

	@Override
	public void addCard(PlayerEnum playerEnum) {
		CardBeanWrapper cardBean = this.controller.addCard();

		switch(playerEnum){
		case PLAYER:
			this.statusAndCardDisplayPanel.addCardToMiniCardPanel(cardBean);
			break;
		case OPPONENT:
			this.playTablePanel.playCard(cardBean, playerEnum);
			break;
		}
	}

	@Override
	public void attack(CardBeanWrapper cardBean) {
		// TODO Auto-generated method stub

	}

	@Override
	public void defend(CardBeanWrapper cardBean) {
		// TODO Auto-generated method stub

	}

	@Override
	public void useAbility(CardBeanWrapper cardBean) {
		// TODO Auto-generated method stub
	}

	@Override
	public void initWindow(){
		JFrame frame = new JFrame();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 1000, 500);
		frame.setContentPane(this);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setResizable(true);
		frame.setVisible(true);

		this.turnOwner = this.controller.getTurnStatus().getTurnOwner();

		for(CardBeanWrapper card : this.controller.getCardsInHand()){
			this.statusAndCardDisplayPanel.getCardsPanel().addCard(card);
		}

		this.statusAndCardDisplayPanel.setTurnOwner(this.turnOwner);
	}

	public CardsPanel getCardsPanel(){
		return this.statusAndCardDisplayPanel.getCardsPanel();
	}

	public PlayTablePanel getPlayTablePanel(){
		return this.playTablePanel;
	}

	public CreatureButton getSelectedButton() {
		return this.selectedButton;
	}

	public void setSelectedButton(CreatureButton selectedButton) {
		this.selectedButton = selectedButton;
	}

	public IControl getController() {
		return this.controller;
	}

	public void setTurnOwner(PlayerEnum turnOwner) {
		this.controller.setTurnOwner(turnOwner);
	}

	public CastingPopupWindow getCastingPopupWindow() {
		return this.castingPopupWindow;
	}

	public void nextStep(){
		this.controller.nextStep();
	}

	public TurnStatusBean getTurnStatus(){
		return this.controller.getTurnStatus();
	}
}
