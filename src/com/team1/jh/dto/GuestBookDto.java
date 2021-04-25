package com.team1.jh.dto;

import java.util.Date;

public class GuestBookDto {
	private int idx;
	private String userid_fk;
	private String content;
	private Date writedate;
	private String readyn;
	private String nickName;
	
	public GuestBookDto() {}  

	public GuestBookDto(int idx, String userid_fk, String content, Date writedate, String readyn, String nickName) {
		super();
		this.idx = idx;
		this.userid_fk = userid_fk;
		this.content = content;
		this.writedate = writedate;
		this.readyn = readyn;
		this.nickName = nickName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
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

	public String getReadyn() {
		return readyn;
	}

	public void setReadyn(String readyn) {
		this.readyn = readyn;
	}
	
	
	
}
