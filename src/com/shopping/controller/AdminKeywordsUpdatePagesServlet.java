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
 * ���Ƕ�������ҳ�����ڴ����ҳajax�����servlet��ǰ̨ajax�ᴫ��������ֵ��
 * �Դ����жϸı�ҳ���С���ǵ�ǰҳ��Ȼ��ֱ�������������
 * �����µĶ��󲢰󶨵�session����ȡ��ԭ���Ķ���
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
		//ͬ������ҲҪ�ж�ԭ��page����治����
		if(request.getSession().getAttribute("page_keys") != null) {
			p = (Page) request.getSession().getAttribute("page_keys");
		}
		//����˵�ڽ������servlet��ʱ��page����Ӧ�þ��Ǵ��ڵģ���Ϊ��ǰ̨jsp��ʱ���ʼ���ˣ�����Ϊ�˱��ⲻ��Ҫ�Ĵ���
		//�����������ж�һ�£����page���󲻴��ڵĻ����ͳ�ʼ��һ��
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
