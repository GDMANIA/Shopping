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
import com.shopping.dao.UserOrderDao;
import com.shopping.daoimpl.GoodsInfoDaoImpl;
import com.shopping.daoimpl.UserOrderDaoImpl;
import com.shopping.entity.GoodsInfo;
import com.shopping.entity.GoodsSearchChoices;
import com.shopping.entity.OrderSearchChoices;
import com.shopping.entity.Page;
import com.shopping.entity.UserOrder;
import com.shopping.entity.UserOrderGoodsInfo;
/**
 * 这里是处理前台清空按钮请求的servlet，主要就是实现了将session里面的搜索类对象重新初始化，然后将page对象
 * 重置到第一页，但是pagesize不变，然后再重定向到原页面
 */
@WebServlet("/admin/resetOrderSearchChoice.do")
public class AdminResetOrderSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Page p = new Page();
		UserOrderDao uod = new UserOrderDaoImpl();
		UserOrder uo = new UserOrder();
		List<UserOrder> uos = new ArrayList<UserOrder>();
		GoodsInfoDao gid = new GoodsInfoDaoImpl();
		GoodsInfo gi = new GoodsInfo();
		List<UserOrderGoodsInfo> uogis = new ArrayList<UserOrderGoodsInfo>();
		UserOrderGoodsInfo uogi = new UserOrderGoodsInfo();
		OrderSearchChoices choices = new OrderSearchChoices();
		String[] goods_id_nums;
		String goods_id = "";
		int goods_num = 0;
		if(request.getSession().getAttribute("page_orders") != null) {
			p = (Page) request.getSession().getAttribute("page_orders");
			p.setPageSize(p.getPageSize());
		} else {
			p.setPageSize(2);
		}
		p.setTotalRows(uod.countAll(choices));
		//p.setPageSize(2);
		p.setCurrentPage(1);
		p.setTotalPage(p.getTotalPage());
		uos = uod.loadAll(p.getOffsetRows(), p.getPageSize(),choices);
		for(int i = 0; i < uos.size(); i++) {
			uo = new UserOrder();
			uogis = new ArrayList<UserOrderGoodsInfo>();
			uo = uos.get(i);
			goods_id_nums = uo.getGoods_id_num().split(",");
			for(int j = 0; j < goods_id_nums.length; j++) {
				goods_id = goods_id_nums[j].split(":")[0];
				goods_num = Integer.parseInt(goods_id_nums[j].split(":")[1]);
				gi = new GoodsInfo();
				gi = gid.loadRegardlessOfDeleted(goods_id);
				uogi = new UserOrderGoodsInfo();
				uogi.setGoods_id(goods_id);
				uogi.setGoods_name(gi.getGoods_name());
				uogi.setGoods_num(goods_num);
				uogi.setGoods_price(gi.getGoods_curprice());
				uogi.setGoods_img(gi.getGoods_img().split(",")[0]);
				uogis.add(uogi);
			}
			uo.setUogis(uogis);
			uos.set(i, uo);
		}
		
		p.setUos(uos);
		
		request.getSession().setAttribute("search_order_choices", choices);
		request.getSession().setAttribute("page_orders", p);
		response.sendRedirect("/shopping/admin/order.jsp");
	}

}
