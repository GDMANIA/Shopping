package com.shopping.entity;

import java.io.Serializable;
import java.util.List;

public class Page implements Serializable{
	private static final long serialVersionUID = 1L;
	private int totalRows;
	private int pageSize;
	private int totalPage;
	private int currentPage;
	private List<GoodsInfo> gis;
	private List<Keyword> keys;
	private List<User> users;
	private List<UserOrder> uos;
	private int offsetRows;
	public Page() {
		super();
	}
	public int getTotalRows() {
		return totalRows;
	}
	public void setTotalRows(int totalRows) {
		this.totalRows = totalRows;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public int getTotalPage() {
		totalPage = totalRows % pageSize == 0 ?  (totalRows / pageSize) : (totalRows / pageSize + 1) ;
		return totalPage;
	}
	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}
	public int getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}
	public List<GoodsInfo> getGis() {
		return gis;
	}
	public void setGis(List<GoodsInfo> gis) {
		this.gis = gis;
	}
	public List<Keyword> getKeys() {
		return keys;
	}
	public void setKeys(List<Keyword> keys) {
		this.keys = keys;
	}
	public List<User> getUsers() {
		return users;
	}
	public void setUsers(List<User> users) {
		this.users = users;
	}
	public List<UserOrder> getUos() {
		return uos;
	}
	public void setUos(List<UserOrder> uos) {
		this.uos = uos;
	}
	public int getOffsetRows() {
		offsetRows = pageSize * (currentPage - 1) ;
		return offsetRows;
	}
	public void setOffsetRows(int offsetRows) {
		this.offsetRows = offsetRows;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + currentPage;
		result = prime * result + ((gis == null) ? 0 : gis.hashCode());
		result = prime * result + ((keys == null) ? 0 : keys.hashCode());
		result = prime * result + offsetRows;
		result = prime * result + pageSize;
		result = prime * result + totalPage;
		result = prime * result + totalRows;
		result = prime * result + ((uos == null) ? 0 : uos.hashCode());
		result = prime * result + ((users == null) ? 0 : users.hashCode());
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
		Page other = (Page) obj;
		if (currentPage != other.currentPage)
			return false;
		if (gis == null) {
			if (other.gis != null)
				return false;
		} else if (!gis.equals(other.gis))
			return false;
		if (keys == null) {
			if (other.keys != null)
				return false;
		} else if (!keys.equals(other.keys))
			return false;
		if (offsetRows != other.offsetRows)
			return false;
		if (pageSize != other.pageSize)
			return false;
		if (totalPage != other.totalPage)
			return false;
		if (totalRows != other.totalRows)
			return false;
		if (uos == null) {
			if (other.uos != null)
				return false;
		} else if (!uos.equals(other.uos))
			return false;
		if (users == null) {
			if (other.users != null)
				return false;
		} else if (!users.equals(other.users))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Page [totalRows=" + totalRows + ", pageSize=" + pageSize
				+ ", totalPage=" + totalPage + ", currentPage=" + currentPage
				+ ", gis=" + gis + ", keys=" + keys + ", users=" + users
				+ ", uos=" + uos + ", offsetRows=" + offsetRows + "]";
	}
	
	
}
