package com.shopping.entity;

import java.io.Serializable;

public class UserOrderGoodsInfo implements Serializable{
	private static final long serialVersionUID = 4339693415974812220L;
	private String goods_id;
	private String goods_name;
	private String goods_img;
	private int goods_num;
	private double goods_price;
	public UserOrderGoodsInfo() {
		super();
	}
	public UserOrderGoodsInfo(String goods_id, String goods_name,
			String goods_img, int goods_num) {
		super();
		this.goods_id = goods_id;
		this.goods_name = goods_name;
		this.goods_img = goods_img;
		this.goods_num = goods_num;
	}
	
	public UserOrderGoodsInfo(String goods_id, String goods_name,
			String goods_img, int goods_num, double goods_price) {
		super();
		this.goods_id = goods_id;
		this.goods_name = goods_name;
		this.goods_img = goods_img;
		this.goods_num = goods_num;
		this.goods_price = goods_price;
	}
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getGoods_img() {
		return goods_img;
	}
	public void setGoods_img(String goods_img) {
		this.goods_img = goods_img;
	}
	public int getGoods_num() {
		return goods_num;
	}
	public void setGoods_num(int goods_num) {
		this.goods_num = goods_num;
	}
	public double getGoods_price() {
		return goods_price;
	}
	public void setGoods_price(double goods_price) {
		this.goods_price = goods_price;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((goods_id == null) ? 0 : goods_id.hashCode());
		result = prime * result
				+ ((goods_img == null) ? 0 : goods_img.hashCode());
		result = prime * result
				+ ((goods_name == null) ? 0 : goods_name.hashCode());
		result = prime * result + goods_num;
		long temp;
		temp = Double.doubleToLongBits(goods_price);
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
		UserOrderGoodsInfo other = (UserOrderGoodsInfo) obj;
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
		if (Double.doubleToLongBits(goods_price) != Double
				.doubleToLongBits(other.goods_price))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "UserOrderGoodsInfo [goods_id=" + goods_id + ", goods_name="
				+ goods_name + ", goods_img=" + goods_img + ", goods_num="
				+ goods_num + ", goods_price=" + goods_price + "]";
	}
	
}
