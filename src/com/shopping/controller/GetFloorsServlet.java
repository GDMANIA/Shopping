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
import com.shopping.daoimpl.FloorDaoImpl;
import com.shopping.daoimpl.GoodsInfoDaoImpl;
import com.shopping.entity.Floor;
import com.shopping.entity.GoodsInfo;

@WebServlet("/user/getFloors.do")
public class GetFloorsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		FloorDao fd = new FloorDaoImpl();
		GoodsInfoDao gid = new GoodsInfoDaoImpl();
		Floor fr = null;
		GoodsInfo gi = null;
		List<Floor> frs = new ArrayList<Floor>();
		List<GoodsInfo> gis = null;
		frs = fd.selectAll();
		for (int i = 0; i < frs.size(); i++) {
			fr = new Floor();
			fr = frs.get(i);
			String[] goods_ids = fr.getGoods_id().split(",");
			gis = new ArrayList<GoodsInfo>();
			for (int j = 0; j < goods_ids.length; j++) {
				gi = new GoodsInfo();
				if(gid.load(goods_ids[j]) != null) {
					gi = gid.load(goods_ids[j]);
					gis.add(gi);
				}
			}
			fr.setGoodsinfos(gis);
			frs.set(i, fr);
		}
		String jsonString = JSON.toJSONString(frs);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonString);
		System.out.println(jsonString);
	}

}
