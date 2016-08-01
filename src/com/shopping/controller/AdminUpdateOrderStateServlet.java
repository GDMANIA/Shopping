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
 * �����Ǵ���ǰ̨���Ķ���״̬��ajax�����servlet���������Ʒ״̬�Ĳ�࣬��Ҫ���ж�ǰ̨�������Ĵ��������ʲô��
 * Ȼ���ٽ�����Ӧ�Ĳ������������ݿ��ж�����״̬��Ȼ���������õ�page���󲢰󶨵�session���棬
 *
 */
@WebServlet("/admin/adminUpdateOrderState.do")
public class AdminUpdateOrderStateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String userorder_id = request.getParameter("userorder_id");
		String operation = request.getParameter("operation");
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
		uo = uod.load(userorder_id);
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
		if("��Ϊ��Ч".equals(operation)) {
			uo.setOrder_state(5);
			uod.updateInvalid(uo);
			p.setTotalRows(uod.countAll(choices));
			if(request.getSession().getAttribute("page_orders") == null) {
				p.setPageSize(2);
				p.setCurrentPage(1);
			}
			p.setTotalPage(p.getTotalPage());
			p.setOffsetRows(p.getOffsetRows());
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
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("�ö����Ѿ��ɹ�����Ϊ��Ч������");
		} else if("��������".equals(operation)) {
			uo.setOrder_state(2);
			uod.update(uo);
			p.setTotalRows(uod.countAll(choices));
			if(request.getSession().getAttribute("page_orders") == null) {
				p.setPageSize(2);
				p.setCurrentPage(1);
			}
			p.setTotalPage(p.getTotalPage());
			p.setOffsetRows(p.getOffsetRows());
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
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("�ö����Ѿ��ɹ�������");
		} else if("ɾ��".equals(operation)) {
			uod.delete(uo);
			p.setTotalRows(uod.countAll(choices));
			if(request.getSession().getAttribute("page_orders") == null) {
				p.setPageSize(2);
				p.setCurrentPage(1);
			}
			p.setTotalPage(p.getTotalPage());
			p.setOffsetRows(p.getOffsetRows());
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
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write("����Ч�����Ѿ��ɹ���ϵͳ��ɾ����");
		}
		request.getSession().setAttribute("search_order_choices", choices);
		request.getSession().setAttribute("page_orders", p);
	}

}
