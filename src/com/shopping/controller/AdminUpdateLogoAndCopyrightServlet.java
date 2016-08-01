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
 * ���ǹ���Աģ�������վlogo�Ͱ�Ȩ��Ϣ��servlet��������Ϊǰ̨���ö����Ƶķ�ʽ�ύ�ı���
 * ���Բ������ļ��ϴ��Ľ����࣬ѭ���õ��ı�����ļ���ֵ��
 * ����������flag�ж�������ֻ�е��ı���������ļ�������Ҫ���ʱ�򣬲Ż�������ݿⲢ�ҽ��ļ�д��ָ���ļ���
 * �����������ǰ̨���ش�����Ϣ
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
		// Ϊ�������ṩ������Ϣ
		DiskFileItemFactory factory = new DiskFileItemFactory();
		// �����������ʵ��
		ServletFileUpload sfu = new ServletFileUpload(factory);
		// ��ʼ����
		sfu.setFileSizeMax(1024 * 400);
		// ÿ�����������ݻ��װ��һ����Ӧ��FileItem������
		try {
			//���ܻ����  input type="text"      input type="password"   input type="file"
			List<FileItem> items = sfu.parseRequest(request);
			// ���ֱ���
			for (int i = 0; i < items.size() ; i++) {
				FileItem item = items.get(i);
				// isFormFieldΪtrue����ʾ�ⲻ���ļ��ϴ�����
				if (item.isFormField()) {
					global_copy = item.getString("UTF-8");
					if(global_copy == "" || global_copy == null || global_copy.isEmpty()) {
						flag_copyright = false;
						updateLogoAndCopyWrongMsg = "��Ȩ��Ϣ����Ϊ��!";
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
						updateLogoAndCopyWrongMsg = "δ�ϴ��ļ�!";
						request.setAttribute("updateLogoAndCopyWrongMsg", updateLogoAndCopyWrongMsg);
						request.getRequestDispatcher("/admin/global.jsp").forward(request, response);
						return;
					} else {
						ServletContext sctx = getServletContext();
						// ��ô���ļ�������·��
						// upload�µ�ĳ���ļ��� �õ���ǰ���ߵ��û� �ҵ���Ӧ���ļ���
	
						//String path = sctx.getRealPath("/img");
						String path = "D:\\CC\\images";
						
						
						//����path����������·���ĳ�Ӳ���ϵ�һ��Ŀ¼��������tomcat��Ŀ¼
						
						
						
						System.out.println(path);
						// ����ļ���
						fileName = item.getName();
						System.out.println(fileName);
						// �÷�����ĳЩƽ̨(����ϵͳ),�᷵��·��+�ļ���
						fileName = fileName
								.substring(fileName.lastIndexOf("/") + 1);
						file = new File(path + "\\" + fileName);
						if (!file.exists()) {
							flag_logo = true;
							// ���ϴ�ͼƬ�����ּ�¼�����ݿ���
							
							 /** //�ֶ�д�� OutputStream out = new FileOutputStream(new File(path,filename));
							 * InputStream in = item.getInputStream() ;
							 * int length = 0 ; 
							 * byte [] buf = new byte[1024] ;
							 * System.out.println("��ȡ�ϴ��ļ����ܹ���������"+item.getSize());
							 * // in.read(buf) ÿ�ζ��������ݴ���� buf ������
							 * while( (length =in.read(buf) ) != -1) { //�� buf ������ ȡ������ д�� ���������������
							 * 	out.write(buf, 0, length);
							 * }
							 * in.close(); 
							 * out.close();
							 */
							
						} else {
							flag_logo = false;
							updateLogoAndCopyWrongMsg = "�ϴ��ļ��Ѵ��ڣ�������ѡ���ļ�������ļ���!";
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
				updateLogoAndCopyWrongMsg = "������վLOGO�Ͱ�Ȩ��Ϣ�ɹ�!���û����ȷ��ʾ��ˢ��ҳ��������������ݣ�";
				request.setAttribute("updateLogoAndCopyWrongMsg", updateLogoAndCopyWrongMsg);
				request.getRequestDispatcher("/admin/global.jsp").forward(request, response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
