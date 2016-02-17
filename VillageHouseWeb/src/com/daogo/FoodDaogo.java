package com.daogo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conn.ConnectionFactory;
import com.dao.FoodDao;
import com.model.FoodModel;

public class FoodDaogo implements FoodDao{
    
	// 建立连接数据库
	private Connection conn = null;
    private PreparedStatement pstm = null;
    private ResultSet rs = null;
	@Override
	public List<FoodModel> getFoodList() {
		try {
			FoodModel frag = null;
			conn = ConnectionFactory.getConnection();
			List<FoodModel> list = new ArrayList<FoodModel>();
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT f_id,f_image,f_name,f_profile,f_send,f_address,f_time ");
			sql.append(" FROM food ");
			sql.append(" ORDER BY f_id DESC");
			
			pstm = conn.prepareStatement(sql.toString());
			rs = pstm.executeQuery();
			while (rs.next()) {
				frag = new FoodModel();
				frag.setFid(rs.getString("f_id"));
				frag.setFimage(rs.getString("f_image"));
				frag.setFname(rs.getString("f_name"));
				frag.setFprofile(rs.getString("f_profile"));
				frag.setFsend(rs.getString("f_send"));
				frag.setFaddress(rs.getString("f_address"));
				frag.setFtime(rs.getString("f_time"));
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
