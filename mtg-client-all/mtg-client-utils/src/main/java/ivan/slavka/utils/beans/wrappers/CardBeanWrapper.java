package ivan.slavka.utils.beans.wrappers;

import ivan.slavka.utils.beans.CardBean;
import ivan.slavka.utils.beans.ManaCostBean;
import ivan.slavka.utils.enums.CardColorEnum;
import ivan.slavka.utils.enums.CardTypeEnum;
import ivan.slavka.utils.enums.PlayerEnum;
import ivan.slavka.utils.exceptions.ParseEnumException;
import ivan.slavka.utils.properties.MTGClientProperties;

import javax.swing.ImageIcon;

public class CardBeanWrapper{

	private static final MTGClientProperties properties = MTGClientProperties.getInstance();

	private int gameCardId;

	private String playerName = "";
	private String opponentName = "";

	private final CardBean cardBean;
	private final ManaCostBean manaCostBean;

	// view requirements
	private CardColorEnum cardColor;
	private CardTypeEnum cardTypeToDisplay;
	private final ImageIcon icon;
	private PlayerEnum cardOwner;
	private boolean customManaSpend = false;

	// this constructor is for testing purposes only
	//	public CardBeanWrapper(int gameCardId, CardBean cardBean){
	//
	//		this.cardBean = cardBean;
	//		this.manaCostBean = new ManaCostBean(this);
	//		this.gameCardId = gameCardId;
	//		this.cardColor = CardColorEnum.GREEN;
	//		this.cardType = CardTypeEnum.CREATURE;
	//		this.icon = new ImageIcon("/home/ivansla/workspace/mtg-client-all/mtg-client-utils/src/test/java/resources/pictures/Air Elemental.full.jpg");
	//	}

	public CardBeanWrapper(CardBean cardBean){

		this.cardBean = cardBean;
		this.manaCostBean = new ManaCostBean(this);
		this.icon = this.getCardImage(cardBean.getName());
		this.setCardType(cardBean);
		this.setCardColor(cardBean);
	}
	//
	//	public CardBeanWrapper(String imagePath, int gameCardId, CardBean cardBean){
	//
	//		this.cardBean = cardBean;
	//		this.manaCostBean = new ManaCostBean(this);
	//		this.gameCardId = gameCardId;
	//		this.cardColor = CardColorEnum.GREEN;
	//		this.cardType = CardTypeEnum.CREATURE;
	//		this.icon = new ImageIcon(imagePath);
	//	}

	public ImageIcon getIcon() {
		return this.icon;
	}

	public int getHeight(){
		return this.icon.getIconHeight();
	}
	public int getWidth(){
		return this.icon.getIconWidth();
	}
	public CardColorEnum getCardColor() {
		return this.cardColor;
	}

	public PlayerEnum getCardOwner() {
		return this.cardOwner;
	}

	public void setCardOwner(PlayerEnum cardOwner) {
		this.cardOwner = cardOwner;
	}

	public int getGameCardId() {
		return this.gameCardId;
	}
	public boolean isCustomManaSpend() {
		return this.customManaSpend;
	}

	public void setCustomManaSpend(boolean customManaSpend) {
		this.customManaSpend = customManaSpend;
	}

	public String getPlayerName() {
		return this.playerName;
	}

	public void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	public String getOpponentName() {
		return this.opponentName;
	}

	public void setOpponentName(String opponentName) {
		this.opponentName = opponentName;
	}

	public ManaCostBean getManaCostBean() {
		return this.manaCostBean;
	}

	public CardBean getCardBean() {
		return this.cardBean;
	}

	private ImageIcon getCardImage(String name){
		String fileName = name + ".full.jpg";
		String path = properties.getProperty("image.path");

		return new ImageIcon(path + fileName);
	}

	private void setCardType(CardBean cardBean){
		for(String type : cardBean.getTypeList()){
			try{
				// Setting card display type by priority
				switch(CardTypeEnum.parse(type)){
				case CREATURE:
					this.cardTypeToDisplay = CardTypeEnum.CREATURE;
					break;
				case ARTIFACT:
					break;
				}
			} catch (ParseEnumException e){
				// Do nothing. Exception is thrown because type is not relevant to the view.
			}
		}

		for(String type : cardBean.getTypeList()){
			type.toUpperCase();
		}

		if(cardBean.getTypeList().contains(CardTypeEnum.CREATURE.toString())){
			this.cardTypeToDisplay = CardTypeEnum.CREATURE;
		} else if(cardBean.getTypeList().contains(CardTypeEnum.ARTIFACT.toString())){
			this.cardTypeToDisplay = CardTypeEnum.ARTIFACT;
		}
	}

	private void setCardColor(CardBean cardBean){

		String manaCost = cardBean.getManaCost();
		this.cardColor = CardColorEnum.parse(manaCost.charAt(manaCost.length() - 1));
	}

	public CardTypeEnum getCardTypeToDisplay() {
		return this.cardTypeToDisplay;
	}
}
