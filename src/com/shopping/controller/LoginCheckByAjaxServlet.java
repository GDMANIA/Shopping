package com.shopping.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.UserDao;
import com.shopping.daoimpl.UserDaoImpl;
import com.shopping.entity.User;
import com.shopping.utils.MD5Util;

/**
 * �������¼ҳ��ajax��֤�Ĺ��������Ӧ��servlet��ֻʵ���������ݿ⽻����
 * �����ݿ��еĸ��û��ĵ�ǰ��¼ʱ���Ϊ��ǰʱ�䣬�������û�����session����
 * �Ա���һ����ҳ���õ�����Ⱦ�Ĺ��ܣ�
 * �ӹ������������Ĵ���request�����user����Ӧ���ڴ��õ��Ժ�Ӧ���������������ͻ�������
 */
@WebServlet("/user/loginCheckByAjax.do")
public class LoginCheckByAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getAttribute("user");	
		request.removeAttribute("user");
		UserDao ud = new UserDaoImpl();
		ud.update(user);
	}
}
