package com.shopping.entity;

import java.io.Serializable;

public class UserCart implements Serializable{
	private static final long serialVersionUID = 2053050370551970632L;
	private int usercart_id;
	private String user_id;
	private String user_name;
	private int ischosen;
	private String goods_img;
	private String goods_name;
	private double goods_curprice;
	private int goods_ttnum;
	private String goods_id;
	private int goods_num;
	public UserCart() {
		super();
		// TODO Auto-generated constructor stub
	}
	public UserCart(int usercart_id, String user_id, String user_name,
			int ischosen, String goods_id, int goods_num) {
		super();
		this.usercart_id = usercart_id;
		this.user_id = user_id;
		this.user_name = user_name;
		this.ischosen = ischosen;
		this.goods_id = goods_id;
		this.goods_num = goods_num;
	}
	public UserCart(int usercart_id, String user_id, String user_name,
			int ischosen, String goods_img, String goods_name,
			double goods_curprice, int goods_ttnum, String goods_id,
			int goods_num) {
		super();
		this.usercart_id = usercart_id;
		this.user_id = user_id;
		this.user_name = user_name;
		this.ischosen = ischosen;
		this.goods_img = goods_img;
		this.goods_name = goods_name;
		this.goods_curprice = goods_curprice;
		this.goods_ttnum = goods_ttnum;
		this.goods_id = goods_id;
		this.goods_num = goods_num;
	}
	public int getUsercart_id() {
		return usercart_id;
	}
	public void setUsercart_id(int usercart_id) {
		this.usercart_id = usercart_id;
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
	public int getIschosen() {
		return ischosen;
	}
	public void setIschosen(int ischosen) {
		this.ischosen = ischosen;
	}
	public String getGoods_img() {
		return goods_img;
	}
	public void setGoods_img(String goods_img) {
		this.goods_img = goods_img;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public double getGoods_curprice() {
		return goods_curprice;
	}
	public void setGoods_curprice(double goods_curprice) {
		this.goods_curprice = goods_curprice;
	}
	public int getGoods_ttnum() {
		return goods_ttnum;
	}
	public void setGoods_ttnum(int goods_ttnum) {
		this.goods_ttnum = goods_ttnum;
	}
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	public int getGoods_num() {
		return goods_num;
	}
	public void setGoods_num(int goods_num) {
		this.goods_num = goods_num;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		long temp;
		temp = Double.doubleToLongBits(goods_curprice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((goods_id == null) ? 0 : goods_id.hashCode());
		result = prime * result
				+ ((goods_img == null) ? 0 : goods_img.hashCode());
		result = prime * result
				+ ((goods_name == null) ? 0 : goods_name.hashCode());
		result = prime * result + goods_num;
		result = prime * result + goods_ttnum;
		result = prime * result + ischosen;
		result = prime * result + ((user_id == null) ? 0 : user_id.hashCode());
		result = prime * result
				+ ((user_name == null) ? 0 : user_name.hashCode());
		result = prime * result + usercart_id;
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
		UserCart other = (UserCart) obj;
		if (Double.doubleToLongBits(goods_curprice) != Double
				.doubleToLongBits(other.goods_curprice))
			return false;
		if (goods_id == null) {
			if (other.goods_id != null)
				return false;
		} else if (!goods_id.equals(other.goods_id))
			return false;
		if (goods_img == null) {
			if (other.goods_img != null)
				return false;
		} else if (!goods_img.equals(other.goods_img))
			return false;
		if (goods_name == null) {
			if (other.goods_name != null)
				return false;
		} else if (!goods_name.equals(other.goods_name))
			return false;
		if (goods_num != other.goods_num)
			return false;
		if (goods_ttnum != other.goods_ttnum)
			return false;
		if (ischosen != other.ischosen)
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
		if (usercart_id != other.usercart_id)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UserCart [usercart_id=" + usercart_id + ", user_id=" + user_id
				+ ", user_name=" + user_name + ", ischosen=" + ischosen
				+ ", goods_img=" + goods_img + ", goods_name=" + goods_name
				+ ", goods_curprice=" + goods_curprice + ", goods_ttnum="
				+ goods_ttnum + ", goods_id=" + goods_id + ", goods_num="
				+ goods_num + "]";
	}
	
	
}
