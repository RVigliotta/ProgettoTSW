package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Order {
	private double totalPrice;
	private String DateTime;
	private int userId;
	public List<ProductBean> products;
	private String state;
	
	


	public Order() {
		products = new ArrayList<ProductBean>();
	}
	
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public double getTotalPrice() {
		return totalPrice;
	}
	
	public int getUserId() {
		return userId;
	}
	
	public void setDateTime(String DateTime) {
		this.DateTime=DateTime;
	}
	
	public String getDateTime() {
		return DateTime;
	}
	
	public void setUserId(int userId) {
		this.userId=userId;
	}
	
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}
	
	public List<ProductBean> getProducts() {
		return  products;
	}
	
	public void addProductsFromCart(Collection<ProductBean> product) {
		products.addAll(product);
	}
	
    
}
