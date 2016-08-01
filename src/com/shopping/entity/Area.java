package com.shopping.entity;

import java.io.Serializable;

public class Area implements Serializable{
	private static final long serialVersionUID = 7902691813773246073L;
	private String area_id;
	private String area_name;
	private String city_id;
	public Area() {
		super();
	}
	public Area(String area_id, String area_name, String city_id) {
		super();
		this.area_id = area_id;
		this.area_name = area_name;
		this.city_id = city_id;
	}
	public String getArea_id() {
		return area_id;
	}
	public void setArea_id(String area_id) {
		this.area_id = area_id;
	}
	public String getArea_name() {
		return area_name;
	}
	public void setArea_name(String area_name) {
		this.area_name = area_name;
	}
	public String getCity_id() {
		return city_id;
	}
	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((area_id == null) ? 0 : area_id.hashCode());
		result = prime * result
				+ ((area_name == null) ? 0 : area_name.hashCode());
		result = prime * result + ((city_id == null) ? 0 : city_id.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Area other = (Area) obj;
		if (area_id == null) {
			if (other.area_id != null)
				return false;
		} else if (!area_id.equals(other.area_id))
			return false;
		if (area_name == null) {
			if (other.area_name != null)
				return false;
		} else if (!area_name.equals(other.area_name))
			return false;
		if (city_id == null) {
			if (other.city_id != null)
				return false;
		} else if (!city_id.equals(other.city_id))
			return false;
		return true;
	}
	
}
