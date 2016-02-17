package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dao.UserDao;
import com.daogo.UserDaoImp;
import com.google.gson.Gson;
import com.model.User;

public class aLogin extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
		String pwd = request.getParameter("pwd");
		ArrayList<User> list = new ArrayList<User>();
		UserDao dao = new UserDaoImp();
		list = dao.login(username, pwd);
		if (list == null) {
			out.print("’À∫≈ªÚ’ﬂ√‹¬Î¥ÌŒÛ");
		} else {
			Gson gson = new Gson();
			out.print(gson.toJson(list));
		}
	}

}
