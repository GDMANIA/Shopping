package com.shopping.entity;

import java.io.Serializable;
import java.util.List;

public class Floor implements Serializable{
	private static final long serialVersionUID = 1832511095598519080L;
	private int floor_id;
	private String floor_name;
	private String goods_id;
	private String administrator_name;
	private String floor_createtime;
	private List<GoodsInfo> goodsinfos;
	public Floor() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Floor(int floor_id, String floor_name, String goods_id,
			String administrator_name, String floor_createtime) {
		super();
		this.floor_id = floor_id;
		this.floor_name = floor_name;
		this.goods_id = goods_id;
		this.administrator_name = administrator_name;
		this.floor_createtime = floor_createtime;
	}
	public Floor(int floor_id, String floor_name, String goods_id,
			String administrator_name, String floor_createtime,
			List<GoodsInfo> goodsinfos) {
		super();
		this.floor_id = floor_id;
		this.floor_name = floor_name;
		this.goods_id = goods_id;
		this.administrator_name = administrator_name;
		this.floor_createtime = floor_createtime;
		this.goodsinfos = goodsinfos;
	}
	public int getFloor_id() {
		return floor_id;
	}
	public void setFloor_id(int floor_id) {
		this.floor_id = floor_id;
	}
	public String getFloor_name() {
		return floor_name;
	}
	public void setFloor_name(String floor_name) {
		this.floor_name = floor_name;
	}
	public String getGoods_id() {
		return goods_id;
	}
	public void setGoods_id(String goods_id) {
		this.goods_id = goods_id;
	}
	public String getAdministrator_name() {
		return administrator_name;
	}
	public void setAdministrator_name(String administrator_name) {
		this.administrator_name = administrator_name;
	}
	public String getFloor_createtime() {
		return floor_createtime;
	}
	public void setFloor_createtime(String floor_createtime) {
		this.floor_createtime = floor_createtime;
	}
	public List<GoodsInfo> getGoodsinfos() {
		return goodsinfos;
	}
	public void setGoodsinfos(List<GoodsInfo> goodsinfos) {
		this.goodsinfos = goodsinfos;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime
				* result
				+ ((administrator_name == null) ? 0 : administrator_name
						.hashCode());
		result = prime
				* result
				+ ((floor_createtime == null) ? 0 : floor_createtime.hashCode());
		result = prime * result + floor_id;
		result = prime * result
				+ ((floor_name == null) ? 0 : floor_name.hashCode());
		result = prime * result
				+ ((goods_id == null) ? 0 : goods_id.hashCode());
		result = prime * result
				+ ((goodsinfos == null) ? 0 : goodsinfos.hashCode());
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
		Floor other = (Floor) obj;
		if (administrator_name == null) {
			if (other.administrator_name != null)
				return false;
		} else if (!administrator_name.equals(other.administrator_name))
			return false;
		if (floor_createtime == null) {
			if (other.floor_createtime != null)
				return false;
		} else if (!floor_createtime.equals(other.floor_createtime))
			return false;
		if (floor_id != other.floor_id)
			return false;
		if (floor_name == null) {
			if (other.floor_name != null)
				return false;
		} else if (!floor_name.equals(other.floor_name))
			return false;
		if (goods_id == null) {
			if (other.goods_id != null)
				return false;
		} else if (!goods_id.equals(other.goods_id))
			return false;
		if (goodsinfos == null) {
			if (other.goodsinfos != null)
				return false;
		} else if (!goodsinfos.equals(other.goodsinfos))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Floor [floor_id=" + floor_id + ", floor_name=" + floor_name
				+ ", goods_id=" + goods_id + ", administrator_name="
				+ administrator_name + ", floor_createtime=" + floor_createtime
				+ ", goodsinfos=" + goodsinfos + "]";
	}
	
	
}
