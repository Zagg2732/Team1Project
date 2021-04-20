package com.team1.sj.dao;

import javax.naming.NamingException;
import javax.sql.DataSource;

import com.team1.sj.dto.HumorBoard;
import com.team1.sj.dto.NoticeBoard;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.Context;
import javax.naming.InitialContext;

public class BoardDao {
	DataSource ds = null;
	
	public BoardDao() throws NamingException{
		Context context = new InitialContext();
		
		ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
		
	}
	
	//글쓰기 - 유머게시판 
	public int writeok(HumorBoard humordata) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "insert into HUMOR_BOARD(idx, userid_fk, up, down, readnum, writedate, subject, content, filename)"+
							"values(HUMOR_BOARD.nextval,?,?,?,0,sysdate,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, humordata.getUp());
			pstmt.setInt(2, humordata.getDown());
			pstmt.setInt(3, humordata.getReadnum());
			pstmt.setString(4, humordata.getSubject());
			pstmt.setString(5, humordata.getContent());
			pstmt.setString(6, humordata.getFilename());
			
			row = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		return row;
		
	}
	
	// 글쓰기 - 공지게시판 
	public int writeok2(NoticeBoard noticedata) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "insert into NOTICE_BOARD(idx, userid_fk, up, down, readnum, writedate, subject, content, filename)"+
						"values(NOTICE_BOARD.nextval,?,?,?,0,sysdate,?,?,?)";
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, noticedata.getUp());
			pstmt.setInt(2, noticedata.getDown());
			pstmt.setInt(3, noticedata.getReadnum());
			pstmt.setString(4, noticedata.getSubject());
			pstmt.setString(5, noticedata.getContent());
			pstmt.setString(6, noticedata.getFilename());
			
			row = pstmt.executeUpdate();
			
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		
		return row;
	}
	
	//게시물 총 건수 구하기
		public int totalBoardCount() {
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				int totalcount = 0;
				try {
					conn = ds.getConnection(); //dbcp 연결객체 얻기
					String sql="select count(*) cnt from jspboard";
					pstmt = conn.prepareStatement(sql);
					rs = pstmt.executeQuery();
					if(rs.next()) {
						totalcount = rs.getInt("cnt");
					}
				}catch (Exception e) {
					
				}finally {
					try {
						pstmt.close();
						rs.close();
						conn.close();//반환  connection pool 에 반환하기
					}catch (Exception e) {
						
					}
				}
				return totalcount;
			}
	
}
