package com.dao;

import java.util.List;

import com.model.MenuModel;



public interface MenuDao {
    
	List<MenuModel> getMenuList(String mid);
}
