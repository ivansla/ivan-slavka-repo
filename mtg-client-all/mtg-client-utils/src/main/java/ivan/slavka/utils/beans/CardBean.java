package ivan.slavka.utils.beans;

import java.util.ArrayList;
import java.util.List;

public class CardBean {

	private String name = "";
	private String loyalty;
	private List<String> typeList = new ArrayList<String>();
	private String power;
	private String toughness;
	private String hand;
	private String life;
	private List<RuleBean> ruleList = new ArrayList<RuleBean>();
	private CardBean multi;
	private String manaCost;

	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getLoyalty() {
		return this.loyalty;
	}
	public void setLoyalty(String loyalty) {
		this.loyalty = loyalty;
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
	public CardBean getMulti() {
		return this.multi;
	}
	public void setMulti(CardBean multi) {
		this.multi = multi;
	}
	public List<String> getTypeList() {
		return this.typeList;
	}
	public List<RuleBean> getRuleList() {
		return this.ruleList;
	}
	public void setTypeList(List<String> typeList) {
		this.typeList = typeList;
	}
	public void setRuleList(List<RuleBean> ruleList) {
		this.ruleList = ruleList;
	}
	public String getManaCost() {
		return this.manaCost;
	}
	public void setManaCost(String manaCost) {
		this.manaCost = manaCost;
	}
}
