package com.shopping.filter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Pattern;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.UserDao;
import com.shopping.daoimpl.UserDaoImpl;
import com.shopping.entity.User;
import com.shopping.entity.UserAddress;

@WebFilter("/user/addAddressByAjax.do")
public class AddAddressByAjaxFilter implements Filter{

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		String user_name = request.getParameter("user_name");
		String consignee_name = request.getParameter("consignee_name");
		String consignee_telnum = request.getParameter("consignee_telnum");
		String consignee_province = request.getParameter("consignee_province");
		String consignee_city = request.getParameter("consignee_city");
		String consignee_area = request.getParameter("consignee_area");
		String consignee_address = request.getParameter("consignee_address");
		
		String consignee_name_test = "(^[\u4e00-\u9fa5]{2,6}$)";
		
		String consignee_telnum_test = "(^((13[0-9])|(15[^4,\\d])|(18[0,5-9]))\\d{8}$)";
		//这里以后再加一个匹配具体地址的正则表达式     String consignee_address_test = "(^[a-zA-Z0-9_]{6,16}$)";
		
		if(consignee_name == null || consignee_name == "") {
			response.getWriter().write("no_consignee_name");
			System.out.println("no_consignee_name");
			return;
		} else if(!Pattern.matches(consignee_name_test, consignee_name)) { 
			response.getWriter().write("invalid_consignee_name");
			return;
		} else if(consignee_telnum == null || consignee_telnum == "") {
			response.getWriter().write("no_consignee_telnum");
			return;
		} else if(!Pattern.matches(consignee_telnum_test, consignee_telnum)){
			response.getWriter().write("invalid_consignee_telnum");
			return;
		} else if("-请选择省/直辖市-".equals(consignee_province)){
			response.getWriter().write("no_consignee_province");
			return;
		} else if("-请选择市-".equals(consignee_city)) {
			response.getWriter().write("no_consignee_city");
			return;
		} else if("-请选择区/县-".equals(consignee_area)) {
			response.getWriter().write("no_consignee_area");
			return;
		} else if(consignee_address == null || consignee_address == ""){
			response.getWriter().write("no_consignee_address");
			return;
		} //else if(!Pattern.matches(consignee_address_test, consignee_address)){
			
				//}
		else {
			//System.out.println("加一个");
			UserDao ud = new UserDaoImpl();
			String user_id = ud.loadByName(user_name).getUser_id();
			UserAddress ua = new UserAddress(user_id, user_name, consignee_name, consignee_province, consignee_city, consignee_area, consignee_address, consignee_telnum, 0);
			request.setAttribute("useraddress", ua);
			chain.doFilter(request, response);
			return;
		} 
	}
	

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
