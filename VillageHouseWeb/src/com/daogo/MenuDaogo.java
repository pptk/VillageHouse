package com.daogo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conn.ConnectionFactory;
import com.dao.MenuDao;
import com.model.MenuModel;


public class MenuDaogo implements MenuDao{
    
	// 建立连接数据库
		private Connection conn = null;
	    private PreparedStatement pstm = null;
	    private ResultSet rs = null;
	@Override
	public List<MenuModel> getMenuList(String mid) {
		try {
			MenuModel frag = null;
			conn = ConnectionFactory.getConnection();
			List<MenuModel> list = new ArrayList<MenuModel>();
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT m_name,m_price ");
			sql.append(" FROM menu ");
			sql.append(" WHERE m_id=? ");
			pstm = conn.prepareStatement(sql.toString());
			pstm.setString(1,mid);
			rs = pstm.executeQuery();
			while (rs.next()) {
				frag = new MenuModel();
				frag.setMname(rs.getString("m_name"));
				frag.setMprice(rs.getString("m_price"));
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

}
