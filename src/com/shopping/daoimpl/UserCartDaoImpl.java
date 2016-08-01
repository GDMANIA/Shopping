package com.shopping.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.dao.UserCartDao;
import com.shopping.entity.Floor;
import com.shopping.entity.UserCart;
import com.shopping.utils.DBUtil;

public class UserCartDaoImpl implements UserCartDao {

	@Override
	public void add(UserCart uc) {
		String sql = "insert into t_usercart(user_id,user_name,goods_id,goods_num) values(?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1,uc.getUser_id());
			pstmt.setString(2, uc.getUser_name());
			pstmt.setString(3, uc.getGoods_id());
			pstmt.setInt(4, uc.getGoods_num());
			pstmt.execute();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			System.out.println("执行添加一条购物车商品UserCart的过程中出现了错误！");
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
	public List<UserCart> load(String user_name) {
		String sql = "select usercart_id,user_id,user_name,ischosen,goods_id,goods_num from t_usercart where user_name=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserCart uc = null;
		List<UserCart> ucs = new ArrayList<UserCart>();
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_name);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				uc = new UserCart(rs.getInt("usercart_id"),
								rs.getString("user_id"),
							    rs.getString("user_name"),
							    rs.getInt("ischosen"),
							    rs.getString("goods_id"),
							    rs.getInt("goods_num"));
				ucs.add(uc);
			}
		} catch (Exception e) {
			System.out.println("执行查询购物车UserCart的过程中出现了错误！");
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
			
		return ucs;
	}
	
	@Override
	public UserCart load(String user_name, String goods_id) {
		String sql = "select usercart_id,user_id,user_name,ischosen,goods_id,goods_num from t_usercart where user_name=? and goods_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserCart uc = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_name);
			pstmt.setString(2, goods_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				uc = new UserCart(rs.getInt("usercart_id"),
						rs.getString("user_id"),
						rs.getString("user_name"),
						rs.getInt("ischosen"),
						rs.getString("goods_id"),
						rs.getInt("goods_num"));
			}
		} catch (Exception e) {
			System.out.println("执行查询购物车UserCart的过程中出现了错误！");
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
		
		return uc;
	}

	@Override
	public void update(UserCart uc) {
		String sql = "update t_usercart set ischosen=?,goods_num=? where user_name=? and goods_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uc.getIschosen());
			pstmt.setInt(2, uc.getGoods_num());
			pstmt.setString(3, uc.getUser_name());
			pstmt.setString(4, uc.getGoods_id());
			pstmt.execute();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			System.out.println("执行更改用户购物车UserCart的过程中出现了错误！");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBUtil.release(null, pstmt, conn);
		}

	}

	@Override
	public void delete(UserCart uc) {
		String sql = "delete from t_usercart where user_id=? and goods_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uc.getUser_id());
			pstmt.setString(2, uc.getGoods_id());
			pstmt.execute();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			System.out.println("执行删除一条购物车商品UserCart的过程中出现了错误！");
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
	public void deleteAll(String user_name) {
		String sql = "delete from t_usercart where user_name=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_name);
			pstmt.execute();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			System.out.println("执行删除某个用户的所有购物车商品UserCarts的过程中出现了错误！");
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

}
