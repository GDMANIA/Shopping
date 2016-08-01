package com.shopping.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.shopping.dao.KeywordDao;
import com.shopping.daoimpl.KeywordDaoImpl;
import com.shopping.entity.Keyword;
/**
 * ������ʵ����ǰ̨�Ķ������������һ��������ˣ���ô��ajax�����������servlet��
 * �õ���Ӧ��һ�������µ����ж��������ٷ���ǰ̨
 */
@WebServlet("/admin/adminGetGoodsKeyByCat.do")
public class AdminGetGoodsKeyByCatServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String cat_id = request.getParameter("cat_id");
		List<Keyword> keys = null;
		KeywordDao kd = new KeywordDaoImpl();
		keys = kd.loadByCategory(Integer.parseInt(cat_id));
		String jsonString = JSON.toJSONString(keys);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonString);
		System.out.println(jsonString);
	}

}
