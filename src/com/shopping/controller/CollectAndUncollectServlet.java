package com.shopping.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.UserCollectionDao;
import com.shopping.daoimpl.UserCollectionDaoImpl;
import com.shopping.entity.UserCollection;

@WebServlet("/user/collectAndUncollect.do")
public class CollectAndUncollectServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_name = (String) request.getAttribute("user_name");
		String goods_id = (String) request.getAttribute("goods_id");
		request.removeAttribute("user_name");
		request.removeAttribute("goods_id");
		System.out.println(goods_id);
		UserCollectionDao ucd = new UserCollectionDaoImpl();
		UserCollection uc = new UserCollection();
		uc = ucd.load(user_name);
		uc.setGoods_id(goods_id);
		ucd.update(uc);
	}

}
