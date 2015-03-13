package ivan.slavka.interfaces;

import ivan.slavka.bean.ProductBean;

public interface IMainPanel {

	public void addProduct(ProductBean product);

	public void commit();
}
