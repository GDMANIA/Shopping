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
import com.shopping.dao.GoodsInfoDao;
import com.shopping.dao.UserCartDao;
import com.shopping.daoimpl.GoodsInfoDaoImpl;
import com.shopping.daoimpl.UserCartDaoImpl;
import com.shopping.entity.GoodsInfo;
import com.shopping.entity.UserCart;

@WebServlet("/user/getUserCart.do")
public class GetUserCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_name = request.getParameter("user_name");
		String from = request.getParameter("from");
		System.out.println(from);
		UserCartDao ucd = new UserCartDaoImpl();
		UserCart uc = new UserCart();
		List<UserCart> ucs = new ArrayList<UserCart>();
		ucs = ucd.load(user_name);
		List<String> goods_ids = new ArrayList<String>();
		GoodsInfoDao gid = new GoodsInfoDaoImpl();
		GoodsInfo gi = new GoodsInfo();
		String jsonString = "";
		if("index".equals(from)) {
			for (int i = 0; i < ucs.size(); i++) {
				goods_ids.add(ucs.get(i).getGoods_id());
			}
			jsonString = JSON.toJSONString(goods_ids);
			response.getWriter().write(jsonString);
			System.out.println(jsonString);
			return;
		} else if("cart".equals(from)){
			for (int j = 0; j < ucs.size(); j++) {
				uc = new UserCart();
				uc = ucs.get(j);
				gi = new GoodsInfo();
				gi = gid.load(uc.getGoods_id());
				uc.setGoods_img(gi.getGoods_img().split(",")[0]);
				uc.setGoods_name(gi.getGoods_name());
				uc.setGoods_curprice(gi.getGoods_curprice());
				uc.setGoods_ttnum(gi.getGoods_ttnum());
				ucs.set(j, uc);
			}
			jsonString = JSON.toJSONString(ucs);
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(jsonString);
			System.out.println(jsonString);
			return;
		}
	}

}
