package com.shopping.dao;

import java.util.List;

import com.shopping.entity.UserAddress;

public interface UserAddressDao {
	public void add(UserAddress ua);
	
	public UserAddress load(int userAddress_id);
	
	public int loadId(UserAddress ua);
	
	public List<UserAddress> selectAllByUserName(String user_name);
	
	public void update(UserAddress ua);
	
	public void delete(UserAddress ua);
	
	public void delete(int userAddress_id);
}
