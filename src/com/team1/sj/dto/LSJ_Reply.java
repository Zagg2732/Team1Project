package com.team1.sj.dto;

import java.sql.Date;

public class LSJ_Reply {
	private int idx; //idx_fk
	private String content;
	private int up;
	private int down;
	private Date writedate;
	private int refer;
	private int depth;
	private int step;
	private String nickname;
	
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	public Date getWritedate() {
		return writedate;
	}
	public void setWritedate(Date writedate) {
		this.writedate = writedate;
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
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	@Override
	public String toString() {
		return "LSJ_Reply [idx=" + idx + ", content=" + content + ", up=" + up + ", down=" + down + ", writedate="
				+ writedate + ", refer=" + refer + ", depth=" + depth + ", step=" + step + ", nickname=" + nickname
				+ "]";
	}
	
}
