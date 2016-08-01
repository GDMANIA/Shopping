package com.shopping.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/user/updateUserOrder.do")
public class UpdateUserOrderFilter implements Filter{

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest resp, ServletResponse req,
			FilterChain chain) throws IOException, ServletException {
		chain.doFilter(resp, req);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}


}