package com.shopping.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.GoodsInfoDao;
import com.shopping.dao.UserDao;
import com.shopping.dao.UserHistoryDao;
import com.shopping.dao.UserOrderDao;
import com.shopping.daoimpl.GoodsInfoDaoImpl;
import com.shopping.daoimpl.UserDaoImpl;
import com.shopping.daoimpl.UserHistoryDaoImpl;
import com.shopping.daoimpl.UserOrderDaoImpl;
import com.shopping.entity.GoodsInfo;
import com.shopping.entity.User;
import com.shopping.entity.UserHistory;
import com.shopping.entity.UserOrder;

@WebServlet("/user/updateUserOrder.do")
public class UpdateUserOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userorder_id = request.getParameter("userorder_id");
		String user_name = request.getParameter("user_name");
		String order_ttprice = request.getParameter("order_ttprice");
		String from = request.getParameter("from");
		UserOrderDao uod = new UserOrderDaoImpl();
		UserOrder uo = new UserOrder();
		UserDao ud = new UserDaoImpl();
		User user = new User();
		GoodsInfoDao gid = new GoodsInfoDaoImpl();
		GoodsInfo gi = new GoodsInfo();
		uo = uod.load(userorder_id);
		String[] goods_id_nums = uo.getGoods_id_num().split(",");
		String goods_id = "";
		int goods_num = 0;
		if("order_created".equals(from)) {
			user = ud.loadByName(user_name);
			user.setUser_ttpayment(user.getUser_ttpayment()+Double.parseDouble(order_ttprice));
			ud.update(user);
			uo.setOrder_state(1);
			uod.update(uo);
			response.getWriter().write("success");
		} else if("order_delete".equals(from)) {
			if(uo.getOrder_state() == 0) {
				uod.updateInvalid(uo);
				uo.setOrder_isdeleted(1);
				response.getWriter().write("success");
			} else {
				uo.setOrder_isdeleted(1);
				uod.update(uo);
				response.getWriter().write("success");
			}
		} else if("order_confirm".equals(from)) {
			uo.setOrder_state(3);
			uod.update(uo);
			response.getWriter().write("success");
		} else if("order_noshow".equals(from)) {
			uo.setOrder_isdeleted(2);
			uod.update(uo);
			response.getWriter().write("success");
		}
	}

}
