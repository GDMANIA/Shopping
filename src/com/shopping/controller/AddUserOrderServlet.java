package com.shopping.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.GoodsInfoDao;
import com.shopping.dao.UserAddressDao;
import com.shopping.dao.UserDao;
import com.shopping.dao.UserOrderDao;
import com.shopping.daoimpl.GoodsInfoDaoImpl;
import com.shopping.daoimpl.UserAddressDaoImpl;
import com.shopping.daoimpl.UserDaoImpl;
import com.shopping.daoimpl.UserOrderDaoImpl;
import com.shopping.entity.User;
import com.shopping.entity.UserAddress;
import com.shopping.entity.UserOrder;

@WebServlet("/user/addUserOrder.do")
public class AddUserOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_name = request.getParameter("user_name");
		String goods_id_num = request.getParameter("goods_id_num");
		String order_ttprice = request.getParameter("order_ttprice");
		String order_address = request.getParameter("order_address");
		String goods_id = "";
		int goods_num = 0;
		UserOrderDao uod = new UserOrderDaoImpl();
		UserOrder uo = new UserOrder();
		UserDao ud = new UserDaoImpl();
		GoodsInfoDao gid = new GoodsInfoDaoImpl();
		String userorder_id = "ABC" + user_name + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date()) + String.valueOf(System.currentTimeMillis()).substring(String.valueOf(System.currentTimeMillis()).length()-8);
		String order_createtime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		String[] goods_id_nums = goods_id_num.split(",");
		for (int i = 0; i < goods_id_nums.length; i++) {
			goods_id = goods_id_nums[i].split(":")[0];
			goods_num = Integer.parseInt(goods_id_nums[i].split(":")[1]);
			if(goods_num > gid.load(goods_id).getGoods_ttnum()) {
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write("not_enough_goods_ttnum|" + gid.load(goods_id).getGoods_name());
				return;
			}
		}
		uo.setUserorder_id(userorder_id);
		uo.setUser_id(ud.loadByName(user_name).getUser_id());
		uo.setUser_name(user_name);
		uo.setOrder_createtime(order_createtime);
		uo.setGoods_id_num(goods_id_num);
		uo.setOrder_ttprice(Double.parseDouble(order_ttprice));
		uo.setOrder_address(order_address);
		uo.setOrder_state(0);
		uo.setOrder_isdeleted(0);
		uod.add(uo);
		response.getWriter().write("success|" + userorder_id);
	}

}
