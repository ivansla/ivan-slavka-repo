package ivan.slavka.controller;

import ivan.slavka.bean.ProductBean;
import ivan.slavka.interfaces.IMainController;
import ivan.slavka.interfaces.IProductCalculationModel;
import ivan.slavka.models.ProductCalculationModel;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

public class MainController implements IMainController{

	private static MainController controller = null;
	private static SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd/MM/yyyy");
	private IProductCalculationModel productCalculationModel = ProductCalculationModel.getInstance();

	private MainController(){

	}

	public static MainController getInstance(){
		if(controller == null){
			controller = new MainController();
		}
		return controller;
	}

	@Override
	public boolean commit(List<ProductBean> productList){

		BufferedWriter bw = null;

		try {

			bw = new BufferedWriter(new FileWriter("./Testing.txt", true));

			for(ProductBean product : productList){

				bw.append(this.stringifyProduct(product));
				bw.newLine();
			}

			bw.flush();

			return true;
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (bw != null)bw.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}

		return false;
	}

	private String stringifyProduct(ProductBean productBean){
		StringBuilder stringifiedProduct = new StringBuilder("");

		stringifiedProduct.append(productBean.getName() + ";");
		stringifiedProduct.append(productBean.getPrice() + ";");
		stringifiedProduct.append(productBean.getQuantity() + ";");
		stringifiedProduct.append(productBean.getShop() + ";");
		stringifiedProduct.append(DATE_FORMATTER.format(productBean.getBuyingDate()));

		return stringifiedProduct.toString();
	}

	@Override
	public List<ProductBean> showProductExpiration() {
		return this.productCalculationModel.calculateProductExpiration();
	}

	@Override
	public List<String> retrieveProductNames() {
		return this.productCalculationModel.retrieveProductNames();
	}
}
