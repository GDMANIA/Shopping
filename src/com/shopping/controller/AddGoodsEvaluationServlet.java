package com.shopping.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.shopping.dao.GoodsEvaluationDao;
import com.shopping.dao.UserDao;
import com.shopping.dao.UserOrderDao;
import com.shopping.daoimpl.GoodsEvaluationDaoImpl;
import com.shopping.daoimpl.UserDaoImpl;
import com.shopping.daoimpl.UserOrderDaoImpl;
import com.shopping.entity.GoodsEvaluation;
import com.shopping.entity.UserOrder;

@WebServlet("/user/addGoodsEvaluation.do")
public class AddGoodsEvaluationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response)
    		throws ServletException, IOException {
    	String user_name = request.getParameter("user_name");
    	String userorder_id = request.getParameter("userorder_id");
    	String[] goods_id_and_goods_evaluate = request.getParameter("goods_id_and_goods_evaluate").split(",");
    	String user_id = "";
    	String goods_id = "";
    	String evaluation_details = "";
    	String evaluation_createtime = "";
    	GoodsEvaluation ge = new GoodsEvaluation();
    	UserDao ud = new UserDaoImpl();
    	UserOrderDao uod = new UserOrderDaoImpl();
    	UserOrder uo = new UserOrder();
    	GoodsEvaluationDao ged = new GoodsEvaluationDaoImpl();
    	user_id = ud.loadByName(user_name).getUser_id();
    	for (int i = 0; i < goods_id_and_goods_evaluate.length; i++) {
    		ge = new GoodsEvaluation();
			goods_id = goods_id_and_goods_evaluate[i].split(":")[0];
			evaluation_details = goods_id_and_goods_evaluate[i].split(":")[1];
			evaluation_createtime = new SimpleDateFormat("yyyyÄêMMÔÂddÈÕ  HH:mm").format(new Date());
			ge.setUser_id(user_id);
			ge.setGoods_id(goods_id);
			ge.setEvaluation_details(evaluation_details);
			ge.setEvaluation_createtime(evaluation_createtime);
			ged.add(ge);
		}
    	uo.setUserorder_id(userorder_id);
    	uo.setOrder_state(4);
    	uod.update(uo);
    	response.getWriter().write("success");
    }
}
