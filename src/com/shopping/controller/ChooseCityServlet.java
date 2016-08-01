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
import com.shopping.entity.City;

@WebServlet("/user/chooseCity.do")
public class ChooseCityServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String province_id = request.getParameter("province_id");
		List<City> cities = new ArrayList<City>();
		DistrictDao dd = new DistrictDaoImpl();
		cities = dd.selectCities(province_id);
		
		String jsonString = JSON.toJSONString(cities);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonString);
	}

}
