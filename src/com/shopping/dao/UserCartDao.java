package com.shopping.dao;

import java.util.List;

import com.shopping.entity.UserCart;

public interface UserCartDao {
	public void add(UserCart uc);
	
	public List<UserCart> load(String user_name);
	
	public UserCart load(String user_name, String goods_id);
	
	public void update(UserCart uc);
	
	public void delete(UserCart uc);
	
	public void deleteAll(String user_name);
}
