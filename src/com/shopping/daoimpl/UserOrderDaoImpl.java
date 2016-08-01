package com.shopping.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.shopping.dao.UserOrderDao;
import com.shopping.entity.OrderSearchChoices;
import com.shopping.entity.UserCart;
import com.shopping.entity.UserOrder;
import com.shopping.entity.UserSearchChoices;
import com.shopping.utils.DBUtil;

public class UserOrderDaoImpl implements UserOrderDao {

	@Override
	public void add(UserOrder uo) {
		String sql_userorder = "insert into t_userorder(userorder_id,user_id,user_name,order_createtime,goods_id_num,order_ttprice,order_address,order_state,order_createtimestamp) values(?,?,?,?,?,?,?,?,unix_timestamp(now()))";
		String sql_goodsinfo = "update t_goodsinfo set goods_ttnum=goods_ttnum-?,goods_soldnum=goods_soldnum+? where goods_id=?";
		String sql_shoprecommendation = "update t_shoprecommendation set goods_soldnum=goods_soldnum+? where goods_id=?";
		String sql_usercart = "delete from t_usercart where user_id=? and goods_id=?";
		String[] goods_id_nums = uo.getGoods_id_num().split(",");
		String goods_id = "";
		int goods_num = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql_userorder);
			pstmt.setString(1,uo.getUserorder_id());
			pstmt.setString(2, uo.getUser_id());
			pstmt.setString(3, uo.getUser_name());
			pstmt.setString(4, uo.getOrder_createtime());
			pstmt.setString(5,uo.getGoods_id_num());
			pstmt.setDouble(6, uo.getOrder_ttprice());
			pstmt.setString(7, uo.getOrder_address());
			pstmt.setInt(8, uo.getOrder_state());
			pstmt.execute();
			
