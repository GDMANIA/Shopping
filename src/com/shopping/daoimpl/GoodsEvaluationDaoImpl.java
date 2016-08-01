package com.shopping.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.dao.GoodsEvaluationDao;
import com.shopping.dao.UserOrderDao;
import com.shopping.entity.GoodsEvaluation;
import com.shopping.entity.UserCart;
import com.shopping.entity.UserOrder;
import com.shopping.utils.DBUtil;

public class GoodsEvaluationDaoImpl implements GoodsEvaluationDao {

	@Override
	public void add(GoodsEvaluation ge) {
		String sql_goodsevaluation = "insert into t_goodsevaluation(goodsevaluation_id,user_id,goods_id,evaluation_details,evaluation_createtime) values(?,?,?,?,?)";
		String sql_goodsinfo = "update t_goodsinfo set goods_commentnum=goods_commentnum+1 where goods_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql_goodsevaluation);
			pstmt.setInt(1,ge.getGoodsevaluation_id());
			pstmt.setString(2, ge.getUser_id());
			pstmt.setString(3, ge.getGoods_id());
			pstmt.setString(4, ge.getEvaluation_details());
			pstmt.setString(5, ge.getEvaluation_createtime());
			pstmt.execute();
			
			pstmt = conn.prepareStatement(sql_goodsinfo);
			pstmt.setString(1, ge.getGoods_id());
			pstmt.execute();
			
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			System.out.println("执行添加一条商品评价GoodsEvaluation的过程中出现了错误！");
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
	public GoodsEvaluation load(String goods_id) {
		return null;
		
	}

	@Override
	public List<GoodsEvaluation> loadAll(String goods_id) {
		String sql = "select goodsevaluation_id,user_id,goods_id,evaluation_details,evaluation_createtime from t_goodsevaluation where goods_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GoodsEvaluation ge = null;
		List<GoodsEvaluation> ges = new ArrayList<GoodsEvaluation>();
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, goods_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				ge = new GoodsEvaluation(rs.getInt("goodsevaluation_id"),
								rs.getString("user_id"),
							    rs.getString("goods_id"),
							    rs.getString("evaluation_details"),
							    rs.getString("evaluation_createtime"));
				ges.add(ge);
			}
		} catch (Exception e) {
			System.out.println("执行查询某用户所有订单UserOrders的过程中出现了错误！");
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
			
		return ges;
	}

	@Override
	public void update(GoodsEvaluation ge) {
		
	}

	@Override
	public void delete(GoodsEvaluation ge) {
		// TODO Auto-generated method stub

	}

}
