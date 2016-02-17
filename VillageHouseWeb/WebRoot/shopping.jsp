<%@ page language="java" contentType="text/html; charset=utf-8"
import="com.conn.*,com.dao.*,com.daogo.*,com.model.*,java.util.*,com.alibaba.fastjson.*"
    pageEncoding="utf-8"%>
<%

      List<ShoppingModel> list = new ArrayList<ShoppingModel>();
 
	  ShoppingDao dao = new ShoppingDaogo();
	  list = dao.getShoppingList();
 	  String s = JSON.toJSONString(list);
	  out.print(s);  
   


%>