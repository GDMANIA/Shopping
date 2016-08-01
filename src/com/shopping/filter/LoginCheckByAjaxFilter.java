package com.shopping.filter;

import java.io.IOException;
import java.io.PrintWriter;
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
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.UserDao;
import com.shopping.daoimpl.UserDaoImpl;
import com.shopping.entity.User;
import com.shopping.utils.MD5Util;

/**
 * 登录页面login.html用ajax来检查各个字段的统一的过滤器，
 * 先判断是否是点击button过来的，或者是触发了验证码的onblur事件过来的，
 * 如果都不是，那么从用户名开始判断到密码，然后判断验证码，包括几个字段是否为空，
 * 用户名是否不存在，密码是否填错，验证码是否填错等
 * 如果有问题则分别返回问题字段，让前台ajax拿到以后再在前台输出错误信息，
 * 没问题则输出正确信息。
 * 如果是点击button过来的则从头到尾全部判断所有信息，如果都没问题，
 * 将user对象的当前登录时间改为当前时间，并把user对象存到request里面，
 * 再传到对应的servlet调用dao层更改用户信息，
 * 并在前台跳转到主页
 */
@WebFilter("/user/loginCheckByAjax.do")
public class LoginCheckByAjaxFilter implements Filter{
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
		String verifyCode = request.getParameter("verifyCode_val");
		String from = request.getParameter("from");
		String verifyCode_session = (String) request.getSession().getAttribute("verifyCode_session");
		
		//下面是单独判断点button按钮过来的验证模块，所有信息从头到尾走一遍，没问题再调用servlet更改字段
		if("onsubmit".equals(from)) {
			if(user_name == null || user_name == "") {
				response.getWriter().write("noUserName");
				return;
			} else {
				UserDao ud = new UserDaoImpl();
				User user = null;
				user = ud.loadByName(user_name);
				if(user == null) {
					response.getWriter().write("username not exist");
					return;
				} else if(user_password == null || user_password == "") {
					System.out.println("noPassword");
					response.getWriter().write("noPassword");
					return;
				} else {
					user_password = MD5Util.getMD5(user_password);
					if(!user_password.equals(user.getUser_password())){
						System.out.println("wrong password");
						response.getWriter().write("wrong password");
						return;
					} else if(verifyCode == null || verifyCode == "") {
						System.out.println("noVerifyCode");
						response.getWriter().write("noVerifyCode");
						return;
					} else if(!verifyCode.equalsIgnoreCase(verifyCode_session)){
						System.out.println("wrong verifyCode");
						response.getWriter().write("wrong verifyCode");
						return;
					} else {
						response.getWriter().write("success");
						String user_lastlogintime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
						user.setUser_lastlogintime(user_lastlogintime);
						request.setAttribute("user", user);
						chain.doFilter(request, response);
						return;
					}
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
		} else {
			UserDao ud = new UserDaoImpl();
			User user = null;
			user = ud.loadByName(user_name);
			if(user == null) {
				response.getWriter().write("username not exist");
				return;
			} else if(user_password == null || user_password == "") {
				response.getWriter().write("noPassword");
				return;
			} else {
				user_password = MD5Util.getMD5(user_password);
				if(!user_password.equals(user.getUser_password())){
					response.getWriter().write("wrong password");
					return;
				} else {
					response.getWriter().write("success");
					return;
				}
			}
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
