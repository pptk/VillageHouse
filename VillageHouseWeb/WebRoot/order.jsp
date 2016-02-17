<%@ page language="java" contentType="text/html; charset=utf-8"
	import="com.conn.*,com.dao.*,com.daogo.*,com.model.*,java.util.*,com.alibaba.fastjson.*,java.text.*,java.io.*"
	pageEncoding="utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");

	String username = request.getParameter("username");//人名
	String shopname = request.getParameter("shopName");//店名
	String ordermenu = request.getParameter("ordermenu");//菜单
	String telphone = request.getParameter("telphone");
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	String time = df.format(new Date());
	//插入
	OrderDao dao = new OrderDaogo();
	OrderModel order = new OrderModel(username, shopname, ordermenu,
			telphone, time);
	dao.add_order(order);
	//验证是否插入成功
	String tishi = dao.order_deal(username, shopname, ordermenu,
			telphone, time);
	if ("yes".equals(tishi)) {
		out.print("yes");
	} else {
		out.print("no");
	}
%>
