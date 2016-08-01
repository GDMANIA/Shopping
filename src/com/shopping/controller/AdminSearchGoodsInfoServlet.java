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
import com.shopping.daoimpl.CategoryDaoImpl;
import com.shopping.daoimpl.GoodsInfoDaoImpl;
import com.shopping.daoimpl.KeywordDaoImpl;
import com.shopping.daoimpl.UserDaoImpl;
import com.shopping.entity.Category;
import com.shopping.entity.GoodsInfo;
import com.shopping.entity.GoodsSearchChoices;
import com.shopping.entity.Keyword;
import com.shopping.entity.Page;
import com.shopping.entity.User;
import com.shopping.entity.UserSearchChoices;
/**
 * 这是前台表单提交过来用于搜索商品信息的servlet处理类，
 * 这里要分别拿到前台提交过来的所有数据，然后重新生成一个搜索choices类，并绑定在session里面，
 * 然后也要重新设置相应分页对象的值，pagesize不用重新设
 * 最后用重定向的方式转回前台
 */
@WebServlet("/admin/adminSearchGoodsInfo.do")
public class AdminSearchGoodsInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		String goods_name = request.getParameter("goods_name");
		String goods_info = request.getParameter("goods_info");
		String goods_cat = request.getParameter("goods_cat");
		String goods_key = request.getParameter("goods_key");
		String goods_price_below = request.getParameter("goods_price_below");
		String goods_price_above = request.getParameter("goods_price_above");
		String goods_ttnum_below = request.getParameter("goods_ttnum_below");
		String goods_ttnum_above = request.getParameter("goods_ttnum_above");
		String goods_soldnum_below = request.getParameter("goods_soldnum_below");
		String goods_soldnum_above = request.getParameter("goods_soldnum_above");
		String goods_commentnum_below = request.getParameter("goods_commentnum_below");
		String goods_commentnum_above = request.getParameter("goods_commentnum_above");
		String goods_isonsite = request.getParameter("goods_isonsite");
		boolean flag_goods_cat = false;
		boolean flag_goods_key = false;
		System.out.println(goods_name);
		System.out.println(goods_info);
		System.out.println(goods_cat);
		System.out.println(goods_key);
		System.out.println(goods_price_below);
		System.out.println(goods_price_above);
		System.out.println(goods_ttnum_below);
		System.out.println(goods_ttnum_above);
		System.out.println(goods_soldnum_below);
		System.out.println(goods_soldnum_above);
		System.out.println(goods_commentnum_below);
		System.out.println(goods_commentnum_above);
		System.out.println(goods_isonsite);
		CategoryDao cd = new CategoryDaoImpl();
		KeywordDao kd = new KeywordDaoImpl();
		Category cat = null;
		Keyword key = new Keyword();
		List<Category> cats = cd.selectAll();
		List<Keyword> keys = kd.loadAll();
		
		if(goods_cat !=null && goods_cat != "") {
			for (int i = 0; i < cats.size(); i++) {
				if(goods_cat.equals(cats.get(i).getCat_name())) {
					goods_cat = String.valueOf(cats.get(i).getCat_id());
					flag_goods_cat = true;
					break;
				}
			}
			if(flag_goods_cat == false) {
				goods_cat = "";
			}
		}
		if(goods_key !=null && goods_key != "") {
			for (int i = 0; i < keys.size(); i++) {
				if(goods_key.equals(keys.get(i).getKey_name())) {
					goods_key = String.valueOf(keys.get(i).getKey_id());
					flag_goods_key = true;
					break;
				}
			}
			if(flag_goods_key == false) {
				goods_key = "";
			}
		}
		//因为前台的下拉选择条可能会传过来这两个默认值，所以要进行判断
		if(goods_isonsite.equals("请选择")) {
			goods_isonsite = "";
		} else if(goods_isonsite.equals("已上架")) {
			goods_isonsite = "1";
		} else if(goods_isonsite.equals("未上架")) {
			goods_isonsite = "0";
		}
		GoodsSearchChoices choices = new GoodsSearchChoices(goods_name,goods_info, goods_cat, goods_key, 
															goods_price_below, goods_price_above, 
															goods_ttnum_below, goods_ttnum_above, 
															goods_soldnum_below, goods_soldnum_above, 
															goods_commentnum_below, goods_commentnum_above, 
															goods_isonsite);
		Page p = new Page();
		if(request.getSession().getAttribute("page_goodsinfos") != null) {
			p = (Page) request.getSession().getAttribute("page_goodsinfos");
			p.setPageSize(p.getPageSize());
		} else {
			p.setPageSize(10);
		}
		GoodsInfoDao gid = new GoodsInfoDaoImpl();
		GoodsInfo gi = new GoodsInfo();
		List<GoodsInfo> gis = new ArrayList<GoodsInfo>();
		p.setTotalRows(gid.countAll(choices));
		p.setCurrentPage(1);
		p.setTotalPage(p.getTotalPage());
		gis = gid.loadAll(p.getOffsetRows(), p.getPageSize(),choices);
		p.setGis(gis);
		
		request.getSession().setAttribute("search_goodsinfo_choices", choices);
		request.getSession().setAttribute("page_goodsinfos", p);
		response.sendRedirect("/shopping/admin/goods.jsp");
		
	}

}
