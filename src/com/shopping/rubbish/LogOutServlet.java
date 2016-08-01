package com.shopping.rubbish;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.UserDao;
import com.shopping.daoimpl.UserDaoImpl;
import com.shopping.entity.User;

/**
 * ´Ëservlet×÷·Ï
 */
//@WebServlet("/user/logout.do")
public class LogOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//String logoutfrom = request.getParameter("logoutfrom");
		//response.sendRedirect("/shopping/user/"+logoutfrom);
		User user = (User) request.getAttribute("user");
		request.removeAttribute("user");
		UserDao ud = new UserDaoImpl();
		ud.update(user);
	}

}
