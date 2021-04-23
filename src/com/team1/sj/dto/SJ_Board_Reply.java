package com.team1.sj.dto;

import java.util.Date;

public class SJ_Board_Reply {
	private int idx_fk;
	private String userid_fk;
	private String content;
	private int up;
	private int down;
	private Date writedate;
	private String nickname;
	
	// 계층형
	private int refer;
	private int depth;
	private int step;
	
	public SJ_Board_Reply() {}
	
	public SJ_Board_Reply(int idx_fk, String userid_fk, String content, int up, int down, Date writedate, int refer, int depth, int step) {
		super();
		this.idx_fk = idx_fk;
		this.userid_fk = userid_fk;
		this.content = content;
		this.up = up;
		this.down = down;
		this.writedate = writedate;
		this.refer = refer;
		this.depth = depth;
		this.step = step;
		
	}

	public int getIdx_fk() {
		return idx_fk;
	}

	public void setIdx_fk(int idx_fk) {
		this.idx_fk = idx_fk;
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
		return "SJ_Board_Reply [idx_fk=" + idx_fk + ", userid_fk=" + userid_fk + ", content=" + content + ", up=" + up
				+ ", down=" + down + ", writedate=" + writedate + ", nickname=" + nickname + ", refer=" + refer
				+ ", depth=" + depth + ", step=" + step + "]";
	}
	
	
}
