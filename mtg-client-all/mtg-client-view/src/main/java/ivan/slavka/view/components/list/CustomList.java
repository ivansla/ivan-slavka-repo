package ivan.slavka.view.components.list;

import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class CustomList extends JScrollPane{

	private final DefaultListModel listModel;
	private boolean testMode = false;

	/**
	 * Constructor to create single selection List for general purposes.
	 * 
	 * @param displayableRows - Number of rows to display.
	 */
	public CustomList(int displayableRows){
		this.listModel = new DefaultListModel();

		this.initComponents(displayableRows);
	}

	/**
	 * Constructor to create test single selection List for general purposes.
	 * 
	 * @param displayableRows - Number of rows to display.
	 */
	public CustomList(int displayableRows, boolean testMode){
		this.listModel = new DefaultListModel();
		this.testMode = testMode;

		this.initComponents(displayableRows);
	}

	private void initComponents(int displayableRows){

		JList list = new JList();
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setModel(this.listModel);
		list.setFixedCellWidth(200);
		list.setVisibleRowCount(displayableRows);
		this.setViewportView(list);

		if(this.testMode){
			this.fillTestValues();
		}
	}

	synchronized public void addElement(Object element){
		this.listModel.addElement(element);
	}

	synchronized public void removeElement(Object element){
		this.listModel.removeElement(element);
		this.resortElements();
	}

	private void resortElements(){
		Object[] tempArray = new Object[this.listModel.getSize()];
		this.listModel.copyInto(tempArray);
		this.listModel.clear();
		for(Object o : tempArray){
			this.listModel.addElement(o);
		}
	}

	private void fillTestValues(){
		for(int i = 0; i < 50; i++){
			this.listModel.addElement("Test Value # " + i);
		}
	}
}
