package com.shopping.entity;

import java.io.Serializable;

public class OrderSearchChoices implements Serializable{
	private static final long serialVersionUID = -4207001736748854821L;
	private String user_name;
	private String order_state;
	private String order_isdeleted;
	private String order_ttprice_below;
	private String order_ttprice_above;
	public OrderSearchChoices() {
		super();
		// TODO Auto-generated constructor stub
	}
	public OrderSearchChoices(String user_name, String order_state,
			String order_isdeleted, String order_ttprice_below,
			String order_ttprice_above) {
		super();
		this.user_name = user_name;
		this.order_state = order_state;
		this.order_isdeleted = order_isdeleted;
		this.order_ttprice_below = order_ttprice_below;
		this.order_ttprice_above = order_ttprice_above;
	}
	public String getUser_name() {
		return user_name;
	}
	public void setUser_name(String user_name) {
		this.user_name = user_name;
	}
	public String getOrder_state() {
		return order_state;
	}
	public void setOrder_state(String order_state) {
		this.order_state = order_state;
	}
	public String getOrder_isdeleted() {
		return order_isdeleted;
	}
	public void setOrder_isdeleted(String order_isdeleted) {
		this.order_isdeleted = order_isdeleted;
	}
	public String getOrder_ttprice_below() {
		return order_ttprice_below;
	}
	public void setOrder_ttprice_below(String order_ttprice_below) {
		this.order_ttprice_below = order_ttprice_below;
	}
	public String getOrder_ttprice_above() {
		return order_ttprice_above;
	}
	public void setOrder_ttprice_above(String order_ttprice_above) {
		this.order_ttprice_above = order_ttprice_above;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((order_isdeleted == null) ? 0 : order_isdeleted.hashCode());
		result = prime * result
				+ ((order_state == null) ? 0 : order_state.hashCode());
		result = prime
				* result
				+ ((order_ttprice_above == null) ? 0 : order_ttprice_above
						.hashCode());
		result = prime
				* result
				+ ((order_ttprice_below == null) ? 0 : order_ttprice_below
						.hashCode());
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
		OrderSearchChoices other = (OrderSearchChoices) obj;
		if (order_isdeleted == null) {
			if (other.order_isdeleted != null)
				return false;
		} else if (!order_isdeleted.equals(other.order_isdeleted))
			return false;
		if (order_state == null) {
			if (other.order_state != null)
				return false;
		} else if (!order_state.equals(other.order_state))
			return false;
		if (order_ttprice_above == null) {
			if (other.order_ttprice_above != null)
				return false;
		} else if (!order_ttprice_above.equals(other.order_ttprice_above))
			return false;
		if (order_ttprice_below == null) {
			if (other.order_ttprice_below != null)
				return false;
		} else if (!order_ttprice_below.equals(other.order_ttprice_below))
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
		return "OrderSearchChoices [user_name=" + user_name + ", order_state="
				+ order_state + ", order_isdeleted=" + order_isdeleted
				+ ", order_ttprice_below=" + order_ttprice_below
				+ ", order_ttprice_above=" + order_ttprice_above + "]";
	}
	
}
