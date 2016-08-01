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

@WebFilter("/user/changeTelCheckByAjax.do")
public class ChangeTelCheckByAjaxFilter implements Filter {
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
		String new_user_telnum = request.getParameter("new_user_telnum");
		String telnum_test = "(^((13[0-9])|(15[^4,\\d])|(18[0,5-9]))\\d{8}$)";
		UserDao ud = new UserDaoImpl();
		User user = new User();
		user = ud.loadByName(user_name);
		if(new_user_telnum == null || new_user_telnum =="") {
			response.getWriter().write("no_new_user_telnum");
			return;
		} else if(user.getUser_name().equals(new_user_telnum) && !new_user_telnum.equals(user.getUser_telnum())) {
			response.getWriter().write("success");
			user.setUser_telnum(new_user_telnum);
			request.setAttribute("user", user);
			chain.doFilter(request, response);
			return;
		} else if(!Pattern.matches(telnum_test, new_user_telnum)){
			response.getWriter().write("invalid_new_user_telnum");
			return;
		} else if(new_user_telnum.equals(user.getUser_telnum())) {
			response.getWriter().write("same_user_telnum");
			return;
		} else if(ud.loadByName(new_user_telnum) != null || ud.loadByTelnum(new_user_telnum) != null) {
			response.getWriter().write("new_user_telnum_exists");
			return;
		} else {
			response.getWriter().write("success");
			user.setUser_telnum(new_user_telnum);
			request.setAttribute("user", user);
			chain.doFilter(request, response);
			return;
		}
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}	
}


