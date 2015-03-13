package ivan.slavka.view.frames;

import ivan.slavka.view.panels.MTGClientPanel;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class ClientFrame extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public ClientFrame() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.contentPane = new MTGClientPanel(null);
		this.setBounds(100, 100, 1000, 500);
		this.setContentPane(this.contentPane);
		this.setResizable(false);
	}
}
