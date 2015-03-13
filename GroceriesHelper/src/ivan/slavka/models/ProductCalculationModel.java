package ivan.slavka.models;

import ivan.slavka.bean.ProductBean;
import ivan.slavka.interfaces.IProductCalculationModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class ProductCalculationModel implements IProductCalculationModel{

	private static ProductCalculationModel instance = null;
	private static SimpleDateFormat DATE_FORMATTER = new SimpleDateFormat("dd/MM/yyyy");

	private HashMap<String, List<ProductBean>> productsHashMap = new HashMap<>();

	private ProductCalculationModel(){

	}

	public static ProductCalculationModel getInstance(){
		if(instance == null){
			instance = new ProductCalculationModel();
		}
		return instance;
	}

	private void loadProducts(){
		this.productsHashMap.clear();
		String sCurrentLine;
		BufferedReader br = null;
		ProductBean product;
		try{
			br = new BufferedReader(new FileReader("./Testing.txt"));

			while ((sCurrentLine = br.readLine()) != null) {
				product = this.parseProduct(sCurrentLine);
				if(this.productsHashMap.containsKey(product.getName())){
					List<ProductBean> productList = this.productsHashMap.get(product.getName());
					productList.add(product);
				} else {
					List<ProductBean> productList = new ArrayList<>();
					productList.add(product);
					this.productsHashMap.put(product.getName(), productList);
				}

			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

	private ProductBean parseProduct(String productString){
		StringTokenizer tokenizer = new StringTokenizer(productString, ";");
		ProductBean product = new ProductBean();
		product.setName(tokenizer.nextToken());
		product.setPrice(Double.parseDouble(tokenizer.nextToken()));
		product.setQuantity(Integer.parseInt(tokenizer.nextToken()));
		product.setShop(tokenizer.nextToken());
		try {
			product.setBuyingDate(DATE_FORMATTER.parse(tokenizer.nextToken()));
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return product;
	}

	@Override
	public List<ProductBean> calculateProductExpiration() {
		this.loadProducts();

		List<ProductBean> productList = new ArrayList<>();

		for(String key : this.productsHashMap.keySet()){
			productList.add(this.calculateExpiration(this.productsHashMap.get(key)));
		}

		return productList;
	}

	private ProductBean calculateExpiration(List<ProductBean> productList){
		ProductBean product;
		this.sortProductsByDate(productList);

		double sumAverageConsumption = 0d;
		for(int i = 0; i < productList.size() - 1; i++){
			int dayDifference = this.getDateDifference(productList.get(i).getBuyingDate(), productList.get(i + 1).getBuyingDate());
			if(dayDifference <= 0){
				dayDifference = 1;
			}

			double averageConsumption = productList.get(i).getQuantity() / dayDifference;
			sumAverageConsumption += averageConsumption;
		}

		int totalAverageConsumption = (int) Math.ceil(sumAverageConsumption / productList.size());

		if(totalAverageConsumption <= 0){
			totalAverageConsumption = 1;
		}
		int expireInDays = productList.get(productList.size() - 1).getQuantity() / totalAverageConsumption;

		product = productList.get(0);

		int daysPassedSinceLastBuy = this.getDateDifference(productList.get(productList.size() - 1).getBuyingDate(), new Date());
		product.setExpireInDays(expireInDays - daysPassedSinceLastBuy);

		return product;
	}

	private void sortProductsByDate(List<ProductBean> productList){

		boolean isSortingFinished = false;
		ProductBean productA;
		ProductBean productB;
		while(!isSortingFinished){
			isSortingFinished = true;
			for(int i = 0; i < productList.size() - 1; i++){
				productA = productList.get(i);
				productB = productList.get(i + 1);
				if(productA.getBuyingDate().after(productB.getBuyingDate())){
					productList.set(i, productB);
					productList.set(i + 1, productA);
					isSortingFinished = false;
				}
			}
		}
	}

	private int getDateDifference(Date dateA, Date dateB){
		Calendar calA = Calendar.getInstance();
		calA.setTime(dateA);

		Calendar calB = Calendar.getInstance();
		calB.setTime(dateB);

		long differenceMillis = calB.getTimeInMillis() - calA.getTimeInMillis();
		int differenceDays = (int) Math.floor(differenceMillis / (1000d * 60d * 60d * 24d));

		return differenceDays;
	}

	@Override
	public List<String> retrieveProductNames() {
		List<String> productNameList = new ArrayList<>();

		if(this.productsHashMap.isEmpty()){
			this.loadProducts();
		}

		productNameList.add("");
		for(String name : this.productsHashMap.keySet()){
			productNameList.add(name);
		}

		return productNameList;
	}

	@Override
	public List<String> retrieveShopNames() {
		// TODO Auto-generated method stub
		return null;
	}
}
