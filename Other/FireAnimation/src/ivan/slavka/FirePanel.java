package ivan.slavka;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import javax.swing.JPanel;

public class FirePanel extends JPanel{

	private static final int HEIGHT = 300;
	private static final int WIDTH = 100;

	private boolean stopDrawing = false;

	private Thread drawingThread;
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage backImage = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private BufferedImage tempImage;
	private Graphics panelGraphics;
	private Random random;

	private int[] riadokVPoli = new int [WIDTH];

	public FirePanel() {

		this.random = new Random();
	}

	public void drawFire(){



		this.panelGraphics = this.getGraphics();
		this.drawingThread = new Thread(new Runnable() {

			@Override
			public void run() {
				long time = System.currentTimeMillis();
				int fps = 0;
				do{
					if((System.currentTimeMillis() - time) < 1000){
						fps++;
					} else {
						System.out.println("Frames/Second: " + fps);
						time = System.currentTimeMillis();
						fps = 0;
					}
					FirePanel.this.drawFirstLine();
					FirePanel.this.calculateFire();

					FirePanel.this.panelGraphics.drawImage(FirePanel.this.backImage, 0, 0, FirePanel.this);
					FirePanel.this.tempImage = FirePanel.this.image;
					FirePanel.this.image = FirePanel.this.backImage;
					FirePanel.this.backImage = FirePanel.this.tempImage;

				} while (!FirePanel.this.stopDrawing) ;
			}
		});

		this.drawingThread.start();

	}

	private void drawFirstLine(){

		for(int i = 0; i < WIDTH; i++){
			switch(this.random.nextInt(2)){
			case 0:
				this.riadokVPoli[i] = Color.BLACK.getRGB();
				//this.image.setRGB(i, HEIGHT - 1, Color.BLACK.getRGB());
				break;
			case 1:
				this.riadokVPoli[i] = Color.GREEN.getRGB();
				//this.image.setRGB(i, HEIGHT - 1, Color.GREEN.getRGB());
				break;
			}
		}
	}

	private void calculateFire(){

		boolean stop = false;
		for(int i = HEIGHT - 2; i > 1; i--){
			for(int j = 1; j < (WIDTH - 1); j++){
				int averageRGB;
				if(i == (HEIGHT - 2)){
					averageRGB = (
							this.riadokVPoli[j - 1] +
							this.riadokVPoli[j] +
							this.riadokVPoli[j + 1]
							) / 3;
				} else {
					averageRGB = (
							this.getPixelColor(this.image.getRGB(j - 1, i)) +
							this.getPixelColor(this.image.getRGB(j - 1, i + 1)) +
							this.getPixelColor(this.image.getRGB(j, i + 1)) +
							this.getPixelColor(this.image.getRGB(j + 1, i + 1)) +
							this.getPixelColor(this.image.getRGB(j + 1, i))
							) / 5;
				}

				this.backImage.setRGB(j, i, Integer.parseInt(this.createNewRGB(averageRGB), 16));
			}
		}
	}

	private String createNewRGB(int averageRGB){

		String newRGB;
		String parsaAverage = Integer.toHexString(averageRGB);
		if(parsaAverage.length() == 1){
			newRGB = "000" + parsaAverage + "00";
		}else if(parsaAverage.length() == 2) {
			newRGB = "00" + parsaAverage + "00";
		} else {
			newRGB = Integer.toHexString(averageRGB);
			newRGB = newRGB.substring(2);
		}
		return newRGB;
	}

	private int getPixelColor(int RGB){
		String hexARGB = Integer.toHexString(RGB);
		String hexRGB = hexARGB.substring(2);
		String hexGREEN = hexRGB.substring(2).substring(0, 2);

		return Integer.parseInt(hexGREEN, 16);
	}

	@Override
	public Dimension getPreferredSize(){
		return new Dimension(WIDTH, HEIGHT);
	}
}
