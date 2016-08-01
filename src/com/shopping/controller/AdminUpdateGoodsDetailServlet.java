package com.shopping.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.shopping.dao.GoodsInfoDao;
import com.shopping.dao.LogoAndCopyrightDao;
import com.shopping.daoimpl.GoodsInfoDaoImpl;
import com.shopping.daoimpl.LogoAndCopyrightDaoImpl;
import com.shopping.entity.Administrator;
import com.shopping.entity.GoodsInfo;
import com.shopping.entity.LogoAndCopyright;
/**
 * 这是管理员更改商品信息的servlet，实现的功能基本与插入一条商品信息是一样的
 */
@WebServlet("/admin/adminUpdateGoodsDetail.do")
public class AdminUpdateGoodsDetailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String goods_id = "";
		String goods_cat = "";
		String goods_key = "";
		String goods_name = "";
		String goods_info = "";
		String[] goods_img = new String[5];
		String goods_oriprice = "";
		String goods_curprice = "";
		String goods_ttnum = "";
		String fileName = "";
		String updateGoodsDetailWrongMsg = "";
		request.setCharacterEncoding("UTF-8");
		GoodsInfoDao gid = new GoodsInfoDaoImpl();
		GoodsInfo gi = new GoodsInfo();
		File file = null;
		FileItem fileItem = null;
		boolean flag_file_exists = false;
		
		response.setContentType("text/html;charset=utf-8");
		// 为解析类提供配置信息
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 创建解析类的实例
		ServletFileUpload sfu = new ServletFileUpload(factory);
		// 开始解析
		//sfu.setHeaderEncoding("UTF-8");
		sfu.setFileSizeMax(1024 * 400);
		// 每个表单域中数据会封装到一个对应的FileItem对象上
		try {
			//可能会存在  input type="text"      input type="password"   input type="file"
			List<FileItem> items = sfu.parseRequest(request);
			// 区分表单域
			goods_id = items.get(0).getString();
			gi = gid.loadRegardlessOfOnSite(goods_id);
			//System.out.println(">>>>"+goods_id);
			goods_cat = items.get(1).getString();
			gi.setGoods_cat(Integer.parseInt(goods_cat));
			//System.out.println(">>>>"+goods_cat);
			goods_key = items.get(2).getString();
			gi.setGoods_key(Integer.parseInt(goods_key));
			//System.out.println(">>>>"+goods_key);
			goods_name = items.get(3).getString("UTF-8");
			gi.setGoods_name(goods_name);
			//System.out.println(">>>>"+goods_name);
			goods_info = items.get(4).getString("UTF-8");
			gi.setGoods_info(goods_info);
			//System.out.println(">>>>"+goods_info);
			goods_img[0] = items.get(5).getName();
			//System.out.println(">>>>"+goods_img[0]);
			goods_img[1] = items.get(6).getName();
			//System.out.println(">>>>"+goods_img[1]);
			goods_img[2] = items.get(7).getName();
			//System.out.println(">>>>"+goods_img[2]);
			goods_img[3] = items.get(8).getName();
			//System.out.println(">>>>"+goods_img[3]);
			goods_img[4] = items.get(9).getName();
			//System.out.println(">>>>"+goods_img[4]);
			goods_oriprice = items.get(10).getString();
			gi.setGoods_oriprice(Double.parseDouble(goods_oriprice));
			//System.out.println(">>>>"+goods_oriprice);
			goods_curprice = items.get(11).getString();
			gi.setGoods_curprice(Double.parseDouble(goods_curprice));
		//	System.out.println(">>>>"+goods_curprice);
			goods_ttnum = items.get(12).getString();
			gi.setGoods_ttnum(Integer.parseInt(goods_ttnum));
			String[] goods_img_origin = gi.getGoods_img().split(",");
			StringBuffer sb_goods_img = new StringBuffer("");
			//System.out.println(">>>>"+goods_ttnum);
			ServletContext sctx = getServletContext();
			//String path = "E:\\JAVA\\shoppinggoods_images";
			String path = "D:\\CC\\goods_images";
			System.out.println(path);
			for (int i = 0; i < 5; i++) {
				if(goods_img[i] != "" && !goods_img[i].isEmpty()) {
					fileName = goods_img[i];
					System.out.println(fileName);
					fileName = fileName
							.substring(fileName.lastIndexOf("/") + 1);
					file = new File(path + "\\" + fileName);
					if (!file.exists()) {
						items.get(i+5).write(file);
					} else {
						flag_file_exists = true;
					}
				} else {
					goods_img[i] = goods_img_origin[i];
					System.out.println(goods_img[i]);
				}
				sb_goods_img.append(goods_img[i] + ",");
			}
			gi.setGoods_img(sb_goods_img.toString());
			gid.update(gi);
			if(flag_file_exists) {
				updateGoodsDetailWrongMsg = "商品信息修改成功，但是上传的图片已经存在，未进行覆盖，请更改文件名以后再试!";
				request.setAttribute("updateGoodsDetailWrongMsg", updateGoodsDetailWrongMsg);
				request.getRequestDispatcher("/admin/goods.jsp").forward(request, response);
			} else {
				updateGoodsDetailWrongMsg = "商品信息修改成功!";
				request.setAttribute("updateGoodsDetailWrongMsg", updateGoodsDetailWrongMsg);
				request.getRequestDispatcher("/admin/goods.jsp").forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
