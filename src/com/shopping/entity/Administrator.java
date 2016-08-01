package com.shopping.entity;

import java.io.Serializable;

public class Administrator implements Serializable{
	private static final long serialVersionUID = 7243786136247832569L;
	private String administrator_id;
	private String administrator_name;
	private String administrator_password;
	public Administrator() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Administrator(String administrator_id, String administrator_name,
			String administrator_password) {
		super();
		this.administrator_id = administrator_id;
		this.administrator_name = administrator_name;
		this.administrator_password = administrator_password;
	}
	public String getAdministrator_id() {
		return administrator_id;
	}
	public void setAdministrator_id(String administrator_id) {
		this.administrator_id = administrator_id;
	}
	public String getAdministrator_name() {
		return administrator_name;
	}
	public void setAdministrator_name(String administrator_name) {
		this.administrator_name = administrator_name;
	}
	public String getAdministrator_password() {
		return administrator_password;
	}
	public void setAdministrator_password(String administrator_password) {
		this.administrator_password = administrator_password;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((administrator_id == null) ? 0 : administrator_id.hashCode());
		result = prime
				* result
				+ ((administrator_name == null) ? 0 : administrator_name
						.hashCode());
		result = prime
				* result
				+ ((administrator_password == null) ? 0
						: administrator_password.hashCode());
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
		Administrator other = (Administrator) obj;
		if (administrator_id == null) {
			if (other.administrator_id != null)
				return false;
		} else if (!administrator_id.equals(other.administrator_id))
			return false;
		if (administrator_name == null) {
			if (other.administrator_name != null)
				return false;
		} else if (!administrator_name.equals(other.administrator_name))
			return false;
		if (administrator_password == null) {
			if (other.administrator_password != null)
				return false;
		} else if (!administrator_password.equals(other.administrator_password))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Administrator [administrator_id=" + administrator_id
				+ ", administrator_name=" + administrator_name
				+ ", administrator_password=" + administrator_password + "]";
	}
	
	
}
