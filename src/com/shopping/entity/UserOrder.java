package com.shopping.entity;

import java.io.Serializable;
import java.util.List;

public class UserOrder implements Serializable{
	private static final long serialVersionUID = 8602354351412822173L;
	private String userorder_id;
	private String user_id;
	private String user_name;
	private String order_createtime;
	private String goods_id_num;
	private double order_ttprice;
	private String order_address;
	private int order_state;
	private int order_isdeleted;
	private List<UserOrderGoodsInfo> uogis;
	public UserOrder() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	public UserOrder(String userorder_id, String user_id, String user_name,
			String order_createtime, String goods_id_num, double order_ttprice,
			String order_address, int order_state, int order_isdeleted) {
		super();
		this.userorder_id = userorder_id;
		this.user_id = user_id;
		this.user_name = user_name;
		this.order_createtime = order_createtime;
		this.goods_id_num = goods_id_num;
		this.order_ttprice = order_ttprice;
		this.order_address = order_address;
		this.order_state = order_state;
		this.order_isdeleted = order_isdeleted;
	}


	public UserOrder(String userorder_id, String user_id, String user_name,
			String order_createtime, String goods_id_num, double order_ttprice,
			String order_address, int order_state, int order_isdeleted,
			List<UserOrderGoodsInfo> uogis) {
		super();
		this.userorder_id = userorder_id;
		this.user_id = user_id;
		this.user_name = user_name;
		this.order_createtime = order_createtime;
		this.goods_id_num = goods_id_num;
		this.order_ttprice = order_ttprice;
		this.order_address = order_address;
		this.order_state = order_state;
		this.order_isdeleted = order_isdeleted;
		this.uogis = uogis;
	}


	public String getUserorder_id() {
		return userorder_id;
	}


	public void setUserorder_id(String userorder_id) {
		this.userorder_id = userorder_id;
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


	public String getOrder_createtime() {
		return order_createtime;
	}


	public void setOrder_createtime(String order_createtime) {
		this.order_createtime = order_createtime;
	}


	public String getGoods_id_num() {
		return goods_id_num;
	}


	public void setGoods_id_num(String goods_id_num) {
		this.goods_id_num = goods_id_num;
	}


	public double getOrder_ttprice() {
		return order_ttprice;
	}


	public void setOrder_ttprice(double order_ttprice) {
		this.order_ttprice = order_ttprice;
	}


	public String getOrder_address() {
		return order_address;
	}


	public void setOrder_address(String order_address) {
		this.order_address = order_address;
	}


	public int getOrder_state() {
		return order_state;
	}


	public void setOrder_state(int order_state) {
		this.order_state = order_state;
	}


	public int getOrder_isdeleted() {
		return order_isdeleted;
	}


	public void setOrder_isdeleted(int order_isdeleted) {
		this.order_isdeleted = order_isdeleted;
	}


	public List<UserOrderGoodsInfo> getUogis() {
		return uogis;
	}


	public void setUogis(List<UserOrderGoodsInfo> uogis) {
		this.uogis = uogis;
	}


	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((goods_id_num == null) ? 0 : goods_id_num.hashCode());
		result = prime * result
				+ ((order_address == null) ? 0 : order_address.hashCode());
		result = prime
				* result
				+ ((order_createtime == null) ? 0 : order_createtime.hashCode());
		result = prime * result + order_isdeleted;
		result = prime * result + order_state;
		long temp;
		temp = Double.doubleToLongBits(order_ttprice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + ((uogis == null) ? 0 : uogis.hashCode());
		result = prime * result + ((user_id == null) ? 0 : user_id.hashCode());
		result = prime * result
				+ ((user_name == null) ? 0 : user_name.hashCode());
		result = prime * result
				+ ((userorder_id == null) ? 0 : userorder_id.hashCode());
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
		UserOrder other = (UserOrder) obj;
		if (goods_id_num == null) {
			if (other.goods_id_num != null)
				return false;
		} else if (!goods_id_num.equals(other.goods_id_num))
			return false;
		if (order_address == null) {
			if (other.order_address != null)
				return false;
		} else if (!order_address.equals(other.order_address))
			return false;
		if (order_createtime == null) {
			if (other.order_createtime != null)
				return false;
		} else if (!order_createtime.equals(other.order_createtime))
			return false;
		if (order_isdeleted != other.order_isdeleted)
			return false;
		if (order_state != other.order_state)
			return false;
		if (Double.doubleToLongBits(order_ttprice) != Double
				.doubleToLongBits(other.order_ttprice))
			return false;
		if (uogis == null) {
			if (other.uogis != null)
				return false;
		} else if (!uogis.equals(other.uogis))
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
		if (userorder_id == null) {
			if (other.userorder_id != null)
				return false;
		} else if (!userorder_id.equals(other.userorder_id))
			return false;
		return true;
	}


	@Override
	public String toString() {
		return "UserOrder [userorder_id=" + userorder_id + ", user_id="
				+ user_id + ", user_name=" + user_name + ", order_createtime="
				+ order_createtime + ", goods_id_num=" + goods_id_num
				+ ", order_ttprice=" + order_ttprice + ", order_address="
				+ order_address + ", order_state=" + order_state
				+ ", order_isdeleted=" + order_isdeleted + ", uogis=" + uogis
				+ "]";
	}
	
}
