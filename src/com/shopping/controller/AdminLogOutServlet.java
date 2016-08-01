package com.shopping.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * ���ǹ���Ա�ǳ���servlet��������ǰ�cookies�����Ӧ�Ķ���MaxAge����Ϊ0��
 * ������������������������ض��򵽹���Ա��¼����
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