			for (int i = 0; i < goods_id_nums.length; i++) {
				goods_id = goods_id_nums[i].split(":")[0];
				goods_num = Integer.parseInt(goods_id_nums[i].split(":")[1]);
				pstmt = conn.prepareStatement(sql_goodsinfo);
				pstmt.setInt(1, goods_num);
				pstmt.setInt(2, goods_num);
				pstmt.setString(3, goods_id);
				pstmt.execute();
				
				pstmt = conn.prepareStatement(sql_shoprecommendation);
				pstmt.setInt(1, goods_num);
				pstmt.setString(2, goods_id);
				pstmt.execute();
				
				pstmt = conn.prepareStatement(sql_usercart);
				pstmt.setString(1, uo.getUser_id());
				pstmt.setString(2, goods_id);
				pstmt.execute();
				
			}
			
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			System.out.println("执行添加一条订单UserOrder的过程中出现了错误！");
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
	public UserOrder load(String userorder_id) {
		String sql = "select userorder_id,user_id,user_name,order_createtime,goods_id_num,order_ttprice,order_address,order_state,order_isdeleted from t_userorder where userorder_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserOrder uo = null;
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userorder_id);
			rs = pstmt.executeQuery();
			if(rs.next()) {
				uo = new UserOrder(rs.getString("userorder_id"),
								rs.getString("user_id"),
							    rs.getString("user_name"),
							    rs.getString("order_createtime"),
							    rs.getString("goods_id_num"),
							    rs.getDouble("order_ttprice"),
							    rs.getString("order_address"),
							    rs.getInt("order_state"),
							    rs.getInt("order_isdeleted"));
			}
		} catch (Exception e) {
			System.out.println("执行查询某用户一条订单UserOrder的过程中出现了错误！");
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
			
		return uo;
	}

	@Override
	public List<UserOrder> loadAll(String user_id) {
		String sql = "select userorder_id,user_id,user_name,order_createtime,goods_id_num,order_ttprice,order_address,order_state,order_isdeleted from t_userorder where user_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserOrder uo = null;
		List<UserOrder> uos = new ArrayList<UserOrder>();
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, user_id);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				uo = new UserOrder(rs.getString("userorder_id"),
								rs.getString("user_id"),
							    rs.getString("user_name"),
							    rs.getString("order_createtime"),
							    rs.getString("goods_id_num"),
							    rs.getDouble("order_ttprice"),
							    rs.getString("order_address"),
							    rs.getInt("order_state"),
							    rs.getInt("order_isdeleted"));
				uos.add(uo);
			}
		} catch (Exception e) {
			System.out.println("执行查询某用户所有订单UserOrders的过程中出现了错误！");
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
			
		return uos;
	}
	
	@Override
	public List<UserOrder> loadAll(int offsetRows, int pageSize, OrderSearchChoices choices) {
		StringBuffer sql = new StringBuffer("select userorder_id,user_id,user_name,order_createtime,goods_id_num,order_ttprice,order_address,order_state,order_isdeleted from t_userorder where userorder_id is not null");
		if(choices.getUser_name() != null && choices.getUser_name() != "" ) {
			sql.append(" and user_name like '%"+choices.getUser_name()+"%'");
		}
		if(choices.getOrder_state() != null && choices.getOrder_state() != "" ) {
			if("待支付".equals(choices.getOrder_state())) {
				sql.append(" and order_state=0");
			}
			if("待发货".equals(choices.getOrder_state())) {
				sql.append(" and order_state=1");
			}
			if("已发货".equals(choices.getOrder_state())) {
				sql.append(" and order_state=2");
			}
			if("待评价".equals(choices.getOrder_state())) {
				sql.append(" and order_state=3");
			}
			if("已评价".equals(choices.getOrder_state())) {
				sql.append(" and order_state=4");
			}
			if("无效".equals(choices.getOrder_state())) {
				sql.append(" and order_state=5");
			}
			
		}
		if(choices.getOrder_isdeleted() != null && choices.getOrder_isdeleted() != "" ) {
			if("未删除".equals(choices.getOrder_isdeleted())) {
				sql.append(" and order_isdeleted=0");
			}
			if("已删除".equals(choices.getOrder_isdeleted())) {
				sql.append(" and order_isdeleted=1");
			}
			if("不再显示".equals(choices.getOrder_isdeleted())) {
				sql.append(" and order_isdeleted=2");
			}
		}
		if(choices.getOrder_ttprice_below() != null && choices.getOrder_ttprice_below() != "" ) {
			sql.append(" and order_ttprice>="+Double.parseDouble(choices.getOrder_ttprice_below()));
		}
		if(choices.getOrder_ttprice_above() != null && choices.getOrder_ttprice_above() != "") {
			sql.append(" and order_ttprice<="+Double.parseDouble(choices.getOrder_ttprice_above()));
		}
		sql.append(" limit ? , ?");
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		UserOrder uo = null;
		List<UserOrder> uos = new ArrayList<UserOrder>();
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql.toString());
			pstmt.setInt(1, offsetRows);
			pstmt.setInt(2, pageSize);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				uo = new UserOrder(rs.getString("userorder_id"),
						rs.getString("user_id"),
						rs.getString("user_name"),
						rs.getString("order_createtime"),
						rs.getString("goods_id_num"),
						rs.getDouble("order_ttprice"),
						rs.getString("order_address"),
						rs.getInt("order_state"),
						rs.getInt("order_isdeleted"));
				uos.add(uo);
			}
		} catch (Exception e) {
			System.out.println("执行查询按页查询所有订单UserOrders的过程中出现了错误！");
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
		
		return uos;
	}

	@Override
	public void update(UserOrder uo) {
		String sql = "update t_userorder set order_state=?,order_isdeleted=? where userorder_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, uo.getOrder_state());
			pstmt.setInt(2, uo.getOrder_isdeleted());
			pstmt.setString(3, uo.getUserorder_id());
			pstmt.execute();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			System.out.println("执行更改一条用户订单UserOrder的过程中出现了错误！");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}

	}
	
	@Override
	public void updateInvalid(UserOrder uo) {
		String sql_userorder = "update t_userorder set order_state=?,order_isdeleted=? where userorder_id=?";
		String sql_goodsinfo = "update t_goodsinfo set goods_ttnum=goods_ttnum+?,goods_soldnum=goods_soldnum-? where goods_id=?";
		String sql_shoprecommendation = "update t_shoprecommendation set goods_soldnum=goods_soldnum-? where goods_id=?";
		String[] goods_id_nums = uo.getGoods_id_num().split(",");
		String goods_id = "";
		int goods_num = 0;
		Connection conn = null;
		PreparedStatement pstmt = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql_userorder);
			pstmt.setInt(1, uo.getOrder_state());
			pstmt.setInt(2, uo.getOrder_isdeleted());
			pstmt.setString(3,uo.getUserorder_id());
			pstmt.execute();
			
			for (int i = 0; i < goods_id_nums.length; i++) {
				goods_id = goods_id_nums[i].split(":")[0];
				goods_num = Integer.parseInt(goods_id_nums[i].split(":")[1]);
				pstmt = conn.prepareStatement(sql_goodsinfo);
				pstmt.setInt(1, goods_num);
				pstmt.setInt(2, goods_num);
				pstmt.setString(3, goods_id);
				pstmt.execute();
				
				pstmt = conn.prepareStatement(sql_shoprecommendation);
				pstmt.setInt(1, goods_num);
				pstmt.setString(2, goods_id);
				pstmt.execute();
				
			}
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			System.out.println("执行将一条无效或者未支付的用户订单UserOrder改为删除状态的过程中出现了错误！");
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
	public void delete(UserOrder uo) {
		String sql = "delete from t_userorder where userorder_id=?";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			conn.setAutoCommit(false);
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, uo.getUserorder_id());
			pstmt.execute();
			conn.commit();
			conn.setAutoCommit(true);
		} catch (Exception e) {
			System.out.println("执行管理员删除一条用户订单UserOrder的过程中出现了错误！");
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}


	}

	
	@Override
	public int countAll(OrderSearchChoices choices) {
		StringBuffer sql = new StringBuffer("select count(userorder_id) from t_userorder where userorder_id is not null");
		if(choices.getUser_name() != null && choices.getUser_name() != "" ) {
			sql.append(" and user_name like '%"+choices.getUser_name()+"%'");
		}
		if(choices.getOrder_state() != null && choices.getOrder_state() != "" ) {
			if("待支付".equals(choices.getOrder_state())) {
				sql.append(" and order_state=0");
			}
			if("待发货".equals(choices.getOrder_state())) {
				sql.append(" and order_state=1");
			}
			if("已发货".equals(choices.getOrder_state())) {
				sql.append(" and order_state=2");
			}
			if("待评价".equals(choices.getOrder_state())) {
				sql.append(" and order_state=3");
			}
			if("已评价".equals(choices.getOrder_state())) {
				sql.append(" and order_state=4");
			}
			if("无效".equals(choices.getOrder_state())) {
				sql.append(" and order_state=5");
			}
			
		}
		if(choices.getOrder_isdeleted() != null && choices.getOrder_isdeleted() != "" ) {
			if("未删除".equals(choices.getOrder_isdeleted())) {
				sql.append(" and order_isdeleted=0");
			}
			if("已删除".equals(choices.getOrder_isdeleted())) {
				sql.append(" and order_isdeleted=1");
			}
			if("不再显示".equals(choices.getOrder_isdeleted())) {
				sql.append(" and order_isdeleted=2");
			}
		}
		if(choices.getOrder_ttprice_below() != null && choices.getOrder_ttprice_below() != "" ) {
			sql.append(" and order_ttprice>="+Double.parseDouble(choices.getOrder_ttprice_below()));
		}
		if(choices.getOrder_ttprice_above() != null && choices.getOrder_ttprice_above() != "") {
			sql.append(" and order_ttprice<="+Double.parseDouble(choices.getOrder_ttprice_above()));
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
	
	@Override
	public int countAll() {
		String sql = "select count(userorder_id) from t_userorder";
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
	public int countPaid() {
		String sql = "select count(userorder_id) from t_userorder where order_state=1 or order_state=2 or order_state=3 or order_state=4";
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
	public int countSuccess() {
		String sql = "select count(userorder_id) from t_userorder where order_state=3 or order_state=4";
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

	

}
