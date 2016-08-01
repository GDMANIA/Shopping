package com.shopping.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.UserCartDao;
import com.shopping.dao.UserCollectionDao;
import com.shopping.dao.UserDao;
import com.shopping.daoimpl.UserCartDaoImpl;
import com.shopping.daoimpl.UserCollectionDaoImpl;
import com.shopping.daoimpl.UserDaoImpl;
import com.shopping.entity.User;
import com.shopping.entity.UserCart;
import com.shopping.entity.UserCollection;

@WebServlet("/user/addCartAndRemoveCart.do")
public class AddCartAndRemoveCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String flag = request.getParameter("flag");
		String goods_id = request.getParameter("goods_id");
		String user_name = request.getParameter("user_name");
		String goods_num = request.getParameter("goods_num");
		boolean isExistOrNot = false;
		UserCartDao ucd = new UserCartDaoImpl();
		List<UserCart> ucs = new ArrayList<UserCart>();
		UserCart uc = new UserCart();
		UserDao ud = new UserDaoImpl();
		User user = new User();
		ucs = ucd.load(user_name);
		user = ud.loadByName(user_name);
		uc.setUser_id(user.getUser_id());
		uc.setUser_name(user_name);
		uc.setGoods_id(goods_id);
		if(goods_num == null || goods_num == "") {
			uc.setGoods_num(1);
		} else {
			uc.setGoods_num(Integer.parseInt(goods_num));
		}
		if(flag.equals("cart_true")) {
			ucd.delete(uc);
			response.getWriter().write("remove_cart");
		} else {
			for (int i = 0; i < ucs.size(); i++) {
				if(ucs.get(i).getGoods_id().equals(goods_id)) {
					isExistOrNot = true;
					break;
				}
			}
			if(isExistOrNot) {
				response.getWriter().write("already_exist");
			} else {
				ucd.add(uc);
				response.getWriter().write("add_cart");
			}
		}
	}

}
