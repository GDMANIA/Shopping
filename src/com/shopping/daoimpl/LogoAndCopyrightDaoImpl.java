package com.shopping.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.shopping.dao.LogoAndCopyrightDao;
import com.shopping.entity.LogoAndCopyright;
import com.shopping.entity.User;
import com.shopping.utils.DBUtil;

public class LogoAndCopyrightDaoImpl implements LogoAndCopyrightDao {

	@Override
	public void add(LogoAndCopyright lac) {
		String sql_update = "update t_logoandcopyright set logoandcopyright_isvalid=0";
		String sql_add = "insert into t_logoandcopyright(logo_img,copyright_detail,administrator_name,logoandcopyright_createtime) values(?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql_update);
			pstmt.execute();
			
			pstmt = conn.prepareStatement(sql_add);
			pstmt.setString(1,lac.getLogo_img());
			pstmt.setString(2, lac.getCopyright_detail());
			pstmt.setString(3, lac.getAdministrator_name());
			pstmt.setString(4, lac.getLogoandcopyright_createtime());
			pstmt.execute();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			System.out.println("执行添加一条网站LOGO和版权信息LogoAndCopyright的过程中出现了错误！");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBUtil.release(null, pstmt, conn);
		}

	}

	@Override
	public LogoAndCopyright load() {
		String sql = "select logoandcopyright_id,logo_img,copyright_detail,logoandcopyright_isvalid,administrator_name,"
				+ "logoandcopyright_createtime from t_logoandcopyright where logoandcopyright_isvalid=1";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		LogoAndCopyright lac = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				lac = new LogoAndCopyright(rs.getInt("logoandcopyright_id"), 
							    rs.getString("logo_img"), 
							    rs.getString("copyright_detail"), 
							    rs.getInt("logoandcopyright_isvalid"), 
							    rs.getString("administrator_name"),
							    rs.getString("logoandcopyright_createtime"));
			}
		} catch (Exception e) {
			System.out.println("执行用户名查询用户的过程中出现了错误！");
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
			
		return lac;
	}

}
