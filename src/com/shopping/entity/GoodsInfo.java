package com.shopping.entity;

import java.io.Serializable;

public class GoodsInfo implements Serializable{
	private static final long serialVersionUID = 2378306428116073234L;
	private String goods_id;
	private String goods_name;
	private String goods_info;
	private int goods_cat;
	private int goods_key;
	private double goods_oriprice;
	private double goods_curprice;
	private int goods_ttnum;
	private int goods_soldnum;
	private int goods_commentnum;
	private int goods_clicknum;
	private String goods_img;
	private int goods_isdeleted;
	private int goods_isonsite;
	private String goods_createtime;
	public GoodsInfo() {
		super();
		// TODO Auto-generated constructor stub
	}
	public GoodsInfo(String goods_id, String goods_name, String goods_info,
			int goods_cat, int goods_key, double goods_oriprice,
			double goods_curprice, int goods_ttnum, int goods_soldnum,
			int goods_commentnum, int goods_clicknum, String goods_img,
			int goods_isdeleted, int goods_isonsite, String goods_createtime) {
		super();
		this.goods_id = goods_id;
		this.goods_name = goods_name;
		this.goods_info = goods_info;
		this.goods_cat = goods_cat;
		this.goods_key = goods_key;
		this.goods_oriprice = goods_oriprice;
		this.goods_curprice = goods_curprice;
		this.goods_ttnum = goods_ttnum;
		this.goods_soldnum = goods_soldnum;
		this.goods_commentnum = goods_commentnum;
		this.goods_clicknum = goods_clicknum;
		this.goods_img = goods_img;
		this.goods_isdeleted = goods_isdeleted;
		this.goods_isonsite = goods_isonsite;
		this.goods_createtime = goods_createtime;
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
	public String getGoods_info() {
		return goods_info;
	}
	public void setGoods_info(String goods_info) {
		this.goods_info = goods_info;
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
	public double getGoods_oriprice() {
		return goods_oriprice;
	}
	public void setGoods_oriprice(double goods_oriprice) {
		this.goods_oriprice = goods_oriprice;
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
	public int getGoods_soldnum() {
		return goods_soldnum;
	}
	public void setGoods_soldnum(int goods_soldnum) {
		this.goods_soldnum = goods_soldnum;
	}
	public int getGoods_commentnum() {
		return goods_commentnum;
	}
	public void setGoods_commentnum(int goods_commentnum) {
		this.goods_commentnum = goods_commentnum;
	}
	public int getGoods_clicknum() {
		return goods_clicknum;
	}
	public void setGoods_clicknum(int goods_clicknum) {
		this.goods_clicknum = goods_clicknum;
	}
	public String getGoods_img() {
		return goods_img;
	}
	public void setGoods_img(String goods_img) {
		this.goods_img = goods_img;
	}
	public int getGoods_isdeleted() {
		return goods_isdeleted;
	}
	public void setGoods_isdeleted(int goods_isdeleted) {
		this.goods_isdeleted = goods_isdeleted;
	}
	public int getGoods_isonsite() {
		return goods_isonsite;
	}
	public void setGoods_isonsite(int goods_isonsite) {
		this.goods_isonsite = goods_isonsite;
	}
	public String getGoods_createtime() {
		return goods_createtime;
	}
	public void setGoods_createtime(String goods_createtime) {
		this.goods_createtime = goods_createtime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + goods_cat;
		result = prime * result + goods_clicknum;
		result = prime * result + goods_commentnum;
		result = prime
				* result
				+ ((goods_createtime == null) ? 0 : goods_createtime.hashCode());
		long temp;
		temp = Double.doubleToLongBits(goods_curprice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result
				+ ((goods_id == null) ? 0 : goods_id.hashCode());
		result = prime * result
				+ ((goods_img == null) ? 0 : goods_img.hashCode());
		result = prime * result
				+ ((goods_info == null) ? 0 : goods_info.hashCode());
		result = prime * result + goods_isdeleted;
		result = prime * result + goods_isonsite;
		result = prime * result + goods_key;
		result = prime * result
				+ ((goods_name == null) ? 0 : goods_name.hashCode());
		temp = Double.doubleToLongBits(goods_oriprice);
		result = prime * result + (int) (temp ^ (temp >>> 32));
		result = prime * result + goods_soldnum;
		result = prime * result + goods_ttnum;
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
		GoodsInfo other = (GoodsInfo) obj;
		if (goods_cat != other.goods_cat)
			return false;
		if (goods_clicknum != other.goods_clicknum)
			return false;
		if (goods_commentnum != other.goods_commentnum)
			return false;
		if (goods_createtime == null) {
			if (other.goods_createtime != null)
				return false;
		} else if (!goods_createtime.equals(other.goods_createtime))
			return false;
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
		if (goods_info == null) {
			if (other.goods_info != null)
				return false;
		} else if (!goods_info.equals(other.goods_info))
			return false;
		if (goods_isdeleted != other.goods_isdeleted)
			return false;
		if (goods_isonsite != other.goods_isonsite)
			return false;
		if (goods_key != other.goods_key)
			return false;
		if (goods_name == null) {
			if (other.goods_name != null)
				return false;
		} else if (!goods_name.equals(other.goods_name))
			return false;
		if (Double.doubleToLongBits(goods_oriprice) != Double
				.doubleToLongBits(other.goods_oriprice))
			return false;
		if (goods_soldnum != other.goods_soldnum)
			return false;
		if (goods_ttnum != other.goods_ttnum)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "GoodsInfo [goods_id=" + goods_id + ", goods_name=" + goods_name
				+ ", goods_info=" + goods_info + ", goods_cat=" + goods_cat
				+ ", goods_key=" + goods_key + ", goods_oriprice="
				+ goods_oriprice + ", goods_curprice=" + goods_curprice
				+ ", goods_ttnum=" + goods_ttnum + ", goods_soldnum="
				+ goods_soldnum + ", goods_commentnum=" + goods_commentnum
				+ ", goods_clicknum=" + goods_clicknum + ", goods_img="
				+ goods_img + ", goods_isdeleted=" + goods_isdeleted
				+ ", goods_isonsite=" + goods_isonsite + ", goods_createtime="
				+ goods_createtime + "]";
	}
	
	
}
