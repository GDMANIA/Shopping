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
 * ��¼ҳ��login.html��ajax���������ֶε�ͳһ�Ĺ�������
 * ���ж��Ƿ��ǵ��button�����ģ������Ǵ�������֤���onblur�¼������ģ�
 * ��������ǣ���ô���û�����ʼ�жϵ����룬Ȼ���ж���֤�룬���������ֶ��Ƿ�Ϊ�գ�
 * �û����Ƿ񲻴��ڣ������Ƿ������֤���Ƿ�����
 * �����������ֱ𷵻������ֶΣ���ǰ̨ajax�õ��Ժ�����ǰ̨���������Ϣ��
 * û�����������ȷ��Ϣ��
 * ����ǵ��button���������ͷ��βȫ���ж�������Ϣ�������û���⣬
 * ��user����ĵ�ǰ��¼ʱ���Ϊ��ǰʱ�䣬����user����浽request���棬
 * �ٴ�����Ӧ��servlet����dao������û���Ϣ��
 * ����ǰ̨��ת����ҳ
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
		
		//�����ǵ����жϵ�button��ť��������֤ģ�飬������Ϣ��ͷ��β��һ�飬û�����ٵ���servlet�����ֶ�
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
			//��������֤��ĵ���onblur��֤ģ��
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
			//�����������ֶε�onblur��֤ģ��
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
