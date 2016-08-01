package com.shopping.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.dao.KeywordDao;
import com.shopping.entity.Keyword;
import com.shopping.utils.DBUtil;

public class KeywordDaoImpl implements KeywordDao{

	@Override
	public void add(Keyword kw) {
		String sql = "insert into t_keyword(key_name,cat_id,administrator_name,key_createtime) values(?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, kw.getKey_name());
			pstmt.setInt(2, kw.getCat_id());
			pstmt.setString(3, kw.getAdministrator_name());
			pstmt.setString(4, kw.getKey_createtime());
			pstmt.execute();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			System.out.println("执行添加一条二级分类Keyword的过程中出现了错误！");
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
	public Keyword load(int key_id) {
		String sql = "select key_id,key_name,cat_id,administrator_name,key_createtime from t_keyword where key_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Keyword key = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, key_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				key = new Keyword(rs.getInt("key_id"), 
							    rs.getString("key_name"),
							    rs.getInt("cat_id"),
							    rs.getString("administrator_name"),
							    rs.getString("key_createtime"));			
				}
		} catch (Exception e) {
			System.out.println("执行查询商品关键词keyword的过程中出现了错误！");
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
			
		return key;
	}
	
	@Override
	public List<Keyword> loadAll() {
		String sql = "select key_id,key_name,cat_id,administrator_name,key_createtime from t_keyword";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Keyword key = null;
		List<Keyword> keys = new ArrayList<Keyword>();
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				key = new Keyword(rs.getInt("key_id"), 
						rs.getString("key_name"),
						rs.getInt("cat_id"),
						rs.getString("administrator_name"),
						rs.getString("key_createtime"));		
				keys.add(key);
			}
		} catch (Exception e) {
			System.out.println("执行查询所有商品关键词组keywords的过程中出现了错误！");
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
		
		return keys;
	}
	
	@Override
	public List<Keyword> loadAll(int offsetRows, int pageSize) {
		String sql = "select key_id,key_name,cat_id,administrator_name,key_createtime from t_keyword limit ?,?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Keyword key = null;
		List<Keyword> keys = new ArrayList<Keyword>();
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, offsetRows);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				key = new Keyword(rs.getInt("key_id"), 
							    rs.getString("key_name"),
							    rs.getInt("cat_id"),
							    rs.getString("administrator_name"),
							    rs.getString("key_createtime"));		
				keys.add(key);
			}
		} catch (Exception e) {
			System.out.println("执行查询所有商品关键词组keywords的过程中出现了错误！");
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
			
		return keys;
	}
	
	@Override
	public int getTotalRows() {
		String sql = "select count(key_id) from t_keyword";
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
	public List<Keyword> loadByCategory(int cat_id) {
		String sql = "select key_id,key_name,cat_id,administrator_name,key_createtime from t_keyword where cat_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Keyword key = null;
		List<Keyword> keys = new ArrayList<Keyword>();
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cat_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				key = new Keyword(rs.getInt("key_id"), 
							    rs.getString("key_name"),
							    rs.getInt("cat_id"),
							    rs.getString("administrator_name"),
							    rs.getString("key_createtime"));		
				keys.add(key);
			}
		} catch (Exception e) {
			System.out.println("执行查询商品关键词组keywords的过程中出现了错误！");
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
			
		return keys;
	}

	@Override
	public List<Keyword> loadByCategory(int cat_id, int count) {
		String sql = "select key_id,key_name,cat_id,administrator_name,key_createtime from t_keyword where cat_id=? limit ?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Keyword key = null;
		List<Keyword> keys = new ArrayList<Keyword>();
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cat_id);
			pstmt.setInt(2, count);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				key = new Keyword(rs.getInt("key_id"), 
						rs.getString("key_name"),
						rs.getInt("cat_id"),
					    rs.getString("administrator_name"),
					    rs.getString("key_createtime"));		
				keys.add(key);
			}
		} catch (Exception e) {
			System.out.println("执行查询商品关键词组keywords的过程中出现了错误！");
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
		
		return keys;
	}
	@Override
	
	public void update(Keyword kw) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Keyword kw) {
		// TODO Auto-generated method stub
		
	}

}
