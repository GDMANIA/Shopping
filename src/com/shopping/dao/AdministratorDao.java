package com.shopping.dao;

import com.shopping.entity.Administrator;

public interface AdministratorDao {

	public Administrator load(String administrator_name);
	
	public void update(Administrator admin);
}
