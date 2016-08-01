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
import com.shopping.dao.KeywordDao;
import com.shopping.daoimpl.CategoryDaoImpl;
import com.shopping.daoimpl.KeywordDaoImpl;
import com.shopping.entity.Administrator;
import com.shopping.entity.Category;
import com.shopping.entity.Keyword;
/**
 * ���ǹ���Ա�����������ҳ��������Ӷ���������ύ�����servlet��
 * �����ǰ̨�ύ���������ݽ����жϣ�����д���ͷ���ǰ̨һ��������Ϣ��
 * ���û�����ֱ�������ݿ����һ�����ݲ�ת����ԭҳ��
 */
@WebServlet("/admin/adminAddKeyword.do")
public class AdminAddKeywordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String categrayfirst = request.getParameter("categrayfirst");
		String categraysecond = request.getParameter("categraysecond");
		System.out.println(categrayfirst);
		System.out.println(categraysecond);
		String keyNameWrongMsg = "";
		KeywordDao kwd = new KeywordDaoImpl();
		Keyword kw = new Keyword();
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
		//�����Ժ��ټ�һ���жϣ����������������Ķ�����������Ѿ��ﵽ10�����������
		/*
		 * ����������ж�һ�����ݿ������ǲ����Ѿ������������������ 
		 * 
		 * */
		if("default".equals(categrayfirst)) {
			keyNameWrongMsg = "������������Ϊ�գ�";
			request.setAttribute("keyNameWrongMsg", keyNameWrongMsg);
			request.getRequestDispatcher("/admin/categorysecond.jsp").forward(request, response);
		} else if(categraysecond == ""){
			keyNameWrongMsg = "��������������Ϊ�գ�";
			request.setAttribute("keyNameWrongMsg", keyNameWrongMsg);
			request.getRequestDispatcher("/admin/categorysecond.jsp").forward(request, response);
		} else {
			kw.setKey_name(categraysecond);
			kw.setCat_id(Integer.parseInt(categrayfirst));
			kw.setAdministrator_name(admin_name);
			kw.setKey_createtime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			kwd.add(kw);
			keyNameWrongMsg = "��ӳɹ���";
			request.setAttribute("keyNameWrongMsg", keyNameWrongMsg);
			request.getRequestDispatcher("/admin/categorysecond.jsp").forward(request, response);
		}
			
	}

}
