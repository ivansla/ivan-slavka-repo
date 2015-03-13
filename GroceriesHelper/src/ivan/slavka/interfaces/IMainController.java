package ivan.slavka.interfaces;

import ivan.slavka.bean.ProductBean;

import java.util.List;

public interface IMainController {

	public boolean commit(List<ProductBean> productList);

	public List<ProductBean> showProductExpiration();

	public List<String> retrieveProductNames();
}
