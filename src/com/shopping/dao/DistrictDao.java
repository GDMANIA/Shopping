package com.shopping.dao;

import java.util.List;

import com.shopping.entity.Area;
import com.shopping.entity.City;
import com.shopping.entity.Province;


public interface DistrictDao {	
	public List<Province> selectProvinces();
		
	public List<City> selectCities(String province_id);
	
	public List<Area> selectAreas(String city_id);
	
}
