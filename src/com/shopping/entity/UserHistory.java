package com.shopping.entity;

import java.io.Serializable;

public class UserHistory implements Serializable{
	private static final long serialVersionUID = 8566579353487908685L;
	private int userhistory_id;
	private String user_id;
	private String user_name;
	private String goods_ids;
	public UserHistory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserHistory(int userhistory_id, String user_id, String user_name,
			String goods_ids) {
		super();
		this.userhistory_id = userhistory_id;
		this.user_id = user_id;
		this.user_name = user_name;
		this.goods_ids = goods_ids;
	}
	public int getUserhistory_id() {
		return userhistory_id;
	}
	public void setUserhistory_id(int userhistory_id) {
		this.userhistory_id = userhistory_id;
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
	public String getGoods_ids() {
		return goods_ids;
	}
	public void setGoods_ids(String goods_ids) {
		this.goods_ids = goods_ids;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((goods_ids == null) ? 0 : goods_ids.hashCode());
		result = prime * result + ((user_id == null) ? 0 : user_id.hashCode());
		result = prime * result
				+ ((user_name == null) ? 0 : user_name.hashCode());
		result = prime * result + userhistory_id;
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
		UserHistory other = (UserHistory) obj;
		if (goods_ids == null) {
			if (other.goods_ids != null)
				return false;
		} else if (!goods_ids.equals(other.goods_ids))
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
		if (userhistory_id != other.userhistory_id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UserHistory [userhistory_id=" + userhistory_id + ", user_id="
				+ user_id + ", user_name=" + user_name + ", goods_ids="
				+ goods_ids + "]";
	}
	
}
