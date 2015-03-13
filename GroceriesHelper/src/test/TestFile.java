package test;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class TestFile {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		BufferedReader br = null;
		BufferedWriter bw = null;

		try {

			String sCurrentLine;

			br = new BufferedReader(new FileReader("D:/Testing.txt"));

			while ((sCurrentLine = br.readLine()) != null) {
				System.out.println(sCurrentLine);
			}


			bw = new BufferedWriter(new FileWriter("D:/Testing.txt", true));
			bw.append("kokot");
			bw.flush();


		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

	}

}
