package com.model;

public class CommentModel {
   
	private String cid;    //���ֽ��ױ��
	private String cname;  //�û�����
	private String ccontent;  //��������
	private String ctime="";    //����ʱ��
	
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
