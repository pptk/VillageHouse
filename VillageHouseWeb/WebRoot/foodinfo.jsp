<%@ page language="java" contentType="text/html; charset=utf-8"
import="com.conn.*,com.dao.*,com.daogo.*,com.model.*,java.util.*,com.alibaba.fastjson.*"
    pageEncoding="utf-8"%>
<%
   String param=request.getParameter("httpclient");
      if("get".equals(param))
      { 
      String mid=request.getParameter("idString");
      List<MenuModel> list = new ArrayList<MenuModel>();
 
	  MenuDao dao = new MenuDaogo();
	  MenuModel mm=new MenuModel();
	  mm.setMid(mid);
	  list = dao.getMenuList(mid);
 	  String s = JSON.toJSONString(list);
	  out.print(s);  
      }else
      {
        out.print("请求不成功");
      }


%>