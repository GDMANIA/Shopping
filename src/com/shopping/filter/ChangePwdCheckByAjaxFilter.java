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

@WebFilter("/user/changePwdCheckByAjax.do")
public class ChangePwdCheckByAjaxFilter implements Filter {
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
		String origin_user_password = request.getParameter("origin_user_password");
		String new_user_password = request.getParameter("new_user_password");
		String new_user_repassword = request.getParameter("new_user_repassword");
		String password_test = "(^[a-zA-Z0-9_]{6,16}$)";
		String from = request.getParameter("from");
		//System.out.println(origin_user_password);
		//下面是单独判断点button按钮过来的验证模块，所有信息从头到尾走一遍，没问题再用servlet添加
		if("onsubmit".equals(from)) {
			System.out.println(origin_user_password);
			System.out.println(new_user_password);
			System.out.println(new_user_repassword);
			System.out.println(from);
			if(origin_user_password == null || origin_user_password == "") {
				response.getWriter().write("no_origin_user_password");
				return;
			} else {
				origin_user_password = MD5Util.getMD5(origin_user_password);
				UserDao ud = new UserDaoImpl();
				User user = null;
				user = ud.loadByName(user_name);
				if(!user.getUser_password().equals(origin_user_password)) {
					response.getWriter().write("wrong_origin_user_password");
					return;
				} else if(new_user_password == null || new_user_password == "") {
					response.getWriter().write("no_new_user_password");
					return;
				} else if(!Pattern.matches(password_test, new_user_password)){
					response.getWriter().write("invalid_new_user_password");
					return;
				} else {
					new_user_password = MD5Util.getMD5(new_user_password);
					if(new_user_password.equals(origin_user_password)) {
						response.getWriter().write("same_user_password");
						return;
					} else if(new_user_repassword == null || new_user_repassword == ""){
						response.getWriter().write("no_new_user_repassword");
						return;
					} else { 
						new_user_repassword = MD5Util.getMD5(new_user_repassword);
						if(!new_user_password.equals(new_user_repassword)) {
							response.getWriter().write("wrong_new_user_repassword");
							return;
						} else {
							response.getWriter().write("success");
							user.setUser_password(new_user_password);
							request.setAttribute("user", user);
							chain.doFilter(request, response);
							return;
						}
					}
				
				}
			}
		} else if("origin_user_password".equals(from)) {
			//System.out.println(origin_user_password);
			if(origin_user_password == null || origin_user_password == "") {
				response.getWriter().write("no_origin_user_password");
				//System.out.println("no_origin_user_password");
				return;
			} else {
				origin_user_password = MD5Util.getMD5(origin_user_password);
				//System.out.println(origin_user_password);
				UserDao ud = new UserDaoImpl();
				User user = null;
				user = ud.loadByName(user_name);
				//System.out.println(user.getUser_password());
				//System.out.println(user.getUser_password().equals(origin_user_password));
				if(!user.getUser_password().equals(origin_user_password)) {
					response.getWriter().write("wrong_origin_user_password");
					//System.out.println("wrong_origin_user_password");
					return;
				} else {
					response.getWriter().write("success");
					//System.out.println("success");
					return;
				}
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}	
}


