package com.shopping.entity;

import java.io.Serializable;

public class AdminLoginRecord implements Serializable{
	private static final long serialVersionUID = 2878404878946099536L;
	private int record_id;
	private String administrator_name;
	private String login_time;
	public AdminLoginRecord() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AdminLoginRecord(int record_id, String administrator_name,
			String login_time) {
		super();
		this.record_id = record_id;
		this.administrator_name = administrator_name;
		this.login_time = login_time;
	}
	public int getRecord_id() {
		return record_id;
	}
	public void setRecord_id(int record_id) {
		this.record_id = record_id;
	}
	public String getAdministrator_name() {
		return administrator_name;
	}
	public void setAdministrator_name(String administrator_name) {
		this.administrator_name = administrator_name;
	}
	public String getLogin_time() {
		return login_time;
	}
	public void setLogin_time(String login_time) {
		this.login_time = login_time;
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
				+ ((login_time == null) ? 0 : login_time.hashCode());
		result = prime * result + record_id;
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
		AdminLoginRecord other = (AdminLoginRecord) obj;
		if (administrator_name == null) {
			if (other.administrator_name != null)
				return false;
		} else if (!administrator_name.equals(other.administrator_name))
			return false;
		if (login_time == null) {
			if (other.login_time != null)
				return false;
		} else if (!login_time.equals(other.login_time))
			return false;
		if (record_id != other.record_id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "AdminLoginRecord [record_id=" + record_id
				+ ", administrator_name=" + administrator_name
				+ ", login_time=" + login_time + "]";
	}
	
}
