package com.opensource.soft.BlogServer.user.blog.model;

import java.util.Date;

public class Likes {

	private Integer userid;
	private String bloguuid;
	private Integer status;
	private Date updatetime;
	
	public Integer getUserid() {
		return userid;
	}
	public void setUserid(Integer userid) {
		this.userid = userid;
	}
	public String getBloguuid() {
		return bloguuid;
	}
	public void setBloguuid(String bloguuid) {
		this.bloguuid = bloguuid;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public Date getUpdatetime() {
		return updatetime;
	}
	public void setUpdatetime(Date updatetime) {
		this.updatetime = updatetime;
	}
	
	
}