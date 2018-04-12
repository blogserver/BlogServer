package com.opensource.soft.BlogServer.user.blog.model;

import java.util.Date;

public class Collect {

	private Integer userid;

	private String bloguuid;

	private Boolean deleteflag;

	private Date createtime;

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

	public Boolean getDeleteflag() {
		return deleteflag;
	}

	public void setDeleteflag(Boolean deleteflag) {
		this.deleteflag = deleteflag;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

}