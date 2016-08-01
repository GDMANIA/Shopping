package com.shopping.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.UserAddressDao;
import com.shopping.dao.UserDao;
import com.shopping.daoimpl.UserAddressDaoImpl;
import com.shopping.daoimpl.UserDaoImpl;
import com.shopping.entity.User;
import com.shopping.entity.UserAddress;

@WebServlet("/user/addAddressByAjax.do")
public class AddAddressByAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserAddress ua = (UserAddress) request.getAttribute("useraddress");
		request.removeAttribute("useraddress");
		UserAddressDao uad = new UserAddressDaoImpl();
		uad.add(ua);
		int useraddress_id = uad.loadId(ua);
		response.getWriter().write("useraddress_id_"+String.valueOf(useraddress_id));
		//System.out.println("servlet");
	}

}
