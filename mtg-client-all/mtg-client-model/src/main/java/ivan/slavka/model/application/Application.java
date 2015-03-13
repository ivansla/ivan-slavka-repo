package ivan.slavka.model.application;

import ivan.slavka.model.managers.ManaPoolManager;
import ivan.slavka.model.managers.TurnManager;
import ivan.slavka.utils.beans.CardBean;
import ivan.slavka.utils.beans.InviteBean;
import ivan.slavka.utils.beans.ManaPoolBean;
import ivan.slavka.utils.beans.RuleBean;
import ivan.slavka.utils.beans.TurnStatusBean;
import ivan.slavka.utils.beans.wrappers.CardBeanWrapper;
import ivan.slavka.utils.enums.CastingPermissionEnum;
import ivan.slavka.utils.enums.PlayerEnum;
import ivan.slavka.utils.interfaces.IApplication;
import ivan.slavka.utils.interfaces.IControl;
import ivan.slavka.utils.jaxb.beans.deck.Deck;
import ivan.slavka.utils.jaxb.beans.mtg.card.Card;
import ivan.slavka.utils.jaxb.beans.mtg.card.Rule;
import ivan.slavka.utils.properties.MTGClientProperties;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


public class Application implements IApplication{

	private static Log logger = LogFactory.getLog(Application.class);

	private final MTGClientProperties properties = MTGClientProperties.getInstance();
	private final TurnManager turnManager;
	private final IControl controller;

	private PlayerEnum turnOwner;
	private AtomicInteger gameCardIdGenerator = new AtomicInteger();

	// Context
	private final List<CardBeanWrapper> cardsInHand = new ArrayList<CardBeanWrapper>();
	private final List<CardBeanWrapper> cardsInBase = new ArrayList<CardBeanWrapper>();
	private final LinkedList<CardBeanWrapper> playerDeck = new LinkedList<CardBeanWrapper>();
	private final ManaPoolManager manaPoolManager = new ManaPoolManager();
	private String playerName;
	private String opponentName;

	public Application(IControl controller){
		this.controller = controller;
		this.turnManager = TurnManager.getInstance(this);
	}

	@Override
	public ManaPoolBean playCard(CardBeanWrapper cardBean) {

		this.cardsInHand.remove(cardBean);
		this.cardsInBase.add(cardBean);

		switch(cardBean.getCardTypeToDisplay()){
		case LAND:
			this.manaPoolManager.increaseManaPool(cardBean.getCardColor());
			break;
		case CREATURE:
			this.manaPoolManager.spendMana(cardBean.getManaCostBean());
			break;
		}

		cardBean.setPlayerName(this.playerName);
		cardBean.setOpponentName(this.opponentName);
		this.controller.sendCard(cardBean);

		return this.manaPoolManager.getManaPool();
	}

	@Override
	public void startGame(InviteBean inviteBean) {
		this.loadAndShuffleDeck();

		int cardsInHand = 0;

		switch(inviteBean.getTurnOwner()){
		case OPPONENT:
			cardsInHand = 6;
			break;
		case PLAYER:
			cardsInHand = 7;
			break;
		}

		for(int i = 0; i < cardsInHand; i++){
			this.cardsInHand.add(this.playerDeck.poll());
		}

		switch(inviteBean.getType()){
		case REQUEST:
			this.playerName = inviteBean.getFrom();
			this.opponentName = inviteBean.getTo();
			break;
		case RESPONSE:
			this.playerName = inviteBean.getTo();
			this.opponentName = inviteBean.getFrom();
			break;
		}
	}

	@Override
	public CardBeanWrapper addCard() {

		CardBeanWrapper card = this.playerDeck.poll();
		this.cardsInHand.add(card);
		return card;
	}

	@Override
	public PlayerEnum getTurnOwner() {
		return this.turnOwner;
	}

	@Override
	public void setTurnOwner(PlayerEnum turnOwner) {
		this.turnOwner = turnOwner;
	}

