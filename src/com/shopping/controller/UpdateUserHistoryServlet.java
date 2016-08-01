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
import com.shopping.daoimpl.GoodsInfoDaoImpl;
import com.shopping.daoimpl.UserDaoImpl;
import com.shopping.daoimpl.UserHistoryDaoImpl;
import com.shopping.entity.GoodsInfo;
import com.shopping.entity.User;
import com.shopping.entity.UserHistory;

@WebServlet("/user/updateUserHistory.do")
public class UpdateUserHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_name = request.getParameter("user_name");
		String goods_id = request.getParameter("goods_id");
		UserHistoryDao uhd = new UserHistoryDaoImpl();
		UserHistory uh = new UserHistory();
		UserDao ud = new UserDaoImpl();
		User user = new User();
		user = ud.loadByName(user_name);
		String new_goods_ids = "";
		int count = 0;
		uh = uhd.load(user.getUser_id());
		if(uh == null) {
			uhd.add(user);
			uh = uhd.load(user.getUser_id());
			if(uh.getGoods_ids() == "" || uh.getGoods_ids() == null) {
				new_goods_ids = goods_id+",";
				uh.setGoods_ids(new_goods_ids);
			} else {
				String[] goods_ids = uh.getGoods_ids().split(",");
				new_goods_ids = new_goods_ids + goods_id + ",";
				if(goods_ids.length >= 4) {
					count = 3;
				} else {
					count = goods_ids.length;
				}
				for (int i = 0; i < count; i++) {
					new_goods_ids = new_goods_ids + goods_ids[i] + ",";
				}
				uh.setGoods_ids(new_goods_ids);
			}
			uhd.update(uh);
			
		} else {
			if(uh.getGoods_ids() == "" || uh.getGoods_ids() == null) {
				new_goods_ids = goods_id+",";
				uh.setGoods_ids(new_goods_ids);
			} else {
				String[] goods_ids = uh.getGoods_ids().split(",");
				new_goods_ids = new_goods_ids + goods_id + ",";
				if(goods_ids.length >= 4) {
					count = 3;
				} else {
					count = goods_ids.length;
				}
				for (int i = 0; i < count; i++) {
					new_goods_ids = new_goods_ids + goods_ids[i] + ",";
				}
				uh.setGoods_ids(new_goods_ids);
			}
			uhd.update(uh);
		}
	}

}
