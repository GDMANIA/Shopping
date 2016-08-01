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

import com.shopping.dao.AdminLoginRecordDao;
import com.shopping.dao.AdministratorDao;
import com.shopping.daoimpl.AdminLoginRecordDaoImpl;
import com.shopping.daoimpl.AdministratorDaoImpl;
import com.shopping.entity.AdminLoginRecord;
import com.shopping.entity.Administrator;
/**
 * 这是管理员登录的控制servlet，拿到账号和密码以后，依次判断账号密码是否为空，
 * 账号是否存在，密码是否正确，如果出了错误就转发到登录页面并加上提示信息，
 * 如果正确，就将管理员名存入cookies中，并在管理员登录记录的表中插入一条数据，
 * 最后再重定向到前台 
 */
@WebServlet("/admin/adminLoginCheck.do")
public class AdminLoginCheckServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String administrator_name = request.getParameter("administrator_name");
		String administrator_password = request.getParameter("administrator_password");
		System.out.println(administrator_name);
		System.out.println(administrator_password);
		AdministratorDao ad = new AdministratorDaoImpl();
		Administrator admin = new Administrator();
		AdminLoginRecordDao alrd = new AdminLoginRecordDaoImpl();
		AdminLoginRecord alr = new AdminLoginRecord();
		String adminLoginWrongMsg = "";
		if(administrator_name == "" || administrator_name == null) {
			adminLoginWrongMsg = "账号不能为空！";
			request.setAttribute("adminLoginWrongMsg", adminLoginWrongMsg);
			request.getRequestDispatcher("/admin/admin_login.jsp").forward(request, response);
		} else {
			admin = ad.load(administrator_name);
			if(admin == null) {
				adminLoginWrongMsg = "账号不存在!";
				request.setAttribute("adminLoginWrongMsg", adminLoginWrongMsg);
				request.getRequestDispatcher("/admin/admin_login.jsp").forward(request, response);
			} else if(administrator_password == "" || administrator_password == null){
				adminLoginWrongMsg = "密码不能为空!";
				request.setAttribute("admin_name", administrator_name);
				request.setAttribute("adminLoginWrongMsg", adminLoginWrongMsg);
				request.getRequestDispatcher("/admin/admin_login.jsp").forward(request, response);;
			} else if(!administrator_password.equals(admin.getAdministrator_password())){
				adminLoginWrongMsg = "密码错误！";
				request.setAttribute("admin_name", administrator_name);
				request.setAttribute("adminLoginWrongMsg", adminLoginWrongMsg);
				request.getRequestDispatcher("/admin/admin_login.jsp").forward(request, response);
			} else {
				alr.setAdministrator_name(administrator_name);
				alr.setLogin_time(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				alrd.add(alr);
				response.addCookie(new Cookie("admin", admin.getAdministrator_name()));
				response.sendRedirect("/shopping/admin/main.jsp");
			}
		}
	}

}
