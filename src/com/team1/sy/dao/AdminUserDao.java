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

public class AdminUserDao {

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;

	public AdminUserDao() {
		try{
			Context init = new InitialContext();
	  		ds = 
	  			(DataSource) init.lookup("java:comp/env/jdbc/oracle");
		}catch(Exception ex){
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
		
	}
	
	//회원 목록보기
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
	
	// 회원 총 건수 구하기
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

}
