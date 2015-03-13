package ivan.slavka.utils.properties;

import java.util.Properties;

public class MTGClientProperties extends Properties{

	private static MTGClientProperties properties = null;

	private MTGClientProperties(){}

	public static MTGClientProperties getInstance(){
		if(properties == null){
			properties = new MTGClientProperties();
		}
		return properties;
	}
}
