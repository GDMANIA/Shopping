package com.shopping.rubbish;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.UserDao;
import com.shopping.daoimpl.UserDaoImpl;
import com.shopping.entity.User;

/**
 * 这是处理用户注销的过滤器，先拿到cookies里面存的user_name，然后取到相应的user对象
 * 另外需要处理登录时间的问题，点注销时应该把这次登录时间赋值给上一次登录的时间
 * 
 * 本来想的是把这次登录的上一次登录时间也拿到放在center页面里面，现在直接拿到这次登录时间，
 * 那么这个过滤器和SERVLET作废
 */
//@WebFilter("/user/logout.do")
public class LogOutFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		Cookie[] cookies = request.getCookies();
		String user_name = null;
		for (int i = 0; i < cookies.length; i++) {
			if(cookies[i].getName().equals("user_name_cookie")) {
				user_name = cookies[i].getValue();
				return;
			}
		}
		/*if(user_name.isEmpty()) {
			response.sendRedirect("/shopping/user/page_goods/index.html");
			return;
		} else {   这里要不要判断user_name是不是为空？如果别人在地址栏直接输入servlet的url进来是不是就崩了？*/
		User user = new User();
		UserDao ud = new UserDaoImpl();
		user = ud.loadByName(user_name);
		//user.setUser_lastlogintime(user.getUser_user_currentlogintime());
		request.setAttribute("user", user);
		chain.doFilter(request, response);

	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
