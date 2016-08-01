package com.shopping.entity;

import java.io.Serializable;

public class Keyword implements Serializable{
	private static final long serialVersionUID = -7589092993551782060L;
	private int key_id;
	private String key_name;
	private int cat_id;
	private String cat_name;
	private String administrator_name;
	private String key_createtime;
	public Keyword() {
		super();
	}
	
	

	public Keyword(int key_id, String key_name, int cat_id,
			String administrator_name, String cat_createtime) {
		super();
		this.key_id = key_id;
		this.key_name = key_name;
		this.cat_id = cat_id;
		this.administrator_name = administrator_name;
		this.key_createtime = cat_createtime;
	}



	public int getKey_id() {
		return key_id;
	}
	public void setKey_id(int key_id) {
		this.key_id = key_id;
	}
	public String getKey_name() {
		return key_name;
	}
	public void setKey_name(String key_name) {
		this.key_name = key_name;
	}
	public int getCat_id() {
		return cat_id;
	}
	public void setCat_id(int cat_id) {
		this.cat_id = cat_id;
	}



	public String getCat_name() {
		return cat_name;
	}



	public void setCat_name(String cat_name) {
		this.cat_name = cat_name;
	}



	public String getAdministrator_name() {
		return administrator_name;
	}



	public void setAdministrator_name(String administrator_name) {
		this.administrator_name = administrator_name;
	}



	public String getKey_createtime() {
		return key_createtime;
	}



	public void setKey_createtime(String key_createtime) {
		this.key_createtime = key_createtime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((administrator_name == null) ? 0 : administrator_name
						.hashCode());
		result = prime * result
				+ ((key_createtime == null) ? 0 : key_createtime.hashCode());
		result = prime * result + cat_id;
		result = prime * result
				+ ((cat_name == null) ? 0 : cat_name.hashCode());
		result = prime * result + key_id;
		result = prime * result
				+ ((key_name == null) ? 0 : key_name.hashCode());
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
		Keyword other = (Keyword) obj;
		if (administrator_name == null) {
			if (other.administrator_name != null)
				return false;
		} else if (!administrator_name.equals(other.administrator_name))
			return false;
		if (key_createtime == null) {
			if (other.key_createtime != null)
				return false;
		} else if (!key_createtime.equals(other.key_createtime))
			return false;
		if (cat_id != other.cat_id)
			return false;
		if (cat_name == null) {
			if (other.cat_name != null)
				return false;
		} else if (!cat_name.equals(other.cat_name))
			return false;
		if (key_id != other.key_id)
			return false;
		if (key_name == null) {
			if (other.key_name != null)
				return false;
		} else if (!key_name.equals(other.key_name))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "Keyword [key_id=" + key_id + ", key_name=" + key_name
				+ ", cat_id=" + cat_id + ", cat_name=" + cat_name
				+ ", administrator_name=" + administrator_name
				+ ", cat_createtime=" + key_createtime + "]";
	}


	
	
	
}
