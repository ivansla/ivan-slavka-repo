package ivan.slavka.main;

import ivan.slavka.view.MainPanel;

public class GroceriesHelperMain {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("ahoj");

		MainPanel t = new MainPanel();
		//t.showProductExpiration();
		t.initComponents();
		t.initWindow();
	}

}
