package com.shopping.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.GoodsInfoDao;
import com.shopping.daoimpl.GoodsInfoDaoImpl;
import com.shopping.entity.GoodsInfo;

@WebServlet("/user/addClickNum.do")
public class AddGoodsClickNumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GoodsInfo gi = (GoodsInfo) request.getAttribute("goodsinfo");
		request.removeAttribute("goodsinfo");
		GoodsInfoDao gid = new GoodsInfoDaoImpl();
		gid.update(gi);
	}

}
