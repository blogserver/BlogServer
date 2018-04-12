package com.opensource.soft.BlogServer.user.user.model;

import java.util.Date;

public class Follow {
	
	private Integer userid;

    private Integer followuserid;

    private Date createtime;

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

	public Integer getFollowuserid() {
		return followuserid;
	}

	public void setFollowuserid(Integer followuserid) {
		this.followuserid = followuserid;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "Follow [userid=" + userid + ", followuserid=" + followuserid + ", createtime=" + createtime + "]";
	}

    
}