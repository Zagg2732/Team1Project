package com.team1.yh.dto;
import java.util.Date;

public class KimsBoard {
	
	private int idx;
	private String userid_fk;
	private String subject;
	private String content;
	private Date writedate;
	private int readnum;
	private int rownum;
	
	
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
	public int getRownum() {
		return rownum;
	}
	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
	
	@Override
	public String toString() {
		return "KimsBoard [idx=" + idx + ", userid_fk=" + userid_fk + ", subject=" + subject + ", content=" + content
				+ ", writedate=" + writedate + ", readnum=" + readnum + ", rownum=" + rownum + "]";
	}
	
}
