package ivan.slavka;

import ivan.slavka.view.components.panels.window.InvitationRequestPanel;

import java.awt.EventQueue;

import javax.swing.JFrame;

public class InvitationRequestPanelTest {

	private JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					InvitationRequestPanelTest window = new InvitationRequestPanelTest();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public InvitationRequestPanelTest() {
		this.initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.frame = new JFrame();
		this.frame.setBounds(100, 100, 500, 500);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.setContentPane(new InvitationRequestPanel(null));
	}

}
