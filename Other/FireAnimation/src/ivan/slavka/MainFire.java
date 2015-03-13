package ivan.slavka;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MainFire {

	public static void main(String[] args) {

		SwingUtilities.invokeLater(new Runnable() {

			@Override
			public void run() {

				JFrame frame = new JFrame();
				NewPanel p1 = new NewPanel();
				FirePanel panel = new FirePanel();
				frame.setContentPane(panel);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.pack();
				frame.setVisible(true);

				//p1.drawRectangle();
				panel.drawFire();
			}
		});
	}
}
