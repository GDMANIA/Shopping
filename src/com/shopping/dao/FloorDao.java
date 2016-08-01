package com.shopping.dao;

import java.util.List;

import com.shopping.entity.Floor;

public interface FloorDao {
	public void add(Floor fr);
	
	public Floor load(int floor_id);
	
	public void update(Floor fr);
	
	public void delete(Floor fr);

	List<Floor> selectAll(); 
}
