package com.shopping.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shopping.dao.ShopRecommendationDao;
import com.shopping.entity.Floor;
import com.shopping.entity.ShopRecommendation;
import com.shopping.utils.DBUtil;

public class ShopRecommendationDaoImpl implements ShopRecommendationDao {

	@Override
	public void add(ShopRecommendation sr) {

	}

	@Override
	public List<ShopRecommendation> load() {
		String sql = "select recommendation_id,goods_cat,goods_key,goods_id,goods_soldnum,administrator_name,shoprecommendation_createtime from t_shoprecommendation order by goods_soldnum desc limit 4";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ShopRecommendation sr = null;
		List<ShopRecommendation> srs = new ArrayList<ShopRecommendation>();
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				sr = new ShopRecommendation(rs.getInt("recommendation_id"),
								rs.getInt("goods_cat"),
								rs.getInt("goods_key"),
							    rs.getString("goods_id"),
							    rs.getInt("goods_soldnum"),
							    rs.getString("administrator_name"),
							    rs.getString("shoprecommendation_createtime"));
				srs.add(sr);
			}
		} catch (Exception e) {
			System.out.println("执行查询商家推荐ShopRecommendations的过程中出现了错误！");
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
			
		return srs;
	}

	@Override
	public List<ShopRecommendation> loadByGoodsCat(int goods_cat,int goods_key) {
		String sql = "select recommendation_id,goods_cat,goods_key,goods_id,goods_soldnum,administrator_name,shoprecommendation_createtime from t_shoprecommendation where goods_cat=? and goods_key=? order by goods_soldnum desc limit 10";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ShopRecommendation sr = null;
		List<ShopRecommendation> srs = new ArrayList<ShopRecommendation>();
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, goods_cat);
			pstmt.setInt(2, goods_key);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				sr = new ShopRecommendation(rs.getInt("recommendation_id"),
								rs.getInt("goods_cat"),
								rs.getInt("goods_key"),
							    rs.getString("goods_id"),
							    rs.getInt("goods_soldnum"),
							    rs.getString("administrator_name"),
							    rs.getString("shoprecommendation_createtime"));
				srs.add(sr);
			}
		} catch (Exception e) {
			System.out.println("执行根据商品分类查询商家推荐ShopRecommendations的过程中出现了错误！");
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
			
		return srs;
	}

	@Override
	public void update(ShopRecommendation sr) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(ShopRecommendationDao sr) {
		// TODO Auto-generated method stub

	}

}
