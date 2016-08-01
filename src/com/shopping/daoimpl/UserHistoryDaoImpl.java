package com.shopping.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.shopping.dao.UserHistoryDao;
import com.shopping.entity.Floor;
import com.shopping.entity.User;
import com.shopping.entity.UserHistory;
import com.shopping.utils.DBUtil;

public class UserHistoryDaoImpl implements UserHistoryDao {

	@Override
	public void add(User user) {
		String sql = "insert into t_userhistory(user_id,user_name,goods_ids) values(?,?,'')";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user.getUser_id());
			pstmt.setString(2, user.getUser_name());
			pstmt.execute();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			System.out.println("执行注册时添加一条用户历史足迹UserHistory的过程中出现了错误！");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
	}

	@Override
	public UserHistory load(String user_id) {
		String sql = "select userhistory_id,user_id,user_name,goods_ids from t_userhistory where user_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserHistory uh = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				uh = new UserHistory(rs.getInt("userhistory_id"),
								rs.getString("user_id"),
								rs.getString("user_name"),
							    rs.getString("goods_ids"));			
			}
		} catch (Exception e) {
			System.out.println("执行查询用户历史足迹UserHistory的过程中出现了错误！");
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
			
		return uh;
	}

	@Override
	public void update(UserHistory uh) {
		String sql = "update t_userhistory set goods_ids=? where user_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uh.getGoods_ids());
			pstmt.setString(2, uh.getUser_id());
			pstmt.execute();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			System.out.println("执行更改一条用户历史足迹UserHistory的过程中出现了错误！");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}

	}

	@Override
	public void delete(UserHistory uh) {
		// TODO Auto-generated method stub

	}

}
