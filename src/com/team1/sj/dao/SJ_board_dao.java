package com.team1.sj.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.team1.sj.dto.SJ_Board_Reply;
import com.team1.sj.dto.SJ_board;


public class SJ_board_dao {
	DataSource ds = null;
	
	public SJ_board_dao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
	}
	
	//글쓰기 
	public int writeok(SJ_board boarddata, String type) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int row = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "insert into "
					+ type +"(idx, nickname, up, down, readnum, writedate, subject, content, filename)"+
							" values("+ type + "_idx.nextval,?,0,0,0,sysdate,?,?,?)";
			
			
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, boarddata.getNickname());
				pstmt.setString(2, boarddata.getSubject());
				pstmt.setString(3, boarddata.getContent());
				pstmt.setString(4, boarddata.getFilename());
				
				
				
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
	
	public List<SJ_board> listWithPage(int cpage, int pagesize, String type){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<SJ_board> list = null;
		try {
			conn = ds.getConnection();
			String sql = "SELECT * FROM " +
					"(SELECT rownum rn, hb.IDX ,tu.NICKNAME , hb.SUBJECT, hb.READNUM , hb.UP , hb.DOWN , hb.WRITEDATE " +
							" from "
							+ type 
							+ " hb " +
							" left join TEAM1_USER tu on hb.USERID_FK = tu.USERID " +
							" WHERE rownum <=? order by hb.IDX desc " +
							")WHERE rn >= ?";
		
			
			pstmt = conn.prepareStatement(sql);
			
			int start = cpage * pagesize - (pagesize -1);
			int end = cpage * pagesize;
			
			pstmt.setInt(1, end);
			pstmt.setInt(2, start);
			
			rs = pstmt.executeQuery();
			
			list = new ArrayList<SJ_board>();
			
			while(rs.next()) {
				SJ_board board = new SJ_board();
				
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
	
	public List<SJ_board> list(String name) { //name 파라미터로 humor_board인지 notice_board 인지 파악할거임
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<SJ_board> list = null;
		
		try {
			conn = ds.getConnection();
			//sql문. board에 출력될 정보가 담긴 컬럼들 조회
			String sql = "SELECT IDX , nickname , UP , DOWN , READNUM , WRITEDATE , SUBJECT FROM " 
						 + name //humor_board || notice_board
						 + " hb LEFT JOIN TEAM1_USER tu ON hb.USERID_FK = tu.USERID ORDER BY idx desc"; 
			
			System.out.println(sql);
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			list = new ArrayList<SJ_board>();
			
			while(rs.next()) {
				SJ_board board = new SJ_board();
				board.setIdx(rs.getInt("idx"));
				board.setNickname(rs.getString("nickname"));
				board.setUp(rs.getInt("UP"));
				board.setDown(rs.getInt("DOWN"));
				board.setReadnum(rs.getInt("READNUM"));
				board.setWritedate(rs.getDate("WRITEDATE"));
				board.setSubject(rs.getString("SUBJECT"));
				
				list.add(board);
			}			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return list;
	}

	
	public boolean getReadNum(String idx, String type) { //idx는 글번호 type은 boardtype(공지사항, 유머게시판 등을 구분)
	
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			conn = ds.getConnection();
			String sql = "update " + type + " set readnum = readnum + 1 where idx = ?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idx);
			
			int row = pstmt.executeUpdate(); //executeUpdate는 db에 적용된 행의갯수를 출력함. 0개면 실패 1개면 성공한거임(해당게시글 조회수만 건드리니까)
			if(row > 0) {
				result = true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (Exception e) {
				
			}
		}
		
		return result;
	}
	
	public SJ_board getContentDetails(String idx, String boardName) {
		
		System.out.println("getContentDetails 입장성공");
		
		SJ_board board = new SJ_board();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			String sql = "SELECT subject , nickname, readnum, up, down, writedate, content FROM "
						+ boardName + 
						" hb LEFT JOIN TEAM1_USER tu ON hb.USERID_FK = tu.USERID WHERE IDX = "
						+ idx ;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				board.setSubject(rs.getString("subject"));
				board.setNickname(rs.getString("nickname"));
				board.setReadnum(rs.getInt("readnum"));
				board.setUp(rs.getInt("up"));
				board.setDown(rs.getInt("down"));
				board.setWritedate(rs.getDate("writedate"));
				board.setContent(rs.getString("content"));
				//idx는 이미 requestScope('idx')에서 쓸수 있기 때문에 굳이 받지않았습니다만 코드짤때 너무불편하다고 판단되면 그냥받을까??
				
			}
			
		} catch (Exception e) {
			
		} finally {
			try {
				pstmt.close();
				conn.close();		
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return board;
	}
	
	public List<SJ_Board_Reply> getReplyList(String idx, String replyType) { //글번호, 게시판이름을 가지고와서 query문 완성
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<SJ_Board_Reply> replyList = null;
			
		try {

			
			conn = ds.getConnection();
			String sql = "SELECT hb.idx, hr.CONTENT , hr.UP , hr.DOWN , hr.WRITEDATE , hr.REFER , hr.\"DEPTH\" , hr.STEP , tu.NICKNAME FROM "
					   + replyType 
					   + " hr LEFT JOIN TEAM1_USER tu ON  hr.USERID_FK = tu.USERID LEFT JOIN HUMOR_BOARD hb ON hr.IDX_FK = hb.IDX WHERE idx = "
					   + idx ;
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			//리플이 들어갈 리스트
			
			replyList = new ArrayList<>();
			
			while(rs.next()) {
					
				SJ_Board_Reply reply = new SJ_Board_Reply();

				reply.setContent(rs.getString("content"));  
				reply.setUp(rs.getInt("up"));
				reply.setDown(rs.getInt("down"));
				reply.setWritedate(rs.getDate("writedate"));
				reply.setRefer(rs.getInt("refer"));
				reply.setDepth(rs.getInt("depth"));
				reply.setStep(rs.getInt("step"));
				reply.setNickname(rs.getString("nickname"));
								
				replyList.add(reply);
				
				reply = null;						
			}
			
			System.out.println("replyList입니다 행님!! : " + replyList);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return replyList;
	}
	
	public int replyWrite(String type, int idx_fk , String writer , String userid, String content, String pwd) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int row = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "insert into reply(no,writer,userid,content,pwd,idx_fk) "+
			           " values(reply_no.nextval,?,?,?,?,?)";
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		return 0;
	}
	
}








































