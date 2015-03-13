package ivan.slavka.panel;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SpendManaPopupWindowPanelTest extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				try {
					SpendManaPopupWindowPanelTest frame = new SpendManaPopupWindowPanelTest();
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
	public SpendManaPopupWindowPanelTest() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setBounds(100, 100, 450, 300);
		//this.setContentPane(new SpendManaPopupWindowPanel(null, null, null));
	}

}
