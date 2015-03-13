package ivan.slavka.beans;

import java.util.ArrayList;
import java.util.List;

public class DeckBean {

	private String deckName;
	private List<CardBean> cardList = new ArrayList<CardBean>();

	public String getDeckName() {
		return this.deckName;
	}
	public void setDeckName(String deckName) {
		this.deckName = deckName;
	}
	public List<CardBean> getCardList() {
		return this.cardList;
	}
	public void setCardList(List<CardBean> cardList) {
		this.cardList = cardList;
	}
}
