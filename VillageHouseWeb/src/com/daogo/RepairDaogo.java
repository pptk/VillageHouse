package com.daogo;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.conn.ConnectionFactory;
import com.dao.RepairDao;
import com.model.RepairModel;

public class RepairDaogo implements RepairDao{
    
	
	 //建立连接对象 
     private Connection conn=null;
	 private PreparedStatement pstm = null;
	 private ResultSet rs = null;
	 
	@Override
	public Serializable add_Repair(RepairModel repair) {
		
		try {
			conn = ConnectionFactory.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append(" INSERT INTO repair(r_username,r_name,r_content,r_address,r_time) ");
			sql.append(" VALUES(?,?,?,?,?) ");

			pstm = conn.prepareStatement(sql.toString());
			pstm.setString(1, repair.getRusername());
			pstm.setString(2, repair.getRname());
			pstm.setString(3, repair.getRcontent());
			pstm.setString(4, repair.getRaddress());
            pstm.setString(5, repair.getRtime());
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
	public String repair_deal(String rusername, String rname, String rcontent,
			String raddress, String rtime) {
		try {
			String flag = "no";
			conn = ConnectionFactory.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT r_username,r_name,r_content,r_address,r_time ");
			sql.append(" FROM repair ");
			sql.append(" WHERE r_username =? ");
			sql.append(" AND r_name= ?");
			sql.append(" AND r_content= ?");
			sql.append(" AND r_address= ?");
			sql.append(" AND r_time= ?");
			
			pstm = conn.prepareStatement(sql.toString());
			pstm.setString(1, rusername);
			pstm.setString(2, rname);
			pstm.setString(3, rcontent);
			pstm.setString(4, raddress);
			pstm.setString(5, rtime);
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
