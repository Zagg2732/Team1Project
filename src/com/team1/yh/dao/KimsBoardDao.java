package com.team1.yh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;

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
			String sql="insert into KIMS_BOARD(idx, userid_fk,subject, content, writedate, readnum)" + 
					   "values(KIMS_BOARD_idx.nextval,?, ?, ?, sysdate, 0)";
			
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, kimsdata.getSubject());
			pstmt.setString(2, kimsdata.getUserid());
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
}
