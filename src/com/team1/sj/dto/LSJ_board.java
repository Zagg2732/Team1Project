package com.team1.sj.dto;

import java.util.Date;

public class LSJ_board {
	private int idx;
	private String user_fk;
	private int up;
	private int down;
	private int readnum;
	private Date writedate;
	private String subject;
	private String content;
	private String filename;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getUser_fk() {
		return user_fk;
	}
	public void setUser_fk(String user_fk) {
		this.user_fk = user_fk;
	}
	public int getUp() {
		return up;
	}
	public void setUp(int up) {
		this.up = up;
	}
	public int getDown() {
		return down;
	}
	public void setDown(int down) {
		this.down = down;
	}
	public int getReadnum() {
		return readnum;
	}
	public void setReadnum(int readnum) {
		this.readnum = readnum;
	}
	public Date getWritedate() {
		return writedate;
	}
	public void setWritedate(Date writedate) {
		this.writedate = writedate;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	@Override
	public String toString() {
		return "lsj_humor_board [idx=" + idx + ", user_fk=" + user_fk + ", up=" + up + ", down=" + down + ", readnum="
				+ readnum + ", writedate=" + writedate + ", subject=" + subject + ", content=" + content + ", filename="
				+ filename + "]";
	}
}
