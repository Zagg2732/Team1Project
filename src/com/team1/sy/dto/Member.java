package com.team1.sy.dto;

import java.util.Date;

public class Member {
	
	private String userId;
	private String userName;
	private String nickName;
	private String userPassword;
	private Date joinDate;
	private int grade;
	
	public Member() { }
	
	public Member(String userId, String userName, String nickName, String userPassword, Date joinDate, int grade) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.nickName = nickName;
		this.userPassword = userPassword;
		this.joinDate = joinDate;
		this.grade = grade;
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public Date getJoinDate() {
		return joinDate;
	}
	public void setJoinDate(Date joinDate) {
		this.joinDate = joinDate;
	}
	public int getGrade() {
		return grade;
	}
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	@Override
	public String toString() {
		return "Member [userId=" + userId + ", userName=" + userName + ", nickName=" + nickName + ", userPassword="
				+ userPassword + ", joinDate=" + joinDate + ", grade=" + grade + "]";
	}

}
