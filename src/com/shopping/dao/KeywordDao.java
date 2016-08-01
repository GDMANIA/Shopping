package com.shopping.dao;

import java.util.List;

import com.shopping.entity.Keyword;

public interface KeywordDao {
	public void add(Keyword kw);
	
	public Keyword load(int key_id);
	
	public List<Keyword> loadAll();
	
	public List<Keyword> loadAll(int offsetRows, int pageSize);
	
	public int getTotalRows();
	
	public List<Keyword> loadByCategory(int cat_id);
	
	public List<Keyword> loadByCategory(int cat_id, int count);
	
	public void update(Keyword kw);
	
	public void delete(Keyword kw);
	
}
