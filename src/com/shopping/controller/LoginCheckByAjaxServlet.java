package com.shopping.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.UserDao;
import com.shopping.daoimpl.UserDaoImpl;
import com.shopping.entity.User;
import com.shopping.utils.MD5Util;

/**
 * 这是与登录页面ajax验证的过滤器相对应的servlet，只实现了与数据库交互，
 * 将数据库中的该用户的当前登录时间改为当前时间，并将该用户存入session里面
 * 以便下一个网页能拿到并渲染的功能，
 * 从过滤器传过来的存在request里面的user对象应该在此拿到以后应该清除，否则后面冲突会出问题
 */
@WebServlet("/user/loginCheckByAjax.do")
public class LoginCheckByAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getAttribute("user");	
		request.removeAttribute("user");
		UserDao ud = new UserDaoImpl();
		ud.update(user);
	}
}
