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

import com.team1.sj.dto.SJ_board;
import com.team1.sy.dto.AdminTalk;
import com.team1.sy.dto.Member;

public class AdminTalkDao {

	//Connection conn;
	//PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;

	public AdminTalkDao() {
		try{
			Context init = new InitialContext();
	  		ds = (DataSource) init.lookup("java:comp/env/jdbc/oracle");
		}catch(Exception ex){
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
		
	}
	
	//talk list 목록보기
		public List<AdminTalk> adminTalkList(int cpage){
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<AdminTalk> list = null;
			
			int pagesize = 5;
			
			try {
				conn = ds.getConnection();
				String sql = "SELECT IDX , USERID_FK , CONTENT , WRITEDATE FROM (SELECT rownum rn, at2.* FROM ADMIN_TALK at2 WHERE ROWNUM <= ? ORDER BY idx DESC) WHERE rn >= ?";
								
				pstmt = conn.prepareStatement(sql);
				
				int start = cpage * pagesize - (pagesize -1);
				int end = cpage * pagesize;

				pstmt.setInt(1, end);
				pstmt.setInt(2, start);
				
				rs = pstmt.executeQuery();
				list = new ArrayList<AdminTalk>();
				while(rs.next()) {
					
					AdminTalk talk = new AdminTalk();
					
					talk.setIdx(rs.getInt("IDX"));
					talk.setUserid_fk(rs.getString("USERID_FK"));
					talk.setContent(rs.getNString("CONTENT"));
					talk.setWritedate(rs.getDate("WRITEDATE"));
					
					list.add(talk);
				}
				
			}catch (Exception e) {
				System.out.println("오류 :" + e.getMessage());
			}finally {
				try {
					rs.close();
					pstmt.close();
					conn.close();//반환
				} catch (Exception e2) {
					
				}
			}
			return list;
		}
	
	// talk 총 건수 구하기
	public int totalTalkCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalTalkCnt = 0;
		try {
			conn = ds.getConnection(); // dbcp 연결객체 얻기
			String sql = "SELECT COUNT(*) cnt FROM ADMIN_TALK";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			if (rs.next()) {
				totalTalkCnt = rs.getInt("cnt");
			}
		} catch (Exception e) {

		} finally {
			try {
				rs.close();
				pstmt.close();
				conn.close();// 반환 connection pool 에 반환하기
			} catch (Exception e) {

			}
		}
		return totalTalkCnt;
	}
	
	// talk Insert
	public boolean insertAdminTalk(AdminTalk admintalk) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		// INSERT INTO ADMIN_TALK (IDX,USERID_FK,CONTENT,WRITEDATE) VALUES (1,'admin','첫번째글입니다.',sysdate);
		
		String sql = "INSERT INTO ADMIN_TALK (IDX,USERID_FK,CONTENT,WRITEDATE) VALUES (ADMIN_TALK_IDX.nextval,?,?,sysdate)";
		int result=-1;
		
		try{
			conn = ds.getConnection();
			pstmt=conn.prepareStatement(sql);
			
			pstmt.setString(1, admintalk.getUserid_fk());
			pstmt.setString(2, admintalk.getContent());

			result=pstmt.executeUpdate();
			
			if(result!=0){
				return true;
			}
		}catch(Exception ex){
			System.out.println("insertAdminTalk 에러: " + ex);		
			return false;
		}finally{
			if(rs!=null) try{rs.close();}catch(SQLException ex){}
			if(pstmt!=null) try{pstmt.close();}catch(SQLException ex){}
			if(conn!=null) try{conn.close();}catch(SQLException ex){}
		}
		return false;		
	}

}
