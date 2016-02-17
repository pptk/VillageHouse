package com.dao;

import java.util.List;

import com.model.YuleModel;

public interface YuleDao {
   
	List<YuleModel> getAllList();
	
	List<YuleModel> getYuleList(String ytype);
	
	List<YuleModel> getYuleListByid(String yid);
}
