package com.daogo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conn.ConnectionFactory;
import com.dao.ShoppingDao;
import com.model.ShoppingModel;

public class ShoppingDaogo implements ShoppingDao{
    
	
	// 建立连接数据库
		private Connection conn = null;
	    private PreparedStatement pstm = null;
	    private ResultSet rs = null;
	@Override
	public List<ShoppingModel> getShoppingList() {
		try {
			ShoppingModel frag = null;
			conn = ConnectionFactory.getConnection();
			List<ShoppingModel> list = new ArrayList<ShoppingModel>();
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT s_id,s_image,s_name,s_phone,s_price ");
			sql.append(" FROM shopping ");
			sql.append(" ORDER BY s_id DESC");
			
			pstm = conn.prepareStatement(sql.toString());
			rs = pstm.executeQuery();
			while (rs.next()) {
				frag = new ShoppingModel();
				frag.setSid(rs.getString("s_id"));
				frag.setSimage(rs.getString("s_image"));
				frag.setSname(rs.getString("s_name"));
				frag.setSphone(rs.getString("s_phone"));
				frag.setSprice(rs.getString("s_price"));
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
