package com.shopping.entity;

import java.io.Serializable;

public class UserSearchChoices implements Serializable{
	private static final long serialVersionUID = -2936043992818555068L;
	private String user_name;
	private String user_telnum;
	private String user_email;
	private String user_level;
	private String user_ttpayment_below;
	private String user_ttpayment_above;
	public UserSearchChoices() {
		super();
	}
	public UserSearchChoices(String user_name, String user_telnum,
			String user_email, String user_level, String user_ttpayment_below,
			String user_ttpayment_above) {
		super();
		this.user_name = user_name;
		this.user_telnum = user_telnum;
		this.user_email = user_email;
		this.user_level = user_level;
		this.user_ttpayment_below = user_ttpayment_below;
		this.user_ttpayment_above = user_ttpayment_above;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getUser_telnum() {
		return user_telnum;
	}
	public void setUser_telnum(String user_telnum) {
		this.user_telnum = user_telnum;
	}
	public String getUser_email() {
		return user_email;
	}
	public void setUser_email(String user_email) {
		this.user_email = user_email;
	}
	public String getUser_level() {
		return user_level;
	}
	public void setUser_level(String user_level) {
		this.user_level = user_level;
	}
	public String getUser_ttpayment_below() {
		return user_ttpayment_below;
	}
	public void setUser_ttpayment_below(String user_ttpayment_below) {
		this.user_ttpayment_below = user_ttpayment_below;
	}
	public String getUser_ttpayment_above() {
		return user_ttpayment_above;
	}
	public void setUser_ttpayment_above(String user_ttpayment_above) {
		this.user_ttpayment_above = user_ttpayment_above;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((user_email == null) ? 0 : user_email.hashCode());
		result = prime * result
				+ ((user_level == null) ? 0 : user_level.hashCode());
		result = prime * result
				+ ((user_name == null) ? 0 : user_name.hashCode());
		result = prime * result
				+ ((user_telnum == null) ? 0 : user_telnum.hashCode());
		result = prime
				* result
				+ ((user_ttpayment_above == null) ? 0 : user_ttpayment_above
						.hashCode());
		result = prime
				* result
				+ ((user_ttpayment_below == null) ? 0 : user_ttpayment_below
						.hashCode());
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
		UserSearchChoices other = (UserSearchChoices) obj;
		if (user_email == null) {
			if (other.user_email != null)
				return false;
		} else if (!user_email.equals(other.user_email))
			return false;
		if (user_level == null) {
			if (other.user_level != null)
				return false;
		} else if (!user_level.equals(other.user_level))
			return false;
		if (user_name == null) {
			if (other.user_name != null)
				return false;
		} else if (!user_name.equals(other.user_name))
			return false;
		if (user_telnum == null) {
			if (other.user_telnum != null)
				return false;
		} else if (!user_telnum.equals(other.user_telnum))
			return false;
		if (user_ttpayment_above == null) {
			if (other.user_ttpayment_above != null)
				return false;
		} else if (!user_ttpayment_above.equals(other.user_ttpayment_above))
			return false;
		if (user_ttpayment_below == null) {
			if (other.user_ttpayment_below != null)
				return false;
		} else if (!user_ttpayment_below.equals(other.user_ttpayment_below))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UserSearchChoices [user_name=" + user_name + ", user_telnum="
				+ user_telnum + ", user_email=" + user_email + ", user_level="
				+ user_level + ", user_ttpayment_below=" + user_ttpayment_below
				+ ", user_ttpayment_above=" + user_ttpayment_above + "]";
	}
	
	
	
}
