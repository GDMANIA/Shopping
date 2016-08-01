package com.shopping.entity;

import java.io.Serializable;
import java.util.List;

public class Category implements Serializable{
	private static final long serialVersionUID = -1466298593160978694L;
	private int cat_id;
	private String cat_name;
	private String administrator_name;
	private String cat_createtime;
	private List<Keyword> keys;
	public Category() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Category(int cat_id, String cat_name, String administrator_name,
			String cat_createtime) {
		super();
		this.cat_id = cat_id;
		this.cat_name = cat_name;
		this.administrator_name = administrator_name;
		this.cat_createtime = cat_createtime;
	}


	public Category(int cat_id, String cat_name, String administrator_name,
			String cat_createtime, List<Keyword> keys) {
		super();
		this.cat_id = cat_id;
		this.cat_name = cat_name;
		this.administrator_name = administrator_name;
		this.cat_createtime = cat_createtime;
		this.keys = keys;
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

	public String getCat_createtime() {
		return cat_createtime;
	}

	public void setCat_createtime(String cat_createtime) {
		this.cat_createtime = cat_createtime;
	}

	public List<Keyword> getKeys() {
		return keys;
	}

	public void setKeys(List<Keyword> keys) {
		this.keys = keys;
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
				+ ((cat_createtime == null) ? 0 : cat_createtime.hashCode());
		result = prime * result + cat_id;
		result = prime * result
				+ ((cat_name == null) ? 0 : cat_name.hashCode());
		result = prime * result + ((keys == null) ? 0 : keys.hashCode());
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
		Category other = (Category) obj;
		if (administrator_name == null) {
			if (other.administrator_name != null)
				return false;
		} else if (!administrator_name.equals(other.administrator_name))
			return false;
		if (cat_createtime == null) {
			if (other.cat_createtime != null)
				return false;
		} else if (!cat_createtime.equals(other.cat_createtime))
			return false;
		if (cat_id != other.cat_id)
			return false;
		if (cat_name == null) {
			if (other.cat_name != null)
				return false;
		} else if (!cat_name.equals(other.cat_name))
			return false;
		if (keys == null) {
			if (other.keys != null)
				return false;
		} else if (!keys.equals(other.keys))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Category [cat_id=" + cat_id + ", cat_name=" + cat_name
				+ ", administrator_name=" + administrator_name
				+ ", cat_createtime=" + cat_createtime + ", keys=" + keys + "]";
	}
	
	
	
	
}
