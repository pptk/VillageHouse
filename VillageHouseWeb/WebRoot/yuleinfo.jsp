<%@ page language="java" contentType="text/html; charset=utf-8"
	import="com.conn.*,com.dao.*,com.daogo.*,com.model.*,java.util.*,com.alibaba.fastjson.*"
	pageEncoding="utf-8"%>
<%
    String param=request.getParameter("httpclient");
    if("get".equals(param))
    {
      String yid=request.getParameter("y_id");
      List<YuleModel> list=new ArrayList<YuleModel>();
      
      YuleModel ym=new YuleModel();
      YuleDao dao=new YuleDaogo();
      
      ym.setYid(yid);
      list=dao.getYuleListByid(yid);
      String s=JSON.toJSONString(list);
      out.print(s);
    }else{
       out.print("请求不成功");
    }



 %>
