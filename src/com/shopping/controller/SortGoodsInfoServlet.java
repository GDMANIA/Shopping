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
import com.shopping.daoimpl.CategoryDaoImpl;
import com.shopping.daoimpl.GoodsInfoDaoImpl;
import com.shopping.daoimpl.KeywordDaoImpl;
import com.shopping.entity.Category;
import com.shopping.entity.GoodsInfo;
import com.shopping.entity.GoodsSearchChoices;
import com.shopping.entity.Keyword;
import com.shopping.entity.Page;

@WebServlet("/user/sortGoodsInfos.do")
public class SortGoodsInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search_key = request.getParameter("search_key");
		String cat_id = request.getParameter("cat_id");
		String key_id = request.getParameter("key_id");
		String order_condition = request.getParameter("order_condition");
		String order_direction = request.getParameter("order_direction");
		int pageSize = 0;
		int currentPage = 0;
		Page page = new Page();
		GoodsSearchChoices choices = new GoodsSearchChoices();
		if(search_key == "" || search_key == null) {
			if(cat_id == "" || cat_id == null || "no_cat_id".equals(cat_id)) {
				if(request.getSession().getAttribute("user_search_goodsinfo_choices") != null) {
					choices = (GoodsSearchChoices) request.getSession().getAttribute("user_search_goodsinfo_choices");
					System.out.println(choices.getOrder_condition());
				} 
				choices.setOrder_condition(order_condition);
				choices.setOrder_direction(order_direction);
				request.getSession().setAttribute("user_search_goodsinfo_choices", choices);
				if(request.getSession().getAttribute("user_page_goodsinfos") != null) {
					page = (Page) request.getSession().getAttribute("user_page_goodsinfos");
				} 
			} else if(key_id == "" || key_id == null) {
				if(request.getSession().getAttribute("user_search_goodsinfo_choices_"+cat_id) != null) {
					choices = (GoodsSearchChoices) request.getSession().getAttribute("user_search_goodsinfo_choices_"+cat_id);
				}
				choices.setGoods_cat(cat_id);
				choices.setOrder_condition(order_condition);
				choices.setOrder_direction(order_direction);
				request.getSession().setAttribute("user_search_goodsinfo_choices_"+cat_id, choices);
				if(request.getSession().getAttribute("user_page_goodsinfos_"+cat_id) != null) {
					page = (Page) request.getSession().getAttribute("user_page_goodsinfos_"+cat_id);
				} 
			} else {
				if(request.getSession().getAttribute("user_search_goodsinfo_choices_"+cat_id+"_"+key_id) != null) {
					choices = (GoodsSearchChoices) request.getSession().getAttribute("user_search_goodsinfo_choices_"+cat_id+"_"+key_id);
				}
				choices.setGoods_cat(cat_id);
				choices.setGoods_key(key_id);
				choices.setOrder_condition(order_condition);
				choices.setOrder_direction(order_direction);
				request.getSession().setAttribute("user_search_goodsinfo_choices_"+cat_id+"_"+key_id, choices);
				if(request.getSession().getAttribute("user_page_goodsinfos_"+cat_id+"_"+key_id) != null) {
					page = (Page) request.getSession().getAttribute("user_page_goodsinfos_"+cat_id+"_"+key_id);
				} 
			} 
		} else {
			if(cat_id == "" || cat_id == null || "no_cat_id".equals(cat_id)) {
				if(request.getSession().getAttribute("user_search_search_goodsinfo_choices") != null) {
					choices = (GoodsSearchChoices) request.getSession().getAttribute("user_search_search_goodsinfo_choices");
					choices.setGoods_name(search_key);
				} 
				choices.setGoods_name(search_key);
				choices.setOrder_condition(order_condition);
				choices.setOrder_direction(order_direction);
				request.getSession().setAttribute("user_search_search_goodsinfo_choices", choices);
				if(request.getSession().getAttribute("user_search_page_goodsinfos") != null) {
					page = (Page) request.getSession().getAttribute("user_search_page_goodsinfos");
				} 
			} else if(key_id == "" || key_id == null) {
				if(request.getSession().getAttribute("user_search_search_goodsinfo_choices_"+cat_id) != null) {
					choices = (GoodsSearchChoices) request.getSession().getAttribute("user_search_search_goodsinfo_choices_"+cat_id);
					choices.setGoods_name(search_key);
				}
				choices.setGoods_name(search_key);
				choices.setGoods_cat(cat_id);
				choices.setOrder_condition(order_condition);
				choices.setOrder_direction(order_direction);
				request.getSession().setAttribute("user_search_search_goodsinfo_choices_"+cat_id, choices);
				if(request.getSession().getAttribute("user_search_page_goodsinfos_"+cat_id) != null) {
					page = (Page) request.getSession().getAttribute("user_search_page_goodsinfos_"+cat_id);
				} 
			} else {
				if(request.getSession().getAttribute("user_search_search_goodsinfo_choices_"+cat_id+"_"+key_id) != null) {
					choices = (GoodsSearchChoices) request.getSession().getAttribute("user_search_search_goodsinfo_choices_"+cat_id+"_"+key_id);
					choices.setGoods_name(search_key);
				}
				choices.setGoods_name(search_key);
				choices.setGoods_cat(cat_id);
				choices.setGoods_key(key_id);
				choices.setOrder_condition(order_condition);
				choices.setOrder_direction(order_direction);
				request.getSession().setAttribute("user_search_search_goodsinfo_choices_"+cat_id+"_"+key_id, choices);
				if(request.getSession().getAttribute("user_search_page_goodsinfos_"+cat_id+"_"+key_id) != null) {
					page = (Page) request.getSession().getAttribute("user_search_page_goodsinfos_"+cat_id+"_"+key_id);
				} 
			} 	
		}
			
		
		GoodsInfoDao gid = new GoodsInfoDaoImpl();
		List<GoodsInfo> gis = new ArrayList<GoodsInfo>();
