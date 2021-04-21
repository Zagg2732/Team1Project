package com.team1.yh.dto;
import java.util.Date;

public class KimsBoard {
	
	private int idx;
	private String userid;
	private String subject;
	private String content;
	private Date writedate;
	private int readnum;
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
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
	
	@Override
	public String toString() {
		return "Board [idx=" + idx + ", userid=" + userid + ", subject=" + subject + ", content=" + content
				+ ", writedate=" + writedate + ", readnum=" + readnum + "]";
	}
}
