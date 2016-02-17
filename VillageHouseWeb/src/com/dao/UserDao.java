package com.dao;

import java.util.ArrayList;

import com.model.User;

public interface UserDao {
	public ArrayList<User> login(String username, String pwd);// 登陆方法

	public String regist(String username, String pwd);// 注册方法
}
