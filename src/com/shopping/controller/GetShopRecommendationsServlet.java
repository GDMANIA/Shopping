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
import com.shopping.dao.FloorDao;
import com.shopping.dao.GoodsInfoDao;
import com.shopping.dao.ShopRecommendationDao;
import com.shopping.daoimpl.FloorDaoImpl;
import com.shopping.daoimpl.GoodsInfoDaoImpl;
import com.shopping.daoimpl.ShopRecommendationDaoImpl;
import com.shopping.entity.Floor;
import com.shopping.entity.GoodsInfo;
import com.shopping.entity.ShopRecommendation;

@WebServlet("/user/getShopRecommendations.do")
public class GetShopRecommendationsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String goods_id = request.getParameter("goods_id");
		ShopRecommendationDao srd = new ShopRecommendationDaoImpl();
		ShopRecommendation sr = new ShopRecommendation();
		
		List<ShopRecommendation> srs = new ArrayList<ShopRecommendation>();
		GoodsInfoDao gid = new GoodsInfoDaoImpl();
		GoodsInfo gi = new GoodsInfo();
		List<GoodsInfo> gis = new ArrayList<GoodsInfo>();
		if(goods_id == null) {
			srs = srd.load();
		} else {
			gi = gid.load(goods_id);
			srs = srd.loadByGoodsCat(gi.getGoods_cat(),gi.getGoods_key());
		}
		
		for (int i = 0; i < srs.size(); i++) {
			sr = new ShopRecommendation();
			sr = srs.get(i);
			gi = new GoodsInfo();
			gi = gid.load(sr.getGoods_id());
			gis.add(gi);
		}
		
		
		String jsonString = JSON.toJSONString(gis);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonString);
		//System.out.println(jsonString);
	}

}
