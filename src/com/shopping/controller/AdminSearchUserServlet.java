package com.shopping.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.CategoryDao;
import com.shopping.dao.GoodsInfoDao;
import com.shopping.dao.KeywordDao;
import com.shopping.dao.UserDao;
import com.shopping.daoimpl.CategoryDaoImpl;
import com.shopping.daoimpl.GoodsInfoDaoImpl;
import com.shopping.daoimpl.KeywordDaoImpl;
import com.shopping.daoimpl.UserDaoImpl;
import com.shopping.entity.Category;
import com.shopping.entity.GoodsInfo;
import com.shopping.entity.GoodsSearchChoices;
import com.shopping.entity.Keyword;
import com.shopping.entity.Page;
import com.shopping.entity.User;
import com.shopping.entity.UserSearchChoices;
/**
 * 这是前台表单提交过来用于搜索用户信息的servlet处理类，
 * 这里要分别拿到前台提交过来的所有数据，然后重新生成一个搜索choices类，并绑定在session里面，
 * 然后也要重新设置相应分页对象的值，pagesize不用重新设
 * 最后用重定向的方式转回前台
 */
@WebServlet("/admin/adminSearchUsers.do")
public class AdminSearchUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String user_name = request.getParameter("user_name");
		String user_telnum = request.getParameter("user_telnum");
		String user_email = request.getParameter("user_email");
		String user_level = request.getParameter("user_level");
		String user_ttpayment_below = request.getParameter("user_ttpayment_below");
		String user_ttpayment_above = request.getParameter("user_ttpayment_above");
		boolean flag_goods_cat = false;
		boolean flag_goods_key = false;
		System.out.println(user_name);
		System.out.println(user_telnum);
		System.out.println(user_email);
		System.out.println(user_level);
		System.out.println(user_ttpayment_below);
		System.out.println(user_ttpayment_above);
		Page p = new Page();
		UserDao ud = new UserDaoImpl();
		List<User> users = new ArrayList<User>();
		
		UserSearchChoices choices = new UserSearchChoices(user_name, user_telnum, user_email, user_level, user_ttpayment_below, user_ttpayment_above);
		if(request.getSession().getAttribute("page_users") != null) {
			p = (Page) request.getSession().getAttribute("page_users");
			p.setPageSize(p.getPageSize());
		} else {
			p.setPageSize(10);
		}
		p.setTotalRows(ud.countAll(choices));
		p.setCurrentPage(1);
		p.setTotalPage(p.getTotalPage());
		users = ud.selectAll(p.getOffsetRows(), p.getPageSize(),choices);
		p.setUsers(users);
		
		request.getSession().setAttribute("search_user_choices", choices);
		request.getSession().setAttribute("page_users", p);
		response.sendRedirect("/shopping/admin/user.jsp");
		
	}

}
