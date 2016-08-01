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

import com.shopping.dao.UserCollectionDao;
import com.shopping.dao.UserDao;
import com.shopping.daoimpl.UserCollectionDaoImpl;
import com.shopping.daoimpl.UserDaoImpl;
import com.shopping.entity.UserCollection;

@WebFilter("/user/collectAndUncollect.do")
public class CollectAndUncollectFilter implements Filter {

	@Override
	public void destroy() {
		// TODO Auto-generated method stub

	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse resp,
			FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest)req;
		HttpServletResponse response = (HttpServletResponse)resp;
		String flag = request.getParameter("flag");
		String goods_id = request.getParameter("goods_id");
		String user_name = request.getParameter("user_name");
		String user_id = "";
		UserCollectionDao ucd = new UserCollectionDaoImpl();
		UserCollection uc = new UserCollection();
		UserDao ud = new UserDaoImpl();
		uc = ucd.load(user_name);
		String new_goods_ids = "";
		String old_goods_ids = "";
		if(uc == null) {
			uc = new UserCollection();
			new_goods_ids = goods_id + ",";
			user_id = ud.loadByName(user_name).getUser_id();
			uc.setUser_id(user_id);
			uc.setUser_name(user_name);
			uc.setGoods_id(new_goods_ids);
			ucd.add(uc);
		} else {
			old_goods_ids = uc.getGoods_id();
			//System.out.println(flag);
			if(flag.equals("collect_true")) {
				String[] goods_ids = old_goods_ids.split(",");
				for (int i = 0; i < goods_ids.length; i++) {
					if(!goods_ids[i].equals(goods_id)) {
						new_goods_ids = new_goods_ids +goods_ids[i]+",";
					}
				}
				response.getWriter().write("uncollect");
			} else {
				new_goods_ids = old_goods_ids + goods_id + ",";
				response.getWriter().write("collect");
			}
		}
			//System.out.println(new_goods_ids);
			request.setAttribute("user_name", user_name);
			request.setAttribute("goods_id", new_goods_ids);
			chain.doFilter(request, response);
		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
		// TODO Auto-generated method stub

	}

}