//		List<GoodsInfo> gis_original = new ArrayList<GoodsInfo>();
//		String goods_cat_id = "";
//		String goods_key_id = "";
		int totalRows = gid.getTotalRows(choices);
		//Page page = null;
		
		totalRows = gid.getTotalRows(choices);
		page.setTotalRows(totalRows);
		if(page.getPageSize() == 0) {
			pageSize = 2;
		} else {
			pageSize = page.getPageSize();
		}
		page.setPageSize(pageSize);
		if(page.getCurrentPage() == 0) {
			currentPage = 1;
		} else {
			currentPage = page.getCurrentPage();
		}
		page.setCurrentPage(currentPage);
		page.setTotalPage(page.getTotalPage());
		gis = gid.selectAll(page.getOffsetRows(), pageSize, choices);
		page.setGis(gis);
		
		if(search_key == "" || search_key == null) {
			if(cat_id == "" || cat_id == null || "no_cat_id".equals(cat_id)) {
				request.getSession().setAttribute("user_page_goodsinfos", page);
			} else if(key_id == "" || key_id == null) {
				request.getSession().setAttribute("user_page_goodsinfos_"+cat_id, page);
			} else {
				request.getSession().setAttribute("user_page_goodsinfos_"+cat_id+"_"+key_id, page);
			}
		} else {
			if(cat_id == "" || cat_id == null || "no_cat_id".equals(cat_id)) {
				request.getSession().setAttribute("user_search_page_goodsinfos", page);
			} else if(key_id == "" || key_id == null) {
				request.getSession().setAttribute("user_search_page_goodsinfos_"+cat_id, page);
			} else {
				request.getSession().setAttribute("user_search_page_goodsinfos_"+cat_id+"_"+key_id, page);
			}
		}
		
		String jsonString = JSON.toJSONString(page);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonString);
		//System.out.println(jsonString);
	}

}
