package com.example.model;

public class FoodModel {
  
	private String fid;
	private String fimage;
	private String fname;
	private String fprofile;
	private String fsend;
	private String faddress;
	private String ftime;
	
	public FoodModel() {
		super();
	}
	public FoodModel(String fid,String fimage,String fname,String fprofile,String fsend
			,String faddress,String ftime)
	{
		super();
		this.fid=fid;
		this.fimage=fimage;
		this.fname=fname;
		this.fprofile=fprofile;
		this.fsend=fsend;
		this.faddress=faddress;
		this.ftime=ftime;
	}
	
	public String getFid() {
		return fid;
	}
	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getFimage() {
		return fimage;
	}
	public void setFimage(String fimage) {
		this.fimage = fimage;
	}
	public String getFname() {
		return fname;
	}
	public void setFname(String fname) {
		this.fname = fname;
	}
	public String getFprofile() {
		return fprofile;
	}
	public void setFprofile(String fprofile) {
		this.fprofile = fprofile;
	}
	public String getFsend() {
		return fsend;
	}
	public void setFsend(String fsend) {
		this.fsend = fsend;
	}
	public String getFaddress() {
		return faddress;
	}
	public void setFaddress(String faddress) {
		this.faddress = faddress;
	}
	public String getFtime() {
		return ftime;
	}
	public void setFtime(String ftime) {
		this.ftime = ftime;
	}
	
	
}
