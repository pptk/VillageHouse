package com.daogo;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conn.ConnectionFactory;
import com.dao.OrderDao;
import com.model.OrderModel;

public class OrderDaogo implements OrderDao {

	// 建立连接数据库
	private Connection conn = null;
	private PreparedStatement pstm = null;
	private ResultSet rs = null;

	// 添加订单
	@Override
	public Serializable add_order(OrderModel order) {
		try {
			conn = ConnectionFactory.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append(" INSERT INTO dingdan(username,shopname,ordermenu,telphone,ordertime) ");
			sql.append(" VALUES(?,?,?,?,?) ");

			pstm = conn.prepareStatement(sql.toString());

			pstm.setString(1, order.getUserName());
			pstm.setString(2, order.getShopName());
			pstm.setString(3, order.getOrderMenu());
			pstm.setString(4, order.getTelphone());
			pstm.setString(5, order.getOrderTime());
			pstm.executeUpdate();
			rs = pstm.getGeneratedKeys();

			if (rs.next()) {
				return rs.getInt(1);
			}

			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			ConnectionFactory.close(conn, pstm, rs);
		}
	}

	// 根据用户名获取订单
	@Override
	public ArrayList<OrderModel> getOrderList(String name) {
		try {
			OrderModel frag = null;
			conn = ConnectionFactory.getConnection();
			ArrayList<OrderModel> list = new ArrayList<OrderModel>();
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT username,shopname,ordermenu,telphone,ordertime ");
			sql.append(" FROM dingdan ");
			sql.append(" WHERE username=? ");
			sql.append(" ORDER BY ordertime DESC");

			pstm = conn.prepareStatement(sql.toString());
			pstm.setString(1, name);
			rs = pstm.executeQuery();
			while (rs.next()) {
				frag = new OrderModel();
				frag.setUserName(rs.getString("username"));
				frag.setShopName(rs.getString("shopname"));
				frag.setOrderMenu(rs.getString("ordermenu"));
				frag.setTelphone(rs.getString("telphone"));
				frag.setOrderTime(rs.getString("ordertime"));
				list.add(frag);
			}
			return list;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		} finally {
			ConnectionFactory.close(conn, pstm, rs);
		}
	}

	// 精确查找
	@Override
	public String order_deal(String username, String shopname,
			String ordermenu, String telphone, String ordertime) {
		try {
			String flag = "no";
			conn = ConnectionFactory.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT username,shopname,ordermenu,telphone,ordertime");
			sql.append(" FROM dingdan ");
			sql.append(" WHERE username = ? ");
			sql.append(" AND shopname= ?");
			sql.append(" AND ordermenu= ?");
			sql.append(" AND telphone = ?");
			sql.append(" AND ordertime= ?");

			pstm = conn.prepareStatement(sql.toString());
			pstm.setString(1, username);
			pstm.setString(2, shopname);
			pstm.setString(3, ordermenu);
			pstm.setString(4, telphone);
			pstm.setString(5, ordertime);
			rs = pstm.executeQuery();
			if (rs.next()) {
				flag = "yes";
				return flag;
			} else {
				return flag;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			ConnectionFactory.close(conn, pstm, rs);
		}
	}

	public static void main(String[] args) {
		OrderDaogo dao = new OrderDaogo();
		ArrayList<OrderModel> list = new ArrayList<OrderModel>();
		list = dao.getOrderList("彭雄辉");
		System.out.println(list.get(0).getUserName()
				+ list.get(0).getShopName() + list.get(0).getOrderMenu()
				+ list.get(0).getTelphone() + list.get(0).getOrderTime());
	}
}
