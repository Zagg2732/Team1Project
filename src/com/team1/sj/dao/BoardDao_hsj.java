package com.team1.sj.dao;

import javax.naming.NamingException;


import javax.sql.DataSource;

import com.team1.sj.dto.Board_hsj;
import com.team1.sj.dto.Board_Reply_hsj;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;

public class BoardDao_hsj {
	DataSource ds = null;
	
	public BoardDao_hsj() throws NamingException{
		Context context = new InitialContext();
		
		ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
		
	}
	
	//글쓰기 - 유머게시판 
	public int writeok(Board_hsj humordata) {
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
	
	
	// 게시물 목록보기
	public List<Board_hsj> list(int cpage, int pagesize){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<Board_hsj> list = null;
		try {
			conn = ds.getConnection();
			String sql = "SELECT * FROM " +
					"(SELECT rownum rn, hb.IDX ,tu.NICKNAME , hb.SUBJECT, hb.READNUM , hb.UP , hb.DOWN , hb.WRITEDATE " +
							" from HUMOR_BOARD hb " +
							" left join TEAM1_USER tu on hb.USERID_FK = tu.USERID " +
							" WHERE rownum <=? order by hb.IDX desc " +
							")WHERE rn >= ?";
		
			
			pstmt = conn.prepareStatement(sql);
			
			int start = cpage * pagesize - (pagesize -1);
			int end = cpage * pagesize;
			
			pstmt.setInt(1, end);
			pstmt.setInt(2, start);
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<Board_hsj>();
			
			while(rs.next()) {
				Board_hsj board = new Board_hsj();
				
				board.setIdx(rs.getInt("idx"));
				board.setNickname(rs.getString("nickname"));
				board.setSubject(rs.getString("subject"));
				board.setReadnum(rs.getInt("readnum"));
				board.setUp(rs.getInt("up"));
				board.setDown(rs.getInt("down"));
				board.setWritedate(rs.getDate("writedate"));
							
				list.add(board);
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("오류 : " + e.getMessage());
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return list;
	}
	
	
	
	//게시물 총 건수 구하기
		public int totalBoardCount() {
				Connection conn = null;
				PreparedStatement pstmt = null;
				ResultSet rs = null;
				int totalcount = 0;
				try {
					conn = ds.getConnection(); //dbcp 연결객체 얻기
					String sql="select count(*) cnt from HUMOR_BOARD";
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
		
	
		
		// 게시글 조회수 증가
		public boolean getReadNum(String idx) {
			Connection conn = null;
			PreparedStatement pstmt = null;
			boolean result = false;
			
			try {
				conn = ds.getConnection();
				String sql = "update HUMOR_BOARD set readnum = readnum + 1 where idx=?";
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, idx);
				
				int row = pstmt.executeUpdate();
				if(row > 0) {
					result = true;
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			
				// TODO: handle exception
			}finally {
				try {
					pstmt.close();
					conn.close();
				} catch (Exception e2) {
					// TODO: handle exception
				}
			}
			
			return result;
		}

	
}
