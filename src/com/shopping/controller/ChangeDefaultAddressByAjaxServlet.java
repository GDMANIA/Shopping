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

@WebServlet("/user/changeDefaultAddressByAjax.do")
public class ChangeDefaultAddressByAjaxServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String new_default_useraddress_id = request.getParameter("new_default_useraddress_id");
		String old_default_useraddress_id = request.getParameter("old_default_useraddress_id");
		System.out.println(new_default_useraddress_id);
		System.out.println(old_default_useraddress_id);
		UserAddressDao uad = new UserAddressDaoImpl();
		UserAddress ua_new_default = new UserAddress();
		if(old_default_useraddress_id == null) {
			ua_new_default = uad.load(Integer.parseInt(new_default_useraddress_id));
			ua_new_default.setIsDefault_address(1);
			uad.update(ua_new_default);
		} else {
			UserAddress ua_old_default = new UserAddress();
			ua_new_default = uad.load(Integer.parseInt(new_default_useraddress_id));
			ua_old_default = uad.load(Integer.parseInt(old_default_useraddress_id));
			ua_new_default.setIsDefault_address(1);
			ua_old_default.setIsDefault_address(0);
			uad.update(ua_new_default);
			uad.update(ua_old_default);
		}
		response.getWriter().write("success");
	}

}
