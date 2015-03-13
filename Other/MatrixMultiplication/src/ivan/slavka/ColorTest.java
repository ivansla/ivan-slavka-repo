package ivan.slavka;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class ColorTest {

	public static void main(String[] args) {

		Color c = new Color(254, 110, 75, 100);
		BufferedImage image = new BufferedImage(100, 100, BufferedImage.TYPE_INT_ARGB);
		Graphics g = image.getGraphics();
		g.setColor(c);
		g.fillRect(0, 0, 100, 100);

		System.out.println(c.getRGB());
		System.out.println(image.getRGB(40, 40));
	}
}
