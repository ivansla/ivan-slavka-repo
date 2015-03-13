package ivan.slavka.model.managers;

import ivan.slavka.utils.beans.ManaCostBean;
import ivan.slavka.utils.beans.ManaPoolBean;
import ivan.slavka.utils.enums.CardColorEnum;

public class ManaPoolManager {

	private int totalBlackMana = 8;
	private int totalBlueMana = 8;
	private int totalGreenMana = 8;
	private int totalRedMana = 8;
	private int totalWhiteMana = 8;

	private int availableBlackMana = 8;
	private int availableBlueMana = 8;
	private int availableGreenMana = 8;
	private int availableRedMana = 8;
	private int availableWhiteMana = 8;

	public void increaseManaPool(CardColorEnum color){

		switch(color){
		case BLACK:
			this.totalBlackMana++;
			this.availableBlackMana++;
			break;
		case BLUE:
			this.totalBlueMana++;
			this.availableBlueMana++;
			break;
		case GREEN:
			this.totalGreenMana++;
			this.availableGreenMana++;
			break;
		case RED:
			this.totalRedMana++;
			this.availableRedMana++;
			break;
		case WHITE:
			this.totalWhiteMana++;
			this.availableWhiteMana++;
			break;
		}
	}

	public void decreaseManaPool(CardColorEnum color){

		switch(color){
		case BLACK:
			if(this.totalBlackMana > 0){
				this.totalBlackMana--;
				this.availableBlackMana--;
			}
			break;
		case BLUE:
			if(this.totalBlueMana > 0){
				this.totalBlueMana--;
				this.availableBlueMana--;
			}
			break;
		case GREEN:
			if(this.totalGreenMana > 0){
				this.totalGreenMana--;
				this.availableGreenMana--;
			}
			break;
		case RED:
			if(this.totalRedMana > 0){
				this.totalRedMana--;
				this.availableRedMana--;
			}
			break;
		case WHITE:
			if(this.totalWhiteMana > 0){
				this.totalWhiteMana--;
				this.availableWhiteMana--;
			}
			break;
		}
	}

	public void spendMana(ManaCostBean manaCostBean){
		this.availableBlackMana -= manaCostBean.getBlackManaCost();
		this.availableBlueMana -= manaCostBean.getBlueManaCost();
		this.availableGreenMana -= manaCostBean.getGreenManaCost();
		this.availableRedMana -= manaCostBean.getRedManaCost();
		this.availableWhiteMana -= manaCostBean.getWhiteManaCost();

		int generalMana = Integer.parseInt(manaCostBean.getGeneralManaCost());

		while(generalMana != 0){
			if(this.availableBlackMana > 0){
				this.availableBlackMana--;
				generalMana--;
				if(generalMana == 0){
					break;
				}
			}

			if(this.availableBlueMana > 0){
				this.availableBlueMana--;
				generalMana--;
				if(generalMana == 0){
					break;
				}
			}

			if(this.availableGreenMana > 0){
				this.availableGreenMana--;
				generalMana--;
				if(generalMana == 0){
					break;
				}
			}

			if(this.availableRedMana > 0){
				this.availableRedMana--;
				generalMana--;
				if(generalMana == 0){
					break;
				}
			}

			if(this.availableWhiteMana > 0){
				this.availableWhiteMana--;
				generalMana--;
				if(generalMana == 0){
					break;
				}
			}
		}
	}

	public void resetManaPool(){
		this.availableBlackMana = this.totalBlackMana;
		this.availableBlueMana = this.totalBlueMana;
		this.availableGreenMana = this.totalGreenMana;
		this.availableRedMana = this.totalRedMana;
		this.availableWhiteMana = this.totalWhiteMana;
	}

	public ManaPoolBean getManaPool(){
		ManaPoolBean manaPool = new ManaPoolBean();

		manaPool.setAvailableBlackMana(this.availableBlackMana);
		manaPool.setAvailableBlueMana(this.availableBlueMana);
		manaPool.setAvailableGreenMana(this.availableGreenMana);
		manaPool.setAvailableRedMana(this.availableRedMana);
		manaPool.setAvailableWhiteMana(this.availableWhiteMana);

		manaPool.setTotalBlackMana(this.totalBlackMana);
		manaPool.setTotalBlueMana(this.totalBlueMana);
		manaPool.setTotalGreenMana(this.totalGreenMana);
		manaPool.setTotalRedMana(this.totalRedMana);
		manaPool.setTotalWhiteMana(this.totalWhiteMana);

		return manaPool;
	}

	public int getAvailableMana(){
		return this.availableBlackMana + this.availableBlueMana + this.availableGreenMana + this.availableRedMana + this.availableWhiteMana;
	}
}
