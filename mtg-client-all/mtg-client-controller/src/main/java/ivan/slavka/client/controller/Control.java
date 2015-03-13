package ivan.slavka.client.controller;

import ivan.slavka.utils.beans.CardBean;
import ivan.slavka.utils.beans.InviteBean;
import ivan.slavka.utils.beans.LoginBean;
import ivan.slavka.utils.beans.ManaPoolBean;
import ivan.slavka.utils.beans.RosterBean;
import ivan.slavka.utils.beans.TurnStatusBean;
import ivan.slavka.utils.beans.wrappers.CardBeanWrapper;
import ivan.slavka.utils.enums.PlayerEnum;
import ivan.slavka.utils.interfaces.IApplication;
import ivan.slavka.utils.interfaces.IClientConnectionView;
import ivan.slavka.utils.interfaces.IClientLoginInvitationControl;
import ivan.slavka.utils.interfaces.IControl;
import ivan.slavka.utils.interfaces.IMTGClientView;
import ivan.slavka.utils.interfaces.IMessageControllerClient;

import java.util.List;

public class Control implements IControl, IClientLoginInvitationControl{

	private IMessageControllerClient messageController;
	private IApplication application;
	private IMTGClientView view;
	private IClientConnectionView connectionView;

	@Override
	public void registerMessageController(IMessageControllerClient messageController) {
		this.messageController = messageController;
	}

	@Override
	public CardBeanWrapper addCard() {
		return this.application.addCard();
	}

	@Override
	public ManaPoolBean playCard(CardBeanWrapper cardBean) {
		return this.application.playCard(cardBean);
	}

	@Override
	public void addViewObserver(IMTGClientView observer) {
		this.view = observer;
	}

	@Override
	public void addModelObserver(IApplication observer) {
		this.application = observer;
	}

	@Override
	public int getAvailableMana() {
		return this.application.getAvailableMana();
	}

	@Override
	public List<CardBeanWrapper> getCardsInHand() {
		return this.application.getCardsInHand();
	}

	@Override
	public void sendCard(CardBeanWrapper cardBean) {
		this.messageController.doPlayCard(cardBean.getCardBean());
	}

	@Override
	public void receivePlayCard(CardBean cardBean) {
		this.application.playCardOpponent(this.createCardWrapper(cardBean));
	}

	//////////////////////////////////////////////////////
	//	implementation	(IControlTurnManagement)
	//////////////////////////////////////////////////////

	@Override
	public void nextStep() {
		this.application.nextStep();
	}

	@Override
	public void setTurnOwner(PlayerEnum turnOwner) {
		this.application.setTurnOwner(turnOwner);
	}

	@Override
	public TurnStatusBean getTurnStatus() {
		return this.application.getTurnStatus();
	}

	////////////////////////////////////////////////////
	//	implementation IClientLoginInvitationControl
	///////////////////////////////////////////////////

	@Override
	public void doLogin(LoginBean loginBean) {
		this.messageController.doLogin(loginBean);
	}

	@Override
	public void doLogout(LoginBean loginBean) {
		// TODO Auto-generated method stub

	}

	@Override
	public void doInvitation(InviteBean inviteBean) {
		this.messageController.doInvitation(inviteBean);
	}

	@Override
	public void onRoster(RosterBean roster) {
		this.connectionView.onRoster(roster);
	}

	@Override
	public void registerClientConnectionView(
			IClientConnectionView connectionView) {
		this.connectionView = connectionView;
	}

	@Override
	public void onInvitation(InviteBean inviteBean) {
		this.connectionView.onInvitation(inviteBean);
	}

	@Override
	public void startGame(InviteBean inviteBean) {
		this.application.setTurnOwner(inviteBean.getTurnOwner());
		this.view.initWindow();
	}

	@Override
	public void closeClientConnecetionView() {
		this.connectionView.closeWindow();
	}

	private CardBeanWrapper createCardWrapper(CardBean cardBean){
		return new CardBeanWrapper(cardBean);
	}
}
