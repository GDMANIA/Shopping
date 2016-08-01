package com.shopping.controller;

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
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.UserDao;
import com.shopping.dao.UserHistoryDao;
import com.shopping.daoimpl.UserDaoImpl;
import com.shopping.daoimpl.UserHistoryDaoImpl;
import com.shopping.entity.User;
import com.shopping.entity.UserHistory;
import com.shopping.utils.MD5Util;

/**
 * ��RegisterCheckByAjaxFilter��ע��ҳ��ͳһ��֤�Ĺ��������Ӧ��servlet
 * ֻʵ���˽���������������user������ӵ����ݿ�Ĺ���
 * �ӹ������������Ĵ���request�����user����Ӧ���ڴ��õ��Ժ�Ӧ���������������ͻ�������
 */
@WebServlet("/user/registerCheckByAjax.do")
public class RegisterCheckByAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException ,IOException {
		User user = (User) request.getAttribute("user");
		request.removeAttribute("user");
		UserDao ud = new UserDaoImpl();
		ud.add(user);
		UserHistoryDao uhd = new UserHistoryDaoImpl();
		uhd.add(user);
	}
}


