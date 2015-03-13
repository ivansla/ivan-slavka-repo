package ivan.slavka.builder;

import ivan.slavka.beans.CardBean;
import ivan.slavka.beans.DeckBean;
import ivan.slavka.enums.DeckNamesEnum;
import ivan.slavka.parsers.CardParser;
import ivan.slavka.parsers.MetaParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.xml.sax.SAXException;

public class DeckBuilder {

	private final Map<String, DeckBean> decks = new HashMap<String, DeckBean>();
	private final List<CardBean> cardList = new ArrayList<CardBean>();
	private final CardParser cardParser;
	private final MetaParser metaParser;

	public DeckBuilder(){
		this.cardParser = new CardParser(this);
		this.metaParser = new MetaParser(this);
	}

	public Map<String, DeckBean> getDecks() {
		return this.decks;
	}

	public List<CardBean> getCardList() {
		return this.cardList;
	}

	public CardBean getCard(String cardName){
		for(CardBean card : this.cardList){
			if(card.getCardName().equalsIgnoreCase(cardName)){
				return card;
			}
		}
		return null;
	}

	public void startParsing(String cardPath, String metaPath){
		try{
			this.cardParser.parse(cardPath);
			this.metaParser.parse(metaPath);
			//			this.checkDecks();
			System.out.println("Number of constructed decks: " + this.decks.size());
		} catch (IOException e){
			e.printStackTrace();
		} catch (SAXException e){
			e.printStackTrace();
		}
	}

	private void checkDecks(){
		List<String> missingDecks = new ArrayList<String>();
		for(String deckCode : this.decks.keySet()){
			boolean found = false;
			for(DeckNamesEnum code : DeckNamesEnum.values()){
				if(deckCode.equalsIgnoreCase(code.toString())){
					found = true;
					break;
				}
			}
			if(!found){
				missingDecks.add(deckCode);
			}
		}

		System.out.println("Not registered decks: ");
		for(String name : missingDecks){
			System.out.println(name);
		}
	}
}
