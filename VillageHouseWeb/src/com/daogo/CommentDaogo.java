package com.daogo;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conn.ConnectionFactory;
import com.dao.CommentDao;
import com.model.CommentModel;

public class CommentDaogo implements CommentDao{
    
	
	//建立连接对象 
	private Connection conn=null;
	private PreparedStatement pstm = null;
	private ResultSet rs = null;
	@Override
	public List<CommentModel> getComment(String srid) {
    try {
			
	        CommentModel frag = null;
			conn = ConnectionFactory.getConnection();
			List<CommentModel> list = new ArrayList<CommentModel>();
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT c_id,c_name,c_content,c_time ");
			sql.append(" FROM comment ");
			sql.append(" WHERE c_id=? ");
			
			pstm = conn.prepareStatement(sql.toString());
			pstm.setString(1,srid);
			rs = pstm.executeQuery();
			while (rs.next()) {
				frag = new CommentModel();
				frag.setCname(rs.getString("c_name"));
				frag.setCcontent(rs.getString("c_content"));
				frag.setCtime(rs.getString("c_time"));
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

	@Override
	public Serializable add_Comment(CommentModel cm) {
		try {
			conn = ConnectionFactory.getConnection();

			StringBuffer sql = new StringBuffer();
			sql.append(" INSERT INTO comment(c_id,c_name,c_content,c_time) ");
			sql.append(" VALUES(?,?,?,?) ");

			pstm = conn.prepareStatement(sql.toString());
			pstm.setString(1, cm.getCid());
			pstm.setString(2, cm.getCname());
			pstm.setString(3, cm.getCcontent());
			pstm.setString(4, cm.getCtime());

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
	public String comment_deal(String name, String content, String time) {
		try {

			String flag = "no";
			conn = ConnectionFactory.getConnection();
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT c_name,c_content,c_time ");
			sql.append(" FROM comment ");
			sql.append(" WHERE c_name =? ");
			sql.append(" AND c_content= ?");
			sql.append(" AND c_time= ?");

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


