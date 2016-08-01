package com.shopping.filter;

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
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.UserDao;
import com.shopping.daoimpl.UserDaoImpl;
import com.shopping.entity.User;
import com.shopping.utils.MD5Util;
import com.shopping.utils.UUIDUtil;

@WebFilter("/user/registerCheckByAjax.do")
public class RegisterCheckByAjaxFilter implements Filter {
	private static final long serialVersionUID = 1L;

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		String user_name = request.getParameter("user_name");
		String user_password = request.getParameter("user_password");
		String user_repassword = request.getParameter("user_repassword");
		String verifyCode = request.getParameter("verifyCode_val");
		String from = request.getParameter("from");
		String email_test = "(^([a-z0-9A-Z]+[-|\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$)";
		String telnum_test = "(^((13[0-9])|(15[^4,\\d])|(18[0,5-9]))\\d{8}$)";
		String password_test = "(^[a-zA-Z0-9_]{6,16}$)";
		String verifyCode_session = (String) request.getSession().getAttribute("verifyCode_session");
		
		//下面是单独判断点button按钮过来的验证模块，所有信息从头到尾走一遍，没问题再用servlet添加
		if("onsubmit".equals(from)) {
			if(user_name == null || user_name == "") {
				response.getWriter().write("noUserName");
				return;
			} else if(!Pattern.matches(email_test, user_name) && !Pattern.matches(telnum_test, user_name)) { 
				response.getWriter().write("invalid username");
				return;
			} else {
				UserDao ud = new UserDaoImpl();
				User user = null;
				user = ud.loadByName(user_name);
				if(ud.loadByName(user_name) != null || ud.loadByEmail(user_name) != null || ud.loadByTelnum(user_name) != null) {
					response.getWriter().write("username exists");
					return;
				} else if(user_password == null || user_password == "") {
					response.getWriter().write("noPassword");
					return;
				} else if(!Pattern.matches(password_test, user_password)){
					response.getWriter().write("invalid password");
					return;
				} else if(user_repassword == null || user_repassword == ""){
					response.getWriter().write("noRePassword");
					return;
				} else if(!user_password.equals(user_repassword)) {
					response.getWriter().write("wrong repassword");
					return;
				} else if(verifyCode == null || verifyCode == "") {
					response.getWriter().write("noVerifyCode");
					return;
				} else if(!verifyCode.equalsIgnoreCase(verifyCode_session)){
					response.getWriter().write("wrong verifyCode");
					return;
				} else {
					response.getWriter().write("success");
					user = new User();
					String user_id = UUIDUtil.getUUID();
					user_password = MD5Util.getMD5(user_password);
					String user_regtime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
					user.setUser_id(user_id);
					user.setUser_name(user_name);
					user.setUser_password(user_password);
					user.setUser_regtime(user_regtime);
					request.setAttribute("user", user);
					chain.doFilter(request, response);
					return;
				} 
			}
			//下面是验证码的单独onblur验证模块
		} else if("verifyCode_val".equals(from)) {
			if(verifyCode == null || verifyCode == "") {
				response.getWriter().write("noVerifyCode");
				return;
			} else if(!verifyCode.equalsIgnoreCase(verifyCode_session)){
				response.getWriter().write("wrong verifyCode");
				return;
			} else {
				response.getWriter().write("right verifyCode");
				return;
			}
			//下面是其他字段的onblur验证模块
		} else if(user_name == null || user_name == "") {
			response.getWriter().write("noUserName");
			return;
		} else if(!Pattern.matches(email_test, user_name) && !Pattern.matches(telnum_test, user_name)) { 
			response.getWriter().write("invalid username");
			return;
		} else {
			UserDao ud = new UserDaoImpl();
			User user = null;
			user = ud.loadByName(user_name);
			if(ud.loadByName(user_name) != null || ud.loadByEmail(user_name) != null || ud.loadByTelnum(user_name) != null) {
				response.getWriter().write("username exists");
				return;
			} else if(user_password == null || user_password == "") {
				response.getWriter().write("noPassword");
				return;
			} else if(!Pattern.matches(password_test, user_password)){
				response.getWriter().write("invalid password");
				return;
			} else if(user_repassword == null || user_repassword == ""){
				response.getWriter().write("noRePassword");
				return;
			} else if(!user_password.equals(user_repassword)) {
				response.getWriter().write("wrong repassword");
				return;
			} else {
				response.getWriter().write("success");
				return;
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}	
}


