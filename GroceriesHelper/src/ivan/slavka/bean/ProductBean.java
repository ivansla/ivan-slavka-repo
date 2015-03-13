package ivan.slavka.bean;

import java.util.Date;

public class ProductBean {

	private String name;
	private int quantity;
	private double price;
	private String shop;
	private Date buyingDate;
	private int expireInDays;

	public ProductBean(){
		this.name = " ";
		this.quantity = 0;
		this.price = 0;
		this.shop = " ";
		this.expireInDays = 0;
	}

	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getQuantity() {
		return this.quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getPrice() {
		return this.price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public String getShop() {
		return this.shop;
	}
	public void setShop(String shop) {
		this.shop = shop;
	}

	public Date getBuyingDate() {
		return this.buyingDate;
	}

	public void setBuyingDate(Date buyingDate) {
		this.buyingDate = buyingDate;
	}

	public int getExpireInDays() {
		return this.expireInDays;
	}

	public void setExpireInDays(int expireInDays) {
		this.expireInDays = expireInDays;
	}
}
