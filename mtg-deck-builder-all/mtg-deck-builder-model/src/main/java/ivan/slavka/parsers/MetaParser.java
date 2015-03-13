package ivan.slavka.parsers;

import ivan.slavka.beans.CardBean;
import ivan.slavka.beans.DeckBean;
import ivan.slavka.beans.RuleBean;
import ivan.slavka.builder.DeckBuilder;

import java.util.ArrayList;
import java.util.List;

import org.apache.xerces.parsers.SAXParser;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.xni.XMLString;

public class MetaParser extends SAXParser {

	private static int size = 0;

	private String cardValue;
	private CardBean cardBean;
	private RuleBean ruleBean;
	private List<String> deckCodeList;

	private final DeckBuilder deckBuilder;

	public MetaParser(DeckBuilder deckBuilder){
		this.deckBuilder = deckBuilder;
	}

	@Override
	public void startElement(QName element, XMLAttributes attrList, Augmentations a) {

		//reset
		this.cardValue = "";
		if(element.rawname == "card") {
			//create a new instance of card
			this.deckCodeList = new ArrayList<String>();
		}
	}


	@Override
	public void characters(XMLString string, Augmentations a){
		this.cardValue = new String(string.ch ,string.offset,string.length);
	}

	@Override
	public void endElement(QName qName, Augmentations a){

		if (qName.rawname == "name") {
			this.cardBean = this.deckBuilder.getCard(this.cardValue);
		} else if (qName.rawname == "set"){
			this.deckCodeList.add(this.cardValue);
		} else if (qName.rawname == "card"){
			for(String deckCode : this.deckCodeList){
				DeckBean deck = this.deckBuilder.getDecks().get(deckCode);
				if(deck == null){
					deck = new DeckBean();
					if(this.cardBean != null){
						deck.getCardList().add(this.cardBean);
					}

					this.deckBuilder.getDecks().put(deckCode, deck);
				} else {
					if(this.cardBean != null){
						deck.getCardList().add(this.cardBean);
					}
				}
			}
		}

	}
}
