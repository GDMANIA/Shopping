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
import com.shopping.entity.Province;


@WebServlet("/user/chooseProvince.do")
public class ChooseProvinceServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		List<Province> provinces = new ArrayList<Province>();
		DistrictDao dd = new DistrictDaoImpl();
		provinces = dd.selectProvinces();

		String jsonString = JSON.toJSONString(provinces);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonString);
	}

}
