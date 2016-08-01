package com.shopping.entity;

import java.io.Serializable;

public class User implements Serializable{
	private static final long serialVersionUID = -7883285522848885086L;
	private String user_id;
	private int user_level;
	private String user_name;
	private String user_password;
	private String user_email;
	private String user_telnum;
	private String user_img;
	private double user_ttpayment;
	private String user_regtime;
	private String user_lastlogintime;
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String user_id, int user_level, String user_name,
			String user_password, String user_email, String user_telnum,
			String user_img, double user_ttpayment, String user_regtime,
			String user_lastlogintime) {
		super();
		this.user_id = user_id;
		this.user_level = user_level;
		this.user_name = user_name;
		this.user_password = user_password;
		this.user_email = user_email;
		this.user_telnum = user_telnum;
		this.user_img = user_img;
		this.user_ttpayment = user_ttpayment;
		this.user_regtime = user_regtime;
		this.user_lastlogintime = user_lastlogintime;
	}
	public String getUser_id() {
		return user_id;
	}
	public void setUser_id(String user_id) {
		this.user_id = user_id;
	}
	public int getUser_level() {
		return user_level;
	}
	public void setUser_level(int user_level) {
		this.user_level = user_level;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_password() {
		return user_password;
	}
	public void setUser_password(String user_password) {
		this.user_password = user_password;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_telnum() {
		return user_telnum;
	}
	public void setUser_telnum(String user_telnum) {
		this.user_telnum = user_telnum;
	}
	public String getUser_img() {
		return user_img;
	}
	public void setUser_img(String user_img) {
		this.user_img = user_img;
	}
	public double getUser_ttpayment() {
		return user_ttpayment;
	}
	public void setUser_ttpayment(double user_ttpayment) {
		this.user_ttpayment = user_ttpayment;
	}
	public String getUser_regtime() {
		return user_regtime;
	}
	public void setUser_regtime(String user_regtime) {
		this.user_regtime = user_regtime;
	}
	public String getUser_lastlogintime() {
		return user_lastlogintime;
	}
	public void setUser_lastlogintime(String user_lastlogintime) {
		this.user_lastlogintime = user_lastlogintime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((user_email == null) ? 0 : user_email.hashCode());
		result = prime * result + ((user_id == null) ? 0 : user_id.hashCode());
		result = prime * result
				+ ((user_img == null) ? 0 : user_img.hashCode());
		result = prime
				* result
				+ ((user_lastlogintime == null) ? 0 : user_lastlogintime
						.hashCode());
		result = prime * result + user_level;
		result = prime * result
				+ ((user_name == null) ? 0 : user_name.hashCode());
		result = prime * result
				+ ((user_password == null) ? 0 : user_password.hashCode());
		result = prime * result
				+ ((user_regtime == null) ? 0 : user_regtime.hashCode());
		result = prime * result
				+ ((user_telnum == null) ? 0 : user_telnum.hashCode());
		long temp;
		temp = Double.doubleToLongBits(user_ttpayment);
		result = prime * result + (int) (temp ^ (temp >>> 32));
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
		User other = (User) obj;
		if (user_email == null) {
			if (other.user_email != null)
				return false;
		} else if (!user_email.equals(other.user_email))
			return false;
		if (user_id == null) {
			if (other.user_id != null)
				return false;
		} else if (!user_id.equals(other.user_id))
			return false;
		if (user_img == null) {
			if (other.user_img != null)
				return false;
		} else if (!user_img.equals(other.user_img))
			return false;
		if (user_lastlogintime == null) {
			if (other.user_lastlogintime != null)
				return false;
		} else if (!user_lastlogintime.equals(other.user_lastlogintime))
			return false;
		if (user_level != other.user_level)
			return false;
		if (user_name == null) {
			if (other.user_name != null)
				return false;
		} else if (!user_name.equals(other.user_name))
			return false;
		if (user_password == null) {
			if (other.user_password != null)
				return false;
		} else if (!user_password.equals(other.user_password))
			return false;
		if (user_regtime == null) {
			if (other.user_regtime != null)
				return false;
		} else if (!user_regtime.equals(other.user_regtime))
			return false;
		if (user_telnum == null) {
			if (other.user_telnum != null)
				return false;
		} else if (!user_telnum.equals(other.user_telnum))
			return false;
		if (Double.doubleToLongBits(user_ttpayment) != Double
				.doubleToLongBits(other.user_ttpayment))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [user_id=" + user_id + ", user_level=" + user_level
				+ ", user_name=" + user_name + ", user_password="
				+ user_password + ", user_email=" + user_email
				+ ", user_telnum=" + user_telnum + ", user_img=" + user_img
				+ ", user_ttpayment=" + user_ttpayment + ", user_regtime="
				+ user_regtime + ", user_lastlogintime=" + user_lastlogintime
				+ "]";
	}
	
}
