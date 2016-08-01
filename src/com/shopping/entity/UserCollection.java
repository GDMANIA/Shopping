package com.shopping.entity;

import java.io.Serializable;

public class UserCollection implements Serializable{
	private static final long serialVersionUID = -5048442450327574546L;
	private int usercollection_id;
	private String user_id;
	private String user_name;
	private String goods_id;
	public UserCollection() {
		super();
	}
	public UserCollection(int usercollection_id, String user_id,
			String user_name, String goods_id) {
		super();
		this.usercollection_id = usercollection_id;
		this.user_id = user_id;
		this.user_name = user_name;
		this.goods_id = goods_id;
	}
	public int getUsercollection_id() {
		return usercollection_id;
	}
	public void setUsercollection_id(int usercollection_id) {
		this.usercollection_id = usercollection_id;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((goods_id == null) ? 0 : goods_id.hashCode());
		result = prime * result + ((user_id == null) ? 0 : user_id.hashCode());
		result = prime * result
				+ ((user_name == null) ? 0 : user_name.hashCode());
		result = prime * result + usercollection_id;
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
		UserCollection other = (UserCollection) obj;
		if (goods_id == null) {
			if (other.goods_id != null)
				return false;
		} else if (!goods_id.equals(other.goods_id))
			return false;
		if (user_id == null) {
			if (other.user_id != null)
				return false;
		} else if (!user_id.equals(other.user_id))
			return false;
		if (user_name == null) {
			if (other.user_name != null)
				return false;
		} else if (!user_name.equals(other.user_name))
			return false;
		if (usercollection_id != other.usercollection_id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UserCollection [usercollection_id=" + usercollection_id
				+ ", user_id=" + user_id + ", user_name=" + user_name
				+ ", goods_id=" + goods_id + "]";
	}
	
	
}
