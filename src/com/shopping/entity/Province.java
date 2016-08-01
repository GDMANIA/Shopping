package com.shopping.entity;

import java.io.Serializable;

public class Province implements Serializable{
	private static final long serialVersionUID = 8833375618938943180L;
	private String province_id;
	private String province;
	public Province() {
		super();
	}
	public Province(String province_id, String province) {
		super();
		this.province_id = province_id;
		this.province = province;
	}
	public String getProvince_id() {
		return province_id;
	}
	public void setProvince_id(String province_id) {
		this.province_id = province_id;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((province == null) ? 0 : province.hashCode());
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
		Province other = (Province) obj;
		if (province == null) {
			if (other.province != null)
				return false;
		} else if (!province.equals(other.province))
			return false;
		if (province_id == null) {
			if (other.province_id != null)
				return false;
		} else if (!province_id.equals(other.province_id))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Province [province_id=" + province_id + ", province="
				+ province + "]";
	}
	
}
