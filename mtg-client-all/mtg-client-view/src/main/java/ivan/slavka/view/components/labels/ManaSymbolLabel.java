package ivan.slavka.view.components.labels;

import ivan.slavka.utils.properties.MTGClientProperties;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ManaSymbolLabel extends JLabel{

	public static final String BLACK_MANA_SYMBOL = "BlackManaSymbol.png";
	public static final String RED_MANA_SYMBOL = "RedManaSymbol.png";
	public static final String BLUE_MANA_SYMBOL = "BlueManaSymbol.png";
	public static final String WHITE_MANA_SYMBOL = "WhiteManaSymbol.png";
	public static final String GREEN_MANA_SYMBOL = "GreenManaSymbol.png";

	private final MTGClientProperties properties = MTGClientProperties.getInstance();
	private final String imageSourcePath = this.properties.getProperty("image.path".toString());

	public ManaSymbolLabel(String image){

		this.setIcon(new ImageIcon(this.imageSourcePath + image));
	}
}
