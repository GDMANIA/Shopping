package com.shopping.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 这是管理员登出的servlet，这里就是把cookies里面对应的对象MaxAge设置为0，
 * 即把这个对象清除掉，最后再重定向到管理员登录界面
 */
@WebServlet("/admin/adminLogOut.do")
public class AdminLogOutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		for(int i = 0; i < cookies.length; i++) {
			if("admin".equals(cookies[i].getName())) {
				cookies[i].setMaxAge(0);
				response.addCookie(cookies[i]);
			}
		}
		response.sendRedirect("/shopping/admin/admin_login.jsp");
	}

}
