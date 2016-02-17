<%@ page language="java" contentType="text/html; charset=utf-8" import="com.conn.*,com.dao.*,com.daogo.*,
com.model.*,java.util.*,com.alibaba.fastjson.*"%>
<%

      List<SecondHandModel> list = new ArrayList<SecondHandModel>(); 
	  SecondHandDao dao = new SecondHandDaogo(); 
	  list = dao.getSecondHandList();
 	  String s = JSON.toJSONString(list); 
	  out.print(s); 

%>