package com.shopping.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.CategoryDao;
import com.shopping.daoimpl.CategoryDaoImpl;
import com.shopping.entity.Administrator;
import com.shopping.entity.Category;

@WebServlet("/admin/searchUsers.do")
public class SearchUsersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String user_name = request.getParameter("user_name");
		String user_telnum = request.getParameter("user_telnum");
		String user_level = request.getParameter("user_level");
		String user_ttpayment_below = request.getParameter("user_ttpayment_below");
		String user_ttpayment_above = request.getParameter("user_ttpayment_above");
			
	}

}
