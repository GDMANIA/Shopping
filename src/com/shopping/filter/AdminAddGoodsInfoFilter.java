package com.shopping.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * �������һ����Ʒ��Ϣ�Ĺ�����������Ӧ��Ҫ�жϱ��ύ�������Ƿ�Ϊ��
 * �Լ��ж��Ƿ��¼����Ϣ�����ǻ�ûд���Ժ���Ҫ�ټ�
 */
@WebFilter("/admin/adminAddGoodsInfo.do")
public class AdminAddGoodsInfoFilter implements Filter{

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		chain.doFilter(req, resp);
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}

}
