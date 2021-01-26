package com.java.kiosk.model.vo;

public class SalesMenu {
	private String menu;
	private int quantity;
	private int price;
	
	
	public SalesMenu() {
		super();
		// TODO Auto-generated constructor stub
	}

	public SalesMenu(String menu, int quantity, int price) {
		super();
		this.menu = menu;
		this.quantity = quantity;
		this.price = price;
	}	
	
	public String getMenu() {
		return menu;
	}
	public void setMenu(String menu) {
		this.menu = menu;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	
	
	@Override
	public String toString() {
		return "Menu [menu=" + menu + ", quantity=" + quantity + ", price=" + price + "]";
	}
}
