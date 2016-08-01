package com.shopping.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.shopping.dao.DistrictDao;
import com.shopping.entity.Area;
import com.shopping.entity.City;
import com.shopping.entity.Province;
import com.shopping.utils.DBUtil;


public class DistrictDaoImpl implements DistrictDao {

	@Override
	public List<Province> selectProvinces() {
		String sql = "select provinceid, province from provinces";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Province p = null;
		List<Province> provinces = new ArrayList<Province>();
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				p = new Province();
				p.setProvince_id(rs.getString(1));
				p.setProvince(rs.getString(2));
				provinces.add(p);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
		return provinces;
	}

	@Override
	public List<City> selectCities(String province_id) {
		String sql = "select cityid, city from cities where provinceid='" + province_id + "'";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		City c = null;
		List<City> cities = new ArrayList<City>();
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				c = new City();
				c.setCity_id(rs.getString(1));
				c.setCity_name(rs.getString(2));
				cities.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
		return cities;
	}

	@Override
	public List<Area> selectAreas(String city_id) {
		String sql = "select areaid, area from areas where cityid='" + city_id + "'";
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		Area a = null;
		List<Area> areas = new ArrayList<Area>();
		try {
			conn = DBUtil.getConnection();
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			while(rs.next()) {
				a = new Area();
				a.setArea_id(rs.getString(1));
				a.setArea_name(rs.getString(2));
				areas.add(a);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			DBUtil.release(rs, pstmt, conn);
		}
		return areas;
	}
}



