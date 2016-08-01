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
 * 这是二级分类页面用于处理分页ajax请求的servlet，前台ajax会传过来两个值，
 * 以此来判断改变页面大小还是当前页，然后分别处理这两个请求，
 * 生成新的对象并绑定到session里面取代原来的对象
 */
@WebServlet("/admin/adminKeywordsUpdatePages.do")
public class AdminKeywordsUpdatePagesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String pageSize_str = request.getParameter("pageSize_str");
		String currentPage_str = request.getParameter("currentPage_str");
		int pageSize = 0;
		int currentPage = 0;
		String from = request.getParameter("from");
		Page p = new Page();
		KeywordDao kd = new KeywordDaoImpl();
		Keyword key = new Keyword();
		List<Keyword> keys = null;
		//同样这里也要判断原先page对象存不存在
		if(request.getSession().getAttribute("page_keys") != null) {
			p = (Page) request.getSession().getAttribute("page_keys");
		}
		//按理说在进入这个servlet的时候，page对象应该就是存在的，因为在前台jsp的时候初始化了，但是为了避免不必要的错误，
		//还是在这里判断一下，如果page对象不存在的话，就初始化一下
		if("page_size".equals(from)) {
			pageSize = Integer.parseInt(pageSize_str);
			if(request.getSession().getAttribute("page_keys") != null) {
				p.setPageSize(pageSize);
				p.setCurrentPage(1);
				p.setTotalPage(p.getTotalPage());
				p.setOffsetRows(p.getOffsetRows());
			} else {
				p.setTotalRows(kd.getTotalRows());
				p.setPageSize(pageSize);
				p.setCurrentPage(1);
				p.setTotalPage(p.getTotalPage());
				p.setOffsetRows(p.getOffsetRows());
			}
			keys = kd.loadAll(p.getOffsetRows(), p.getPageSize());
			p.setKeys(keys);
			
		} else if("current_page".equals(from)) {
			currentPage = Integer.parseInt(currentPage_str);
			if(request.getSession().getAttribute("page_keys") != null) {
				p.setCurrentPage(currentPage);
				p.setOffsetRows(p.getOffsetRows());
			} else {
				p.setTotalRows(kd.getTotalRows());
				p.setPageSize(2);
				p.setCurrentPage(currentPage);
				p.setTotalPage(p.getTotalPage());
				p.setOffsetRows(p.getOffsetRows());
			}
			keys = kd.loadAll(p.getOffsetRows(), p.getPageSize());
			p.setKeys(keys);
		}
		
		request.getSession().setAttribute("page_keys", p);
	}

}
