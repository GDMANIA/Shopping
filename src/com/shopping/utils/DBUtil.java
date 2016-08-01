package com.shopping.utils;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class DBUtil {
	//定义连接参数变量
	private static String driver = null;
	private static String url = null;
	private static String username = null;
	private static String password = null;
	private static Properties pp = null;
	//静态代码块读取配置文件
	static {
		pp = new Properties();
		InputStream is = DBUtil.class.getClassLoader().getResourceAsStream("mysql.properties");
		try {
			pp.load(is);
			driver = pp.getProperty("driver");
			url = pp.getProperty("url");
			username = pp.getProperty("username");
			password = pp.getProperty("password");
			//使用反射机制加载JDBC驱动
			Class.forName(driver);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url, username, password);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}
	
	//释放连接
	public static void release(ResultSet rs, PreparedStatement pstmt, Connection conn) {
		if(rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(pstmt != null) {
			try {
				pstmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn != null) {
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public static void main(String[] args) {
		/*System.out.println(getConnection());
		String goods_id_num = "0006:1,0007:6,0001:10";
		String goods_id = "";
		int goods_num = 0;
		System.out.println(goods_id_num);
		String[] goods_id_nums = goods_id_num.split(",");
		for (int i = 0; i < goods_id_nums.length; i++) {
			goods_id = goods_id_nums[i].split(":")[0];
			goods_num = Integer.parseInt(goods_id_nums[i].split(":")[1]);
			System.out.println(goods_id);
			System.out.println(goods_num);*/
		System.out.println(new SimpleDateFormat("yyyy年MM月dd日  HH:mm").format(new Date()));
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
