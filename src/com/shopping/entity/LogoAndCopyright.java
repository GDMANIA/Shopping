package com.shopping.entity;

import java.io.Serializable;

public class LogoAndCopyright implements Serializable{
	private static final long serialVersionUID = -8722456256671665072L;
	private int logoandcopyright_id;
	private String logo_img;
	private String copyright_detail;
	private int logoandcopyright_isvalid;
	private String administrator_name;
	private String logoandcopyright_createtime;
	public LogoAndCopyright() {
		super();
		// TODO Auto-generated constructor stub
	}
	public LogoAndCopyright(int logoandcopyright_id, String logo_img,
			String copyright_detail, int logoandcopyright_isvalid,
			String administrator_name, String logoandcopyright_createtime) {
		super();
		this.logoandcopyright_id = logoandcopyright_id;
		this.logo_img = logo_img;
		this.copyright_detail = copyright_detail;
		this.logoandcopyright_isvalid = logoandcopyright_isvalid;
		this.administrator_name = administrator_name;
		this.logoandcopyright_createtime = logoandcopyright_createtime;
	}
	public int getLogoandcopyright_id() {
		return logoandcopyright_id;
	}
	public void setLogoandcopyright_id(int logoandcopyright_id) {
		this.logoandcopyright_id = logoandcopyright_id;
	}
	public String getLogo_img() {
		return logo_img;
	}
	public void setLogo_img(String logo_img) {
		this.logo_img = logo_img;
	}
	public String getCopyright_detail() {
		return copyright_detail;
	}
	public void setCopyright_detail(String copyright_detail) {
		this.copyright_detail = copyright_detail;
	}
	public int getLogoandcopyright_isvalid() {
		return logoandcopyright_isvalid;
	}
	public void setLogoandcopyright_isvalid(int logoandcopyright_isvalid) {
		this.logoandcopyright_isvalid = logoandcopyright_isvalid;
	}
	public String getAdministrator_name() {
		return administrator_name;
	}
	public void setAdministrator_name(String administrator_name) {
		this.administrator_name = administrator_name;
	}
	public String getLogoandcopyright_createtime() {
		return logoandcopyright_createtime;
	}
	public void setLogoandcopyright_createtime(String logoandcopyright_createtime) {
		this.logoandcopyright_createtime = logoandcopyright_createtime;
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
				+ ((copyright_detail == null) ? 0 : copyright_detail.hashCode());
		result = prime * result
				+ ((logo_img == null) ? 0 : logo_img.hashCode());
		result = prime
				* result
				+ ((logoandcopyright_createtime == null) ? 0
						: logoandcopyright_createtime.hashCode());
		result = prime * result + logoandcopyright_id;
		result = prime * result + logoandcopyright_isvalid;
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
		LogoAndCopyright other = (LogoAndCopyright) obj;
		if (administrator_name == null) {
			if (other.administrator_name != null)
				return false;
		} else if (!administrator_name.equals(other.administrator_name))
			return false;
		if (copyright_detail == null) {
			if (other.copyright_detail != null)
				return false;
		} else if (!copyright_detail.equals(other.copyright_detail))
			return false;
		if (logo_img == null) {
			if (other.logo_img != null)
				return false;
		} else if (!logo_img.equals(other.logo_img))
			return false;
		if (logoandcopyright_createtime == null) {
			if (other.logoandcopyright_createtime != null)
				return false;
		} else if (!logoandcopyright_createtime
				.equals(other.logoandcopyright_createtime))
			return false;
		if (logoandcopyright_id != other.logoandcopyright_id)
			return false;
		if (logoandcopyright_isvalid != other.logoandcopyright_isvalid)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "LogoAndCopyright [logoandcopyright_id=" + logoandcopyright_id
				+ ", logo_img=" + logo_img + ", copyright_detail="
				+ copyright_detail + ", logoandcopyright_isvalid="
				+ logoandcopyright_isvalid + ", administrator_name="
				+ administrator_name + ", logoandcopyright_createtime="
				+ logoandcopyright_createtime + "]";
	}
	
}
