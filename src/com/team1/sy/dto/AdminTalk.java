package com.team1.sy.dto;

import java.util.Date;

public class AdminTalk {
	
	private int idx;
	private String userid_fk;
	private String content;
	private Date writedate;
	
	
	public AdminTalk() {}
	
	public AdminTalk(int idx, String userid_fk, String content, Date writedate) {
		super();
		this.idx = idx;
		this.userid_fk = userid_fk;
		this.content = content;
		this.writedate = writedate;
	}
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getUserid_fk() {
		return userid_fk;
	}
	public void setUserid_fk(String userid_fk) {
		this.userid_fk = userid_fk;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getWritedate() {
		return writedate;
	}
	public void setWritedate(Date writedate) {
		this.writedate = writedate;
	}

	@Override
	public String toString() {
		return "AdminTalk [idx=" + idx + ", userid_fk=" + userid_fk + ", content=" + content + ", writedate="
				+ writedate + "]";
	}
	

}
