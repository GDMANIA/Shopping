package com.shopping.dao;


import java.util.List;

import com.shopping.entity.GoodsSearchChoices;
import com.shopping.entity.User;
import com.shopping.entity.UserSearchChoices;

/**
 * ����User�������ݿ⽻���Ľӿ�
 */
public interface UserDao {
	public void add(User user);
	
	public User loadByName(String user_name);
	
	public User loadByEmail(String user_email);

	public User loadByTelnum(String user_telnum);
	
	public List<User> selectAll(int offsetRows, int pageSize, UserSearchChoices choices);
	
	public void update(User user);
	
	public int countAll();
	
	public int countAll(UserSearchChoices choices);
	
	public int countAllDeal();
	
	public double countAllPayment();
	
}
