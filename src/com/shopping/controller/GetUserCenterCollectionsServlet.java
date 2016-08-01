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
import com.shopping.dao.UserCollectionDao;
import com.shopping.daoimpl.GoodsInfoDaoImpl;
import com.shopping.daoimpl.UserCollectionDaoImpl;
import com.shopping.entity.GoodsInfo;
import com.shopping.entity.UserCollection;

@WebServlet("/user/getUserCenterCollections.do")
public class GetUserCenterCollectionsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_name = request.getParameter("user_name");
		UserCollection uc = new UserCollection();
		UserCollectionDao ucd = new UserCollectionDaoImpl();
		uc = ucd.load(user_name);
		System.out.println(uc.getGoods_id());
		String[] goods_ids = uc.getGoods_id().split(",");
		List<GoodsInfo> gis = new ArrayList<GoodsInfo>();
		GoodsInfo gi = null;
		GoodsInfoDao gid = new GoodsInfoDaoImpl();
		System.out.println(goods_ids.length);
		if(goods_ids.length >=4 ) {
			for (int i = 0; i < 4; i++) {
				gi = new GoodsInfo();
				gi = gid.load(goods_ids[i]);
				gis.add(gi);
			}
		} else {
			for (int i = 0; i < goods_ids.length; i++) {
				gi = new GoodsInfo();
				gi = gid.load(goods_ids[i]);
				gis.add(gi);
			}
		}
		String jsonString = JSON.toJSONString(gis);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonString);
		//System.out.println(jsonString);
	}

}
