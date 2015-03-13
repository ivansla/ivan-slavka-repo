package ivan.slavka.parsers;

import ivan.slavka.beans.CardBean;
import ivan.slavka.beans.RuleBean;
import ivan.slavka.builder.DeckBuilder;

import org.apache.xerces.parsers.SAXParser;
import org.apache.xerces.xni.Augmentations;
import org.apache.xerces.xni.QName;
import org.apache.xerces.xni.XMLAttributes;
import org.apache.xerces.xni.XMLString;

public class CardParser extends SAXParser{

	private String cardValue;
	private CardBean cardBean;
	private RuleBean ruleBean;
	private boolean multi = false;

	private final DeckBuilder deckBuilder;

	public CardParser(DeckBuilder deckBuilder){
		this.deckBuilder = deckBuilder;
	}

	@Override
	public void startElement(QName element, XMLAttributes attrList, Augmentations a)  {

		//reset
		this.cardValue = "";
		if(element.rawname == "card") {
			//create a new instance of card
			this.cardBean = new CardBean();
		} else if (element.rawname == "multi"){
			this.cardBean.createMulti();
			this.cardBean.getMulti().setType(attrList.getValue("type"));
			this.multi = true;
		} else if (element.rawname == "rule"){
			this.ruleBean = new RuleBean();
			this.ruleBean.setRuleReminder(attrList.getValue("reminder"));
		}
	}

	@Override
	public void characters(XMLString string, Augmentations a){
		this.cardValue = new String(string.ch ,string.offset,string.length);
	}

	@Override
	public void endElement(QName qName, Augmentations a) {

		if(qName.rawname == "card") {
			this.deckBuilder.getCardList().add(this.cardBean);
		}else if (qName.rawname == "name") {
			if(this.multi){
				this.cardBean.getMulti().setCardName(this.cardValue);
			} else {
				this.cardBean.setCardName(this.cardValue);
			}
		}else if (qName.rawname == "cost") {
			if(this.multi){
				this.cardBean.getMulti().setCost(this.cardValue);
			} else {
				this.cardBean.setCost(this.cardValue);
			}
		}else if (qName.rawname == "loyalty") {
			if(this.multi){
				this.cardBean.getMulti().setLoyalty(this.cardValue);
			} else {
				this.cardBean.setLoyalty(this.cardValue);
			}
		}else if (qName.rawname == "type") {
			if(this.multi){
				this.cardBean.getMulti().getTypeList().add(this.cardValue);
			} else {
				this.cardBean.getTypeList().add(this.cardValue);
			}
		}else if (qName.rawname == "pow") {
			if(this.multi){
				this.cardBean.getMulti().setPower(this.cardValue);
			} else {
				this.cardBean.setPower(this.cardValue);
			}
		}else if (qName.rawname == "tgh") {
			if(this.multi){
				this.cardBean.getMulti().setToughness(this.cardValue);
			} else {
				this.cardBean.setToughness(this.cardValue);
			}
		}else if (qName.rawname == "hand") {
			if(this.multi){
				this.cardBean.getMulti().setHand(this.cardValue);
			} else {
				this.cardBean.setHand(this.cardValue);
			}
		}else if (qName.rawname == "life") {
			if(this.multi){
				this.cardBean.getMulti().setLife(this.cardValue);
			} else {
				this.cardBean.setLife(this.cardValue);
			}
		}else if (qName.rawname == "rule") {
			this.ruleBean.setRule(this.cardValue);
			if(this.multi){
				this.cardBean.getMulti().getRuleList().add(this.ruleBean);
			} else {
				this.cardBean.getRuleList().add(this.ruleBean);
			}
		}else if (qName.rawname == "multi") {
			this.multi = false;
		}
	}
}
