package com.team1.sy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.team1.sy.dto.Member;

public class AdminDao {

	Connection con;

	PreparedStatement pstmt;

	ResultSet rs;

	DataSource ds;

	public AdminDao() {
		try{
			Context init = new InitialContext();
	  		ds = 
	  			(DataSource) init.lookup("java:comp/env/jdbc/oracle");
		}catch(Exception ex){
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
		
	}
	
	//게시물 목록보기
		public List<Member> list(int cpage , int pagesize){
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<Member> list = null;
			try {
				conn = ds.getConnection();
				String sql = "select userid, username, nickname,joindate,grade_fk from TEAM1_USER";
				pstmt = conn.prepareStatement(sql);
				//공식같은 로직
				int start = cpage * pagesize - (pagesize -1); //1 * 5 - (5 - 1) >> 1
				int end = cpage * pagesize; // 1 * 5 >> 5
				//
				//pstmt.setInt(1, end);
				//pstmt.setInt(2, start);
				
				rs = pstmt.executeQuery();
				list = new ArrayList<Member>();
				while(rs.next()) {
					Member member = new Member();
					
					member.setUserId(rs.getString("USERID"));
					member.setUserName(rs.getString("USERNAME"));
					member.setNickName(rs.getString("NICKNAME"));
					member.setJoinDate(rs.getDate("JOINDATE"));
					member.setGrade(rs.getInt("GRADE_FK"));
					
					list.add(member);
				}
				
			}catch (Exception e) {
				System.out.println("오류 :" + e.getMessage());
			}finally {
				try {
					pstmt.close();
					rs.close();
					conn.close();//반환
				} catch (Exception e2) {
					
				}
			}
				
			return list;
		}
	
	// 게시물 총 건수 구하기
	public int totalUserCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalUserCnt = 0;
		try {
			conn = ds.getConnection(); // dbcp 연결객체 얻기
			String sql = "select count(*) cnt from TEAM1_USER";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				totalUserCnt = rs.getInt("cnt");
			}
		} catch (Exception e) {

		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();// 반환 connection pool 에 반환하기
			} catch (Exception e) {

			}
		}
		return totalUserCnt;
	}
	
	//login 멤버 조회
	public Member isMember(String userid, String userpw) {
		String sql = "SELECT * FROM TEAM1_USER WHERE USERID=?";
		int result = -1;
		
		Member member = null;

		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, userid);
			rs = pstmt.executeQuery();

			if (rs.next()) {
				if (rs.getString("PASSWORD").equals(userpw)) {
					
					String id = rs.getString("USERID");
					String name = rs.getString("USERNAME");
					String nick = rs.getString("NICKNAME");
					String pw = rs.getString("PASSWORD");
					Date join = rs.getDate("JOINDATE");
					int grade = rs.getInt("GRADE_FK");
					
					member = new Member(id, name, nick, pw, join, grade);
					
					result = 1;// 일치.
				} else {
					result = 0;// 불일치.
				}
			} else {
				result = -1;// 아이디 존재하지 않음.
			}
		} catch (Exception ex) {
			System.out.println("isMember 에러: " + ex);
		} finally {
			System.out.println("result : " + result);
			if (rs != null)
				try {
					rs.close();
				} catch (SQLException ex) {
				}
			if (pstmt != null)
				try {
					pstmt.close();
				} catch (SQLException ex) {
				}
			if (con != null)
				try {
					con.close();
				} catch (SQLException ex) {
				}
		}
		return member;
	}
	
	
	public boolean insertMember(Member tempMember) {
		//INSERT INTO TEAM1_USER(USERID,USERNAME,NICKNAME,PASSWORD,JOINDATE,GRADE_FK)
		//VALUES ('admin','홍길동','관리자','1004',sysdate,2);
		String sql = "INSERT INTO TEAM1_USER(USERID,USERNAME,NICKNAME,PASSWORD,JOINDATE,GRADE_FK) VALUES (?,?,?,?,sysdate,0)";
		int result=-1;
		
		try{
			con = ds.getConnection();
			pstmt=con.prepareStatement(sql);
			
			pstmt.setString(1, tempMember.getUserId());
			pstmt.setString(2, tempMember.getUserName());
			pstmt.setString(3, tempMember.getNickName());
			pstmt.setString(4, tempMember.getUserPassword());

			result=pstmt.executeUpdate();
			
			if(result!=0){
				return true;
			}
		}catch(Exception ex){
			System.out.println("insertMember 에러: " + ex);		
			return false;
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(con!=null) try{con.close();}catch(SQLException ex){}
		}
		return false;
	}
	
	
	

}
