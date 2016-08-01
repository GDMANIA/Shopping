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
 * �����Ǵ���ǰ̨������Ʒ״̬��ajax�����servlet����Ҫ���ж�ǰ̨�������Ĵ��������ʲô��
 * Ȼ���ٽ�����Ӧ�Ĳ������������ݿ�����Ʒ��״̬��Ȼ���������õ�page���󲢰󶨵�session���棬
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
		/*if("�޸�".equals(operation)) {
			gi = new GoodsInfo();
			gi = gid.loadRegardlessOfOnSite(goods_id);
			request.setAttribute("goodsinfo", gi);
			response.getWriter().write("modify");
			request.getRequestDispatcher("/admin/goods_modify.jsp").forward(request, response);
		} else */
		if("ɾ��".equals(operation)) {
			gid.delete(gid.load(goods_id));
			p.setTotalRows(gid.countAll(choices));
			if(request.getSession().getAttribute("page_goodsinfos") == null) {
				p.setPageSize(10);
				p.setCurrentPage(1);
			} 
			p.setTotalPage(p.getTotalPage());
			p.setOffsetRows(p.getOffsetRows());
			//������Ҫ����һ���жϣ������ɾ������Ʒ�����һҳ��Ψһ����Ʒ����ô��ҳ����һ��ͬʱ����ǰҳҲҪ��Ϊ��ҳ����һ�������һҳ��ֵ
			if(p.getCurrentPage() >= p.getTotalPage()) {
				p.setCurrentPage(p.getTotalPage());
			}
			gis = gid.loadAll(p.getOffsetRows(), p.getPageSize(),choices);
			p.setGis(gis);
			response.getWriter().write("success");
		} else if("�¼�".equals(operation)) {
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
		} else if("�ϼ�".equals(operation)) {
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
