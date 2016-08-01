package com.shopping.dao;

import com.shopping.entity.UserCollection;

public interface UserCollectionDao {
	public void add(UserCollection uc);
	
	public UserCollection load(String user_name);
	
	public void update(UserCollection uc);
	
	public void delete(UserCollection uc);
}
