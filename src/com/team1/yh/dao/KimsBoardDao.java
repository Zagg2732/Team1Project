package com.team1.yh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.team1.yh.dto.KimsBoard;

public class KimsBoardDao {
DataSource ds = null;
	
	//POOL
	public KimsBoardDao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
	}
	
	//킴스보드 글 삽입
	public int writeok(KimsBoard kimsdata) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		try {
			conn = ds.getConnection();
			String sql = "insert into KIMS_BOARD(idx, userid_fk, subject, content, writedate, readnum)" + 
					     "values(KIMS_BOARD_idx.nextval,?, ?, ?, sysdate, 0)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, kimsdata.getUserid());
			pstmt.setString(2, kimsdata.getSubject());
			pstmt.setString(3, kimsdata.getContent());
			
			row = pstmt.executeUpdate();
			
		}catch(Exception e) {
			
		}finally {
			try {
				pstmt.close();
				conn.close();//반환하기
			} catch (Exception e2) {
			
			}
		}
		return row;
	}
	
	// 게시물 목록보기
		public List<KimsBoard> list(int cpage, int pagesize){
			Connection conn = null;
			PreparedStatement pstmt = null;
			ResultSet rs = null;
			List<KimsBoard> list = null;
			try {
				conn = ds.getConnection();
				String sql = "(SELECT kb.IDX , kb.SUBJECT, kb.USERID_FK , kb.WRITEDATE" +
							 " from KIMS_BOARD kb " +
							 " left join TEAM1_USER tu on kb.USERID_FK = tu.USERID)";
				
				
				pstmt = conn.prepareStatement(sql);
				
				int start = cpage * pagesize - (pagesize -1);
				int end = cpage * pagesize;
				
				pstmt.setInt(1, end);
				pstmt.setInt(2, start);
				
				rs = pstmt.executeQuery();
				
				list = new ArrayList<KimsBoard>();
				
				while(rs.next()) {
					KimsBoard board = new KimsBoard();
					
					board.setIdx(rs.getInt("idx"));
					board.setSubject(rs.getString("subject"));
					board.setReadnum(rs.getInt("readnum"));
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
						String sql="select count(*) cnt from KIMS_BOARD";
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
