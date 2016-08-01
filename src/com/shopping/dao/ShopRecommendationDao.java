package com.shopping.dao;

import java.util.List;

import com.shopping.entity.ShopRecommendation;

public interface ShopRecommendationDao {
	public void add(ShopRecommendation sr);
	
	public List<ShopRecommendation> load();
	
	public List<ShopRecommendation> loadByGoodsCat(int goods_cat,int goods_key);
	
	public void update(ShopRecommendation sr);
	
	public void delete(ShopRecommendationDao sr);
	
}
