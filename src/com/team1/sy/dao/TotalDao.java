package com.team1.sy.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.team1.sy.dto.Member;

public class TotalDao {

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;

	public TotalDao() {
		try{
			Context init = new InitialContext();
	  		ds = (DataSource) init.lookup("java:comp/env/jdbc/oracle");
		}catch(Exception ex){
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
		
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
	
	//회원가입
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
	
	
	//아이디 닉네임 중복체크 로직
	public boolean overlapCheck(String columnName, String search) {
		String sql = "SELECT " + columnName + " FROM TEAM1_USER WHERE " + columnName + " = ?";
		boolean result = false;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, search);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				result = false;
			}else {
				result = true;
			}
			
		} catch (Exception e) {
			e.getStackTrace();
		} finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){ex.getStackTrace();}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){ex.getStackTrace();}
			if(con!=null) try{con.close();}catch(SQLException ex){ex.getStackTrace();}
		}
		return result;
	}
	
	public boolean editProfileUpdate(String userId, String afterNick, String afterPW) {
		String sql = "UPDATE TEAM1_USER SET NICKNAME = ?, PASSWORD = ? WHERE USERID = ?";
		boolean result = false;
		int updateResult = 0;
		
		try {
			con = ds.getConnection();
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, afterNick);
			pstmt.setString(2, afterPW);
			pstmt.setString(3, userId);

			updateResult = pstmt.executeUpdate();
			
			if(updateResult > 0) {
				result = true;
			}
			
		} catch (Exception e) {
			e.getStackTrace();
		} finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){ex.getStackTrace();}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){ex.getStackTrace();}
			if(con!=null) try{con.close();}catch(SQLException ex){ex.getStackTrace();}
		}
		
		return result;
	}
	

}
