package com.shopping.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.GoodsInfoDao;
import com.shopping.dao.UserCartDao;
import com.shopping.dao.UserDao;
import com.shopping.daoimpl.GoodsInfoDaoImpl;
import com.shopping.daoimpl.UserCartDaoImpl;
import com.shopping.daoimpl.UserDaoImpl;
import com.shopping.entity.GoodsInfo;
import com.shopping.entity.UserCart;

@WebServlet("/user/changeUserCart.do")
public class ChangeUserCartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String user_name = request.getParameter("user_name");
		String from = request.getParameter("from");
		String goods_id = request.getParameter("goods_id");
		String goods_num = request.getParameter("goods_num");
		UserCartDao ucd = new UserCartDaoImpl();
		UserCart uc = new UserCart();
		List<UserCart> ucs = new ArrayList<UserCart>();
		GoodsInfoDao gid = new GoodsInfoDaoImpl();
		GoodsInfo gi = new GoodsInfo();
		ucs = ucd.load(user_name);
		
		if("check_all".equals(from)) {
			for (int i = 0; i < ucs.size(); i++) {
				uc = new UserCart();
				uc = ucs.get(i);
				uc.setIschosen(1);
				ucd.update(uc);
			}
			response.getWriter().write("success");
		} else if("uncheck_all".equals(from)) {
			for (int i = 0; i < ucs.size(); i++) {
				uc = new UserCart();
				uc = ucs.get(i);
				uc.setIschosen(0);
				ucd.update(uc);
			}
			response.getWriter().write("success");
		} else if("check_one".equals(from)){
			uc = new UserCart();
			uc = ucd.load(user_name, goods_id);
			uc.setIschosen(1);
			ucd.update(uc);
		} else if("uncheck_one".equals(from)){
			uc = new UserCart();
			uc = ucd.load(user_name, goods_id);
			uc.setIschosen(0);
			ucd.update(uc);
		} else if("reducetoone".equals(from)){
			uc = new UserCart();
			uc = ucd.load(user_name, goods_id);
			uc.setGoods_num(1);
			ucd.update(uc);
		} else if("reduce".equals(from)){
			uc = new UserCart();
			uc = ucd.load(user_name, goods_id);
			uc.setGoods_num(uc.getGoods_num()-1);
			ucd.update(uc);
		} else if("plus".equals(from)){
			uc = new UserCart();
			uc = ucd.load(user_name, goods_id);
			gi = gid.load(goods_id);
			uc.setGoods_ttnum(gi.getGoods_ttnum());
			if(Integer.parseInt(goods_num) > uc.getGoods_ttnum()) {
				uc.setGoods_num(uc.getGoods_ttnum());
				response.getWriter().write(String.valueOf(uc.getGoods_num()));
			} else {
				uc.setGoods_num(Integer.parseInt(goods_num));
				response.getWriter().write("success");
			}
			ucd.update(uc);
		} else if("plusorreduce".equals(from)){
			uc = new UserCart();
			uc = ucd.load(user_name, goods_id);
			gi = gid.load(goods_id);
			uc.setGoods_ttnum(gi.getGoods_ttnum());
			if(Integer.parseInt(goods_num) > uc.getGoods_ttnum()) {
				uc.setGoods_num(uc.getGoods_ttnum());
				response.getWriter().write(String.valueOf(uc.getGoods_num()));
			} else if(Integer.parseInt(goods_num) < 1) {
				uc.setGoods_num(1);
				response.getWriter().write("toosmall");
			} else {
				uc.setGoods_num(Integer.parseInt(goods_num));
				response.getWriter().write("success");
			}
			ucd.update(uc);
		} else if("delete".equals(from)) {
			uc = new UserCart();
			UserDao ud = new UserDaoImpl();
			uc.setUser_id(ud.loadByName(user_name).getUser_id());
			uc.setUser_name(user_name);
			uc.setGoods_id(goods_id);
			ucd.delete(uc);
			response.getWriter().write("success");
		} else if("deleteall".equals(from)) {
			ucd.deleteAll(user_name);
			response.getWriter().write("success");
		}
	}

}
