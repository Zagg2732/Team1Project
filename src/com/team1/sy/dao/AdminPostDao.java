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

import com.team1.jh.dto.DiaryDto;
import com.team1.sj.dto.SJ_board;
import com.team1.sy.dto.Member;
import com.team1.yh.dto.KimsBoard;

public class AdminPostDao {

	//Connection conn;
	//PreparedStatement pstmt;
	//ResultSet rs;
	DataSource ds;

	public AdminPostDao() {
		try{
			Context init = new InitialContext();
	  		ds = (DataSource) init.lookup("java:comp/env/jdbc/oracle");
		}catch(Exception ex){
			System.out.println("DB 연결 실패 : " + ex);
			return;
		}
		
	}
	
	//SJ 추천수순 리스트 가져오기
		public List<SJ_board> sjBoardBestList(String boardName, int pagesize){
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<SJ_board> list = null;
			try {
				conn = ds.getConnection();
				String sql = "SELECT IDX , NICKNAME , UP , DOWN , READNUM , WRITEDATE , SUBJECT FROM (SELECT * FROM "
								+ boardName + 
								" bn LEFT JOIN TEAM1_USER tu ON bn.USERID_FK = tu.USERID ORDER BY up DESC) WHERE rownum <= ?";
				
				pstmt = conn.prepareStatement(sql);

				pstmt.setInt(1, pagesize);
				
				rs = pstmt.executeQuery();
				list = new ArrayList<SJ_board>();
				while(rs.next()) {
					
					SJ_board board = new SJ_board();
					
					board.setIdx(rs.getInt("IDX"));
					board.setNickname(rs.getString("NICKNAME"));
					board.setUp(rs.getInt("UP"));
					board.setDown(rs.getInt("DOWN"));
					board.setReadnum(rs.getInt("READNUM"));
					board.setWritedate(rs.getDate("WRITEDATE"));
					board.setSubject(rs.getString("SUBJECT"));
					
					list.add(board);
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
	
	//jh minihomepy 추천수순 리스트 가져오기
	public List<DiaryDto> diaryBestList(int pagesize){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<DiaryDto> list = null;
		try {
			conn = ds.getConnection();
			String sql = "SELECT IDX, USERNAME, SUBJECT, WRITEDATE, READNUM FROM DIARY d LEFT JOIN TEAM1_USER tu ON d.USERID_FK = tu.USERID WHERE rownum <= ? ORDER BY READNUM DESC";
			
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, pagesize);
			
			rs = pstmt.executeQuery();
			list = new ArrayList<DiaryDto>();
			while(rs.next()) {
				
				DiaryDto diary = new DiaryDto();
				
				diary.setIdx(rs.getInt("IDX"));
				diary.setUsername(rs.getString("USERNAME"));
				diary.setSubject(rs.getString("SUBJECT"));
				diary.setWritedate(rs.getDate("WRITEDATE"));
				diary.setReadnum(rs.getInt("READNUM"));
							
				list.add(diary);
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
	
	//Kim'sboard 조회수순 리스트 가져오기
		public List<KimsBoard> kimBestList(int pagesize){
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<KimsBoard> list = null;
			try {
				conn = ds.getConnection();
				String sql = "SELECT IDX, USERID_FK, SUBJECT, WRITEDATE, READNUM FROM (SELECT rownum, kb.* FROM KIMS_BOARD kb ORDER BY READNUM DESC) WHERE ROWNUM <= ?";
				
				pstmt = conn.prepareStatement(sql);

				pstmt.setInt(1, pagesize);
				
				rs = pstmt.executeQuery();
				list = new ArrayList<KimsBoard>();
				while(rs.next()) {
					
					KimsBoard kim = new KimsBoard();
					
					kim.setIdx(rs.getInt("IDX"));
					kim.setUserid_fk(rs.getString("USERID_FK"));
					kim.setSubject(rs.getString("SUBJECT"));
					kim.setWritedate(rs.getDate("WRITEDATE"));
					kim.setReadnum(rs.getInt("READNUM"));
								
					list.add(kim);
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

}
