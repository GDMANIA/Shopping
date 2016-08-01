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
import com.shopping.dao.FloorDao;
import com.shopping.dao.GoodsInfoDao;
import com.shopping.dao.LogoAndCopyrightDao;
import com.shopping.dao.ShopRecommendationDao;
import com.shopping.dao.UserDao;
import com.shopping.dao.UserHistoryDao;
import com.shopping.daoimpl.FloorDaoImpl;
import com.shopping.daoimpl.GoodsInfoDaoImpl;
import com.shopping.daoimpl.LogoAndCopyrightDaoImpl;
import com.shopping.daoimpl.ShopRecommendationDaoImpl;
import com.shopping.daoimpl.UserDaoImpl;
import com.shopping.daoimpl.UserHistoryDaoImpl;
import com.shopping.entity.Floor;
import com.shopping.entity.GoodsInfo;
import com.shopping.entity.LogoAndCopyright;
import com.shopping.entity.ShopRecommendation;
import com.shopping.entity.User;
import com.shopping.entity.UserHistory;
/**
 * 这是从数据库中取到当前网站的Logo图片和版权信息的servlet，
 * 取到以后用json形式发给前台的ajax
 */
@WebServlet("/user/getLogoAndCopyright.do")
public class GetLogoAndCopyrightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		LogoAndCopyrightDao lacd = new LogoAndCopyrightDaoImpl();
		LogoAndCopyright lac = new LogoAndCopyright();
		lac = lacd.load();
		String jsonString = JSON.toJSONString(lac);
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonString);
		//System.out.println(jsonString);
		
	}

}
