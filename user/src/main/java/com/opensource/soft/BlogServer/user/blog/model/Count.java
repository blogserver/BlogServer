package com.opensource.soft.BlogServer.user.blog.model;

public class Count {
	private String bloguuid;

	private Integer visit;

	private Integer like;

	private Integer hate;

	private Integer collect;

	private Integer userid;

	public String getBloguuid() {
		return bloguuid;
	}

	public void setBloguuid(String bloguuid) {
		this.bloguuid = bloguuid;
	}

	public Integer getVisit() {
		return visit;
	}

	public void setVisit(Integer visit) {
		this.visit = visit;
	}

	public Integer getLike() {
		return like;
	}

	public void setLike(Integer like) {
		this.like = like;
	}

	public Integer getHate() {
		return hate;
	}

	public void setHate(Integer hate) {
		this.hate = hate;
	}

	public Integer getCollect() {
		return collect;
	}

	public void setCollect(Integer collect) {
		this.collect = collect;
	}

	public Integer getUserid() {
		return userid;
	}

	public void setUserid(Integer userid) {
		this.userid = userid;
	}

}