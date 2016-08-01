package com.shopping.dao;

import java.util.List;

import com.shopping.entity.AdminLoginRecord;

public interface AdminLoginRecordDao {
	public void add(AdminLoginRecord alr);
	
	public List<AdminLoginRecord> selectLatestFive();
	
	public List<AdminLoginRecord> selectAll();
	
}
