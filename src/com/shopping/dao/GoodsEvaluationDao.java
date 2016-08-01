package com.shopping.dao;

import java.util.List;

import com.shopping.entity.GoodsEvaluation;
import com.shopping.entity.UserOrder;

public interface GoodsEvaluationDao {
	public void add(GoodsEvaluation ge);
	
	public GoodsEvaluation load(String goods_id);
	
	public List<GoodsEvaluation> loadAll(String goods_id);
	
	public void update(GoodsEvaluation ge);
	
	public void delete(GoodsEvaluation ge);
}
