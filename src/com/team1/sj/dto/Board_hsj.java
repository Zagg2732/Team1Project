package com.team1.sj.dto;

import java.util.Date;

public class Board_hsj {
 private int idx;
 private int idx_fk;
 private int up;
 private int down;
 private int readnum; // default 0 
 private Date writedate; // default sysdate
 private String subject;
 private String content;
 private String filename;
 
 public Board_hsj() {}

 public Board_hsj(int idx, int idx_fk, int up, int down, int readnum, Date writedate, 
		 String subject, String content, String filename) {
	 super();
	 this.idx = idx;
	 this.idx_fk = idx_fk;
	 this.up = up;
	 this.down = down;
	 this.readnum = readnum;
	 this.writedate = writedate;
	 this.subject = subject;
	 this.content = content;
	 this.filename = filename;
 }
 
public int getIdx() {
	return idx;
}

public void setIdx(int idx) {
	this.idx = idx;
}

public int getIdx_fk() {
	return idx_fk;
}

public void setIdx_fk(int idx_fk) {
	this.idx_fk = idx_fk;
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
	return "HumorBoard [idx=" + idx + ", idx_fk=" + idx_fk + ", up=" + up + ", down=" + down + ", readnum=" + readnum
			+ ", writedate=" + writedate + ", subject=" + subject + ", content=" + content + ", filename=" + filename
			+ "]";
}
 
 
 
}
