package my.first.game.utils;

import java.util.Random;

public class GenericRandomGenerator {

	private static Random randomGenerator = null;

	private GenericRandomGenerator(){}

	public static Random getInstance(){
		if(GenericRandomGenerator.randomGenerator == null){
			GenericRandomGenerator.randomGenerator = new Random();
		}
		return randomGenerator;
	}
}
