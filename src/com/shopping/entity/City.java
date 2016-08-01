package com.shopping.entity;

import java.io.Serializable;

public class City implements Serializable{
	private static final long serialVersionUID = 492264370227392813L;
	private String city_id;
	private String city_name;
	private String province_id;
	public City() {
		super();
		// TODO Auto-generated constructor stub
	}
	public City(String city_id, String city_name, String province_id) {
		super();
		this.city_id = city_id;
		this.city_name = city_name;
		this.province_id = province_id;
	}
	public String getCity_id() {
		return city_id;
	}
	public void setCity_id(String city_id) {
		this.city_id = city_id;
	}
	public String getCity_name() {
		return city_name;
	}
	public void setCity_name(String city_name) {
		this.city_name = city_name;
	}
	public String getProvince_id() {
		return province_id;
	}
	public void setProvince_id(String province_id) {
		this.province_id = province_id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((city_id == null) ? 0 : city_id.hashCode());
		result = prime * result
				+ ((city_name == null) ? 0 : city_name.hashCode());
		result = prime * result
				+ ((province_id == null) ? 0 : province_id.hashCode());
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
		City other = (City) obj;
		if (city_id == null) {
			if (other.city_id != null)
				return false;
		} else if (!city_id.equals(other.city_id))
			return false;
		if (city_name == null) {
			if (other.city_name != null)
				return false;
		} else if (!city_name.equals(other.city_name))
			return false;
		if (province_id == null) {
			if (other.province_id != null)
				return false;
		} else if (!province_id.equals(other.province_id))
			return false;
		return true;
	}
	
}
