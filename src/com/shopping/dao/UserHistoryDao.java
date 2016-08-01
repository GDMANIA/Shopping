package com.shopping.dao;

import com.shopping.entity.User;
import com.shopping.entity.UserHistory;

public interface UserHistoryDao {
	public void add(User user);
	
	public UserHistory load(String user_name);
	
	public void update(UserHistory uh);
	
	public void delete(UserHistory uh);
}
