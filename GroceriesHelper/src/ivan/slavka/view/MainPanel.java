package ivan.slavka.view;


import ivan.slavka.bean.ProductBean;
import ivan.slavka.controller.MainController;
import ivan.slavka.interfaces.IMainController;
import ivan.slavka.interfaces.IMainPanel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

import net.miginfocom.swing.MigLayout;

public class MainPanel extends JPanel implements IMainPanel{

	private ProductAdditionPanel productAdditionPanel;
	private ProductListPanel productListPanel = new ProductListPanel();
	private ProductListPanel productPredictionPanel;

	private IMainController controller = MainController.getInstance();

	public MainPanel(){
		this.productAdditionPanel = new ProductAdditionPanel(this);
		this.productPredictionPanel = new ProductListPanel();

		this.setLayout(new MigLayout("", "[grow][grow]", "[60, top][grow][30]"));

		this.createComponents();
	}

	public void initComponents(){
		this.productAdditionPanel.initComponents(this.controller.retrieveProductNames());
		this.productPredictionPanel.showProductExpiration(this.controller.showProductExpiration());
	}

	private void createComponents(){
		JScrollPane scrollPane = new JScrollPane(this.productListPanel);
		scrollPane.setBorder(BorderFactory.createEmptyBorder());

		this.add(this.productAdditionPanel, "cell 0 0");
		this.add(scrollPane, "cell 0 1, grow");

		JButton commitButton = new JButton("Commit");
		this.add(commitButton, "cell 0 2");
		commitButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel.this.commit();
			}
		});

		JScrollPane productPredictionScrollPane = new JScrollPane(this.productPredictionPanel);
		this.add(productPredictionScrollPane, "cell 1 0, spany");
		productPredictionScrollPane.setBorder(BorderFactory.createEmptyBorder());
	}

	public void initWindow(){
		JFrame frame = new JFrame();

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setBounds(100, 100, 1000, 500);
		frame.setContentPane(this);
		//frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setResizable(true);
		frame.setVisible(true);
	}

	@Override
	public void addProduct(ProductBean product){
		this.productListPanel.addProduct(product);
	}

	@Override
	public void commit(){
		this.controller.commit(this.productListPanel.getProductList());
		this.productListPanel.commitProducts();
		this.productPredictionPanel.showProductExpiration(this.controller.showProductExpiration());
	}

	/*
	@Override
	public List<ProductBean> showProductExpiration() {
		return this.controller.showProductExpiration();
	}

	@Override
	public List<String> retrieveProductNames() {
		return this.controller.retrieveProductNames();
	}
	 */
}
