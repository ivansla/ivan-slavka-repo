package ivan.slavka;

public class MatrixMulitplication {

	private static final int MATRIX_SIZE = 1000;

	public static void main(String[] args) {

		int[][] maticaA = new int[MATRIX_SIZE][MATRIX_SIZE];
		int[][] maticaB = new int[MATRIX_SIZE][MATRIX_SIZE];
		int[][] maticaC = new int[MATRIX_SIZE][MATRIX_SIZE];

		int cislo = 1;

		for(int i = 0; i < MATRIX_SIZE; i++){
			for(int j = 0; j < MATRIX_SIZE; j++){
				maticaA[i][j] = cislo;
				maticaB[i][j] = cislo;
				cislo++;
			}
		}

		// klasicke nasobenie
		long startTime = System.currentTimeMillis();
		for(int i = 0; i < MATRIX_SIZE; i++){
			for(int j = 0; j < MATRIX_SIZE; j++){
				int hodnota = 0;
				for(int index = 0; index < MATRIX_SIZE; index++){
					hodnota += maticaA[i][index] * maticaB[index][j];
				}
				maticaC[i][j] = hodnota;
			}
		}

		System.out.println("Cas na vypocet nasobenia matic: " + (System.currentTimeMillis() - startTime));
		/*
		maticaB = transpose(maticaB);

		// zrychlene nasobenie
		startTime = System.nanoTime();
		for(int i = 0; i < MATRIX_SIZE; i++){
			for(int j = 0; j < MATRIX_SIZE; j++){
				int hodnota = 0;
				for(int index = 0; index < MATRIX_SIZE; index++){
					hodnota += maticaA[i][index] * maticaB[j][index];
				}
				maticaC[i][j] = hodnota;
			}
		}

		System.out.println("Cas na vypocet nasobenia matic: " + ((System.nanoTime() - startTime) / 1000000));
	}

	private static int[][] transpose(int matica[][]){

		int[][] maticaT = new int[MATRIX_SIZE][MATRIX_SIZE];

		for(int i = 0; i < MATRIX_SIZE; i++){
			for(int j = 0; j < MATRIX_SIZE; j++){
				maticaT[i][j] = matica[j][i];
			}
		}

		return maticaT;
	}
		 */
	}
}
