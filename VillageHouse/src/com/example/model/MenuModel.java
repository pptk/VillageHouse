package com.example.model;

public class MenuModel {
    
	private String mid;
	private String mname;
	private String mprice;
	
	public MenuModel()
	{
		super();
	}
	public MenuModel(String mname,String mprice)
	{
		super();
		this.mname=mname;
		this.mprice=mprice;
	}
	
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getMname() {
		return mname;
	}
	public void setMname(String mname) {
		this.mname = mname;
	}
	public String getMprice() {
		return mprice;
	}
	public void setMprice(String mprice) {
		this.mprice = mprice;
	}
	
	
}
