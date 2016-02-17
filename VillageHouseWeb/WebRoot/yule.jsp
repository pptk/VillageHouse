<%@ page language="java" contentType="text/html; charset=utf-8"
	import="com.conn.*,com.dao.*,com.daogo.*,com.model.*,java.util.*,com.alibaba.fastjson.*"
	pageEncoding="utf-8"%>
<%
	request.setCharacterEncoding("utf-8");
	response.setCharacterEncoding("utf-8");
	String ytype = request.getParameter("typea");
	// String ytype=request.getQueryString("typea");

	List<YuleModel> list = new ArrayList<YuleModel>();
	String x = null;
	YuleDao dao = new YuleDaogo();


	if (ytype == null) {

		// 	out.print("000kkkkk");

		list = dao.getAllList();
		x = JSON.toJSONString(list);
		out.print(x);
	} else {
		list = dao.getYuleList(ytype);
		String y = JSON.toJSONString(list);
		out.print(y);
	}
%>