package com.shopping.dao;

import java.util.List;

import com.shopping.entity.Category;

public interface CategoryDao {
	public void add(Category cat);
	
	public Category load(int cat_id);
	
	public List<Category> selectAll();
	
	public void update(Category cat);
	
	public void delete(Category cat);
}
