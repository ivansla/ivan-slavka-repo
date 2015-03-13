package ivan.slavka.interfaces;

import ivan.slavka.bean.ProductBean;

import java.util.List;

public interface IProductCalculationModel {

	public List<ProductBean> calculateProductExpiration();

	public List<String> retrieveProductNames();

	public List<String> retrieveShopNames();
}
