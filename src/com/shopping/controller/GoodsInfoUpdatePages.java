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

/**
 * Servlet implementation class GoodsInfoUpdatePages
 */
@WebServlet("/user/userGoodsInfoUpdatePages.do")
public class GoodsInfoUpdatePages extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search_key = request.getParameter("search_key");
		String cat_id = request.getParameter("cat_id");
		String key_id = request.getParameter("key_id");
		String pageSize_str = request.getParameter("pageSize_str");
		String currentPage_str = request.getParameter("currentPage_str");
		int pageSize = 0;
		int currentPage = 0;
		String from = request.getParameter("from");
		GoodsSearchChoices choices = new GoodsSearchChoices();
		Page p = new Page();
		GoodsInfoDao gid = new GoodsInfoDaoImpl();
		List<GoodsInfo> gis = new ArrayList<GoodsInfo>();
		CategoryDao cd = new CategoryDaoImpl();
		KeywordDao kd = new KeywordDaoImpl();
		Category cat = null;
		Keyword key = new Keyword();
		List<Category> cats = cd.selectAll();
		List<Keyword> keys = kd.loadAll();
		if(search_key == "" || search_key == null) {
			if(cat_id == "" || cat_id == null || "no_cat_id".equals(cat_id)) {
				if(request.getSession().getAttribute("user_search_goodsinfo_choices") != null) {
					choices = (GoodsSearchChoices) request.getSession().getAttribute("user_search_goodsinfo_choices");
					System.out.println(choices.getOrder_condition());
				} 
				if("page_size".equals(from)) {
					pageSize = Integer.parseInt(pageSize_str);
					if(request.getSession().getAttribute("user_page_goodsinfos") != null) {
						p = (Page) request.getSession().getAttribute("user_page_goodsinfos");
						p.setPageSize(pageSize);
						p.setCurrentPage(1);
						p.setTotalPage(p.getTotalPage());
						p.setOffsetRows(p.getOffsetRows());
					} else {
						p.setTotalRows(gid.getTotalRows(choices));
						p.setPageSize(pageSize);
						p.setCurrentPage(1);
						p.setTotalPage(p.getTotalPage());
					}
					gis = gid.selectAll(p.getOffsetRows(), p.getPageSize(),choices);
					p.setGis(gis);
					request.getSession().setAttribute("user_page_goodsinfos", p);
				} else if("current_page".equals(from)) {
					currentPage = Integer.parseInt(currentPage_str);
					if(request.getSession().getAttribute("user_page_goodsinfos") != null) {
						p = (Page) request.getSession().getAttribute("user_page_goodsinfos");
						p.setCurrentPage(currentPage);
						p.setOffsetRows(p.getOffsetRows());
					} else {
						p.setTotalRows(gid.getTotalRows(choices));
						p.setPageSize(2);
						p.setCurrentPage(currentPage);
						p.setTotalPage(p.getTotalPage());
						p.setOffsetRows(p.getOffsetRows());
					}
					gis = gid.selectAll(p.getOffsetRows(), p.getPageSize(),choices);
					p.setGis(gis);
					request.getSession().setAttribute("user_page_goodsinfos", p);
				}
			} else if(key_id == "" || key_id == null) {
				if(request.getSession().getAttribute("user_search_goodsinfo_choices_"+cat_id) != null) {
					choices = (GoodsSearchChoices) request.getSession().getAttribute("user_search_goodsinfo_choices_"+cat_id);
				}
				choices.setGoods_cat(cat_id);
				if("page_size".equals(from)) {
					pageSize = Integer.parseInt(pageSize_str);
					if(request.getSession().getAttribute("user_page_goodsinfos_"+cat_id) != null) {
						p = (Page) request.getSession().getAttribute("user_page_goodsinfos_"+cat_id);
						p.setPageSize(pageSize);
						p.setCurrentPage(1);
						p.setTotalPage(p.getTotalPage());
						p.setOffsetRows(p.getOffsetRows());
					} else {
						p.setTotalRows(gid.getTotalRows(choices));
						p.setPageSize(pageSize);
						p.setCurrentPage(1);
						p.setTotalPage(p.getTotalPage());
					}
					gis = gid.selectAll(p.getOffsetRows(), p.getPageSize(),choices);
					p.setGis(gis);
					request.getSession().setAttribute("user_page_goodsinfos_"+cat_id, p);
				} else if("current_page".equals(from)) {
					currentPage = Integer.parseInt(currentPage_str);
					if(request.getSession().getAttribute("user_page_goodsinfos_"+cat_id) != null) {
						p = (Page) request.getSession().getAttribute("user_page_goodsinfos_"+cat_id);
						p.setCurrentPage(currentPage);
						p.setOffsetRows(p.getOffsetRows());
					} else {
						p.setTotalRows(gid.getTotalRows(choices));
						p.setPageSize(2);
						p.setCurrentPage(currentPage);
						p.setTotalPage(p.getTotalPage());
						p.setOffsetRows(p.getOffsetRows());
					}
					gis = gid.selectAll(p.getOffsetRows(), p.getPageSize(),choices);
					p.setGis(gis);
					request.getSession().setAttribute("user_page_goodsinfos_"+cat_id, p);
				}
			} else {
				if(request.getSession().getAttribute("user_search_goodsinfo_choices_"+cat_id+"_"+key_id) != null) {
					choices = (GoodsSearchChoices) request.getSession().getAttribute("user_search_goodsinfo_choices_"+cat_id+"_"+key_id);
				}
				choices.setGoods_cat(cat_id);
				choices.setGoods_key(key_id);
				if(request.getSession().getAttribute("user_page_goodsinfos_"+cat_id+"_"+key_id) != null) {
					p = (Page) request.getSession().getAttribute("user_page_goodsinfos_"+cat_id+"_"+key_id);
				} 
				if("page_size".equals(from)) {
					pageSize = Integer.parseInt(pageSize_str);
					if(request.getSession().getAttribute("user_page_goodsinfos_"+cat_id+"_"+key_id) != null) {
						p = (Page) request.getSession().getAttribute("user_page_goodsinfos_"+cat_id+"_"+key_id);
						p.setPageSize(pageSize);
						p.setCurrentPage(1);
						p.setTotalPage(p.getTotalPage());
						p.setOffsetRows(p.getOffsetRows());
					} else {
						p.setTotalRows(gid.getTotalRows(choices));
						p.setPageSize(pageSize);
						p.setCurrentPage(1);
						p.setTotalPage(p.getTotalPage());
					}
					gis = gid.selectAll(p.getOffsetRows(), p.getPageSize(),choices);
					p.setGis(gis);
					request.getSession().setAttribute("user_page_goodsinfos_"+cat_id+"_"+key_id, p);
				} else if("current_page".equals(from)) {
					currentPage = Integer.parseInt(currentPage_str);
					if(request.getSession().getAttribute("user_page_goodsinfos_"+cat_id+"_"+key_id) != null) {
						p = (Page) request.getSession().getAttribute("user_page_goodsinfos_"+cat_id+"_"+key_id);
						p.setCurrentPage(currentPage);
						p.setOffsetRows(p.getOffsetRows());
					} else {
						p.setTotalRows(gid.getTotalRows(choices));
						p.setPageSize(2);
						p.setCurrentPage(currentPage);
						p.setTotalPage(p.getTotalPage());
						p.setOffsetRows(p.getOffsetRows());
					}
					gis = gid.selectAll(p.getOffsetRows(), p.getPageSize(),choices);
					p.setGis(gis);
					request.getSession().setAttribute("user_page_goodsinfos_"+cat_id+"_"+key_id, p);
				}
			}
		} else {
			if(cat_id == "" || cat_id == null || "no_cat_id".equals(cat_id)) {
				if(request.getSession().getAttribute("user_search_search_goodsinfo_choices") != null) {
					choices = (GoodsSearchChoices) request.getSession().getAttribute("user_search_search_goodsinfo_choices");
					choices.setGoods_name(search_key);
				} 
				choices.setGoods_name(search_key);
				if("page_size".equals(from)) {
					pageSize = Integer.parseInt(pageSize_str);
					if(request.getSession().getAttribute("user_search_page_goodsinfos") != null) {
						p = (Page) request.getSession().getAttribute("user_search_page_goodsinfos");
						p.setPageSize(pageSize);
						p.setCurrentPage(1);
						p.setTotalPage(p.getTotalPage());
						p.setOffsetRows(p.getOffsetRows());
					} else {
						p.setTotalRows(gid.getTotalRows(choices));
						p.setPageSize(pageSize);
						p.setCurrentPage(1);
						p.setTotalPage(p.getTotalPage());
					}
					gis = gid.selectAll(p.getOffsetRows(), p.getPageSize(),choices);
					p.setGis(gis);
					request.getSession().setAttribute("user_search_page_goodsinfos", p);
				} else if("current_page".equals(from)) {
					currentPage = Integer.parseInt(currentPage_str);
					if(request.getSession().getAttribute("user_search_page_goodsinfos") != null) {
						p = (Page) request.getSession().getAttribute("user_search_page_goodsinfos");
						p.setCurrentPage(currentPage);
						p.setOffsetRows(p.getOffsetRows());
					} else {
						p.setTotalRows(gid.getTotalRows(choices));
						p.setPageSize(2);
						p.setCurrentPage(currentPage);
						p.setTotalPage(p.getTotalPage());
						p.setOffsetRows(p.getOffsetRows());
					}
					gis = gid.selectAll(p.getOffsetRows(), p.getPageSize(),choices);
					p.setGis(gis);
					request.getSession().setAttribute("user_search_page_goodsinfos", p);
				}
			} else if(key_id == "" || key_id == null) {
				if(request.getSession().getAttribute("user_search_search_goodsinfo_choices_"+cat_id) != null) {
					choices = (GoodsSearchChoices) request.getSession().getAttribute("user_search_search_goodsinfo_choices_"+cat_id);
					choices.setGoods_name(search_key);
				}
				choices.setGoods_name(search_key);
				choices.setGoods_cat(cat_id);
				if("page_size".equals(from)) {
					pageSize = Integer.parseInt(pageSize_str);
					if(request.getSession().getAttribute("user_search_page_goodsinfos_"+cat_id) != null) {
						p = (Page) request.getSession().getAttribute("user_search_page_goodsinfos_"+cat_id);
						p.setPageSize(pageSize);
						p.setCurrentPage(1);
						p.setTotalPage(p.getTotalPage());
						p.setOffsetRows(p.getOffsetRows());
					} else {
						p.setTotalRows(gid.getTotalRows(choices));
						p.setPageSize(pageSize);
						p.setCurrentPage(1);
						p.setTotalPage(p.getTotalPage());
					}
					gis = gid.selectAll(p.getOffsetRows(), p.getPageSize(),choices);
					p.setGis(gis);
					request.getSession().setAttribute("user_search_page_goodsinfos_"+cat_id, p);
				} else if("current_page".equals(from)) {
					currentPage = Integer.parseInt(currentPage_str);
					if(request.getSession().getAttribute("user_search_page_goodsinfos_"+cat_id) != null) {
						p = (Page) request.getSession().getAttribute("user_search_page_goodsinfos_"+cat_id);
						p.setCurrentPage(currentPage);
						p.setOffsetRows(p.getOffsetRows());
					} else {
						p.setTotalRows(gid.getTotalRows(choices));
						p.setPageSize(2);
						p.setCurrentPage(currentPage);
						p.setTotalPage(p.getTotalPage());
						p.setOffsetRows(p.getOffsetRows());
					}
					gis = gid.selectAll(p.getOffsetRows(), p.getPageSize(),choices);
					p.setGis(gis);
					request.getSession().setAttribute("user_search_page_goodsinfos_"+cat_id, p);
				}
			} else {
				if(request.getSession().getAttribute("user_search_search_goodsinfo_choices_"+cat_id+"_"+key_id) != null) {
					choices = (GoodsSearchChoices) request.getSession().getAttribute("user_search_search_goodsinfo_choices_"+cat_id+"_"+key_id);
					choices.setGoods_name(search_key);
				}
				choices.setGoods_name(search_key);
				choices.setGoods_cat(cat_id);
				choices.setGoods_key(key_id);
				if(request.getSession().getAttribute("user_search_page_goodsinfos_"+cat_id+"_"+key_id) != null) {
					p = (Page) request.getSession().getAttribute("user_search_page_goodsinfos_"+cat_id+"_"+key_id);
				} 
				if("page_size".equals(from)) {
					pageSize = Integer.parseInt(pageSize_str);
					if(request.getSession().getAttribute("user_search_page_goodsinfos_"+cat_id+"_"+key_id) != null) {
						p = (Page) request.getSession().getAttribute("user_search_page_goodsinfos_"+cat_id+"_"+key_id);
						p.setPageSize(pageSize);
						p.setCurrentPage(1);
						p.setTotalPage(p.getTotalPage());
						p.setOffsetRows(p.getOffsetRows());
					} else {
						p.setTotalRows(gid.getTotalRows(choices));
						p.setPageSize(pageSize);
						p.setCurrentPage(1);
						p.setTotalPage(p.getTotalPage());
					}
					gis = gid.selectAll(p.getOffsetRows(), p.getPageSize(),choices);
					p.setGis(gis);
					request.getSession().setAttribute("user_search_page_goodsinfos_"+cat_id+"_"+key_id, p);
				} else if("current_page".equals(from)) {
					currentPage = Integer.parseInt(currentPage_str);
					if(request.getSession().getAttribute("user_search_page_goodsinfos_"+cat_id+"_"+key_id) != null) {
						p = (Page) request.getSession().getAttribute("user_search_page_goodsinfos_"+cat_id+"_"+key_id);
						p.setCurrentPage(currentPage);
						p.setOffsetRows(p.getOffsetRows());
					} else {
						p.setTotalRows(gid.getTotalRows(choices));
						p.setPageSize(2);
						p.setCurrentPage(currentPage);
						p.setTotalPage(p.getTotalPage());
						p.setOffsetRows(p.getOffsetRows());
					}
					gis = gid.selectAll(p.getOffsetRows(), p.getPageSize(),choices);
					p.setGis(gis);
					request.getSession().setAttribute("user_search_page_goodsinfos_"+cat_id+"_"+key_id, p);
				}
			}
		}
		String jsonString = JSON.toJSONString(p);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonString);
		//System.out.println(jsonString);
		
	}

}
