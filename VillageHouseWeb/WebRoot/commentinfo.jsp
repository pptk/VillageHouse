<%@ page language="java" contentType="text/html; charset=utf-8"
    import="com.conn.*,com.dao.*,com.daogo.*,
      com.model.*,java.util.*,com.alibaba.fastjson.*"
    pageEncoding="utf-8"%>
<%

    String param=request.getParameter("httpclient");
     if("get".equals(param))
     { 
      String cid=request.getParameter("s_id");
      
      List<CommentModel> list = new ArrayList<CommentModel>();
  
	  CommentDao dao = new CommentDaogo();
	  CommentModel cm=new CommentModel();
	  cm.setCid(cid);
	  list = dao.getComment(cid);
 	  String s = JSON.toJSONString(list);
	  out.print(s);  
     }else
     {
        out.print("请求不成功");
     }



 %>
