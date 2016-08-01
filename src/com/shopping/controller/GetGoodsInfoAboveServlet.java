package com.shopping.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.shopping.dao.GoodsInfoDao;
import com.shopping.daoimpl.GoodsInfoDaoImpl;
import com.shopping.entity.GoodsInfo;

@WebServlet("/user/getGoodsInfoAbove.do")
public class GetGoodsInfoAboveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String goods_id = request.getParameter("goods_id");
		GoodsInfoDao gid = new GoodsInfoDaoImpl();
		GoodsInfo gi = new GoodsInfo();
		gi = gid.load(goods_id);
		if(gi == null) {
			response.getWriter().write("no goods_id");
			return;
		} else {
			String jsonString = JSON.toJSONString(gi);
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(jsonString);
			return;
		}
	}

}
