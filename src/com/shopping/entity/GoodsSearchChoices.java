package com.shopping.entity;

import java.io.Serializable;

public class GoodsSearchChoices implements Serializable{
	private static final long serialVersionUID = -7438502469122988619L;
	private String goods_name;
	private String goods_info;
	private String goods_cat;
	private String goods_key;
	private String goods_price_below;
	private String goods_price_above;
	private String goods_ttnum_below;
	private String goods_ttnum_above;
	private String goods_soldnum_below;
	private String goods_soldnum_above;
	private String goods_commentnum_below;
	private String goods_commentnum_above;
	private String goods_isonsite;
	private String order_condition;
	private String order_direction;
	public GoodsSearchChoices() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GoodsSearchChoices(String goods_name, String goods_info,
			String goods_cat, String goods_key, String goods_price_below,
			String goods_price_above, String goods_ttnum_below,
			String goods_ttnum_above, String goods_soldnum_below,
			String goods_soldnum_above, String goods_commentnum_below,
			String goods_commentnum_above, String goods_isonsite) {
		super();
		this.goods_name = goods_name;
		this.goods_info = goods_info;
		this.goods_cat = goods_cat;
		this.goods_key = goods_key;
		this.goods_price_below = goods_price_below;
		this.goods_price_above = goods_price_above;
		this.goods_ttnum_below = goods_ttnum_below;
		this.goods_ttnum_above = goods_ttnum_above;
		this.goods_soldnum_below = goods_soldnum_below;
		this.goods_soldnum_above = goods_soldnum_above;
		this.goods_commentnum_below = goods_commentnum_below;
		this.goods_commentnum_above = goods_commentnum_above;
		this.goods_isonsite = goods_isonsite;
	}
	public String getGoods_name() {
		return goods_name;
	}
	public void setGoods_name(String goods_name) {
		this.goods_name = goods_name;
	}
	public String getGoods_info() {
		return goods_info;
	}
	public void setGoods_info(String goods_info) {
		this.goods_info = goods_info;
	}
	public String getGoods_cat() {
		return goods_cat;
	}
	public void setGoods_cat(String goods_cat) {
		this.goods_cat = goods_cat;
	}
	public String getGoods_key() {
		return goods_key;
	}
	public void setGoods_key(String goods_key) {
		this.goods_key = goods_key;
	}
	public String getGoods_price_below() {
		return goods_price_below;
	}
	public void setGoods_price_below(String goods_price_below) {
		this.goods_price_below = goods_price_below;
	}
	public String getGoods_price_above() {
		return goods_price_above;
	}
	public void setGoods_price_above(String goods_price_above) {
		this.goods_price_above = goods_price_above;
	}
	public String getGoods_ttnum_below() {
		return goods_ttnum_below;
	}
	public void setGoods_ttnum_below(String goods_ttnum_below) {
		this.goods_ttnum_below = goods_ttnum_below;
	}
	public String getGoods_ttnum_above() {
		return goods_ttnum_above;
	}
	public void setGoods_ttnum_above(String goods_ttnum_above) {
		this.goods_ttnum_above = goods_ttnum_above;
	}
	public String getGoods_soldnum_below() {
		return goods_soldnum_below;
	}
	public void setGoods_soldnum_below(String goods_soldnum_below) {
		this.goods_soldnum_below = goods_soldnum_below;
	}
	public String getGoods_soldnum_above() {
		return goods_soldnum_above;
	}
	public void setGoods_soldnum_above(String goods_soldnum_above) {
		this.goods_soldnum_above = goods_soldnum_above;
	}
	public String getGoods_commentnum_below() {
		return goods_commentnum_below;
	}
	public void setGoods_commentnum_below(String goods_commentnum_below) {
		this.goods_commentnum_below = goods_commentnum_below;
	}
	public String getGoods_commentnum_above() {
		return goods_commentnum_above;
	}
	public void setGoods_commentnum_above(String goods_commentnum_above) {
		this.goods_commentnum_above = goods_commentnum_above;
	}
	public String getGoods_isonsite() {
		return goods_isonsite;
	}
	public void setGoods_isonsite(String goods_isonsite) {
		this.goods_isonsite = goods_isonsite;
	}
	public String getOrder_condition() {
		return order_condition;
	}
	public void setOrder_condition(String order_condition) {
		this.order_condition = order_condition;
	}
	public String getOrder_direction() {
		return order_direction;
	}
	public void setOrder_direction(String order_direction) {
		this.order_direction = order_direction;
	}
	@Override
	public String toString() {
		return "GoodsSearchChoices [goods_name=" + goods_name + ", goods_info="
				+ goods_info + ", goods_cat=" + goods_cat + ", goods_key="
				+ goods_key + ", goods_price_below=" + goods_price_below
				+ ", goods_price_above=" + goods_price_above
				+ ", goods_ttnum_below=" + goods_ttnum_below
				+ ", goods_ttnum_above=" + goods_ttnum_above
				+ ", goods_soldnum_below=" + goods_soldnum_below
				+ ", goods_soldnum_above=" + goods_soldnum_above
				+ ", goods_commentnum_below=" + goods_commentnum_below
				+ ", goods_commentnum_above=" + goods_commentnum_above
				+ ", goods_isonsite=" + goods_isonsite + ", order_condition="
				+ order_condition + ", order_direction=" + order_direction
				+ "]";
	}
	
	
}
