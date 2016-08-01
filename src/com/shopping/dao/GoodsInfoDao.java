package com.shopping.dao;

import java.util.List;

import com.shopping.entity.GoodsInfo;
import com.shopping.entity.GoodsSearchChoices;

public interface GoodsInfoDao {
	public void add(GoodsInfo gi);
	
	public GoodsInfo load(String goods_id);
	
	public GoodsInfo loadRegardlessOfOnSite(String goods_id) ;
	
	public GoodsInfo loadRegardlessOfDeleted(String goods_id);
	
	public void update(GoodsInfo gi);
	
	public void insert(GoodsInfo gi);
	
	public void delete(GoodsInfo gi);
	
	public int getTotalRows(GoodsSearchChoices choicess);
	
	public int countAll(GoodsSearchChoices choices);
	
	public List<GoodsInfo> selectAll();
	
	public List<GoodsInfo> selectAll(int offsetRows, int pageSize, GoodsSearchChoices choices);
	
	public List<GoodsInfo> loadAll(int offsetRows, int pageSize, GoodsSearchChoices choices);
	
	public List<GoodsInfo> selectByCategory(int goods_cat);
}
