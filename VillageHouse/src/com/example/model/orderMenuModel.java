package com.example.model;

public class orderMenuModel {
	public String foodName;
	public String foodPrice;

	public orderMenuModel(String foodName, String foodPrice) {
		super();
		this.foodName = foodName;
		this.foodPrice = foodPrice;
	}

	public String getFoodName() {
		return foodName;
	}

	public void setFoodName(String foodName) {
		this.foodName = foodName;
	}

	public String getFoodPrice() {
		return foodPrice;
	}

	public void setFoodPrice(String foodPrice) {
		this.foodPrice = foodPrice;
	}

}
