package com.model;

public class CommentModel {
   
	private String cid;    //二手交易编号
	private String cname;  //用户姓名
	private String ccontent;  //评论内容
	private String ctime="";    //评论时间
	
	public CommentModel() {
		super();
	}
	
	public CommentModel(String cid,String cname,String ccontent,String ctime){
		super();
		this.setCid(cid);
		this.setCname(cname);
		this.setCcontent(ccontent);
		this.setCtime(ctime);
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
