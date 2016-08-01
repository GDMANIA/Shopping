package com.shopping.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.shopping.dao.UserCollectionDao;
import com.shopping.daoimpl.UserCollectionDaoImpl;
import com.shopping.entity.UserCollection;

@WebServlet("/user/getUserCollection.do")
public class GetUserCollectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_name = request.getParameter("user_name");
		UserCollection uc = new UserCollection();
		UserCollectionDao ucd = new UserCollectionDaoImpl();
		uc = ucd.load(user_name);
		if(uc != null) {
			String jsonString = JSON.toJSONString(uc.getGoods_id());
			response.getWriter().write(jsonString);
		} else {
			response.getWriter().write("no_user_collection");
			
		}
	}

}
