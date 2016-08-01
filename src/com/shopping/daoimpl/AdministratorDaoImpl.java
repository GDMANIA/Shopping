package com.shopping.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.shopping.dao.AdministratorDao;
import com.shopping.entity.Administrator;
import com.shopping.entity.Floor;
import com.shopping.utils.DBUtil;

public class AdministratorDaoImpl implements AdministratorDao {

	@Override
	public Administrator load(String administrator_name) {
		String sql = "select administrator_id,administrator_name,administrator_password from t_administrator where administrator_name=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Administrator admin = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, administrator_name);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				admin = new Administrator(rs.getString("administrator_id"),
								rs.getString("administrator_name"),
							    rs.getString("administrator_password"));			
			}
		} catch (Exception e) {
			System.out.println("执行查询管理员信息Administrator的过程中出现了错误！");
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
			
		return admin;
	}

	@Override
	public void update(Administrator admin) {
		// TODO Auto-generated method stub

	}

}
