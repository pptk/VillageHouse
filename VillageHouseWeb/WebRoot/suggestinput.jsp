<%@ page language="java" contentType="text/html; charset=utf-8"
import="com.conn.*,com.dao.*,com.daogo.*,com.model.*,java.util.*,com.alibaba.fastjson.*,java.text.*,java.io.*"
    pageEncoding="utf-8"%>
<%
    request.setCharacterEncoding("utf-8");
    response.setCharacterEncoding("utf-8");
    String param=request.getParameter("httpclient");
    if("send".equals(param))
    {
      String name=request.getParameter("username");
      String content=request.getParameter("miaosu");
      SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	  String time=df.format(new Date()); 
	   
	  SuggestDao dao=new SuggestDaogo();
	  SuggestModel sm=new SuggestModel();
	  sm.setSu_name(name);
	  sm.setSu_content(content);
	  sm.setSu_time(time);
	  
	  dao.add_Suggest(sm);
	  String tishi=dao.suggest_deal(name, content, time);
	  if("yes".equals(tishi))
	  {
	    out.print("发布成功！");
	  }else{
	    out.print("发布失败！");
	  }
    
    
    }


 %>
