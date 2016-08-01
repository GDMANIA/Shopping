package com.shopping.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
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
 * 这里是处理前台分页请求的servlet，主要先判断是改变pagesize还是currentpage，
 * 然后再执行相应的 操作，更改Page对象然后重新拿到数据再绑定到session
 */
@WebServlet("/admin/adminUserUpdatePages.do")
public class AdminUserUpdatePagesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageSize_str = request.getParameter("pageSize_str");
		String currentPage_str = request.getParameter("currentPage_str");
		int pageSize = 0;
		int currentPage = 0;
		String from = request.getParameter("from");
		Page p = new Page();
		UserDao ud = new UserDaoImpl();
		List<User> users = new ArrayList<User>();
		UserSearchChoices choices = new UserSearchChoices();
		if(request.getSession().getAttribute("search_user_choices") != null) {
			choices = (UserSearchChoices) request.getSession().getAttribute("search_user_choices");
		}
		if(request.getSession().getAttribute("page_users") != null) {
			p = (Page) request.getSession().getAttribute("page_users");
		}
		if("page_size".equals(from)) {
			pageSize = Integer.parseInt(pageSize_str);
			if(request.getSession().getAttribute("page_users") != null) {
				p.setPageSize(pageSize);
				p.setCurrentPage(1);
				p.setTotalPage(p.getTotalPage());
				p.setOffsetRows(p.getOffsetRows());
			} else {
				p.setTotalRows(ud.countAll(choices));
				p.setPageSize(pageSize);
				p.setCurrentPage(1);
				p.setTotalPage(p.getTotalPage());
				p.setOffsetRows(p.getOffsetRows());
			}
			users = ud.selectAll(p.getOffsetRows(), p.getPageSize(),choices);
			p.setUsers(users);
			
		} else if("current_page".equals(from)) {
			currentPage = Integer.parseInt(currentPage_str);
			if(request.getSession().getAttribute("page_users") != null) {
				p.setCurrentPage(currentPage);
				p.setOffsetRows(p.getOffsetRows());
			} else {
				p.setTotalRows(ud.countAll(choices));
				p.setPageSize(2);
				p.setCurrentPage(currentPage);
				p.setTotalPage(p.getTotalPage());
				p.setOffsetRows(p.getOffsetRows());
			}
			users = ud.selectAll(p.getOffsetRows(), p.getPageSize(),choices);
			p.setUsers(users);
		}
		
		request.getSession().setAttribute("page_users", p);
	}

}
