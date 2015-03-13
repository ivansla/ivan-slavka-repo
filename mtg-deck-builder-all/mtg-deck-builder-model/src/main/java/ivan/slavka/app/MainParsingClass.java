package ivan.slavka.app;

import ivan.slavka.beans.CardBean;
import ivan.slavka.beans.DeckBean;
import ivan.slavka.beans.RuleBean;
import ivan.slavka.builder.DeckBuilder;
import ivan.slavka.jaxb.beans.Card;
import ivan.slavka.jaxb.beans.Deck;
import ivan.slavka.jaxb.beans.Multi;
import ivan.slavka.jaxb.beans.ObjectFactory;
import ivan.slavka.jaxb.beans.Rule;
import ivan.slavka.jaxb.beans.Rulelist;
import ivan.slavka.jaxb.beans.Typelist;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;

public class MainParsingClass {

	public static void main(String[] args) {

		Properties properties = new Properties();
		try{
			properties.load(new FileInputStream("D:/MyProject/deck-builder-workspace/mtg-deck-builder-all/mtg-deck-builder-utils/src/test/resources/decks.properties"));
		}catch (IOException e){
			e.printStackTrace();
		}
		DeckBuilder deckBuilder = new DeckBuilder();
		deckBuilder.startParsing("D:/MyProject/deck-builder-workspace/mtg-deck-builder-all/mtg-deck-builder-utils/src/test/resources/mtg-data/xml/cards.xml",
		"D:/MyProject/deck-builder-workspace/mtg-deck-builder-all/mtg-deck-builder-utils/src/test/resources/mtg-data/xml/meta.xml");

		DeckBean deckBean;
		for(String deckCode : deckBuilder.getDecks().keySet()){
			deckBean = deckBuilder.getDecks().get(deckCode);
			deckBean.setDeckName(properties.getProperty(deckCode));
			createDeck(deckBean);
		}
	}

	private static void createDeck(DeckBean deckBean){

		ObjectFactory factory = new ObjectFactory();
		Deck deck = factory.createDeck();
		deck.setName(deckBean.getDeckName());

		// Card
		Card card;
		int size = 0;
		for(CardBean cardBean : deckBean.getCardList()){
			size++;
			card = factory.createCard();
			if(cardBean.getCost() != null){
				card.setCost(cardBean.getCost());
			}
			if(cardBean.getHand() != null){
				card.setHand(cardBean.getHand());
			}
			if(cardBean.getLife() != null){
				card.setLife(cardBean.getLife());
			}
			if(cardBean.getLoyalty() != null){
				card.setLoyalty(cardBean.getLoyalty());
			}
			if(cardBean.getCardName() != null){
				card.setName(cardBean.getCardName());
			}
			if(cardBean.getPower() != null){
				card.setPow(cardBean.getPower());
			}
			if(cardBean.getToughness() != null){
				card.setTgh(cardBean.getToughness());
			}

			Typelist typeList = factory.createTypelist();
			for(String cardType : cardBean.getTypeList()){
				typeList.getType().add(cardType);
			}
			if(!typeList.getType().isEmpty()){
				card.setTypelist(typeList);
			}

			Rulelist ruleList = factory.createRulelist();
			Rule rule;
			for(RuleBean ruleBean : cardBean.getRuleList()){
				rule = factory.createRule();
				rule.setContent(ruleBean.getRule());
				rule.setReminder(ruleBean.getRuleReminder());
				ruleList.getRule().add(rule);
			}
			if(!ruleList.getRule().isEmpty()){
				card.setRulelist(ruleList);
			}

			// Multi
			CardBean multiBean = cardBean.getMulti();
			if(multiBean != null){

				Multi multi = factory.createMulti();
				multi.setCost(multiBean.getCost());
				multi.setHand(multiBean.getHand());
				multi.setLife(multiBean.getLife());
				multi.setLoyalty(multiBean.getLoyalty());
				multi.setName(multiBean.getCardName());
				multi.setPow(multiBean.getPower());
				multi.setTgh(multiBean.getToughness());

				Typelist multiTypeList = factory.createTypelist();
				for(String cardType : multiBean.getTypeList()){
					multiTypeList.getType().add(cardType);
				}
				multi.setTypelist(multiTypeList);

				Rulelist multiRuleList = factory.createRulelist();
				Rule multirule;
				for(RuleBean ruleBean : multiBean.getRuleList()){
					multirule = factory.createRule();
					multirule.setContent(ruleBean.getRule());
					multirule.setReminder(ruleBean.getRuleReminder());
					multiRuleList.getRule().add(multirule);
				}
				multi.setRulelist(multiRuleList);

				card.setMulti(multi);
			}
			deck.getCard().add(card);
		}

		try{
			JAXBElement<Deck> jaxbDeck = factory.createDeck(deck);
			JAXBContext jaxbContext = JAXBContext.newInstance("ivan.slavka.jaxb.beans");

			Marshaller marshaller = jaxbContext.createMarshaller();
			marshaller.marshal(jaxbDeck, new FileOutputStream("./decks/" + deck.getName() + ".xml"));
		} catch (Exception e){
			e.printStackTrace();
		}
	}
}
