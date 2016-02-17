package com.dao;

import java.io.Serializable;
import java.util.List;

import com.model.SecondHandModel;


public interface SecondHandDao {
   
	List<SecondHandModel> getSecondHandList();
	
	Serializable add_secondhand(SecondHandModel show);
	
	String secondhand_deal(String name,String content,String time);
}
