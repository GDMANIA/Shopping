package com.shopping.dao;

import java.util.List;

import com.shopping.entity.OrderSearchChoices;
import com.shopping.entity.UserOrder;

public interface UserOrderDao {
	public void add(UserOrder uo);
	
	public UserOrder load(String userorder_id);
	
	public List<UserOrder> loadAll(String user_id);
	
	public List<UserOrder> loadAll(int offsetRows, int pageSize, OrderSearchChoices choices);
	
	public void update(UserOrder uo);
	
	public void updateInvalid(UserOrder uo);

	public void delete(UserOrder uo);
	
	public int countAll() ;
	
	public int countAll(OrderSearchChoices choices);
	
	public int countPaid() ;
	
	public int countSuccess() ;
}
