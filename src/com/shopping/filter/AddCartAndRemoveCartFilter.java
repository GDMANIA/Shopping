package com.shopping.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.UserCartDao;
import com.shopping.dao.UserCollectionDao;
import com.shopping.daoimpl.UserCartDaoImpl;
import com.shopping.daoimpl.UserCollectionDaoImpl;
import com.shopping.entity.UserCart;
import com.shopping.entity.UserCollection;

@WebFilter("/user/addCartAndRemoveCart.do")
public class AddCartAndRemoveCartFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		chain.doFilter(req, resp);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}