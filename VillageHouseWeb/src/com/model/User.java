package com.model;

public class User {
	private String username;
	private String pwd;
	private String name;
	private String head;

	public User() {
		super();
	}

	public User(String username, String pwd, String name, String head) {
		super();
		this.username = username;
		this.pwd = pwd;
		this.name = name;
		this.head = head;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getHead() {
		return head;
	}

	public void setHead(String head) {
		this.head = head;
	}

}
