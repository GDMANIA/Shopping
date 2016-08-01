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
import com.shopping.daoimpl.CategoryDaoImpl;
import com.shopping.daoimpl.GoodsInfoDaoImpl;
import com.shopping.daoimpl.KeywordDaoImpl;
import com.shopping.entity.Category;
import com.shopping.entity.GoodsInfo;
import com.shopping.entity.GoodsSearchChoices;
import com.shopping.entity.Keyword;
import com.shopping.entity.Page;

/**
 * 这里是处理前台更改商品状态的ajax请求的servlet，主要是判断前台传过来的处理参数是什么，
 * 然后再进行相应的操作，更改数据库中商品的状态，然后再重新拿到page对象并绑定到session里面，
 *
 */
@WebServlet("/admin/adminUpdateGoodsState.do")
public class AdminUpdateGoodsStateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		String goods_id = request.getParameter("goods_id").split("_")[2];
		String operation = request.getParameter("operation");
		GoodsSearchChoices choices = new GoodsSearchChoices();
		Page p = new Page();
		GoodsInfoDao gid = new GoodsInfoDaoImpl();
		GoodsInfo gi = new GoodsInfo();
		List<GoodsInfo> gis = new ArrayList<GoodsInfo>();
		CategoryDao cd = new CategoryDaoImpl();
		KeywordDao kd = new KeywordDaoImpl();
		Category cat = null;
		Keyword key = new Keyword();
		List<Category> cats = cd.selectAll();
		List<Keyword> keys = kd.loadAll();
		/*这里同前台一样，先判断一下搜索商品信息的类在session里面存在与否，如果不存在就初始化一下，
		同时这里需要判断一下搜索类里面存的主分类和二级分类是数字还id是中文名，如果是数字
		需要转化为中文名，因为前台输出的要求是中文名*/
		if(request.getSession().getAttribute("search_goodsinfo_choices") != null) {
			choices = (GoodsSearchChoices) request.getSession().getAttribute("search_goodsinfo_choices");
			String goods_cat = choices.getGoods_cat();
			String goods_key = choices.getGoods_key();
			System.out.println(goods_cat);
			System.out.println(goods_key);
			boolean flag_goods_cat = false;
			boolean flag_goods_key = false;
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
				choices.setGoods_cat(goods_cat);
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
				choices.setGoods_key(goods_key);
			}
		}
		if(request.getSession().getAttribute("page_goodsinfos") != null) {
			p = (Page) request.getSession().getAttribute("page_goodsinfos");
		}
		/*if("修改".equals(operation)) {
			gi = new GoodsInfo();
			gi = gid.loadRegardlessOfOnSite(goods_id);
			request.setAttribute("goodsinfo", gi);
			response.getWriter().write("modify");
			request.getRequestDispatcher("/admin/goods_modify.jsp").forward(request, response);
		} else */
		if("删除".equals(operation)) {
			gid.delete(gid.load(goods_id));
			p.setTotalRows(gid.countAll(choices));
			if(request.getSession().getAttribute("page_goodsinfos") == null) {
				p.setPageSize(10);
				p.setCurrentPage(1);
			} 
			p.setTotalPage(p.getTotalPage());
			p.setOffsetRows(p.getOffsetRows());
			//这里需要进行一个判断，如果所删除的商品是最后一页的唯一的商品，那么总页数减一的同时，当前页也要变为总页数减一，即最后一页的值
			if(p.getCurrentPage() >= p.getTotalPage()) {
				p.setCurrentPage(p.getTotalPage());
			}
			gis = gid.loadAll(p.getOffsetRows(), p.getPageSize(),choices);
			p.setGis(gis);
			response.getWriter().write("success");
		} else if("下架".equals(operation)) {
			gi = new GoodsInfo();
			gi = gid.loadRegardlessOfOnSite(goods_id);
			gi.setGoods_isonsite(0);
			gid.update(gi);
			p.setTotalRows(gid.countAll(choices));
			if(request.getSession().getAttribute("page_goodsinfos") == null) {
				p.setPageSize(10);
				p.setCurrentPage(1);
			} 
			p.setTotalPage(p.getTotalPage());
			p.setOffsetRows(p.getOffsetRows());
			gis = gid.loadAll(p.getOffsetRows(), p.getPageSize(),choices);
			p.setGis(gis);
			response.getWriter().write("success");
		} else if("上架".equals(operation)) {
			gi = new GoodsInfo();
			gi = gid.loadRegardlessOfOnSite(goods_id);
			gi.setGoods_isonsite(1);
			gid.update(gi);
			p.setTotalRows(gid.countAll(choices));
			if(request.getSession().getAttribute("page_goodsinfos") == null) {
				p.setPageSize(10);
				p.setCurrentPage(1);
			} 
			p.setTotalPage(p.getTotalPage());
			p.setOffsetRows(p.getOffsetRows());
			gis = gid.loadAll(p.getOffsetRows(), p.getPageSize(),choices);
			p.setGis(gis);
			response.getWriter().write("success");
		}
		request.getSession().setAttribute("search_goodsinfo_choices", choices);
		request.getSession().setAttribute("page_goodsinfos", p);
	}

}
