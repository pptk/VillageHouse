package com.example.model;

public class YuleModel {
    
	private String yid;
	private String ytype;
	private String yimage;
	private String yprofile;
	private String ytitle;
	private String ycontent;
	private String yphone;
	private String ybimage;
	
	public YuleModel()
	{
		super();
	}
	public YuleModel(String yid,String yimage,String ytitle,String yprofile)
	{
		super();
		this.yid=yid;
		this.yimage=yimage;
		this.ytitle=ytitle;
		this.yprofile=yprofile;
	}
	
	public YuleModel(String ytype,String ytitle,String ycontent,String yphone,String ybimage)
	{
		super();
		this.ytype=ytype;
		this.ytitle=ytitle;
		this.ycontent=ycontent;
		this.yphone=yphone;
		this.ybimage=ybimage;
	}
	
	public YuleModel(String ybimage,String yphone,String ycontent)
	{
		super();
		this.ybimage=ybimage;
		this.yphone=yphone;
		this.ycontent=ycontent;
	}
	
	public String getYid() {
		return yid;
	}
	public void setYid(String yid) {
		this.yid = yid;
	}
	public String getYtype() {
		return ytype;
	}
	public void setYtype(String ytype) {
		this.ytype = ytype;
	}
	public String getYimage() {
		return yimage;
	}
	public void setYimage(String yimage) {
		this.yimage = yimage;
	}
	public String getYprofile() {
		return yprofile;
	}
	public void setYprofile(String yprofile) {
		this.yprofile = yprofile;
	}
	public String getYtitle() {
		return ytitle;
	}
	public void setYtitle(String ytitle) {
		this.ytitle = ytitle;
	}
	public String getYcontent() {
		return ycontent;
	}
	public void setYcontent(String ycontent) {
		this.ycontent = ycontent;
	}
	public String getYphone() {
		return yphone;
	}
	public void setYphone(String yphone) {
		this.yphone = yphone;
	}
	public String getYbimage() {
		return ybimage;
	}
	public void setYbimage(String ybimage) {
		this.ybimage = ybimage;
	}
	
}
