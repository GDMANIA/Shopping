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
import com.shopping.dao.KeywordDao;
import com.shopping.daoimpl.CategoryDaoImpl;
import com.shopping.daoimpl.KeywordDaoImpl;
import com.shopping.entity.Category;
import com.shopping.entity.Keyword;

@WebServlet("/user/getCatsAndKeys.do")
public class GetCatsAndKeysServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		CategoryDao cd = new CategoryDaoImpl();
		KeywordDao kd = new KeywordDaoImpl();
		Category cat = null;
		Keyword key = new Keyword();
		List<Category> cats = cd.selectAll();
		List<Keyword> keys = null;
		for (int i = 0; i < cats.size(); i++) {
			keys = new ArrayList<Keyword>();
			cat = new Category();
			cat = cats.get(i);
			keys = kd.loadByCategory(cat.getCat_id());
			for (int j = 0; j < keys.size(); j++) {
				key = new Keyword();
				key = keys.get(j);
				key.setCat_name(cat.getCat_name());
				keys.set(j, key);
			}
			cat.setKeys(keys);
			cats.set(i, cat);
		}
		String jsonString = JSON.toJSONString(cats);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonString);
		System.out.println(jsonString);
	}

}
