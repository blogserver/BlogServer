package com.opensource.soft.BlogServer.api.comment.model;

import java.util.Date;

public class Comment {
    private Integer id;

    private String bloguuid;

    private String content;

    private Integer pid;

    private Integer likes;

    private Integer hates;

    private Date createtime;

    private Integer createuser;

    private Boolean deleteflag;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getBloguuid() {
		return bloguuid;
	}

	public void setBloguuid(String bloguuid) {
		this.bloguuid = bloguuid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public Integer getLikes() {
		return likes;
	}

	public void setLikes(Integer likes) {
		this.likes = likes;
	}

	public Integer getHates() {
		return hates;
	}

	public void setHates(Integer hates) {
		this.hates = hates;
	}

	public Date getCreatetime() {
		return createtime;
	}

	public void setCreatetime(Date createtime) {
		this.createtime = createtime;
	}

	public Integer getCreateuser() {
		return createuser;
	}

	public void setCreateuser(Integer createuser) {
		this.createuser = createuser;
	}

	public Boolean getDeleteflag() {
		return deleteflag;
	}

	public void setDeleteflag(Boolean deleteflag) {
		this.deleteflag = deleteflag;
	}

	@Override
	public String toString() {
		return "Comment [id=" + id + ", bloguuid=" + bloguuid + ", content=" + content + ", pid=" + pid + ", likes="
				+ likes + ", hates=" + hates + ", createtime=" + createtime + ", createuser=" + createuser
				+ ", deleteflag=" + deleteflag + "]";
	}

}