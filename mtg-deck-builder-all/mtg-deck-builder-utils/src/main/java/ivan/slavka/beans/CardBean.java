package ivan.slavka.beans;

import java.util.ArrayList;
import java.util.List;

public class CardBean {

	private String type;
	private String cardName;
	private String cost;
	private String loyalty;
	private List<String> typeList;
	private String power;
	private String toughness;
	private String hand;
	private String life;
	private List<RuleBean> ruleList;
	private CardBean multi;

	public CardBean(){
		this.typeList = new ArrayList<String>();
		this.ruleList = new ArrayList<RuleBean>();
	}

	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getCardName() {
		return this.cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	public String getCost() {
		return this.cost;
	}
	public void setCost(String cost) {
		this.cost = cost;
	}
	public String getLoyalty() {
		return this.loyalty;
	}
	public void setLoyalty(String loyalty) {
		this.loyalty = loyalty;
	}
	public List<String> getTypeList() {
		return this.typeList;
	}
	public void setTypeList(List<String> typeList) {
		this.typeList = typeList;
	}
	public String getPower() {
		return this.power;
	}
	public void setPower(String power) {
		this.power = power;
	}
	public String getToughness() {
		return this.toughness;
	}
	public void setToughness(String toughness) {
		this.toughness = toughness;
	}
	public String getHand() {
		return this.hand;
	}
	public void setHand(String hand) {
		this.hand = hand;
	}
	public String getLife() {
		return this.life;
	}
	public void setLife(String life) {
		this.life = life;
	}
	public List<RuleBean> getRuleList() {
		return this.ruleList;
	}
	public void setRuleList(List<RuleBean> ruleList) {
		this.ruleList = ruleList;
	}
	public void createMulti(){
		if(this.multi == null){
			this.multi = new CardBean();
		}
	}

	public CardBean getMulti() {
		if(this.multi != null){
			return this.multi;
		}
		return null;
	}
	public void setMulti(CardBean multi) {
		this.multi = multi;
	}
}
