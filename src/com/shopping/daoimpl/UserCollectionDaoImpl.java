package com.shopping.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.shopping.dao.UserCollectionDao;
import com.shopping.entity.Floor;
import com.shopping.entity.UserCollection;
import com.shopping.utils.DBUtil;

public class UserCollectionDaoImpl implements UserCollectionDao{

	@Override
	public void add(UserCollection uc) {
		String sql = "insert into t_usercollection(user_id,user_name,goods_id) values(?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uc.getUser_id());
			pstmt.setString(2, uc.getUser_name());
			pstmt.setString(3, uc.getGoods_id());
			pstmt.execute();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			System.out.println("执行添加一条新用户收藏的过程中出现了错误！");
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
	public UserCollection load(String user_name) {
		String sql = "select usercollection_id,user_id,user_name,goods_id from t_usercollection where user_name=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserCollection uc = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_name);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				uc = new UserCollection(rs.getInt("usercollection_id"),
								rs.getString("user_id"),
							    rs.getString("user_name"),
							    rs.getString("goods_id"));			
			}
		} catch (Exception e) {
			System.out.println("执行查询用户收藏UserCollection的过程中出现了错误！");
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
			
		return uc;
	}

	@Override
	public void update(UserCollection uc) {
		String sql = "update t_usercollection set goods_id=? where user_name=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uc.getGoods_id());
			pstmt.setString(2, uc.getUser_name());
			pstmt.execute();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			System.out.println("执行更改用户收藏UserCollection的过程中出现了错误！");
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
	public void delete(UserCollection uc) {
		// TODO Auto-generated method stub
		
	}

}
