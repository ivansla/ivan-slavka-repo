package ivan.slavka;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

public class NewPanel extends JPanel{

	private static final int HEIGHT = 300;
	private static final int WIDTH = 400;

	private BufferedImage image;

	public NewPanel(){
		this.image = new BufferedImage(200, 200, BufferedImage.TYPE_INT_ARGB);
	}

	public void drawRectangle(){
		this.repaint();
	}

	@Override
	public void paintComponent(Graphics g){

		Graphics g1 = this.image.getGraphics();
		g1.setColor(Color.BLACK);
		g1.fillRect(0, 0, 200, 200);

		for(int i = 0; i < 100; i++){
			for(int j = 0; j < 100; j++){
				this.image.setRGB(i + 50, j + 100, 0xFF00FF00);
			}
		}




		g.drawImage(this.image,	50, 50, this);
		System.out.println(this.image.getRGB(80, 80));
		System.out.println(0xFFFFFFFF);
	}

	@Override
	public Dimension getPreferredSize(){
		return new Dimension(WIDTH, HEIGHT);
	}
}
