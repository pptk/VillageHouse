package com.example.model;

public class CommentModel {
   
	private String cid;
	private String cname;
	private String ccontent;
	private String ctime;
	
	public CommentModel(String cname,String ctime,String ccontent)
	{
		super();
		this.setCname(cname);
		this.setCtime(ctime);
		this.setCcontent(ccontent);
	}

	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}

	public String getCname() {
		return cname;
	}

	public void setCname(String cname) {
		this.cname = cname;
	}

	public String getCcontent() {
		return ccontent;
	}

	public void setCcontent(String ccontent) {
		this.ccontent = ccontent;
	}

	public String getCtime() {
		return ctime;
	}

	public void setCtime(String ctime) {
		this.ctime = ctime;
	}



}
