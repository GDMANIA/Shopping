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
import com.shopping.daoimpl.GoodsInfoDaoImpl;
import com.shopping.entity.GoodsInfo;
import com.shopping.entity.GoodsSearchChoices;
import com.shopping.entity.Page;

@WebServlet("/user/getGoodsChoices.do")
public class GetGoodsChoicesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String search_key = request.getParameter("search_key");
		String cat_id = request.getParameter("cat_id");
		String key_id = request.getParameter("key_id");
		GoodsSearchChoices choices = new GoodsSearchChoices();
		response.setCharacterEncoding("UTF-8");
		if(search_key == "" || search_key == null) {
			if(cat_id == "" || cat_id == null || "no_cat_id".equals(cat_id)) {
				choices = (GoodsSearchChoices) request.getSession().getAttribute("user_search_goodsinfo_choices");
			} else if(key_id == "" || key_id == null) {
				choices = (GoodsSearchChoices) request.getSession().getAttribute("user_search_goodsinfo_choices_"+cat_id);
			} else {
				choices = (GoodsSearchChoices) request.getSession().getAttribute("user_search_goodsinfo_choices_"+cat_id+"_"+key_id);
			}
		} else {
			if(cat_id == "" || cat_id == null || "no_cat_id".equals(cat_id)) {
				if(request.getSession().getAttribute("user_search_search_goodsinfo_choices") != null) {
					choices = (GoodsSearchChoices) request.getSession().getAttribute("user_search_search_goodsinfo_choices");
				}
				choices.setGoods_name(search_key);
			} else if(key_id == "" || key_id == null) {
				if(request.getSession().getAttribute("user_search_search_goodsinfo_choices_"+cat_id) != null) {
					choices = (GoodsSearchChoices) request.getSession().getAttribute("user_search_search_goodsinfo_choices_"+cat_id);
				}
				choices.setGoods_name(search_key);
			} else {
				if(request.getSession().getAttribute("user_search_search_goodsinfo_choices_"+cat_id+"_"+key_id) != null) {
					choices = (GoodsSearchChoices) request.getSession().getAttribute("user_search_search_goodsinfo_choices_"+cat_id+"_"+key_id);
				}
				choices.setGoods_name(search_key);
			}
		}
		
//		choices.setOrder_condition("goods_soldnum");
//		choices.setOrder_direction("asc");
		String jsonString = JSON.toJSONString(choices);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonString);
		System.out.println("<<<<");
		System.out.println(jsonString);
		
	}

}
