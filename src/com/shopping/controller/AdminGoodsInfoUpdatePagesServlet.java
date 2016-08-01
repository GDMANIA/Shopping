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
 * �����Ǵ���ǰ̨��ҳ�����servlet����Ҫ���ж��Ǹı�pagesize����currentpage��
 * Ȼ����ִ����Ӧ�� ����������Page����Ȼ�������õ������ٰ󶨵�session
 */
@WebServlet("/admin/adminGoodsInfoUpdatePages.do")
public class AdminGoodsInfoUpdatePagesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
		/*����ͬǰ̨һ�������ж�һ��������Ʒ��Ϣ������session������������������ھͳ�ʼ��һ�£�
		ͬʱ������Ҫ�ж�һ��������������������Ͷ������������ֻ�id�������������������
		��Ҫת��Ϊ����������Ϊǰ̨�����Ҫ����������*/
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
		if("page_size".equals(from)) {
			pageSize = Integer.parseInt(pageSize_str);
			if(request.getSession().getAttribute("page_goodsinfos") != null) {
				p.setPageSize(pageSize);
				p.setCurrentPage(1);
				p.setTotalPage(p.getTotalPage());
				p.setOffsetRows(p.getOffsetRows());
			} else {
				p.setTotalRows(gid.countAll(choices));
				p.setPageSize(pageSize);
				p.setCurrentPage(1);
				p.setTotalPage(p.getTotalPage());
			}
			gis = gid.loadAll(p.getOffsetRows(), p.getPageSize(),choices);
			p.setGis(gis);
			
		} else if("current_page".equals(from)) {
			currentPage = Integer.parseInt(currentPage_str);
			if(request.getSession().getAttribute("page_goodsinfos") != null) {
				p.setCurrentPage(currentPage);
				p.setOffsetRows(p.getOffsetRows());
			} else {
				p.setTotalRows(gid.countAll(choices));
				p.setPageSize(10);
				p.setCurrentPage(currentPage);
				p.setTotalPage(p.getTotalPage());
				p.setOffsetRows(p.getOffsetRows());
			}
			gis = gid.loadAll(p.getOffsetRows(), p.getPageSize(),choices);
			p.setGis(gis);
		}
		
		request.getSession().setAttribute("search_goodsinfo_choices", choices);
		request.getSession().setAttribute("page_goodsinfos", p);
	}

}
