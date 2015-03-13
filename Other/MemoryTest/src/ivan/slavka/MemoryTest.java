package ivan.slavka;

public class MemoryTest {

	private static int PASSES = 16;

	public static void main(String[] args){
		long sucet = 0;
		long time = System.currentTimeMillis();
		for(int k = 0; k < PASSES; k++){
			MB[] gigaPole = new MB[1024];
			for(int i = 0; i < gigaPole.length; i++){
				gigaPole[i] = new MB();
			}

			int[] pole;
			for(int i = 0; i < gigaPole.length; i++){
				pole = gigaPole[i].getPole();
				for(int j = 0; j < pole.length; j++){
					sucet += pole[j];
				}
			}
			System.out.println("Pass #" + k);
		}
		System.out.println(sucet);
		System.out.println("time passed: " + (System.currentTimeMillis() - time));
	}
}
