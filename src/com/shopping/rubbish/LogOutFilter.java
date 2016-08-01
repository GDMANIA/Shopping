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
 * ���Ǵ����û�ע���Ĺ����������õ�cookies������user_name��Ȼ��ȡ����Ӧ��user����
 * ������Ҫ�����¼ʱ������⣬��ע��ʱӦ�ð���ε�¼ʱ�丳ֵ����һ�ε�¼��ʱ��
 * 
 * ��������ǰ���ε�¼����һ�ε�¼ʱ��Ҳ�õ�����centerҳ�����棬����ֱ���õ���ε�¼ʱ�䣬
 * ��ô�����������SERVLET����
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
		} else {   ����Ҫ��Ҫ�ж�user_name�ǲ���Ϊ�գ���������ڵ�ַ��ֱ������servlet��url�����ǲ��Ǿͱ��ˣ�*/
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