	private void loadAndShuffleDeck(){

		this.loadDeck();
		this.shuffleDeck();
	}

	private void loadDeck(){

		try{
			JAXBContext jaxbContext = JAXBContext.newInstance("ivan.slavka.utils.jaxb.beans");
			Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
			JAXBElement<Deck> jaxbDeck = (JAXBElement<Deck>) unmarshaller.unmarshal(new FileInputStream(this.properties.getProperty("deck.path") + "Fourth Edition.xml"));

			for(Card card : jaxbDeck.getValue().getCard()){
				CardBean cardBean = new CardBean();
				cardBean.setName(card.getName());
				if(card.getCost() != null){
					cardBean.setManaCost(card.getCost());
				}
				if(card.getLife() != null){
					cardBean.setLoyalty(card.getLife());
				}
				for(String type : card.getTypelist().getType()){
					cardBean.getTypeList().add(type);
				}
				if(card.getPow() != null){
					cardBean.setPower(card.getPow());
				}
				if(card.getTgh() != null){
					cardBean.setToughness(card.getTgh());
				}
				if(card.getHand() != null){
					cardBean.setHand(card.getHand());
				}
				if(card.getLife() != null){
					cardBean.setLife(card.getLife());
				}
				if(card.getRulelist() != null){
					for(Object object : card.getRulelist().getRule()){
						Rule rule = (Rule) object;
						RuleBean ruleBean = new RuleBean();
						ruleBean.setReminder(rule.getReminder());
						ruleBean.setRule(rule.getValue());
						cardBean.getRuleList().add(ruleBean);
					}
				}
				// TODO: Need to add support for MultiCard

				CardBeanWrapper cardBeanWrapper = new CardBeanWrapper(cardBean);
				this.playerDeck.add(cardBeanWrapper);
			}

		} catch (JAXBException e){
			logger.debug("Problem loading a deck", e);
		} catch (FileNotFoundException e){
			logger.debug("Problem loading a deck", e);
		}
	}

	private void shuffleDeck(){
		Random random = new Random();

		CardBeanWrapper firstCard;
		CardBeanWrapper secondCard;
		for(int i = 0; i < 1000; i++){

			int firstCardPosition = random.nextInt(this.playerDeck.size());
			int secondCardPosition = random.nextInt(this.playerDeck.size());

			firstCard = this.playerDeck.get(firstCardPosition);
			secondCard = this.playerDeck.get(secondCardPosition);

			this.playerDeck.set(firstCardPosition, secondCard);
			this.playerDeck.set(secondCardPosition, firstCard);
		}
	}

	@Override
	public int getAvailableMana() {
		return this.manaPoolManager.getAvailableMana();
	}

	@Override
	public List<CardBeanWrapper> getCardsInHand() {
		return this.cardsInHand;
	}

	////////////////////////////////////////////////////////////
	//	implementation (IApplicationTurnManagement)
	////////////////////////////////////////////////////////////

	@Override
	public void changeTurnOwner() {
		switch(this.turnOwner){
		case PLAYER:
			this.turnOwner = PlayerEnum.OPPONENT;
			break;
		case OPPONENT:
			this.turnOwner = PlayerEnum.PLAYER;
			break;
		}
	}

	@Override
	public void nextStep() {
		this.turnManager.nextStep();
	}

	@Override
	public TurnStatusBean getTurnStatus() {
		TurnStatusBean turnStatus = new TurnStatusBean();
		turnStatus.setGamePhase(this.turnManager.getGamePhase());
		turnStatus.setGamePhaseStep(this.turnManager.getGamePhaseStep());
		turnStatus.setTurnOwner(this.turnOwner);
		for(CastingPermissionEnum permission : this.turnManager.getStepPermissions()){
			turnStatus.getPermissions().add(permission);
		}
		return turnStatus;
	}

	@Override
	public void playCardOpponent(CardBeanWrapper cardBeanWrapper) {
		// TODO Auto-generated method stub

	}
}