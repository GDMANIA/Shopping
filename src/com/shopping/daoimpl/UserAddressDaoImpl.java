package com.shopping.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.dao.UserAddressDao;
import com.shopping.entity.Category;
import com.shopping.entity.GoodsInfo;
import com.shopping.entity.UserAddress;
import com.shopping.utils.DBUtil;

public class UserAddressDaoImpl implements UserAddressDao {

	@Override
	public void add(UserAddress ua) {
		String sql = "insert into t_useraddress(user_id,user_name,consignee_name,consignee_province, consignee_city, consignee_area, consignee_address, consignee_telnum, isdefault_address) values(?,?,?,?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ua.getUser_id());
			pstmt.setString(2, ua.getUser_name());
			pstmt.setString(3, ua.getConsignee_name());
			pstmt.setString(4, ua.getConsignee_province());
			pstmt.setString(5, ua.getConsignee_city());
			pstmt.setString(6, ua.getConsignee_area());
			pstmt.setString(7, ua.getConsignee_address());
			pstmt.setString(8, ua.getConsignee_telnum());
			pstmt.setInt(9, ua.getIsDefault_address());
			pstmt.execute();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("执行新增收货地址UserAddress的过程中出现了错误！");
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
	public UserAddress load(int userAddress_id) {
		String sql = "select useraddress_id,user_id,user_name,consignee_name,consignee_province,consignee_city,"
				+ "consignee_area,consignee_address,consignee_telnum,isdefault_address from t_useraddress "
				+ "where useraddress_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserAddress ua = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userAddress_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				ua = new UserAddress(rs.getInt("useraddress_id"), 
							    rs.getString("user_id"), 
							    rs.getString("user_name"), 
							    rs.getString("consignee_name"), 
							    rs.getString("consignee_province"), 
							    rs.getString("consignee_city"), 
							    rs.getString("consignee_area"), 
							    rs.getString("consignee_address"),
							    rs.getString("consignee_telnum"),
							    rs.getInt("isdefault_address"));
			}
		} catch (Exception e) {
			System.out.println("执行查询用户收货地址UserAddress的过程中出现了错误！");
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
			
		return ua;
	}
	
	@Override
	public int loadId(UserAddress ua) {
		String sql = "select useraddress_id from t_useraddress where user_id=? and user_name=? and consignee_name=? and consignee_province=? and consignee_city=? and consignee_area=? and consignee_address=? and consignee_telnum=? and isdefault_address=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int useraddress_id = 0;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ua.getUser_id());
			pstmt.setString(2, ua.getUser_name());
			pstmt.setString(3, ua.getConsignee_name());
			pstmt.setString(4, ua.getConsignee_province());
			pstmt.setString(5, ua.getConsignee_city());
			pstmt.setString(6, ua.getConsignee_area());
			pstmt.setString(7, ua.getConsignee_address());
			pstmt.setString(8, ua.getConsignee_telnum());
			pstmt.setInt(9, ua.getIsDefault_address());
			rs = pstmt.executeQuery();
			while(rs.next()) {
				useraddress_id = rs.getInt("useraddress_id"); 
			}	
		} catch (Exception e) {
			System.out.println("执行查询某特定收货地址ID,UserAddress_ID的过程中出现了错误！");
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
		
		return useraddress_id;
	}

	@Override
	public List<UserAddress> selectAllByUserName(String user_name) {
		String sql = "select useraddress_id,user_id,user_name,consignee_name,consignee_province,consignee_city,consignee_area,consignee_address,consignee_telnum,isdefault_address from t_useraddress where user_name=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserAddress ua = null;
		List<UserAddress> uas = new ArrayList<UserAddress>();
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_name);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ua = new UserAddress(rs.getInt("useraddress_id"), 
						             rs.getString("user_id"), 
						             rs.getString("user_name"), 
						             rs.getString("consignee_name"), 
						             rs.getString("consignee_province"),
						             rs.getString("consignee_city"),
						             rs.getString("consignee_area"),
						             rs.getString("consignee_address"), 
						             rs.getString("consignee_telnum"),
						             rs.getInt("isdefault_address"));
				uas.add(ua);
			}	
		} catch (Exception e) {
			System.out.println("执行查询某特定用户的所有收货地址UserAddress的过程中出现了错误！");
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
		
		return uas;
	}

	@Override
	public void update(UserAddress ua) {
		String sql = "update t_useraddress set consignee_name=?,consignee_province=?,consignee_city=?,consignee_area=?,consignee_address=?,"
				+ "consignee_telnum=?,isdefault_address=? where useraddress_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, ua.getConsignee_name());
			pstmt.setString(2, ua.getConsignee_province());
			pstmt.setString(3, ua.getConsignee_city());
			pstmt.setString(4, ua.getConsignee_area());
			pstmt.setString(5, ua.getConsignee_address());
			pstmt.setString(6, ua.getConsignee_telnum());
			pstmt.setInt(7, ua.getIsDefault_address());
			pstmt.setInt(8, ua.getUserAddress_id());
			pstmt.execute();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			System.out.println("执行更改收货地址useraddress的过程中出现了错误！");
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
	public void delete(UserAddress ua) {
		// TODO Auto-generated method stub

	}
	
	@Override
	public void delete(int userAddress_id) {
		String sql = "delete from t_useraddress where useraddress_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, userAddress_id);
			pstmt.execute();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			try {
				conn.rollback();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("执行删除收货地址UserAddress的过程中出现了错误！");
			e.printStackTrace();
		}
		
	}

}
