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
import com.shopping.dao.UserOrderDao;
import com.shopping.daoimpl.CategoryDaoImpl;
import com.shopping.daoimpl.GoodsInfoDaoImpl;
import com.shopping.daoimpl.KeywordDaoImpl;
import com.shopping.daoimpl.UserOrderDaoImpl;
import com.shopping.entity.Category;
import com.shopping.entity.GoodsInfo;
import com.shopping.entity.GoodsSearchChoices;
import com.shopping.entity.Keyword;
import com.shopping.entity.OrderSearchChoices;
import com.shopping.entity.Page;
import com.shopping.entity.UserOrder;
import com.shopping.entity.UserOrderGoodsInfo;
/**
 * �����Ǵ���ǰ̨��ҳ�����servlet����Ҫ���ж��Ǹı�pagesize����currentpage��
 * Ȼ����ִ����Ӧ�� ����������Page����Ȼ�������õ������ٰ󶨵�session
 */
@WebServlet("/admin/adminOrderUpdatePages.do")
public class AdminOrderUpdatePagesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageSize_str = request.getParameter("pageSize_str");
		String currentPage_str = request.getParameter("currentPage_str");
		int pageSize = 0;
		int currentPage = 0;
		String from = request.getParameter("from");
		OrderSearchChoices choices = new OrderSearchChoices();
		Page p = new Page();
		UserOrderDao uod = new UserOrderDaoImpl();
		UserOrder uo = new UserOrder();
		List<UserOrder> uos = new ArrayList<UserOrder>();
		GoodsInfoDao gid = new GoodsInfoDaoImpl();
		GoodsInfo gi = new GoodsInfo();
		List<UserOrderGoodsInfo> uogis = new ArrayList<UserOrderGoodsInfo>();
		UserOrderGoodsInfo uogi = new UserOrderGoodsInfo();
		String[] goods_id_nums;
		String goods_id = "";
		int goods_num = 0;
		if(request.getSession().getAttribute("search_order_choices") != null) {
			choices = (OrderSearchChoices) request.getSession().getAttribute("search_order_choices");
			if("��ѡ��".equals(choices.getOrder_state())) {
				choices.setOrder_state("");
			}
			if("��ѡ��".equals(choices.getOrder_isdeleted())) {
				choices.setOrder_isdeleted("");
			}
		}
		if(request.getSession().getAttribute("page_orders") != null) {
			p = (Page) request.getSession().getAttribute("page_orders");
		}
		if("page_size".equals(from)) {
			pageSize = Integer.parseInt(pageSize_str);
			if(request.getSession().getAttribute("page_orders") != null) {
				p.setPageSize(pageSize);
				p.setCurrentPage(1);
				p.setTotalPage(p.getTotalPage());
				p.setOffsetRows(p.getOffsetRows());
			} else {
				p.setTotalRows(uod.countAll(choices));
				p.setPageSize(pageSize);
				p.setCurrentPage(1);
				p.setTotalPage(p.getTotalPage());
			}
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
			
		} else if("current_page".equals(from)) {
			currentPage = Integer.parseInt(currentPage_str);
			if(request.getSession().getAttribute("page_orders") != null) {
				p.setCurrentPage(currentPage);
				p.setOffsetRows(p.getOffsetRows());
			} else {
				p.setTotalRows(uod.countAll(choices));
				p.setPageSize(10);
				p.setCurrentPage(currentPage);
				p.setTotalPage(p.getTotalPage());
				p.setOffsetRows(p.getOffsetRows());
			}
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
		}
		
		request.getSession().setAttribute("search_order_choices", choices);
		request.getSession().setAttribute("page_orders", p);
	}

}
