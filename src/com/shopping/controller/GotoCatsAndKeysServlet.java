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
import com.shopping.daoimpl.GoodsInfoDaoImpl;
import com.shopping.entity.GoodsInfo;
import com.shopping.entity.GoodsSearchChoices;
import com.shopping.entity.Page;

@WebServlet("/user/gotoCatsAndKeys.do")
public class GotoCatsAndKeysServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search_key_original = request.getParameter("search_key_original");
		String search_key = request.getParameter("search_key");
		String cat_id = request.getParameter("cat_id");
		String key_id = request.getParameter("key_id");
		Page p = new Page();
		GoodsSearchChoices choices = new GoodsSearchChoices();
		GoodsInfoDao gid = new GoodsInfoDaoImpl();
//		GoodsInfo gi = new GoodsInfo();
		List<GoodsInfo> gis = new ArrayList<GoodsInfo>();
//		List<GoodsInfo> gis_original = new ArrayList<GoodsInfo>();
//		String goods_cat_id = "";
//		String goods_key_id = "";
		if(cat_id == "" || cat_id == null || "no_cat_id".equals(cat_id)) {
			
		} else if(key_id == "" || key_id == null) {
			choices.setGoods_cat(cat_id);
		} else {
			choices.setGoods_cat(cat_id);
			choices.setGoods_key(key_id);
		}
		if(search_key != "" && search_key != null) {
			choices.setGoods_name(search_key);
		}
		//System.out.println("search_key>>>"+search_key);
		//System.out.println("search_key_original>>>"+ search_key_original);
		//System.out.println("search_key_original>>>"+ new String(search_key_original.getBytes("GBK"),"iso-8859-1"));
		int totalRows = gid.getTotalRows(choices);
		p.setTotalRows(totalRows);
		p.setPageSize(2);
		p.setTotalPage(p.getTotalPage());
		p.setCurrentPage(1);
		p.setOffsetRows(p.getOffsetRows());
		gis = gid.selectAll(p.getOffsetRows(), p.getPageSize(),choices);
		p.setGis(gis);
		response.setCharacterEncoding("UTF-8");
		if(cat_id == "" || cat_id == null || "no_cat_id".equals(cat_id)) {
			
			//System.out.println(">>>>");
			//System.out.println(request.getSession().getAttribute("user_search_goodsinfo_choices"));
			if(search_key != "" && search_key != null) {
				request.getSession().setAttribute("user_search_search_goodsinfo_choices", choices);
				request.getSession().setAttribute("user_search_page_goodsinfos", p);
				response.sendRedirect("/shopping/user/page_goods/list_search.html?search_key="+search_key_original);
			} else {
				request.getSession().setAttribute("user_search_goodsinfo_choices", choices);
				request.getSession().setAttribute("user_page_goodsinfos", p);
				response.sendRedirect("/shopping/user/page_goods/list_categray.html");
			}
		} else if(key_id == "" || key_id == null) {
			
			//System.out.println(">>>>");
			//System.out.println(request.getSession().getAttribute("user_search_goodsinfo_choices_"+cat_id));
			if(search_key != "" && search_key != null) {
				request.getSession().setAttribute("user_search_search_goodsinfo_choices_"+cat_id, choices);
				request.getSession().setAttribute("user_search_page_goodsinfos_"+cat_id, p);
				response.sendRedirect("/shopping/user/page_goods/list_search.html?search_key="+search_key_original+"&cat_id="+cat_id);
			} else {
				request.getSession().setAttribute("user_search_goodsinfo_choices_"+cat_id, choices);
				request.getSession().setAttribute("user_page_goodsinfos_"+cat_id, p);
				response.sendRedirect("/shopping/user/page_goods/list_categray.html?cat_id="+cat_id);
			}
		} else {
			
			if(search_key != "" && search_key != null) {
				request.getSession().setAttribute("user_search_search_goodsinfo_choices_"+cat_id+"_"+key_id, choices);
				request.getSession().setAttribute("user_search_page_goodsinfos_"+cat_id+"_"+key_id, p);
				response.sendRedirect("/shopping/user/page_goods/list_search.html?search_key="+search_key_original+"&cat_id="+cat_id+"&key_id="+key_id);
			} else {
				request.getSession().setAttribute("user_search_goodsinfo_choices_"+cat_id+"_"+key_id, choices);
				request.getSession().setAttribute("user_page_goodsinfos_"+cat_id+"_"+key_id, p);
				response.sendRedirect("/shopping/user/page_goods/list_categray.html?cat_id="+cat_id+"&key_id="+key_id);
			}
		}
		
	}

}
