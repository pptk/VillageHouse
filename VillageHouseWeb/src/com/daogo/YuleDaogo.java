package com.daogo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.conn.ConnectionFactory;
import com.dao.YuleDao;
import com.model.FoodModel;
import com.model.YuleModel;

import com.alibaba.fastjson.*;

public class YuleDaogo implements YuleDao {

	// 建立连接数据库
	private Connection conn = null;
	private PreparedStatement pstm = null;
	private ResultSet rs = null;

	@Override
	public List<YuleModel> getYuleList(String ytype) {
		try {
			YuleModel frag = null;
			conn = ConnectionFactory.getConnection();
			List<YuleModel> list = new ArrayList<YuleModel>();
			StringBuffer sql = new StringBuffer();
			sql.append("select y_id,y_title,y_image,y_profile from yule where y_type like '");
			sql.append(ytype + "%'");
			// sql.append("%'");

			pstm = conn.prepareStatement(sql.toString());
			rs = pstm.executeQuery();
			while (rs.next()) {
				frag = new YuleModel();
				frag.setYid(rs.getString("y_id"));
				frag.setYtitle(rs.getString("y_title"));
				frag.setYimage(rs.getString("y_image"));
				frag.setYprofile(rs.getString("y_profile"));
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
	public List<YuleModel> getYuleListByid(String yid) {
		try {
			YuleModel frag = null;
			conn = ConnectionFactory.getConnection();
			List<YuleModel> list = new ArrayList<YuleModel>();
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT y_type,y_title,y_bimage,y_content,y_phone ");
			sql.append(" FROM yule ");
			sql.append(" WHERE y_id=? ");

			pstm = conn.prepareStatement(sql.toString());
			pstm.setString(1, yid);
			rs = pstm.executeQuery();
			while (rs.next()) {
				frag = new YuleModel();
				frag.setYtype(rs.getString("y_type"));
				frag.setYtitle(rs.getString("y_title"));
				frag.setYbimage(rs.getString("y_bimage"));
				frag.setYcontent(rs.getString("y_content"));
				frag.setYphone(rs.getString("y_phone"));
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
	public List<YuleModel> getAllList() {
		try {
			YuleModel frag = null;
			conn = ConnectionFactory.getConnection();
			List<YuleModel> list = new ArrayList<YuleModel>();
			StringBuffer sql = new StringBuffer();
			sql.append(" SELECT y_id,y_image,y_title,y_profile ");
			sql.append(" FROM yule ");

			pstm = conn.prepareStatement(sql.toString());
			rs = pstm.executeQuery();
			while (rs.next()) {
				frag = new YuleModel();
				frag.setYid(rs.getString("y_id"));
				frag.setYtitle(rs.getString("y_title"));
				frag.setYimage(rs.getString("y_image"));
				frag.setYprofile(rs.getString("y_profile"));
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

	public static void main(String args[]) {

		List<YuleModel> list = new ArrayList<YuleModel>();
		YuleDaogo yd = new YuleDaogo();
		list = yd.getYuleListByid("1");
		// System.out.println(yd.getAllList());
		System.out.println(JSON.toJSONString(list));

	}

}
