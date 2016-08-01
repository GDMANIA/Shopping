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
 * 这是管理员二级分类管理页面用于添加二级分类表单提交处理的servlet，
 * 这里对前台提交过来的数据进行判断，如果有错误就返回前台一个错误信息，
 * 如果没错误就直接向数据库插入一条数据并转发到原页面
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
		//这里以后再加一个判断，如果该主分类下面的二级分类个数已经达到10个，则不能添加
		/*
		 * 这里最好在判断一下数据库里面是不是已经有这个二级分类名了 
		 * 
		 * */
		if("default".equals(categrayfirst)) {
			keyNameWrongMsg = "主分类名不能为空！";
			request.setAttribute("keyNameWrongMsg", keyNameWrongMsg);
			request.getRequestDispatcher("/admin/categorysecond.jsp").forward(request, response);
		} else if(categraysecond == ""){
			keyNameWrongMsg = "二级分类名不能为空！";
			request.setAttribute("keyNameWrongMsg", keyNameWrongMsg);
			request.getRequestDispatcher("/admin/categorysecond.jsp").forward(request, response);
		} else {
			kw.setKey_name(categraysecond);
			kw.setCat_id(Integer.parseInt(categrayfirst));
			kw.setAdministrator_name(admin_name);
			kw.setKey_createtime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
			kwd.add(kw);
			keyNameWrongMsg = "添加成功！";
			request.setAttribute("keyNameWrongMsg", keyNameWrongMsg);
			request.getRequestDispatcher("/admin/categorysecond.jsp").forward(request, response);
		}
			
	}

}
