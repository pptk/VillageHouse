<%@ page language="java" contentType="text/html; charset=utf-8"
import="com.conn.*,com.dao.*,com.daogo.*,com.model.*,java.util.*,com.alibaba.fastjson.*"
    pageEncoding="utf-8"%>
<%
      List<FoodModel> list = new ArrayList<FoodModel>();
 
	  FoodDao dao = new FoodDaogo();
	  list = dao.getFoodList();
 	  String s = JSON.toJSONString(list);
	  out.print(s);  

%>