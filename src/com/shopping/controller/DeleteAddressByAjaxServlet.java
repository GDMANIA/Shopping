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
import com.shopping.dao.UserAddressDao;
import com.shopping.daoimpl.UserAddressDaoImpl;
import com.shopping.entity.UserAddress;

@WebServlet("/user/deleteAddressByAjax.do")
public class DeleteAddressByAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userAddress_id = request.getParameter("userAddress_id");
		UserAddressDao uad = new UserAddressDaoImpl();
		UserAddress ua = new UserAddress();
		ua = uad.load(Integer.parseInt(userAddress_id));
		if(ua.getIsDefault_address() == 1) {
			response.getWriter().write("success_is_default");
		} else {
			response.getWriter().write("success_not_default");
		}
		uad.delete(Integer.parseInt(userAddress_id));
	}

}
