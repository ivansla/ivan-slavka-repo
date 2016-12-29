package ivan.slavka.generators;

import java.util.Random;

public class Randomizer {

	private static Random INSTANCE = null;

	public static Random getInstance(){
		if(INSTANCE == null){
			INSTANCE = new Random();
		}

		return INSTANCE;
	}
}
