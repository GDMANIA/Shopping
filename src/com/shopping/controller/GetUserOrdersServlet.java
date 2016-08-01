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
import com.shopping.dao.FloorDao;
import com.shopping.dao.GoodsInfoDao;
import com.shopping.dao.UserDao;
import com.shopping.dao.UserOrderDao;
import com.shopping.daoimpl.FloorDaoImpl;
import com.shopping.daoimpl.GoodsInfoDaoImpl;
import com.shopping.daoimpl.UserDaoImpl;
import com.shopping.daoimpl.UserOrderDaoImpl;
import com.shopping.entity.Floor;
import com.shopping.entity.GoodsInfo;
import com.shopping.entity.UserOrder;
import com.shopping.entity.UserOrderGoodsInfo;

@WebServlet("/user/getUserOrders.do")
public class GetUserOrdersServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_name = request.getParameter("user_name");
		System.out.println("111");
		UserDao ud = new UserDaoImpl();
		UserOrderDao uod = new UserOrderDaoImpl();
		GoodsInfoDao gid = new GoodsInfoDaoImpl();
		UserOrderGoodsInfo uogi = new UserOrderGoodsInfo();
		UserOrder uo = new UserOrder();
		String[] goods_id_nums;
		String goods_id;
		String goods_name;
		String goods_img;
		int goods_num;
		//String[] goods_nums;
		List<UserOrder> uos = new ArrayList<UserOrder>();
		List<UserOrderGoodsInfo> uogis = new ArrayList<UserOrderGoodsInfo>();
		uos = uod.loadAll(ud.loadByName(user_name).getUser_id());
		for (int i = 0; i < uos.size(); i++) {
			uo = new UserOrder();
			uogis = new ArrayList<UserOrderGoodsInfo>();
			uo = uos.get(i);
			//if(uo.getOrder_isdeleted() == 0) {
				goods_id_nums = uo.getGoods_id_num().split(",");
				for (int j = 0; j < goods_id_nums.length; j++) {
					uogi = new UserOrderGoodsInfo();
					goods_id = goods_id_nums[j].split(":")[0];
					goods_num = Integer.parseInt(goods_id_nums[j].split(":")[1]);
					goods_name = gid.load(goods_id).getGoods_name();
					goods_img = gid.load(goods_id).getGoods_img().split(",")[0];
	//				System.out.println(goods_id);
	//				System.out.println(goods_num);
	//				System.out.println(goods_name);
	//				System.out.println(goods_img);
					uogi.setGoods_id(goods_id);
					uogi.setGoods_name(goods_name);
					uogi.setGoods_img(goods_img);
					uogi.setGoods_num(goods_num);
					uogis.add(uogi);
				}
				uo.setUogis(uogis);
				uos.set(i, uo);
		//	}
			
		}
		String jsonString = JSON.toJSONString(uos);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonString);
		System.out.println(jsonString);
	}

}
