package ivan.slavka;

import java.util.Random;

public class MB {

	private int[] pole = new int[262144];
	private static Random r = new Random();

	public MB(){

		for(int i = 0; i < this.pole.length; i++){
			this.pole[i] = r.nextInt();
		}
	}

	public int[] getPole(){
		return this.pole;
	}
}
