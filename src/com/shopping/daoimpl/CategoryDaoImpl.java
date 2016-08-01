package com.shopping.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.dao.CategoryDao;
import com.shopping.entity.Category;
import com.shopping.entity.User;
import com.shopping.utils.DBUtil;

public class CategoryDaoImpl implements CategoryDao {

	@Override
	public void add(Category cat) {
		String sql = "insert into t_category(cat_name,administrator_name,cat_createtime) values(?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, cat.getCat_name());
			pstmt.setString(2, cat.getAdministrator_name());
			pstmt.setString(3, cat.getCat_createtime());
			pstmt.execute();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			System.out.println("执行添加一条主分类Category的过程中出现了错误！");
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
	public Category load(int cat_id) {
		String sql = "select cat_id,cat_name,administrator_name,cat_createtime from t_category where cat_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Category cat = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, cat_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				cat = new Category(rs.getInt("cat_id"), 
							    rs.getString("cat_name"),
							    rs.getString("administrator_name"),
							    rs.getString("cat_createtime"));			
				}
		} catch (Exception e) {
			System.out.println("执行查询商品主分类category的过程中出现了错误！");
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
			
		return cat;
	}

	@Override
	public List<Category> selectAll() {
		String sql = "select cat_id,cat_name,administrator_name,cat_createtime from t_category";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Category cat = null;
		List<Category> cats = new ArrayList<Category>();
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				cat = new Category(rs.getInt("cat_id"), 
						 rs.getString("cat_name"),
						    rs.getString("administrator_name"),
						    rs.getString("cat_createtime"));			
				cats.add(cat);
			}	
		} catch (Exception e) {
			System.out.println("执行查询所有商品主分类categorys的过程中出现了错误！");
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
		
		return cats;
	}
	
	@Override
	public void update(Category cat) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Category cat) {
		// TODO Auto-generated method stub

	}

}
