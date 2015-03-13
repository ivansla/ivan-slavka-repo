package ivan.slavka.view.components.panels.connection;

import ivan.slavka.utils.beans.RosterBean;
import ivan.slavka.utils.interfaces.IRosterControl;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;

import net.miginfocom.swing.MigLayout;

public class RosterPanel extends JPanel implements IRosterControl{

	private final DefaultListModel listModel;

	private JList listOfUsersJList;

	public RosterPanel() {
		this.listModel = new DefaultListModel();

		this.initComponents();
	}

	private void initComponents(){

		this.setLayout(new MigLayout("", "[grow]", "[grow]"));
		this.setBorder(new TitledBorder("Connected Users: "));

		this.listOfUsersJList = new JList();
		this.listOfUsersJList.setModel(this.listModel);
		this.listOfUsersJList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.add(this.listOfUsersJList, "cell 0 0,grow");
	}

	/////////////////////////////////////////////////////
	//	implementation (IRosterControl)
	/////////////////////////////////////////////////////

	@Override
	public void updateRoster(RosterBean roster) {
		this.listModel.clear();
		for(String username : roster.getRoster()){
			this.listModel.addElement(username);
		}
	}

	@Override
	public String getOpponentName() {
		return (String)this.listOfUsersJList.getSelectedValue();
	}
}
