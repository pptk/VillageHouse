package com.daogo;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.conn.ConnectionFactory;
import com.dao.SuggestDao;
import com.model.SuggestModel;

public class SuggestDaogo implements SuggestDao{
    
	
	
	 //建立连接对象 
     private Connection conn=null;
	 private PreparedStatement pstm = null;
	 private ResultSet rs = null;
	@Override
	public Serializable add_Suggest(SuggestModel sm) {
		try {
			conn = ConnectionFactory.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append(" INSERT INTO suggest(su_name,su_content,su_time) ");
			sql.append(" VALUES(?,?,?) ");

			pstm = conn.prepareStatement(sql.toString());
			pstm.setString(1, sm.getSu_name());
			pstm.setString(2, sm.getSu_content());
			pstm.setString(3,sm.getSu_time());
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

	@Override
	public String suggest_deal(String name, String content, String time) {
		try {
			String flag = "no";
			conn = ConnectionFactory.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT su_name,su_content,su_time ");
			sql.append(" FROM suggest ");
			sql.append(" WHERE su_name =? ");
			sql.append(" AND su_content= ?");
			sql.append(" AND su_time= ?");
			
			pstm = conn.prepareStatement(sql.toString());
			pstm.setString(1, name);
			pstm.setString(2, content);
			pstm.setString(3, time);
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
   
}
