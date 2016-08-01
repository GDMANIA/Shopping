package com.shopping.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.UserDao;
import com.shopping.dao.UserHistoryDao;
import com.shopping.daoimpl.UserDaoImpl;
import com.shopping.daoimpl.UserHistoryDaoImpl;
import com.shopping.entity.User;
import com.shopping.entity.UserHistory;
import com.shopping.utils.MD5Util;

/**
 * 与RegisterCheckByAjaxFilter即注册页面统一验证的过滤器相对应的servlet
 * 只实现了将过滤器传过来的user对象添加到数据库的功能
 * 从过滤器传过来的存在request里面的user对象应该在此拿到以后应该清除，否则后面冲突会出问题
 */
@WebServlet("/user/registerCheckByAjax.do")
public class RegisterCheckByAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException {
		User user = (User) request.getAttribute("user");
		request.removeAttribute("user");
		UserDao ud = new UserDaoImpl();
		ud.add(user);
		UserHistoryDao uhd = new UserHistoryDaoImpl();
		uhd.add(user);
	}
}


