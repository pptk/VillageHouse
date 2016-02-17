package com.daogo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.conn.ConnectionFactory;
import com.dao.UserDao;
import com.model.User;

public class UserDaoImp implements UserDao {
	private Connection conn;
	private PreparedStatement pstm;
	private ResultSet rs;

	// µÇÂ½
	public ArrayList<User> login(String username, String pwd) {
		conn = ConnectionFactory.getConnection();
		ArrayList<User> list = new ArrayList<User>();
		try {
			StringBuffer sb = new StringBuffer();
			sb.append(" SELECT * ");
			sb.append(" FROM userTab ");
			sb.append(" WHERE username = ? ");
			sb.append(" And pwd = ? ");
			pstm = conn.prepareStatement(sb.toString());
			pstm.setString(1, username);
			pstm.setString(2, pwd);
			rs = pstm.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUsername(rs.getString("username"));
				user.setHead(rs.getString("head"));
				user.setName(rs.getString("name"));
				user.setPwd(rs.getString("pwd"));
				list.add(user);
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	// ×¢²á
	public String regist(String username, String pwd) {
		String flag = null;
		conn = ConnectionFactory.getConnection();
		try {
			StringBuffer sb = new StringBuffer();
			sb.append(" INSERT INTO userTab(username,pwd,name,head) ");
			sb.append(" VALUES(?,?,?,?) ");
			pstm = conn.prepareStatement(sb.toString());
			pstm.setString(1, username);
			pstm.setString(2, pwd);
			pstm.setString(3, "ØýÃû");
			pstm.setString(4, "http://qlogo1.store.qq.com/qzone/360791772/360791772/100");
			rs = pstm.executeQuery();
			flag = "yes";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public static void main(String[] args) {
		ArrayList<User> list = new ArrayList<User>();
		UserDao dao = new UserDaoImp();
		System.out.println(dao.regist("120", "120"));
	}

}
