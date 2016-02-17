package com.model;

public class RepairModel {
   
	private String rusername;
	private String rname;
	private String rcontent;
	private String raddress;
	private String rtime;
	
	public RepairModel()
	{
		super();
	}
	
	public RepairModel(String rusername,String rname,String rcontent,String raddress,String rtime)
	{
		super();
		this.rusername=rusername;
		this.rname=rname;
		this.rcontent=rcontent;
		this.raddress=raddress;
		this.rtime=rtime;
	}


	public String getRusername() {
		return rusername;
	}

	public void setRusername(String rusername) {
		this.rusername = rusername;
	}

	public String getRname() {
		return rname;
	}

	public void setRname(String rname) {
		this.rname = rname;
	}

	public String getRcontent() {
		return rcontent;
	}

	public void setRcontent(String rcontent) {
		this.rcontent = rcontent;
	}

	public String getRaddress() {
		return raddress;
	}

	public void setRaddress(String raddress) {
		this.raddress = raddress;
	}

	public String getRtime() {
		return rtime;
	}

	public void setRtime(String rtime) {
		this.rtime = rtime;
	}
	
	
}
