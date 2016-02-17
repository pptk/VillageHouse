<%@ page language="java" contentType="text/html; charset=utf-8"
    import="java.util.*,com.conn.*,com.dao.*,com.daogo.*,com.model.*,java.text.*,java.io.*"
    pageEncoding="utf-8"%>
<%
     request.setCharacterEncoding("utf-8");
     response.setCharacterEncoding("utf-8");
     String param=request.getParameter("httpclient");
     if("send".equals(param))
     {
     String cid=request.getParameter("s_id");
     String name=request.getParameter("username");
     String content=request.getParameter("miaosu");
     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
	 String time=df.format(new Date());
	 
     CommentDao dao=new CommentDaogo();
     CommentModel cm=new CommentModel();

     //获取专家的姓名
     cm.setCid(cid);
     cm.setCname(name);
     cm.setCcontent(content);
     cm.setCtime(time);
     dao.add_Comment(cm);

     String flag=dao.comment_deal(name, content, time);
     if(flag.equals("yes"))
     {
      out.print("发表成功");
     }else
     {
      out.print("发表失败");
     }
       
   }


%>
