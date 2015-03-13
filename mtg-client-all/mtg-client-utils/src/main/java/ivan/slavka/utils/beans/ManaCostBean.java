package ivan.slavka.utils.beans;

import ivan.slavka.utils.beans.wrappers.CardBeanWrapper;

public class ManaCostBean {

	private int blackManaCost = 0;
	private int blueManaCost = 0;
	private int redManaCost = 0;
	private int whiteManaCost = 0;
	private int greenManaCost = 0;
	private String generalManaCost = "";

	private final CardBeanWrapper cardBean;

	public ManaCostBean(CardBeanWrapper cardBean){
		this.cardBean = cardBean;
	}

	public int getBlackManaCost() {
		return this.blackManaCost;
	}
	public void setBlackManaCost(int blackManaCost) {
		this.blackManaCost = blackManaCost;
	}
	public int getBlueManaCost() {
		return this.blueManaCost;
	}
	public void setBlueManaCost(int blueManaCost) {
		this.blueManaCost = blueManaCost;
	}
	public int getRedManaCost() {
		return this.redManaCost;
	}
	public void setRedManaCost(int redManaCost) {
		this.redManaCost = redManaCost;
	}
	public int getWhiteManaCost() {
		return this.whiteManaCost;
	}
	public void setWhiteManaCost(int whiteManaCost) {
		this.whiteManaCost = whiteManaCost;
	}
	public int getGreenManaCost() {
		return this.greenManaCost;
	}
	public void setGreenManaCost(int greenManaCost) {
		this.greenManaCost = greenManaCost;
	}
	public String getGeneralManaCost() {
		return this.generalManaCost;
	}
	public void setGeneralManaCost(String generalManaCost) {
		this.generalManaCost = generalManaCost;
	}
	public void setGeneralManaCost(int mana){
		this.generalManaCost = String.valueOf(mana);
	}

	public void setManaCostBean(String cost){

		for(char mana : cost.toCharArray()){
			switch(mana){
			case 'B':
				this.blackManaCost++;
				break;
			case 'W':
				this.whiteManaCost++;
				break;
			case 'R':
				this.redManaCost++;
				break;
			case 'G':
				this.greenManaCost++;
				break;
			case 'U':
				this.blueManaCost++;
				break;
			case 'X':
				this.generalManaCost += mana;
				this.cardBean.setCustomManaSpend(true);
				break;
			default:
				this.generalManaCost += mana;
				break;
			}
		}
	}
}
