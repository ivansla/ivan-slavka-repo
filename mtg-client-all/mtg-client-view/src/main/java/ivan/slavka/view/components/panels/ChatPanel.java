package ivan.slavka.view.components.panels;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

public class ChatPanel extends JPanel {

	private static ChatPanel chatComponent = null;
	/**
	 * Create the panel.
	 */
	public ChatPanel() {
		this.setLayout(new MigLayout("", "[]", "[]"));
		this.setBorder(new TitledBorder("Chat Window"));
	}

}
