package com.shopping.entity;

import java.io.Serializable;

public class GoodsEvaluation implements Serializable{
	private static final long serialVersionUID = -8361112243972180619L;
	private int goodsevaluation_id;
	private String user_id;
	private String user_name;
	private String user_img;
	private int user_level;
	private String goods_id;
	private String evaluation_details;
	private String evaluation_createtime;
	public GoodsEvaluation() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GoodsEvaluation(int goodsevaluation_id, String user_id,
			String goods_id, String evaluation_details,
			String evaluation_createtime) {
		super();
		this.goodsevaluation_id = goodsevaluation_id;
		this.user_id = user_id;
		this.goods_id = goods_id;
		this.evaluation_details = evaluation_details;
		this.evaluation_createtime = evaluation_createtime;
	}
	public GoodsEvaluation(int goodsevaluation_id, String user_id,
			String user_name, String user_img, int user_level, String goods_id,
			String evaluation_details, String evaluation_createtime) {
		super();
		this.goodsevaluation_id = goodsevaluation_id;
		this.user_id = user_id;
		this.user_name = user_name;
		this.user_img = user_img;
		this.user_level = user_level;
		this.goods_id = goods_id;
		this.evaluation_details = evaluation_details;
		this.evaluation_createtime = evaluation_createtime;
	}
	public int getGoodsevaluation_id() {
		return goodsevaluation_id;
	}
	public void setGoodsevaluation_id(int goodsevaluation_id) {
		this.goodsevaluation_id = goodsevaluation_id;
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
	public String getUser_img() {
		return user_img;
	}
	public void setUser_img(String user_img) {
		this.user_img = user_img;
	}
	public int getUser_level() {
		return user_level;
	}
	public void setUser_level(int user_level) {
		this.user_level = user_level;
	}
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	public String getEvaluation_details() {
		return evaluation_details;
	}
	public void setEvaluation_details(String evaluation_details) {
		this.evaluation_details = evaluation_details;
	}
	public String getEvaluation_createtime() {
		return evaluation_createtime;
	}
	public void setEvaluation_createtime(String evaluation_createtime) {
		this.evaluation_createtime = evaluation_createtime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((evaluation_createtime == null) ? 0 : evaluation_createtime
						.hashCode());
		result = prime
				* result
				+ ((evaluation_details == null) ? 0 : evaluation_details
						.hashCode());
		result = prime * result
				+ ((goods_id == null) ? 0 : goods_id.hashCode());
		result = prime * result + goodsevaluation_id;
		result = prime * result + ((user_id == null) ? 0 : user_id.hashCode());
		result = prime * result
				+ ((user_img == null) ? 0 : user_img.hashCode());
		result = prime * result + user_level;
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
		GoodsEvaluation other = (GoodsEvaluation) obj;
		if (evaluation_createtime == null) {
			if (other.evaluation_createtime != null)
				return false;
		} else if (!evaluation_createtime.equals(other.evaluation_createtime))
			return false;
		if (evaluation_details == null) {
			if (other.evaluation_details != null)
				return false;
		} else if (!evaluation_details.equals(other.evaluation_details))
			return false;
		if (goods_id == null) {
			if (other.goods_id != null)
				return false;
		} else if (!goods_id.equals(other.goods_id))
			return false;
		if (goodsevaluation_id != other.goodsevaluation_id)
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
		if (user_level != other.user_level)
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
		return "GoodsEvaluation [goodsevaluation_id=" + goodsevaluation_id
				+ ", user_id=" + user_id + ", user_name=" + user_name
				+ ", user_img=" + user_img + ", user_level=" + user_level
				+ ", goods_id=" + goods_id + ", evaluation_details="
				+ evaluation_details + ", evaluation_createtime="
				+ evaluation_createtime + "]";
	}
	
}
