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
import com.shopping.dao.UserDao;
import com.shopping.dao.UserHistoryDao;
import com.shopping.daoimpl.FloorDaoImpl;
import com.shopping.daoimpl.GoodsInfoDaoImpl;
import com.shopping.daoimpl.ShopRecommendationDaoImpl;
import com.shopping.daoimpl.UserDaoImpl;
import com.shopping.daoimpl.UserHistoryDaoImpl;
import com.shopping.entity.Floor;
import com.shopping.entity.GoodsInfo;
import com.shopping.entity.ShopRecommendation;
import com.shopping.entity.User;
import com.shopping.entity.UserHistory;

@WebServlet("/user/getUserHistory.do")
public class GetUserHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_name = request.getParameter("user_name");
		UserHistoryDao uhd = new UserHistoryDaoImpl();
		UserHistory uh = new UserHistory();
		GoodsInfoDao gid = new GoodsInfoDaoImpl();
		GoodsInfo gi = new GoodsInfo();
		List<GoodsInfo> goodsinfos = new ArrayList<GoodsInfo>();
		UserDao ud = new UserDaoImpl();
		User user = new User();
		user = ud.loadByName(user_name);
		uh = uhd.load(user.getUser_id());
		if(uh.getGoods_ids() == "" || uh.getGoods_ids() == null) {
			response.getWriter().write("no_history");
		} else {
			String[] goods_ids = uh.getGoods_ids().split(",");
			for (int i = 0; i < goods_ids.length; i++) {
				gi = new GoodsInfo();
				gi = gid.load(goods_ids[i]);
				goodsinfos.add(gi);
			}
			String jsonString = JSON.toJSONString(goodsinfos);
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(jsonString);
			System.out.println(jsonString);
		}
		
	}

}
