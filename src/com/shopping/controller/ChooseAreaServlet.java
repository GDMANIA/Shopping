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
import com.shopping.dao.DistrictDao;
import com.shopping.daoimpl.DistrictDaoImpl;
import com.shopping.entity.Area;

@WebServlet("/user/chooseArea.do")
public class ChooseAreaServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String city_id = request.getParameter("city_id");
		List<Area> areas = new ArrayList<Area>();
		DistrictDao dd = new DistrictDaoImpl();
		areas = dd.selectAreas(city_id);
		
		String jsonString = JSON.toJSONString(areas);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonString);
	}

}
