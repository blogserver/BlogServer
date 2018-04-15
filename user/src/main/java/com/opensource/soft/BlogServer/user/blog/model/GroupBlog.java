package com.opensource.soft.BlogServer.user.blog.model;
public class GroupBlog {
	
    private Integer groupid;

    private String bloguuid;
    
    private Integer status;

	public Integer getGroupid() {
		return groupid;
	}

	public void setGroupid(Integer groupid) {
		this.groupid = groupid;
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

	@Override
	public String toString() {
		return "GroupBlog [groupid=" + groupid + ", bloguuid=" + bloguuid + ", status=" + status + "]";
	}

    
}