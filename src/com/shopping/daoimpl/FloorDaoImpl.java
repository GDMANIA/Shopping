package com.shopping.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shopping.dao.FloorDao;
import com.shopping.entity.Floor;
import com.shopping.entity.Keyword;
import com.shopping.utils.DBUtil;

public class FloorDaoImpl implements FloorDao {

	@Override
	public void add(Floor fr) {
		// TODO Auto-generated method stub

	}

	@Override
	public Floor load(int floor_id) {
		String sql = "select floor_id,floor_name,goods_id,administrator_name,floor_createtime from t_floor where floor_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Floor fr = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, floor_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				fr = new Floor(rs.getInt("floor_id"),
								rs.getString("floor_name"),
							    rs.getString("goods_id"),
							    rs.getString("administrator_name"),
							    rs.getString("floor_createtime"));			
			}
		} catch (Exception e) {
			System.out.println("执行查询楼层floor的过程中出现了错误！");
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
			
		return fr;
	}
	
	@Override
	public List<Floor> selectAll() {
		String sql = "select floor_id,floor_name,goods_id,administrator_name,floor_createtime from t_floor";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Floor fr = null;
		List<Floor> frs = new ArrayList<Floor>();
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				fr = new Floor(rs.getInt("floor_id"),
								rs.getString("floor_name"),
							    rs.getString("goods_id"),
							    rs.getString("administrator_name"),
							    rs.getString("floor_createtime"));
				frs.add(fr);
			}
		} catch (Exception e) {
			System.out.println("执行查询楼层floor的过程中出现了错误！");
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
			
		return frs;
	}

	@Override
	public void update(Floor fr) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Floor fr) {
		// TODO Auto-generated method stub

	}

}
