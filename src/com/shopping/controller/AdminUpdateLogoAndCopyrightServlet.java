package com.shopping.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.shopping.dao.AdministratorDao;
import com.shopping.dao.LogoAndCopyrightDao;
import com.shopping.daoimpl.AdministratorDaoImpl;
import com.shopping.daoimpl.LogoAndCopyrightDaoImpl;
import com.shopping.entity.Administrator;
import com.shopping.entity.LogoAndCopyright;
import com.sun.org.apache.xalan.internal.xsltc.trax.SmartTransformerFactoryImpl;

/**
 * 这是管理员模块更改网站logo和版权信息的servlet，这里因为前台是用二进制的方式提交的表单，
 * 所以采用了文件上传的解析类，循环得到文本框和文件的值。
 * 加入了两个flag判断条件，只有当文本框输入和文件都符合要求的时候，才会更改数据库并且将文件写入指定文件夹
 * 如果有问题会给前台返回错误信息
 */
@WebServlet("/admin/adminUpdateLogoAndCopyright.do")
public class AdminUpdateLogoAndCopyrightServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String global_copy = "";
		String fileName = "";
		String updateLogoAndCopyWrongMsg = "";
		request.setCharacterEncoding("utf-8");
		boolean flag_copyright = false;
		boolean flag_logo = false;
		File file = null;
		FileItem fileItem = null;
		String admin_name = "";
		LogoAndCopyrightDao lacd = new LogoAndCopyrightDaoImpl();
		LogoAndCopyright lac = new LogoAndCopyright();
		
		response.setContentType("text/html;charset=utf-8");
		
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for(int i = 0; i < cookies.length; i++) {
				if("admin".equals(cookies[i].getName())) {
					if(cookies[i].getValue() != null || cookies[i].getValue() != "" || !"null".equals(cookies[i].getValue())) {
						admin_name = cookies[i].getValue();
					}
				} 
			}
		}
		// 为解析类提供配置信息
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// 创建解析类的实例
		ServletFileUpload sfu = new ServletFileUpload(factory);
		// 开始解析
		sfu.setFileSizeMax(1024 * 400);
		// 每个表单域中数据会封装到一个对应的FileItem对象上
		try {
			//可能会存在  input type="text"      input type="password"   input type="file"
			List<FileItem> items = sfu.parseRequest(request);
			// 区分表单域
			for (int i = 0; i < items.size() ; i++) {
				FileItem item = items.get(i);
				// isFormField为true，表示这不是文件上传表单域
				if (item.isFormField()) {
					global_copy = item.getString("UTF-8");
					if(global_copy == "" || global_copy == null || global_copy.isEmpty()) {
						flag_copyright = false;
						updateLogoAndCopyWrongMsg = "版权信息不能为空!";
						request.setAttribute("updateLogoAndCopyWrongMsg", updateLogoAndCopyWrongMsg);
						request.getRequestDispatcher("/admin/global.jsp").forward(request, response);
						return;
					} else {
						flag_copyright = true;
					}
				} else {
					fileItem = item = items.get(i);
					if(item.getName() == "") {
						flag_logo = false;
						updateLogoAndCopyWrongMsg = "未上传文件!";
						request.setAttribute("updateLogoAndCopyWrongMsg", updateLogoAndCopyWrongMsg);
						request.getRequestDispatcher("/admin/global.jsp").forward(request, response);
						return;
					} else {
						ServletContext sctx = getServletContext();
						// 获得存放文件的物理路径
						// upload下的某个文件夹 得到当前在线的用户 找到对应的文件夹
	
						//String path = sctx.getRealPath("/img");
						String path = "D:\\CC\\images";
						
						
						//这里path后面用虚拟路径改成硬盘上的一个目录，不能用tomcat的目录
						
						
						
						System.out.println(path);
						// 获得文件名
						fileName = item.getName();
						System.out.println(fileName);
						// 该方法在某些平台(操作系统),会返回路径+文件名
						fileName = fileName
								.substring(fileName.lastIndexOf("/") + 1);
						file = new File(path + "\\" + fileName);
						if (!file.exists()) {
							flag_logo = true;
							// 将上传图片的名字记录到数据库中
							
							 /** //手动写的 OutputStream out = new FileOutputStream(new File(path,filename));
							 * InputStream in = item.getInputStream() ;
							 * int length = 0 ; 
							 * byte [] buf = new byte[1024] ;
							 * System.out.println("获取上传文件的总共的容量："+item.getSize());
							 * // in.read(buf) 每次读到的数据存放在 buf 数组中
							 * while( (length =in.read(buf) ) != -1) { //在 buf 数组中 取出数据 写到 （输出流）磁盘上
							 * 	out.write(buf, 0, length);
							 * }
							 * in.close(); 
							 * out.close();
							 */
							
						} else {
							flag_logo = false;
							updateLogoAndCopyWrongMsg = "上传文件已存在，请重新选择文件或更改文件名!";
							request.setAttribute("updateLogoAndCopyWrongMsg", updateLogoAndCopyWrongMsg);
							request.getRequestDispatcher("/admin/global.jsp").forward(request, response);
							return;
						}
					}
				}
			}
			if(flag_copyright == true && flag_logo == true) {
				fileItem.write(file);
				lac.setAdministrator_name(admin_name);
				lac.setLogo_img(fileName);
				lac.setCopyright_detail(global_copy);
				lac.setLogoandcopyright_createtime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
				lacd.add(lac);
				updateLogoAndCopyWrongMsg = "更改网站LOGO和版权信息成功!如果没有正确显示请刷新页面或清除浏览器数据！";
				request.setAttribute("updateLogoAndCopyWrongMsg", updateLogoAndCopyWrongMsg);
				request.getRequestDispatcher("/admin/global.jsp").forward(request, response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
