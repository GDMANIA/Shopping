package com.shopping.entity;

import java.io.Serializable;

public class ShopRecommendation implements Serializable{
	private static final long serialVersionUID = 3197750808223752340L;
	private int recommendation_id;
	private int goods_cat;
	private int goods_key;
	private String goods_id;
	private int goods_soldnum;
	private String administrator_name;
	private String shoprecommendation_createtime;
	
	public ShopRecommendation() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ShopRecommendation(int recommendation_id, int goods_cat,
			int goods_key, String goods_id, int goods_soldnum,
			String administrator_name, String shoprecommendation_createtime) {
		super();
		this.recommendation_id = recommendation_id;
		this.goods_cat = goods_cat;
		this.goods_key = goods_key;
		this.goods_id = goods_id;
		this.goods_soldnum = goods_soldnum;
		this.administrator_name = administrator_name;
		this.shoprecommendation_createtime = shoprecommendation_createtime;
	}

	public int getRecommendation_id() {
		return recommendation_id;
	}

	public void setRecommendation_id(int recommendation_id) {
		this.recommendation_id = recommendation_id;
	}

	public int getGoods_cat() {
		return goods_cat;
	}

	public void setGoods_cat(int goods_cat) {
		this.goods_cat = goods_cat;
	}

	public int getGoods_key() {
		return goods_key;
	}

	public void setGoods_key(int goods_key) {
		this.goods_key = goods_key;
	}

	public String getGoods_id() {
		return goods_id;
	}

	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}

	public int getGoods_soldnum() {
		return goods_soldnum;
	}

	public void setGoods_soldnum(int goods_soldnum) {
		this.goods_soldnum = goods_soldnum;
	}

	public String getAdministrator_name() {
		return administrator_name;
	}

	public void setAdministrator_name(String administrator_name) {
		this.administrator_name = administrator_name;
	}

	public String getShoprecommendation_createtime() {
		return shoprecommendation_createtime;
	}

	public void setShoprecommendation_createtime(
			String shoprecommendation_createtime) {
		this.shoprecommendation_createtime = shoprecommendation_createtime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((administrator_name == null) ? 0 : administrator_name
						.hashCode());
		result = prime * result + goods_cat;
		result = prime * result
				+ ((goods_id == null) ? 0 : goods_id.hashCode());
		result = prime * result + goods_key;
		result = prime * result + goods_soldnum;
		result = prime * result + recommendation_id;
		result = prime
				* result
				+ ((shoprecommendation_createtime == null) ? 0
						: shoprecommendation_createtime.hashCode());
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
		ShopRecommendation other = (ShopRecommendation) obj;
		if (administrator_name == null) {
			if (other.administrator_name != null)
				return false;
		} else if (!administrator_name.equals(other.administrator_name))
			return false;
		if (goods_cat != other.goods_cat)
			return false;
		if (goods_id == null) {
			if (other.goods_id != null)
				return false;
		} else if (!goods_id.equals(other.goods_id))
			return false;
		if (goods_key != other.goods_key)
			return false;
		if (goods_soldnum != other.goods_soldnum)
			return false;
		if (recommendation_id != other.recommendation_id)
			return false;
		if (shoprecommendation_createtime == null) {
			if (other.shoprecommendation_createtime != null)
				return false;
		} else if (!shoprecommendation_createtime
				.equals(other.shoprecommendation_createtime))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "ShopRecommendation [recommendation_id=" + recommendation_id
				+ ", goods_cat=" + goods_cat + ", goods_key=" + goods_key
				+ ", goods_id=" + goods_id + ", goods_soldnum=" + goods_soldnum
				+ ", administrator_name=" + administrator_name
				+ ", shoprecommendation_createtime="
				+ shoprecommendation_createtime + "]";
	}
	
	
}
