<%@ page language="java" contentType="text/html; charset=utf-8" import="com.conn.*,com.dao.*,com.daogo.*,
com.model.*,java.util.*,com.alibaba.fastjson.*,java.io.*,java.text.*"%>
<%
    	   request.setCharacterEncoding("utf-8");
		   response.setCharacterEncoding("utf-8");
		   String param=request.getParameter("httpclient");
		   if("send".equals(param))
		    {
		     String count = request.getParameter("count");//先获取传输的图片张数。
		     String name=request.getParameter("username");
		     String content=request.getParameter("miaosu");
		     SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			 String time=df.format(new Date());
			
			  SecondHandDao dao=new SecondHandDaogo();
			  SecondHandModel sh=new SecondHandModel();
			  
			  sh.setName(name);
			  sh.setContent(content);
		      sh.setTime(time);
		 
		      
		      //判断用户选择的图片张数，选择对应的方法执行
             if(Integer.parseInt(count) == 1){//如果一张图。
		          String image1= request.getParameter("name0");
		          sh.setImage1(image1);
		      }
		     if(Integer.parseInt(count) == 2){//如果两张图。
		          String image1= request.getParameter("name0");
		          sh.setImage1(image1);
		          String image2= request.getParameter("name1");
		          sh.setImage2(image2);
		     }
		        dao.add_secondhand(sh);
		        String tishi=dao.secondhand_deal(name, content, time);
		   if(tishi.equals("yes")){
		   	    out.print("发表成功");
		    	}
			}else{
		        out.print("no");  
		  
		  }




 %>
