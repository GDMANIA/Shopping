package com.shopping.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.CategoryDao;
import com.shopping.dao.LogoAndCopyrightDao;
import com.shopping.daoimpl.CategoryDaoImpl;
import com.shopping.daoimpl.LogoAndCopyrightDaoImpl;
import com.shopping.entity.Administrator;
import com.shopping.entity.Category;
import com.shopping.entity.LogoAndCopyright;
/**
 * ���ǹ���Աһ���������ҳ���������һ��������ύ�����servlet��
 * �����ǰ̨�ύ���������ݽ����жϣ�����д���ͷ���ǰ̨һ��������Ϣ��
 * ���û�����ֱ�������ݿ����һ�����ݲ�ת����ԭҳ��
 */
@WebServlet("/admin/adminAddCategory.do")
public class AdminAddCategoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String categrayfirst = request.getParameter("categrayfirst");
		System.out.println(categrayfirst);
		String catNameWrongMsg = "";
		CategoryDao cd = new CategoryDaoImpl();
		Category cat = new Category();
		String admin_name = "";
		/*
		 * �����cookie���ж�Ӧ�÷ŵ�����������
		 * */
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(int i = 0; i < cookies.length; i++) {
				if("admin".equals(cookies[i].getName())) {
					if(cookies[i].getValue() != null || cookies[i].getValue() != "" || !"null".equals(cookies[i].getValue())) {
						admin_name = cookies[i].getValue();
					}
				} 
			}
		}
		if(categrayfirst == "") {
			catNameWrongMsg = "������������Ϊ�գ�";
			request.setAttribute("catNameWrongMsg", catNameWrongMsg);
			request.getRequestDispatcher("/admin/categoryfirst.jsp").forward(request, response);
			/*
			 * ����������ж�һ�����ݿ������ǲ����Ѿ���������������� 
			 * 
			 * */
		} else {
			cat.setCat_name(categrayfirst);
			cat.setAdministrator_name(admin_name);
			cat.setCat_createtime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			cd.add(cat);
			catNameWrongMsg = "��ӳɹ���";
			request.setAttribute("catNameWrongMsg", catNameWrongMsg);
			request.getRequestDispatcher("/admin/categoryfirst.jsp").forward(request, response);
		}
			
	}

}
