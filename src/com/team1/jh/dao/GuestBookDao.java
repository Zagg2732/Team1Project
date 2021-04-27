package com.team1.jh.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.http.HttpSession;
import javax.sql.DataSource;
import javax.websocket.Session;

import com.team1.jh.dto.GuestBookDto;
import com.team1.sy.dto.AdminTalk;
import com.team1.sy.dto.Member;

public class GuestBookDao {
	DataSource ds = null;
	
	public GuestBookDao() throws NamingException {
		//JNDI
		Context context = new InitialContext(); //현재 프로젝트에서 특정 이름을 가진 녀석을 검색(이름 기반 검색)
		Context contextDetail = (Context)context.lookup("java:comp/env");
		
		ds = (DataSource)contextDetail.lookup("jdbc/oracle");  //java:comp/env/  +  jdbc/oracle   이름  => 정해진 약속	
	}

	//방명록 전체조회 
	public List<GuestBookDto> guestBookList(int grade, String userid) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<GuestBookDto> list = null;
		
		try {
			conn = ds.getConnection();			
			String sql = "SELECT GB.IDX, GB.USERID_FK, TU.USERNAME, GB.CONTENT, GB.WRITEDATE, GB.READYN " +
					   "FROM GUESTBOOK GB " +
					   "JOIN TEAM1_USER TU ON GB.USERID_FK = TU.USERID ";
			
			if( grade != 1 ) {
				sql += "WHERE GB.READYN = 'Y' OR GB.USERID_FK = ? ";
				sql += "ORDER BY GB.IDX DESC";
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, userid);
				rs = pstmt.executeQuery();
				
			}else {
				sql += "ORDER BY GB.IDX DESC";	
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
			}

			list = new ArrayList<>();
			
			while(rs.next()) {
				
				int idx = rs.getInt("idx");
				String userid_fk = rs.getString("userid_fk");
				String content  =rs.getString("content");				
				Date date = rs.getDate("writedate");
				String readyn = rs.getString("readyn");
				String username  =rs.getString("username");
				
				GuestBookDto book = new GuestBookDto();
				book.setIdx(idx);
				book.setUserid_fk(userid_fk);
				book.setContent(content);
				book.setWritedate(date);
				book.setReadyn(readyn);
				book.setUserName(username);
								
				list.add(book);

			}			

		} catch (Exception e) {
			System.out.println("방명록 조회 오류: " +e.getMessage());
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();//반환
			}catch (Exception e) {
				System.out.println("오류: " +e.getMessage());
			}
		}
		
		return list;
	}
	
	//방명록 글쓰기 
	public int guestBookAdd(GuestBookDto gusestbook) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try{
			conn = ds.getConnection();
			
			String sql = "INSERT INTO GUESTBOOK (IDX, USERID_FK, CONTENT, READYN) " + 
					 	 "VALUES (GUESTBOOK_idx.NEXTVAL, ?, ?, ?)";
			
			pstmt= conn.prepareStatement(sql);
			pstmt.setString(1, gusestbook.getUserid_fk());
			pstmt.setString(2, gusestbook.getContent());
			pstmt.setString(3, gusestbook.getReadyn());

			row = pstmt.executeUpdate();
			
		}catch(Exception e){
			System.out.println("guestBookAdd 에러: " + e.getMessage());		
		}finally {
			try {
				pstmt.close();
				conn.close();//반환
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return row;		
	}
	
	//방명록 삭제
	public int guestBookDelete(String idx, String userid_fk) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		
		try {
			String select = "SELECT USERID_FK FROM GUESTBOOK WHERE IDX =?";
			String delete = "DELETE FROM GUESTBOOK WHERE IDX =?";
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(select);
			pstmt.setString(1, idx);
			rs =pstmt.executeQuery();
			
			if(rs.next()) {
				String userid = rs.getString("userid_fk");
				if(userid_fk.equals(userid)) {
					pstmt.close();
					pstmt = conn.prepareStatement(delete);
					pstmt.setString(1, idx);
					row = pstmt.executeUpdate();
				}else {
					row = 0;
				}
			}else {
				row = -1;
			}
		} catch (Exception e) {
			System.out.println("오류: " +e.getMessage());
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();//반환
			}catch (Exception e) {
				
			}
		}
		return row;
	}
	
	
}
