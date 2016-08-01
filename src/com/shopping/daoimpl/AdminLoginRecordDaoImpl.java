package com.shopping.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.dao.AdminLoginRecordDao;
import com.shopping.entity.AdminLoginRecord;
import com.shopping.entity.Category;
import com.shopping.utils.DBUtil;

public class AdminLoginRecordDaoImpl implements AdminLoginRecordDao {

	@Override
	public void add(AdminLoginRecord alr) {
		String sql = "insert into t_adminloginrecord(administrator_name,login_time) values(?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, alr.getAdministrator_name());
			pstmt.setString(2, alr.getLogin_time());
			pstmt.execute();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			System.out.println("执行添加一条管理员登陆记录AdminLoginRecord的过程中出现了错误！");
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
	public List<AdminLoginRecord> selectLatestFive() {
		String sql = "select record_id,administrator_name,login_time from t_adminloginrecord order by record_id desc limit 5";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		AdminLoginRecord alr = null;
		List<AdminLoginRecord> alrs = new ArrayList<AdminLoginRecord>();
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				alr = new AdminLoginRecord(rs.getInt("record_id"),
						    rs.getString("administrator_name"),
						    rs.getString("login_time"));			
				alrs.add(alr);
			}	
		} catch (Exception e) {
			System.out.println("执行查询最后五条管理员登陆记录AdminLoginRecords的过程中出现了错误！");
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
		
		return alrs;
	}

	@Override
	public List<AdminLoginRecord> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

}
