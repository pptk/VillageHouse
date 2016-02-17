package com.dao;

import java.io.Serializable;

import com.model.SuggestModel;


public interface SuggestDao {
	
    Serializable add_Suggest(SuggestModel sm);
	
	String suggest_deal(String name,String content,String time); //—È÷§
}
