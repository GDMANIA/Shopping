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
import com.shopping.dao.UserOrderDao;
import com.shopping.daoimpl.CategoryDaoImpl;
import com.shopping.daoimpl.GoodsInfoDaoImpl;
import com.shopping.daoimpl.KeywordDaoImpl;
import com.shopping.daoimpl.UserDaoImpl;
import com.shopping.daoimpl.UserOrderDaoImpl;
import com.shopping.entity.Category;
import com.shopping.entity.GoodsInfo;
import com.shopping.entity.GoodsSearchChoices;
import com.shopping.entity.Keyword;
import com.shopping.entity.OrderSearchChoices;
import com.shopping.entity.Page;
import com.shopping.entity.User;
import com.shopping.entity.UserOrder;
import com.shopping.entity.UserOrderGoodsInfo;
import com.shopping.entity.UserSearchChoices;
/**
 * 这是前台表单提交过来用于搜索订单信息的servlet处理类，
 * 这里要分别拿到前台提交过来的所有数据，然后重新生成一个搜索choices类，并绑定在session里面，
 * 然后也要重新设置相应分页对象的值，pagesize不用重新设
 * 最后用重定向的方式转回前台
 */
@WebServlet("/admin/adminSearchOrders.do")
public class AdminSearchOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String user_name = request.getParameter("user_name");
		String order_state = request.getParameter("order_state");
		String order_isdeleted = request.getParameter("order_isdeleted");
		String order_ttprice_below = request.getParameter("order_ttprice_below");
		String order_ttprice_above = request.getParameter("order_ttprice_above");
		//System.out.println(user_name);
		System.out.println(order_state);
		//System.out.println(order_isdeleted);
		//System.out.println(order_ttprice_below);
		//System.out.println(order_ttprice_above);
		Page p = new Page();
		UserOrderDao uod = new UserOrderDaoImpl();
		UserOrder uo = new UserOrder();
		List<UserOrder> uos = new ArrayList<UserOrder>();
		GoodsInfoDao gid = new GoodsInfoDaoImpl();
		GoodsInfo gi = new GoodsInfo();
		List<UserOrderGoodsInfo> uogis = new ArrayList<UserOrderGoodsInfo>();
		UserOrderGoodsInfo uogi = new UserOrderGoodsInfo();
		OrderSearchChoices choices = new OrderSearchChoices(user_name, order_state, order_isdeleted, order_ttprice_below, order_ttprice_above);
		String[] goods_id_nums;
		String goods_id = "";
		int goods_num = 0;
		
		//因为前台的下拉选择条可能会传过来这两个默认值，所以要进行判断
		if("请选择".equals(choices.getOrder_state())) {
			choices.setOrder_state("");
		}
		if("请选择".equals(choices.getOrder_isdeleted())) {
			choices.setOrder_isdeleted("");
		}
		
		//如果session里面存在分页数据，就不用重新设置pagesize，但是其他值要重新设置
		//并且还要从数据库重新拿一次数据
		if(request.getSession().getAttribute("page_orders") != null) {
			p = (Page) request.getSession().getAttribute("page_orders");
			p.setPageSize(p.getPageSize());
		} else {
			p.setPageSize(10);
		}
		p.setTotalRows(uod.countAll(choices));
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
		System.out.println(choices);
		request.getSession().setAttribute("page_orders", p);
		response.sendRedirect("/shopping/admin/order.jsp");
		
	}

}
