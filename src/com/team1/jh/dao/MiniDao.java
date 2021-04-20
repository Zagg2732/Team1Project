package com.team1.jh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.team1.jh.dto.DiaryDto;

public class MiniDao {
	DataSource ds = null;
	
	public MiniDao() throws NamingException {
		//JNDI
		Context context = new InitialContext(); //현재 프로젝트에서 특정 이름을 가진 녀석을 검색(이름 기반 검색)
		Context contextDetail = (Context)context.lookup("java:comp/env");
		
		ds = (DataSource)contextDetail.lookup("jdbc/oracle");  //java:comp/env/  +  jdbc/oracle   이름  => 정해진 약속	
	}
	
	//다이어리 게시물 총 건수 조회 
	public int totalBoardCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalcount = 0;
		try {
			conn = ds.getConnection(); //dbcp 연결객체 얻기
			String sql="select count(*) cnt from diary";
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
	
	//다이어리 조회 
	public List<DiaryDto> list(int cpage , int pagesize){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<DiaryDto> list = null;
		
		try {
			conn = ds.getConnection();
			
			//String sql = "select * from diary";
			
			String sql = "SELECT * FROM" +
                    					"(SELECT rownum rn, idx, userid_fk, subject, content, writedate, readnum, filename, refer, depth, step" +
                    					"FROM (SELECT * FROM DIARY ORDER BY REFER DESC , STEP ASC)"+
                    					"WHERE rownum <= ?" +  //endrow
                    					") WHERE rn >= ?"; //startrow
			
			pstmt = conn.prepareStatement(sql);

			//공식같은 로직
			int start = cpage * pagesize - (pagesize -1); //1 * 5 - (5 - 1) >> 1
			int end = cpage * pagesize; // 1 * 5 >> 5
			
			pstmt.setInt(1, start);
			pstmt.setInt(5, end);
			
			rs = pstmt.executeQuery();
			list = new ArrayList<DiaryDto>();
			while(rs.next()) {
				DiaryDto diaryDto = new DiaryDto();
				diaryDto.setIdx(rs.getInt("idx"));
				diaryDto.setUserid_fk(rs.getString("userid_fk"));
				diaryDto.setSubject(rs.getString("subject"));
				diaryDto.setContent(rs.getString("content"));
				diaryDto.setWritedate(rs.getDate("writedate"));
				diaryDto.setReadnum(rs.getInt("readnum"));
				diaryDto.setFilename(rs.getString("filename"));
				diaryDto.setRefer(rs.getInt("refer"));
				diaryDto.setDepth(rs.getInt("depth"));
				diaryDto.setStep(rs.getInt("step"));
				
				list.add(diaryDto);
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
	
	
}
