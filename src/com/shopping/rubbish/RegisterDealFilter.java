package com.shopping.rubbish;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.UserDao;
import com.shopping.daoimpl.UserDaoImpl;
import com.shopping.entity.User;
import com.shopping.utils.MD5Util;
import com.shopping.utils.UUIDUtil;

public class RegisterDealFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		UserDao ud = new UserDaoImpl();
		User user = new User();
		String user_id = UUIDUtil.getUUID();
		String user_name = request.getParameter("user_name");
		String user_password = request.getParameter("user_password");
		user_password = MD5Util.getMD5(user_password);
		String user_regtime = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(new Date());
		user.setUser_id(user_id);
		user.setUser_name(user_name);
		user.setUser_password(user_password);
		user.setUser_regtime(user_regtime);
		ud.add(user);
		chain.doFilter(request, response);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
