package com.team1.sj.dto;

import java.util.Date;

public class Board_hsj {
	
	private int idx;
	private String userid_fk;
	private int up;
	private int down;
	private int readnum;
	private Date writedate;
	private String subject;
	private String content;
	private String filename;
	private String nickname;
 
 public Board_hsj() {
	 
 }
 
 public Board_hsj(int idx, String userid_fk, int up, int down, int readnum, Date writedate,String subject, String content, String filename, String nickname) {
	 super();
	 this.idx = idx;
	 this.userid_fk = userid_fk;
	 this.up = up;
	 this.down = down;
	 this.readnum = readnum;
	 this.writedate = writedate;
	 this.subject = subject;
	 this.content = content;
	 this.filename = filename;
	 this.nickname = nickname;
	 
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


public String getNickname() {
	return nickname;
}

public void setNickname(String nickname) {
	this.nickname = nickname;
}

@Override
public String toString() {
	return "Board_hsj [idx=" + idx + ", userid_fk=" + userid_fk + ", up=" + up + ", down=" + down + ", readnum="
			+ readnum + ", writedate=" + writedate + ", subject=" + subject + ", content=" + content + ", filename="
			+ filename + ", nickname=" + nickname + "]";
}


 
 
}
