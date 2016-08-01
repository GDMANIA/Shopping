package com.shopping.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.dao.UserDao;
import com.shopping.entity.GoodsSearchChoices;
import com.shopping.entity.User;
import com.shopping.entity.UserSearchChoices;
import com.shopping.utils.DBUtil;

/**
 * 这是User类与数据库交互的实现类
 */
public class UserDaoImpl implements UserDao{

	@Override
	public void add(User user) {
		String sql = "insert into t_user(user_id,user_name,user_password,user_regtime) values(?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUser_id());
			pstmt.setString(2, user.getUser_name());
			pstmt.setString(3, user.getUser_password());
			pstmt.setString(4, user.getUser_regtime());
			pstmt.execute();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			DBUtil.release(null, pstmt, conn);
		}
	}

	@Override
	public User loadByName(String user_name) {
		String sql = "select user_id,user_level,user_name,user_password,user_email,"
				+ "user_telnum,user_img,user_ttpayment,user_regtime,user_lastlogintime from t_user "
				+ "where user_name=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_name);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				user = new User(rs.getString("user_id"), 
							    rs.getInt("user_level"), 
							    rs.getString("user_name"), 
							    rs.getString("user_password"), 
							    rs.getString("user_email"), 
							    rs.getString("user_telnum"), 
							    rs.getString("user_img"),
							    rs.getDouble("user_ttpayment"),
							    rs.getString("user_regtime"), 
							    rs.getString("user_lastlogintime"));
			}
		} catch (Exception e) {
			System.out.println("执行用户名查询用户的过程中出现了错误！");
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
			
		return user;
	}
	
	@Override
	public User loadByEmail(String user_email) {
		String sql = "select user_id,user_level,user_name,user_password,user_email,"
				+ "user_telnum,user_img,user_ttpayment,user_regtime,user_lastlogintime from t_user "
				+ "where user_email=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_email);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				user = new User(rs.getString("user_id"), 
							    rs.getInt("user_level"), 
							    rs.getString("user_name"), 
							    rs.getString("user_password"), 
							    rs.getString("user_email"), 
							    rs.getString("user_telnum"), 
							    rs.getString("user_img"), 
							    rs.getDouble("user_ttpayment"),
							    rs.getString("user_regtime"), 
							    rs.getString("user_lastlogintime"));
			}
		} catch (Exception e) {
			System.out.println("执行用户名查询用户的过程中出现了错误！");
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
			
		return user;
	}

	@Override
	public User loadByTelnum(String user_telnum) {
		String sql = "select user_id,user_level,user_name,user_password,user_email,"
				+ "user_telnum,user_img,user_ttpayment,user_regtime,user_lastlogintime from t_user "
				+ "where user_telnum=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_telnum);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				user = new User(rs.getString("user_id"), 
							    rs.getInt("user_level"), 
							    rs.getString("user_name"), 
							    rs.getString("user_password"), 
							    rs.getString("user_email"), 
							    rs.getString("user_telnum"), 
							    rs.getString("user_img"), 
							    rs.getDouble("user_ttpayment"),
							    rs.getString("user_regtime"), 
							    rs.getString("user_lastlogintime"));
			}
		} catch (Exception e) {
			System.out.println("执行用户名查询用户的过程中出现了错误！");
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
			
		return user;
	}
	
	@Override
	public List<User> selectAll(int offsetRows, int pageSize, UserSearchChoices choices) {
		StringBuffer sql = new StringBuffer("select user_id,user_level,user_name,user_password,user_email,"
				+ "user_telnum,user_img,user_ttpayment,user_regtime,user_lastlogintime from t_user where user_id is not null");
		if(choices.getUser_name() != null && choices.getUser_name() != "" ) {
			sql.append(" and user_name like '%"+choices.getUser_name()+"%'");
		}
		if(choices.getUser_telnum() != null && choices.getUser_telnum() != "" ) {
			sql.append(" and user_telnum like '%"+choices.getUser_telnum()+"%'");
		}
		if(choices.getUser_email() != null && choices.getUser_email() != "" ) {
			sql.append(" and user_email like '%"+choices.getUser_email()+"%'");
		}
		if(choices.getUser_level() != null && choices.getUser_level() != "") {
			sql.append(" and user_level="+Integer.parseInt(choices.getUser_level()));
		}
		if(choices.getUser_ttpayment_below() != null && choices.getUser_ttpayment_below() != "" ) {
			sql.append(" and user_ttpayment>="+Double.parseDouble(choices.getUser_ttpayment_below()));
		}
		if(choices.getUser_ttpayment_above() != null && choices.getUser_ttpayment_above() != "") {
			sql.append(" and user_ttpayment<="+Double.parseDouble(choices.getUser_ttpayment_above()));
		}
		sql.append(" limit ? , ?");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		User user = null;
		List<User> users = new ArrayList<User>();
		
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, offsetRows);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				user = new User(rs.getString("user_id"), 
							    rs.getInt("user_level"), 
							    rs.getString("user_name"), 
							    rs.getString("user_password"), 
							    rs.getString("user_email"), 
							    rs.getString("user_telnum"), 
							    rs.getString("user_img"),
							    rs.getDouble("user_ttpayment"),
							    rs.getString("user_regtime"), 
							    rs.getString("user_lastlogintime"));
				
				users.add(user);
				
			}
		} catch (Exception e) {
			System.out.println("执行用户名查询用户的过程中出现了错误！");
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
			
		return users;
	}
	
	@Override
	public void update(User user) {
		String sql = "update t_user set user_level=?,user_password=?,"
				+ "user_email=?,user_telnum=?,user_img=?,user_ttpayment=?,"
				+ "user_lastlogintime=? where user_name=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, user.getUser_level());
			//pstmt.setString(2, user.getUser_name());
			pstmt.setString(2, user.getUser_password());
			pstmt.setString(3, user.getUser_email());
			pstmt.setString(4, user.getUser_telnum());
			pstmt.setString(5, user.getUser_img());
			pstmt.setDouble(6, user.getUser_ttpayment());
			pstmt.setString(7, user.getUser_lastlogintime());
			pstmt.setString(8, user.getUser_name());
			
			pstmt.execute();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
			DBUtil.release(null, pstmt, conn);
		}
	}

	@Override
	public int countAll() {
		String sql = "select count(user_id) from t_user";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalRows = 0;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalRows = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
		return totalRows;
	}
	
	@Override
	public int countAll(UserSearchChoices choices) {
		StringBuffer sql = new StringBuffer("select count(user_id) from t_user where user_name is not null");
		if(choices.getUser_name() != null && choices.getUser_name() != "" ) {
			sql.append(" and user_name like '%"+choices.getUser_name()+"%'");
		}
		if(choices.getUser_telnum() != null && choices.getUser_telnum() != "" ) {
			sql.append(" and user_telnum like '%"+choices.getUser_telnum()+"%'");
		}
		if(choices.getUser_email() != null && choices.getUser_email() != "" ) {
			sql.append(" and user_email like '%"+choices.getUser_email()+"%'");
		}
		if(choices.getUser_level() != null && choices.getUser_level() != "") {
			sql.append(" and user_level="+Integer.parseInt(choices.getUser_level()));
		}
		if(choices.getUser_ttpayment_below() != null && choices.getUser_ttpayment_below() != "" ) {
			sql.append(" and user_ttpayment>="+Double.parseDouble(choices.getUser_ttpayment_below()));
		}
		if(choices.getUser_ttpayment_above() != null && choices.getUser_ttpayment_above() != "") {
			sql.append(" and user_ttpayment<="+Double.parseDouble(choices.getUser_ttpayment_above()));
		}
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalRows = 0;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalRows = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
		return totalRows;
	}
	
	@Override
	public int countAllDeal() {
		String sql = "select count(user_id) from t_user where user_ttpayment<>0";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalRows = 0;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				totalRows = rs.getInt(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
		return totalRows;
	}
	
	@Override
	public double countAllPayment() {
		String sql = "select sum(user_ttpayment) from t_user";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		double ttPayment = 0;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ttPayment = rs.getDouble(1);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
		return ttPayment;
	}

	

}
