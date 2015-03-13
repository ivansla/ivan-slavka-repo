package ivan.slavka.view;

import ivan.slavka.bean.ProductBean;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class ProductPanel extends JPanel{

	private JLabel nameLabel;
	private JLabel quantityLabel;
	private JLabel priceLabel;
	private JLabel shopLabel;
	private JLabel buyingDateLabel;
	private JLabel expirationInDaysLabel;
	private JButton removeProductButton;

	private ProductListPanel listPanel;
	private ProductBean product;

	private static SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd/MM/yyyy");

	public ProductPanel(ProductBean product, ProductListPanel listPanel){
		this.product = product;
		this.listPanel = listPanel;

		//this.setLayout(new MigLayout("", "[grow][50][50][grow][50][100][grow]", "[grow]"));
		this.setLayout(new MigLayout("", "[:50:150]10[20][20][:50:150]10[20][:50:150][:50:150]", "[grow]"));

		this.initComponents(product);
	}

	private void initComponents(ProductBean product){

		this.nameLabel = new JLabel(product.getName());
		this.add(this.nameLabel, "cell 0 0");

		this.quantityLabel = new JLabel(String.valueOf(product.getQuantity()));
		this.add(this.quantityLabel, "cell 1 0");

		this.priceLabel = new JLabel(String.valueOf(product.getPrice()));
		this.add(this.priceLabel, "cell 2 0");

		this.shopLabel = new JLabel(product.getShop());
		this.add(this.shopLabel, "cell 3 0");

		this.expirationInDaysLabel = new JLabel(String.valueOf(product.getExpireInDays()));
		this.expirationInDaysLabel.setVisible(false);
		this.add(this.expirationInDaysLabel, "cell 4 0");

		this.buyingDateLabel = new JLabel(DATE_FORMATTER.format(product.getBuyingDate()));
		this.add(this.buyingDateLabel, "cell 5 0");

		this.removeProductButton = new JButton("Remove");
		this.add(this.removeProductButton, "cell 6 0");
		if(this.listPanel != null){
			this.removeProductButton.addActionListener(new ActionListener() {

				@Override
				public void actionPerformed(ActionEvent e) {
					ProductPanel.this.listPanel.removeProduct(ProductPanel.this);
				}
			});
		}
	}

	public ProductBean getProduct(){
		return this.product;
	}

	public void setProductPanelDisplay(int displayParameter){
		switch (displayParameter) {
		case 0:
			this.quantityLabel.setVisible(false);
			this.priceLabel.setVisible(false);
			this.removeProductButton.setVisible(false);
			this.buyingDateLabel.setVisible(false);
			this.expirationInDaysLabel.setVisible(true);
			break;
		default:
			break;
		}
	}
}
