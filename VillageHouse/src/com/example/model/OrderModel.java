package com.example.model;

public class OrderModel {
	String userName;
	String shopName;
	String orderMenu;
	String telphone;
	String orderTime;

	public OrderModel() {
		super();
	}

	public OrderModel(String userName, String shopName, String orderMenu,
			String telphone, String orderTime) {
		super();
		this.userName = userName;
		this.shopName = shopName;
		this.orderMenu = orderMenu;
		this.telphone = telphone;
		this.orderTime = orderTime;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getShopName() {
		return shopName;
	}

	public void setShopName(String shopName) {
		this.shopName = shopName;
	}

	public String getOrderMenu() {
		return orderMenu;
	}

	public void setOrderMenu(String orderMenu) {
		this.orderMenu = orderMenu;
	}

	public String getTelphone() {
		return telphone;
	}

	public void setTelphone(String telphone) {
		this.telphone = telphone;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public void setOrderTime(String orderTime) {
		this.orderTime = orderTime;
	}

}
