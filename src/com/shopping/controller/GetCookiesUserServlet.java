package com.shopping.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.shopping.dao.UserDao;
import com.shopping.daoimpl.UserDaoImpl;
import com.shopping.entity.User;

/**
 * ���ǵ�¼����Ժ�ǰ̨Ϊ���õ�Cookies������user_nameֵ
 * ������һ��user����json��servlet
 * �õ��Ժ���json��ʽ����ǰ̨��ǰ̨ajaxȡֵ����Ⱦ
 */
@WebServlet("/user/getCookiesUser.do")
public class GetCookiesUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_name = request.getParameter("user_name");
		User user = new User();
		UserDao ud = new UserDaoImpl();
		user = ud.loadByName(user_name);
		String jsonString = JSON.toJSONString(user);
		/*if(jsonString.equals("null")) {
			response.getWriter().write("not login yet");
		} else {
			response.getWriter().write(jsonString);
		}*/
		response.getWriter().write(jsonString);
	}

}
