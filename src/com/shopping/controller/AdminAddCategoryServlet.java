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
 * 这是管理员一级分类管理页面用于添加一级分类表单提交处理的servlet，
 * 这里对前台提交过来的数据进行判断，如果有错误就返回前台一个错误信息，
 * 如果没错误就直接向数据库插入一条数据并转发到原页面
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
		 * 这里对cookie的判断应该放到过滤器里面
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
			catNameWrongMsg = "主分类名不能为空！";
			request.setAttribute("catNameWrongMsg", catNameWrongMsg);
			request.getRequestDispatcher("/admin/categoryfirst.jsp").forward(request, response);
			/*
			 * 这里最好在判断一下数据库里面是不是已经有这个主分类名了 
			 * 
			 * */
		} else {
			cat.setCat_name(categrayfirst);
			cat.setAdministrator_name(admin_name);
			cat.setCat_createtime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			cd.add(cat);
			catNameWrongMsg = "添加成功！";
			request.setAttribute("catNameWrongMsg", catNameWrongMsg);
			request.getRequestDispatcher("/admin/categoryfirst.jsp").forward(request, response);
		}
			
	}

}
