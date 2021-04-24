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

	Connection con;
	PreparedStatement pstmt;
	ResultSet rs;
	DataSource ds;

	public AdminTalkDao() {
		try{
			Context init = new InitialContext();
	  		ds = 
	  			(DataSource) init.lookup("java:comp/env/jdbc/oracle");
		}catch(Exception ex){
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
		
	}
	
	//talk list 목록보기
		public List<AdminTalk> adminTalkList(int cpage , int pagesize){
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<AdminTalk> list = null;
			try {
				conn = ds.getConnection();
				String sql = "SELECT * FROM (SELECT * FROM ADMIN_TALK WHERE ROWNUM <= 5 ORDER BY idx DESC) WHERE ROWNUM >= 1";
								
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
					pstmt.close();
					rs.close();
					conn.close();//반환
				} catch (Exception e2) {
					
				}
			}
			return list;
		}
	
	// 회원 총 건수 구하기
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
				pstmt.close();
				rs.close();
				conn.close();// 반환 connection pool 에 반환하기
			} catch (Exception e) {

			}
		}
		return totalTalkCnt;
	}

}
