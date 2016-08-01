package com.shopping.controller;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.GoodsInfoDao;
import com.shopping.dao.UserDao;
import com.shopping.dao.UserOrderDao;
import com.shopping.daoimpl.GoodsInfoDaoImpl;
import com.shopping.daoimpl.UserDaoImpl;
import com.shopping.daoimpl.UserOrderDaoImpl;
import com.shopping.entity.GoodsInfo;
import com.shopping.entity.GoodsSearchChoices;
import com.shopping.entity.OrderSearchChoices;
import com.shopping.entity.Page;
import com.shopping.entity.User;
import com.shopping.entity.UserOrder;
import com.shopping.entity.UserOrderGoodsInfo;
import com.shopping.entity.UserSearchChoices;
/**
 * �����Ǵ���ǰ̨��հ�ť�����servlet����Ҫ����ʵ���˽�session�����������������³�ʼ����Ȼ��page����
 * ���õ���һҳ������pagesize���䣬Ȼ�����ض���ԭҳ��
 */
@WebServlet("/admin/resetUserSearchChoices.do")
public class AdminResetUserSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Page p = new Page();
		UserSearchChoices choices = new UserSearchChoices();
		UserDao ud = new UserDaoImpl();
		List<User> users = new ArrayList<User>();
		if(request.getSession().getAttribute("page_users") != null) {
			p = (Page) request.getSession().getAttribute("page_users");
			p.setPageSize(p.getPageSize());
		} else {
			p.setPageSize(2);
		}
		p.setTotalRows(ud.countAll(choices));
		p.setPageSize(2);
		p.setCurrentPage(1);
		p.setTotalPage(p.getTotalPage());
		users = ud.selectAll(p.getOffsetRows(), p.getPageSize(),choices);
		p.setUsers(users);
		
		request.getSession().setAttribute("search_user_choices", choices);
		request.getSession().setAttribute("page_users", p);
		response.sendRedirect("/shopping/admin/order.jsp");
	}

}
