package com.example.model;

public class ShoppingModel {
      
	private String sid;
	private String simage;
	private String sname;
	private String sphone;
	private String sprice;
	
	public ShoppingModel(){
		super();
	}
	
	public ShoppingModel(String sid,String simage,String sname,String sphone,String sprice){
	    super();
	    this.sid=sid;
	    this.simage=simage;
	    this.sname=sname;
	    this.sphone=sphone;
	    this.sprice=sprice;
	}
	
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getSname() {
		return sname;
	}
	public void setSname(String sname) {
		this.sname = sname;
	}
	public String getSphone() {
		return sphone;
	}
	public void setSphone(String sphone) {
		this.sphone = sphone;
	}
	public String getSprice() {
		return sprice;
	}
	public void setSprice(String sprice) {
		this.sprice = sprice;
	}

	public String getSimage() {
		return simage;
	}

	public void setSimage(String simage) {
		this.simage = simage;
	}
	
	
}
