<%@ page language="java" contentType="text/html; charset=utf-8"
	import="com.conn.*,com.dao.*,com.daogo.*,com.model.*,java.util.*,com.alibaba.fastjson.*"
	pageEncoding="utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
	String name = request.getParameter("name");

	ArrayList<OrderModel> list = new ArrayList<OrderModel>();

	OrderDao dao = new OrderDaogo();
	list = dao.getOrderList(name);//根据用户名查找
	String s = JSON.toJSONString(list);
	out.print(s);
%>
