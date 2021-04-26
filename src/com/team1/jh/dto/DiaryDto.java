package com.team1.jh.dto;

import java.util.Date;

public class DiaryDto {
	private int idx; 
	private String userid_fk;
	private String username;
	private String subject; 
	private String content; 
	private Date writedate; 
	private int readnum; 
	private String filename; 
	private int refer; 
	private int depth; 
	private int step;
	
	public DiaryDto() {}
	
	public DiaryDto(int idx, String userid_fk, String subject, String content, Date writedate, 
			int readnum, String filename, int refer, int depth, int step) {
		super();
		this.idx = idx;
		this.userid_fk = userid_fk;
		this.subject = subject;
		this.content = content;
		this.writedate = writedate;
		this.readnum = readnum;
		this.filename = filename;
		this.refer = refer;
		this.depth = depth;
		this.step = step;
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
	public Date getWritedate() {
		return writedate;
	}
	public void setWritedate(Date writedate) {
		this.writedate = writedate;
	}
	public int getReadnum() {
		return readnum;
	}
	public void setReadnum(int readnum) {
		this.readnum = readnum;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getRefer() {
		return refer;
	}
	public void setRefer(int refer) {
		this.refer = refer;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getStep() {
		return step;
	}
	public void setStep(int step) {
		this.step = step;
	} 
}
