package com.model;

public class SuggestModel {
   
	private String su_name;
	private String su_content;
	private String su_time;
	
	public SuggestModel()
	{
		super();
	}
	
	public SuggestModel(String su_name,String su_content,String su_time)
	{
		super();
		this.setSu_name(su_name);
		this.setSu_content(su_content);
		this.setSu_time(su_time);
	}

	public String getSu_name() {
		return su_name;
	}

	public void setSu_name(String su_name) {
		this.su_name = su_name;
	}

	public String getSu_content() {
		return su_content;
	}

	public void setSu_content(String su_content) {
		this.su_content = su_content;
	}

	public String getSu_time() {
		return su_time;
	}

	public void setSu_time(String su_time) {
		this.su_time = su_time;
	}
}
