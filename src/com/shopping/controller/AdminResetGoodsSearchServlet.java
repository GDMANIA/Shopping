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
/**
 * 这里是处理前台清空按钮请求的servlet，主要就是实现了将session里面的搜索类对象重新初始化，然后将page对象
 * 重置到第一页，但是pagesize不变，然后再重定向到原页面
 */
@WebServlet("/admin/adminResetGoodsSearchChoice.do")
public class AdminResetGoodsSearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GoodsSearchChoices choices = new GoodsSearchChoices();
		Page p = new Page();
		if(request.getSession().getAttribute("page_goodsinfos") != null) {
			p = (Page) request.getSession().getAttribute("page_goodsinfos");
			p.setPageSize(p.getPageSize());
		} else {
			p.setPageSize(10);
		}
		GoodsInfoDao gid = new GoodsInfoDaoImpl();
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
