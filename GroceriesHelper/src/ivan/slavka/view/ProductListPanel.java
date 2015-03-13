package ivan.slavka.view;

import ivan.slavka.bean.ProductBean;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class ProductListPanel extends JPanel{

	private List<ProductBean> productList = new ArrayList<>();

	public ProductListPanel(){

		//this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setLayout(new MigLayout("", "[grow]", "[grow, top]"));
		//this.setBorder(BorderFactory.createEmptyBorder());
	}

	public void addProduct(ProductBean product){
		ProductPanel productPanel = new ProductPanel(product, this);
		this.productList.add(product);
		this.add(productPanel, "dock north");
		this.revalidate();
	}

	public void removeProduct(ProductPanel product){
		this.productList.remove(product.getProduct());
		this.remove(product);
		this.revalidate();
	}

	public List<ProductBean> getProductList(){
		return this.productList;
	}

	public void commitProducts(){
		this.removeAll();
		this.validate();
	}

	public void showProductExpiration(List<ProductBean> productList){
		this.removeAll();
		//List<ProductBean> productList = this.mainPanel.showProductExpiration();

		ProductPanel productPanel;
		for(ProductBean product : productList){
			productPanel = new ProductPanel(product, null);
			productPanel.setProductPanelDisplay(0);
			this.add(productPanel, "dock north");
		}
		this.revalidate();
	}
}
