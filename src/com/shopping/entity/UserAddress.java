package com.shopping.entity;

import java.io.Serializable;

public class UserAddress implements Serializable{
	private static final long serialVersionUID = 5843333453629612263L;
	private int userAddress_id;
	private String user_id;
	private String user_name;
	private String consignee_name;
	private String consignee_province;
	private String consignee_city;
	private String consignee_area;
	private String consignee_address;
	private String consignee_telnum;
	private int isDefault_address;
	public UserAddress() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public UserAddress(int userAddress_id, String user_id, String user_name,
			String consignee_name, String consignee_province,
			String consignee_city, String consignee_area,
			String consignee_address, String consignee_telnum,
			int isDefault_address) {
		super();
		this.userAddress_id = userAddress_id;
		this.user_id = user_id;
		this.user_name = user_name;
		this.consignee_name = consignee_name;
		this.consignee_province = consignee_province;
		this.consignee_city = consignee_city;
		this.consignee_area = consignee_area;
		this.consignee_address = consignee_address;
		this.consignee_telnum = consignee_telnum;
		this.isDefault_address = isDefault_address;
	}
	public int getUserAddress_id() {
		return userAddress_id;
	}
	public void setUserAddress_id(int userAddress_id) {
		this.userAddress_id = userAddress_id;
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
	public String getConsignee_name() {
		return consignee_name;
	}
	public void setConsignee_name(String consignee_name) {
		this.consignee_name = consignee_name;
	}
	public String getConsignee_province() {
		return consignee_province;
	}
	public void setConsignee_province(String consignee_province) {
		this.consignee_province = consignee_province;
	}
	public String getConsignee_city() {
		return consignee_city;
	}
	public void setConsignee_city(String consignee_city) {
		this.consignee_city = consignee_city;
	}
	public String getConsignee_area() {
		return consignee_area;
	}
	public void setConsignee_area(String consignee_area) {
		this.consignee_area = consignee_area;
	}
	public String getConsignee_address() {
		return consignee_address;
	}
	public void setConsignee_address(String consignee_address) {
		this.consignee_address = consignee_address;
	}
	public String getConsignee_telnum() {
		return consignee_telnum;
	}
	public void setConsignee_telnum(String consignee_telnum) {
		this.consignee_telnum = consignee_telnum;
	}
	public int getIsDefault_address() {
		return isDefault_address;
	}
	public void setIsDefault_address(int isDefault_address) {
		this.isDefault_address = isDefault_address;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((consignee_address == null) ? 0 : consignee_address
						.hashCode());
		result = prime * result
				+ ((consignee_area == null) ? 0 : consignee_area.hashCode());
		result = prime * result
				+ ((consignee_city == null) ? 0 : consignee_city.hashCode());
		result = prime * result
				+ ((consignee_name == null) ? 0 : consignee_name.hashCode());
		result = prime
				* result
				+ ((consignee_province == null) ? 0 : consignee_province
						.hashCode());
		result = prime
				* result
				+ ((consignee_telnum == null) ? 0 : consignee_telnum.hashCode());
		result = prime * result + isDefault_address;
		result = prime * result + userAddress_id;
		result = prime * result + ((user_id == null) ? 0 : user_id.hashCode());
		result = prime * result
				+ ((user_name == null) ? 0 : user_name.hashCode());
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
		UserAddress other = (UserAddress) obj;
		if (consignee_address == null) {
			if (other.consignee_address != null)
				return false;
		} else if (!consignee_address.equals(other.consignee_address))
			return false;
		if (consignee_area == null) {
			if (other.consignee_area != null)
				return false;
		} else if (!consignee_area.equals(other.consignee_area))
			return false;
		if (consignee_city == null) {
			if (other.consignee_city != null)
				return false;
		} else if (!consignee_city.equals(other.consignee_city))
			return false;
		if (consignee_name == null) {
			if (other.consignee_name != null)
				return false;
		} else if (!consignee_name.equals(other.consignee_name))
			return false;
		if (consignee_province == null) {
			if (other.consignee_province != null)
				return false;
		} else if (!consignee_province.equals(other.consignee_province))
			return false;
		if (consignee_telnum == null) {
			if (other.consignee_telnum != null)
				return false;
		} else if (!consignee_telnum.equals(other.consignee_telnum))
			return false;
		if (isDefault_address != other.isDefault_address)
			return false;
		if (userAddress_id != other.userAddress_id)
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
		return true;
	}
	@Override
	public String toString() {
		return "UserAddress [userAddress_id=" + userAddress_id + ", user_id="
				+ user_id + ", user_name=" + user_name + ", consignee_name="
				+ consignee_name + ", consignee_province=" + consignee_province
				+ ", consignee_city=" + consignee_city + ", consignee_area="
				+ consignee_area + ", consignee_address=" + consignee_address
				+ ", consignee_telnum=" + consignee_telnum
				+ ", isDefault_address=" + isDefault_address + "]";
	}

	public UserAddress(String user_id, String user_name, String consignee_name,
			String consignee_province, String consignee_city,
			String consignee_area, String consignee_address,
			String consignee_telnum, int isDefault_address) {
		super();
		this.user_id = user_id;
		this.user_name = user_name;
		this.consignee_name = consignee_name;
		this.consignee_province = consignee_province;
		this.consignee_city = consignee_city;
		this.consignee_area = consignee_area;
		this.consignee_address = consignee_address;
		this.consignee_telnum = consignee_telnum;
		this.isDefault_address = isDefault_address;
	}
	
}
