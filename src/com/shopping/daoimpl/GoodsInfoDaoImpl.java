package com.shopping.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.dao.GoodsInfoDao;
import com.shopping.entity.GoodsInfo;
import com.shopping.entity.GoodsSearchChoices;
import com.shopping.entity.User;
import com.shopping.utils.DBUtil;

public class GoodsInfoDaoImpl implements GoodsInfoDao {
	
	@Override
	public void add(GoodsInfo gi) {
		String sql = "insert into t_goodsinfo(goods_id,goods_name,goods_info,goods_cat,goods_key,goods_oriprice,goods_curprice,goods_ttnum,goods_img,goods_createtime) values(?,?,?,?,?,?,?,?,?,?)";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, gi.getGoods_id());
			pstmt.setString(2, gi.getGoods_name());
			pstmt.setString(3, gi.getGoods_info());
			pstmt.setInt(4, gi.getGoods_cat());
			pstmt.setInt(5, gi.getGoods_key());
			pstmt.setDouble(6, gi.getGoods_oriprice());
			pstmt.setDouble(7, gi.getGoods_curprice());
			pstmt.setInt(8, gi.getGoods_ttnum());
			pstmt.setString(9, gi.getGoods_img());
			pstmt.setString(10, gi.getGoods_createtime());
			pstmt.execute();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			System.out.println("执行添加一条商品GoodsInfo的过程中出现了错误！");
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
	public GoodsInfo load(String goods_id) {
		String sql = "select goods_id,goods_name,goods_info,goods_cat,goods_key,goods_oriprice,goods_curprice,"
				+ "goods_ttnum,goods_soldnum,goods_commentnum,goods_clicknum,goods_img,goods_isdeleted,goods_isonsite,goods_createtime from t_goodsinfo "
				+ "where goods_id=? and goods_isdeleted=0 and goods_isonsite=1";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GoodsInfo gi = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, goods_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				gi = new GoodsInfo(rs.getString("goods_id"), 
							    rs.getString("goods_name"), 
							    rs.getString("goods_info"), 
							    rs.getInt("goods_cat"), 
							    rs.getInt("goods_key"),
							    rs.getDouble("goods_oriprice"), 
							    rs.getDouble("goods_curprice"), 
							    rs.getInt("goods_ttnum"), 
							    rs.getInt("goods_soldnum"),
							    rs.getInt("goods_commentnum"),
							    rs.getInt("goods_clicknum"),
							    rs.getString("goods_img"),
							    rs.getInt("goods_isdeleted"),
							    rs.getInt("goods_isonsite"),
							    rs.getString("goods_createtime"));
			}
		} catch (Exception e) {
			System.out.println("执行查询商品信息goodsinfo的过程中出现了错误！");
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
			
		return gi;
	}
	
	@Override
	public GoodsInfo loadRegardlessOfOnSite(String goods_id) {
		String sql = "select goods_id,goods_name,goods_info,goods_cat,goods_key,goods_oriprice,goods_curprice,"
				+ "goods_ttnum,goods_soldnum,goods_commentnum,goods_clicknum,goods_img,goods_isdeleted,goods_isonsite,goods_createtime from t_goodsinfo "
				+ "where goods_id=? and goods_isdeleted=0";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GoodsInfo gi = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, goods_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				gi = new GoodsInfo(rs.getString("goods_id"), 
						rs.getString("goods_name"), 
						rs.getString("goods_info"), 
						rs.getInt("goods_cat"), 
						rs.getInt("goods_key"),
						rs.getDouble("goods_oriprice"), 
						rs.getDouble("goods_curprice"), 
						rs.getInt("goods_ttnum"), 
						rs.getInt("goods_soldnum"),
						rs.getInt("goods_commentnum"),
						rs.getInt("goods_clicknum"),
						rs.getString("goods_img"),
						rs.getInt("goods_isdeleted"),
						rs.getInt("goods_isonsite"),
						rs.getString("goods_createtime"));
			}
		} catch (Exception e) {
			System.out.println("执行查询商品信息goodsinfo的过程中出现了错误！");
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
		
		return gi;
	}
	
	@Override
	public GoodsInfo loadRegardlessOfDeleted(String goods_id) {
		String sql = "select goods_id,goods_name,goods_info,goods_cat,goods_key,goods_oriprice,goods_curprice,"
				+ "goods_ttnum,goods_soldnum,goods_commentnum,goods_clicknum,goods_img,goods_isdeleted,goods_isonsite,goods_createtime from t_goodsinfo "
				+ "where goods_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GoodsInfo gi = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, goods_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				gi = new GoodsInfo(rs.getString("goods_id"), 
						rs.getString("goods_name"), 
						rs.getString("goods_info"), 
						rs.getInt("goods_cat"), 
						rs.getInt("goods_key"),
						rs.getDouble("goods_oriprice"), 
						rs.getDouble("goods_curprice"), 
						rs.getInt("goods_ttnum"), 
						rs.getInt("goods_soldnum"),
						rs.getInt("goods_commentnum"),
						rs.getInt("goods_clicknum"),
						rs.getString("goods_img"),
						rs.getInt("goods_isdeleted"),
						rs.getInt("goods_isonsite"),
						rs.getString("goods_createtime"));
			}
		} catch (Exception e) {
			System.out.println("执行查询商品信息goodsinfo的过程中出现了错误！");
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
		
		return gi;
	}

	@Override
	public void update(GoodsInfo gi) {
		String sql = "update t_goodsinfo set goods_name=?,goods_info=?,goods_cat=?,goods_key=?,goods_oriprice=?,goods_curprice=?,"
				+ "goods_ttnum=?,goods_soldnum=?,goods_commentnum=?,goods_clicknum=?,goods_img=?,goods_isdeleted=?,goods_isonsite=? where goods_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, gi.getGoods_name());
			pstmt.setString(2, gi.getGoods_info());
			pstmt.setInt(3, gi.getGoods_cat());
			pstmt.setInt(4, gi.getGoods_key());
			pstmt.setDouble(5, gi.getGoods_oriprice());
			pstmt.setDouble(6, gi.getGoods_curprice());
			pstmt.setInt(7, gi.getGoods_ttnum());
			pstmt.setInt(8, gi.getGoods_soldnum());
			pstmt.setInt(9, gi.getGoods_commentnum());
			pstmt.setInt(10, gi.getGoods_clicknum());
			pstmt.setString(11, gi.getGoods_img());
			pstmt.setInt(12, gi.getGoods_isdeleted());
			pstmt.setInt(13, gi.getGoods_isonsite());
			pstmt.setString(14, gi.getGoods_id());
			pstmt.execute();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			System.out.println("执行更改商品信息goodsinfo的过程中出现了错误！");
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
	public void insert(GoodsInfo gi) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(GoodsInfo gi) {
		String sql = "update t_goodsinfo set goods_isdeleted=1 where goods_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, gi.getGoods_id());
			pstmt.execute();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			System.out.println("执行管理员删除一条商品信息goodsinfo的过程中出现了错误！");
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
	
	//这是list_categray.html分页拿到所有已上架的商品的数量方法
	@Override
	public int getTotalRows(GoodsSearchChoices choices) {
		StringBuffer sql = new StringBuffer("select count(goods_id) from t_goodsinfo where goods_isdeleted=0 and goods_isonsite=1");
		if(choices.getGoods_name() != null && choices.getGoods_name() != "") {
			sql.append(" and goods_name like '%"+choices.getGoods_name()+"%'");
		}
		if(choices.getGoods_cat() != null && choices.getGoods_cat() != "") {
			sql.append(" and goods_cat="+Integer.parseInt(choices.getGoods_cat()));
		}
		if(choices.getGoods_key() != null && choices.getGoods_key() != "") {
			sql.append(" and goods_key="+Integer.parseInt(choices.getGoods_key()));
		}
		
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalRows = 0;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
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
	
	//这是admin商品管理页面分页按搜索结果，拿到所有未被删除的商品的数量方法
	@Override
	public int countAll(GoodsSearchChoices choices) {
		StringBuffer sql = new StringBuffer("select count(goods_id) from t_goodsinfo where goods_isdeleted=0");
		if(choices.getGoods_name() != null && choices.getGoods_name() != "") {
			sql.append(" and goods_name like '%"+choices.getGoods_name()+"%'");
		}
		if(choices.getGoods_info() != null && choices.getGoods_info() != "") {
			sql.append(" and goods_info like '%"+choices.getGoods_info()+"%'");
		}
		if(choices.getGoods_cat() != null && choices.getGoods_cat() != "") {
			sql.append(" and goods_cat="+Integer.parseInt(choices.getGoods_cat()));
		}
		if(choices.getGoods_key() != null && choices.getGoods_key() != "") {
			sql.append(" and goods_key="+Integer.parseInt(choices.getGoods_key()));
		}
		if(choices.getGoods_price_below() != null && choices.getGoods_price_below() != "") {
			sql.append(" and goods_curprice>="+Integer.parseInt(choices.getGoods_price_below()));
		}
		if(choices.getGoods_price_above() != null && choices.getGoods_price_above() != "") {
			sql.append(" and goods_curprice<="+Integer.parseInt(choices.getGoods_price_above()));
		}
		if(choices.getGoods_ttnum_below() != null && choices.getGoods_ttnum_below() != "") {
			sql.append(" and goods_ttnum>="+Integer.parseInt(choices.getGoods_ttnum_below()));
		}
		if(choices.getGoods_ttnum_above() != null && choices.getGoods_ttnum_above() != "") {
			sql.append(" and goods_ttnum<="+Integer.parseInt(choices.getGoods_ttnum_above()));
		}
		if(choices.getGoods_soldnum_below() != null && choices.getGoods_soldnum_below() != "") {
			sql.append(" and goods_soldnum>="+Integer.parseInt(choices.getGoods_soldnum_below()));
		}
		if(choices.getGoods_soldnum_above() != null && choices.getGoods_soldnum_above() != "") {
			sql.append(" and goods_soldnum<="+Integer.parseInt(choices.getGoods_soldnum_above()));
		}
		if(choices.getGoods_commentnum_below() != null && choices.getGoods_commentnum_below() != "") {
			sql.append(" and goods_commentnum>="+Integer.parseInt(choices.getGoods_commentnum_below()));
		}
		if(choices.getGoods_commentnum_above() != null && choices.getGoods_commentnum_above() != "") {
			sql.append(" and goods_commentnum<="+Integer.parseInt(choices.getGoods_commentnum_above()));
		}
		if(choices.getGoods_isonsite() != null && choices.getGoods_isonsite() != "") {
			sql.append(" and goods_isonsite="+Integer.parseInt(choices.getGoods_isonsite()));
		}
		System.out.println(sql);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalRows = 0;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
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
	public List<GoodsInfo> selectAll() {
		String sql = "select goods_id,goods_name,goods_info,goods_cat,goods_key,goods_oriprice,goods_curprice,"
				+ "goods_ttnum,goods_soldnum,goods_commentnum,goods_clicknum,goods_img,goods_isdeleted,goods_isonsite,goods_createtime "
				+ "from t_goodsinfo where goods_isdeleted=0 and goods_isonsite=1";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GoodsInfo gi = null;
		List<GoodsInfo> gis = new ArrayList<GoodsInfo>();
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				gi = new GoodsInfo(rs.getString("goods_id"), 
						rs.getString("goods_name"), 
						rs.getString("goods_info"), 
						rs.getInt("goods_cat"), 
						rs.getInt("goods_key"), 
						rs.getDouble("goods_oriprice"), 
						rs.getDouble("goods_curprice"), 
						rs.getInt("goods_ttnum"), 
						rs.getInt("goods_soldnum"),
						rs.getInt("goods_commentnum"),
						rs.getInt("goods_clicknum"),
						rs.getString("goods_img"),
						rs.getInt("goods_isdeleted"),
						rs.getInt("goods_isonsite"),
						rs.getString("goods_createtime"));
				gis.add(gi);
			}
		} catch (Exception e) {
			System.out.println("执行查询商品信息goodsinfo的过程中出现了错误！");
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
		
		return gis;
	}
	
	//这是list_categray.html分页拿到所有已上架的商品的方法
	@Override
	public List<GoodsInfo> selectAll(int offsetRows, int pageSize,GoodsSearchChoices choices) {
		StringBuffer sql = new StringBuffer("select goods_id,goods_name,goods_info,goods_cat,goods_key,goods_oriprice,goods_curprice,"
				+ "goods_ttnum,goods_soldnum,goods_commentnum,goods_clicknum,goods_img,goods_isdeleted,goods_isonsite,goods_createtime "
				+ "from t_goodsinfo where goods_isdeleted=0 and goods_isonsite=1");
		if(choices.getGoods_name() != null && choices.getGoods_name() != "") {
			sql.append(" and goods_name like '%"+choices.getGoods_name()+"%'");
		}
		if(choices.getGoods_cat() != null && choices.getGoods_cat() != "") {
			sql.append(" and goods_cat="+Integer.parseInt(choices.getGoods_cat()));
		}
		if(choices.getGoods_key() != null && choices.getGoods_key() != "") {
			sql.append(" and goods_key="+Integer.parseInt(choices.getGoods_key()));
		}
		if(choices.getOrder_condition() != null && choices.getOrder_condition() != null) {
			sql.append(" order by "+choices.getOrder_condition());
		}
		if(choices.getOrder_direction() != null && choices.getOrder_direction() != null) {
			sql.append(" "+choices.getOrder_direction());
		}
		sql.append(" limit ? , ?");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GoodsInfo gi = null;
		List<GoodsInfo> gis = new ArrayList<GoodsInfo>();
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, offsetRows);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				gi = new GoodsInfo(rs.getString("goods_id"), 
							    rs.getString("goods_name"), 
							    rs.getString("goods_info"), 
							    rs.getInt("goods_cat"), 
							    rs.getInt("goods_key"), 
							    rs.getDouble("goods_oriprice"), 
							    rs.getDouble("goods_curprice"), 
							    rs.getInt("goods_ttnum"), 
							    rs.getInt("goods_soldnum"),
							    rs.getInt("goods_commentnum"),
							    rs.getInt("goods_clicknum"),
							    rs.getString("goods_img"),
							    rs.getInt("goods_isdeleted"),
							    rs.getInt("goods_isonsite"),
							    rs.getString("goods_createtime"));
				gis.add(gi);
			}
		} catch (Exception e) {
			System.out.println("执行查询商品信息goodsinfo的过程中出现了错误！");
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
			
		return gis;
	}
	
	//这是admin商品管理页面分页按搜索结果拿到所有未被删除的商品的方法
	@Override
	public List<GoodsInfo> loadAll(int offsetRows, int pageSize, GoodsSearchChoices choices) {
		StringBuffer sql = new StringBuffer("select goods_id,goods_name,goods_info,goods_cat,goods_key,goods_oriprice,goods_curprice,"
				+ "goods_ttnum,goods_soldnum,goods_commentnum,goods_clicknum,goods_img,goods_isdeleted,goods_isonsite,goods_createtime "
				+ "from t_goodsinfo where goods_isdeleted=0") ;
		if(choices.getGoods_name() != null && choices.getGoods_name() != "" ) {
			sql.append(" and goods_name like '%"+choices.getGoods_name()+"%'");
		}
		if(choices.getGoods_info() != null && choices.getGoods_info() != "") {
			sql.append(" and goods_info like '%"+choices.getGoods_info()+"%'");
		}
		if(choices.getGoods_cat() != null && choices.getGoods_cat() != "") {
			sql.append(" and goods_cat="+Integer.parseInt(choices.getGoods_cat()));
		}
		if(choices.getGoods_key() != null && choices.getGoods_key() != "") {
			sql.append(" and goods_key="+Integer.parseInt(choices.getGoods_key()));
		}
		if(choices.getGoods_price_below() != null && choices.getGoods_price_below() != "" ) {
			sql.append(" and goods_curprice>="+Double.parseDouble(choices.getGoods_price_below()));
		}
		if(choices.getGoods_price_above() != null && choices.getGoods_price_above() != "") {
			sql.append(" and goods_curprice<="+Double.parseDouble(choices.getGoods_price_above()));
		}
		if(choices.getGoods_ttnum_below() != null && choices.getGoods_ttnum_below() != "") {
			sql.append(" and goods_ttnum>="+Integer.parseInt(choices.getGoods_ttnum_below()));
		}
		if(choices.getGoods_ttnum_above() != null && choices.getGoods_ttnum_above() != "") {
			sql.append(" and goods_ttnum<="+Integer.parseInt(choices.getGoods_ttnum_above()));
		}
		if(choices.getGoods_soldnum_below() != null && choices.getGoods_soldnum_below() != "") {
			sql.append(" and goods_soldnum>="+Integer.parseInt(choices.getGoods_soldnum_below()));
		}
		if(choices.getGoods_soldnum_above() != null && choices.getGoods_soldnum_above() != "") {
			sql.append(" and goods_soldnum<="+Integer.parseInt(choices.getGoods_soldnum_above()));
		}
		if(choices.getGoods_commentnum_below() != null && choices.getGoods_commentnum_below() != "") {
			sql.append(" and goods_commentnum>="+Integer.parseInt(choices.getGoods_commentnum_below()));
		}
		if(choices.getGoods_commentnum_above() != null && choices.getGoods_commentnum_above() != "") {
			sql.append(" and goods_commentnum<="+Integer.parseInt(choices.getGoods_commentnum_above()));
		}
		if(choices.getGoods_isonsite() != null && choices.getGoods_isonsite() != "") {
			sql.append(" and goods_isonsite="+Integer.parseInt(choices.getGoods_isonsite()));
		}
		sql.append(" limit ? , ?");
		System.out.println(sql);
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		GoodsInfo gi = null;
		List<GoodsInfo> gis = new ArrayList<GoodsInfo>();
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, offsetRows);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				gi = new GoodsInfo(rs.getString("goods_id"), 
						rs.getString("goods_name"), 
						rs.getString("goods_info"), 
						rs.getInt("goods_cat"), 
						rs.getInt("goods_key"), 
						rs.getDouble("goods_oriprice"), 
						rs.getDouble("goods_curprice"), 
						rs.getInt("goods_ttnum"), 
						rs.getInt("goods_soldnum"),
						rs.getInt("goods_commentnum"),
						rs.getInt("goods_clicknum"),
						rs.getString("goods_img"),
						rs.getInt("goods_isdeleted"),
						rs.getInt("goods_isonsite"),
						rs.getString("goods_createtime"));
				gis.add(gi);
			}
		} catch (Exception e) {
			System.out.println("执行管理员按搜索结果查询商品信息goodsinfo的过程中出现了错误！");
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
		
		return gis;
	}

	@Override
	public List<GoodsInfo> selectByCategory(int goods_cat) {
		return null;

	}

	
	

}
