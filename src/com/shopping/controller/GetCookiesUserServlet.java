package com.shopping.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.shopping.dao.UserDao;
import com.shopping.daoimpl.UserDaoImpl;
import com.shopping.entity.User;

/**
 * 这是登录完成以后，前台为了拿到Cookies里面存的user_name值
 * 并返回一个user对象json的servlet
 * 拿到以后以json格式传到前台，前台ajax取值并渲染
 */
@WebServlet("/user/getCookiesUser.do")
public class GetCookiesUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_name = request.getParameter("user_name");
		User user = new User();
		UserDao ud = new UserDaoImpl();
		user = ud.loadByName(user_name);
		String jsonString = JSON.toJSONString(user);
		/*if(jsonString.equals("null")) {
			response.getWriter().write("not login yet");
		} else {
			response.getWriter().write(jsonString);
		}*/
		response.getWriter().write(jsonString);
	}

}
