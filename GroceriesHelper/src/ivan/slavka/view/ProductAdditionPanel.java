package ivan.slavka.view;

import ivan.slavka.bean.ProductBean;
import ivan.slavka.interfaces.IMainPanel;
import ivan.slavka.utils.Java2sAutoTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import net.miginfocom.swing.MigLayout;

public class ProductAdditionPanel extends JPanel{

	private JTextField quantityTextField;
	private JTextField priceTextField;
	private JTextField shopTextField;
	private JTextField buyingDateTextField;
	private JButton addProductButton;
	private Java2sAutoTextField productNameAutoTextField;

	private IMainPanel mainPanel;
	private static SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd/MM/yyyy");

	/* *
	 * Default constructor only necessary for window builder pro to be able to crate view.
	 * */

	public ProductAdditionPanel(){
		this.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow][grow]", "[grow][grow]"));

		this.createComponents();
	}

	public ProductAdditionPanel(IMainPanel mainPanel){
		this.mainPanel = mainPanel;

		this.setLayout(new MigLayout("", "[grow][grow][grow][grow][grow][grow]", "[grow][grow]"));

		this.createComponents();
	}

	public void initComponents(List<String> productNameList){
		this.productNameAutoTextField.init(productNameList);
		this.buyingDateTextField.setText(DATE_FORMATTER.format(new Date()));
	}

	private void createComponents(){

		this.productNameAutoTextField = new Java2sAutoTextField();
		this.productNameAutoTextField.setStrict(false);
		this.productNameAutoTextField.setColumns(15);
		this.add(this.productNameAutoTextField, "cell 0 1");
		this.add(new JLabel("Product Name"), "cell 0 0");

		this.quantityTextField = new JTextField(5);
		this.add(this.quantityTextField, "cell 1 1");
		this.add(new JLabel("Qty"), "cell 1 0");

		this.priceTextField = new JTextField(5);
		this.add(this.priceTextField, "cell 2 1");
		this.add(new JLabel("Price"), "cell 2 0");

		this.shopTextField = new JTextField(10);
		this.add(this.shopTextField, "cell 3 1");
		this.add(new JLabel("Shop Name"), "cell 3 0");

		this.buyingDateTextField = new JTextField(6);
		this.add(this.buyingDateTextField, "cell 4 1");
		this.add(new JLabel("Buying Date"), "cell 4 0");

		this.addProductButton = new JButton("Add");
		this.add(this.addProductButton, "cell 5 1");

		this.addProductButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				ProductAdditionPanel.this.validateFields();

				ProductBean product = new ProductBean();
				product.setName(ProductAdditionPanel.this.productNameAutoTextField.getText());
				if(!ProductAdditionPanel.this.priceTextField.getText().isEmpty()){
					product.setPrice(Double.parseDouble(ProductAdditionPanel.this.priceTextField.getText()));
				}

				if(!ProductAdditionPanel.this.quantityTextField.getText().isEmpty()){
					product.setQuantity(Integer.parseInt(ProductAdditionPanel.this.quantityTextField.getText()));
				}
				product.setShop(ProductAdditionPanel.this.shopTextField.getText());

				if(!ProductAdditionPanel.this.buyingDateTextField.getText().isEmpty()){
					try {
						product.setBuyingDate(DATE_FORMATTER.parse(ProductAdditionPanel.this.buyingDateTextField.getText()));
					} catch (ParseException e1) {
						System.out.println("Unable to parse date");
						System.out.println(e1);
					}
				} else {
					product.setBuyingDate(new Date());
				}


				ProductAdditionPanel.this.mainPanel.addProduct(product);
				ProductAdditionPanel.this.clearInputFields();
			}
		});
	}

	private boolean validateFields(){

		if(this.productNameAutoTextField.getText().isEmpty()){
			this.productNameAutoTextField.setText("UNKNOWN_PRODUCT");
		}

		if(this.quantityTextField.getText().isEmpty()){
			this.quantityTextField.setText("1");
		}

		if(this.priceTextField.getText().isEmpty()){
			this.priceTextField.setText("0.0");
		}

		if(this.shopTextField.getText().isEmpty()){
			this.shopTextField.setText("UNKNOWN_SHOP");
		}


		return true;
	}

	private void clearInputFields(){
		this.productNameAutoTextField.setText("");
		this.priceTextField.setText("");
		this.quantityTextField.setText("");
		this.shopTextField.setText("");
		this.buyingDateTextField.setText(DATE_FORMATTER.format(new Date()));
	}
}
