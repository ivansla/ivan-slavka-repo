package ivan.slavka;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 * Unit test for simple App.
 */
public class AppTest extends JFrame{

	private final JPanel contentPane = new JPanel();
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					AppTest frame = new AppTest();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public AppTest() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//this.contentPane = new MTGClientPanel(null);
		this.setBounds(50, 20, 1000, 700);
		this.setContentPane(new JPanel());
		this.setResizable(false);
	}
}
