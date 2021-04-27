package com.team1.sj.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
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
		Context contextDetail = (Context)context.lookup("java:comp/env");
		
		ds = (DataSource)contextDetail.lookup("jdbc/oracle"); 
	}
	
	//유머게시판  글쓰기 
	public int writeok(SJ_board boarddata, String type) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int row = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "insert into "
					+type
					+ "(idx, userid_fk, writedate, subject, content, filename, readnum) " 
					+"values("
						 +type
						 +"_idx.nextval, ?, sysdate, ?, ?, ?, 0)";
			
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, boarddata.getUserid_fk());
				pstmt.setString(2, boarddata.getSubject());
				pstmt.setString(3, boarddata.getContent());
				pstmt.setString(4, boarddata.getFilename());
				
				row = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("널 어쩌면 좋을까 dao " +e.getMessage());
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
			System.out.println("갑자기 너니  : " + e.getMessage());
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (Exception e2) {
				
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
						 + " hb LEFT JOIN TEAM1_USER tu ON hb.USERID_FK = tu.USERID"
						 + " WHERE rownum <= 7 ORDER BY idx desc"; 
			
			
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
				board.setType(name);
				
				list.add(board);
			}			
			
		} catch (SQLException e) {
			e.printStackTrace();
			
		System.out.println("넌 아니겠지 : " + e.getMessage());
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				
				System.out.println("너??" + e.getMessage());
			}
		}
		return list;
	}
	
	//인기글
	public List<SJ_board> hotlist() { 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<SJ_board> list = null;
		
		try {
			conn = ds.getConnection();
			//sql문. board에 출력될 정보가 담긴 컬럼들 조회
			String sql = "SELECT IDX , nickname , UP , DOWN , READNUM , WRITEDATE , SUBJECT FROM HUMOR_BOARD hb LEFT JOIN TEAM1_USER tu ON hb.USERID_FK = tu.USERID ORDER BY hb.Up DESC";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			list = new ArrayList<SJ_board>();
			
			int rows = 0;
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
				rows += 1;
				if(rows >= 7) {
					break;
				}
			}			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			System.out.println("넌 아니겠지 : " + e.getMessage());
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				
				System.out.println("너??" + e.getMessage());
			}
		}
		return list;
	}
	//새글
	public List<SJ_board> newlist() { 
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<SJ_board> list = null;
		
		try {
			conn = ds.getConnection();
			//sql문. board에 출력될 정보가 담긴 컬럼들 조회
			String sql = "SELECT * FROM humor_board ORDER BY WRITEDATE desc";
			
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			list = new ArrayList<SJ_board>();
			
			int rows = 0;
			
			while(rs.next()) {
				SJ_board board = new SJ_board();
				board.setIdx(rs.getInt("idx"));
				//board.setNickname(rs.getString("nickname"));
				board.setUp(rs.getInt("UP"));
				board.setDown(rs.getInt("DOWN"));
				board.setReadnum(rs.getInt("READNUM"));
				board.setWritedate(rs.getDate("WRITEDATE"));
				board.setSubject(rs.getString("SUBJECT"));
				
				list.add(board);
				
				rows += 1;
				if(rows >= 7) {
					break;
				}
			}			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			
			System.out.println("넌 아니겠지 : " + e.getMessage());
		} finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
				
				System.out.println("너??" + e.getMessage());
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
			if(row == 0) {
				System.out.println("readnum error");
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
		
		SJ_board board = new SJ_board();
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		try {
			conn = ds.getConnection();
			String sql = "SELECT filename, userid_fk, subject , nickname, readnum, up, down, writedate, content FROM "
						+ boardName + 
						" hb LEFT JOIN TEAM1_USER tu ON hb.USERID_FK = tu.USERID WHERE IDX = "
						+ idx ;
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				board.setUserid_fk(rs.getString("userid_fk"));
				board.setSubject(rs.getString("subject"));
				board.setNickname(rs.getString("nickName"));
				board.setReadnum(rs.getInt("readnum"));
				board.setUp(rs.getInt("up"));
				board.setDown(rs.getInt("down"));
				board.setWritedate(rs.getDate("writedate"));
				board.setContent(rs.getString("content"));
				board.setFilename(rs.getString("filename"));
			}
			
		} catch (Exception e) {
			
		} finally {
			try {
				pstmt.close();
				conn.close();
				rs.close();
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
			String sql = "SELECT hr.USERID_FK, hb.idx, hr.CONTENT , hr.UP , hr.DOWN , hr.WRITEDATE , hr.REFER , hr.\"DEPTH\" , hr.STEP , tu.NICKNAME FROM "
					   + replyType 
					   + " hr LEFT JOIN TEAM1_USER tu ON  hr.USERID_FK = tu.USERID LEFT JOIN HUMOR_BOARD hb ON hr.IDX_FK = hb.IDX WHERE idx = "
					   + idx + " ORDER BY refer ASC, DEPTH ASC, step desc";
			
			pstmt = conn.prepareStatement(sql);
			
			rs = pstmt.executeQuery();
			
			//리플이 들어갈 리스트
			
			replyList = new ArrayList<>();
			
			while(rs.next()) {
					
				SJ_Board_Reply reply = new SJ_Board_Reply();
				
				reply.setUserid_fk(rs.getString("USERID_FK"));
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
	
	public int replyWrite(String id, String content, String idx, String type) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int nextvalRefer = 0;
		int row = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql = "select max(refer) from "
					   + type
					   + " WHERE idx_fk = "
					   + idx;
		
			pstmt = conn.prepareStatement(sql);		
			rs = pstmt.executeQuery();
			
			while(rs.next()) {
				if(rs.getString("MAX(REFER)") == null ) { //첫 댓글이라 refer가 아직없다면 0
					nextvalRefer = 0;
				} else {								  //아니면 max + 1이 새로들어갈 refer
					int recentVal = Integer.parseInt(rs.getString("MAX(REFER)"));
					nextvalRefer = recentVal + 1;
				}
			}
		
			sql = "insert into "
				   + type
				   + " (idx_fk, userid_fk, content, up, down, writedate, refer, DEPTH, step) "+
			         " values(?, ?, ?, 0, 0, sysdate, ?, 0, 0)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idx);
			pstmt.setString(2, id);
			pstmt.setString(3, content);			
			pstmt.setInt(4, nextvalRefer);
			
			row = pstmt.executeUpdate();
			
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
		
		return row;
	}
	
	public int replyAddReply(String idx, String type, String id, String content, String refer, String depth) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int depthUpdate = Integer.parseInt(depth) + 1;
		int result = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql = "UPDATE "
					+ type
					+ " SET STEP = STEP + 1 WHERE IDX_FK = "
					+ idx
					+ " AND REFER = "
					+ refer
					+ " AND DEPTH = "
					+ depthUpdate ; //같은 댓글의 step 들 1씩 올려줌
		
			pstmt = conn.prepareStatement(sql);		
			result = pstmt.executeUpdate();
			
			if(result == 0) {
				System.out.println("step안올라감");
			} else {
				System.out.println("step올라갔어야함");
			}
			
			pstmt = null;
			
			sql = "INSERT INTO "
				+ type
				+ " (IDX_FK, USERID_FK, CONTENT , UP, DOWN, WRITEDATE, REFER , DEPTH, STEP)"
				+ " VALUES (?, ?, ?, 0, 0, sysdate, ?, ?, 1)";
			
			pstmt = conn.prepareStatement(sql);					
			pstmt.setString(1, idx);
			pstmt.setString(2, id);
			pstmt.setString(3, content);			
			pstmt.setString(4, refer);
			pstmt.setInt(5, depthUpdate);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public int replyDelete(String sessionId, String idx, String type, String refer, String depth, String step) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "DELETE FROM "
					+ type
					+ " WHERE IDX_FK = "
					+ idx
					+ " AND REFER = "
					+ refer
					+ " AND DEPTH = "
					+ depth
					+ " AND step = "
					+ step ;
			
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();
			
			if(result == 0 ) {
				System.out.println("replyDelete DB 오류");
			}

			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}

	public int boardDelete(String type, String idx, String replyType) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			conn = ds.getConnection();
			
			String sql = "DELETE FROM "
					+ replyType
					+ " WHERE IDX_FK = "
					+ idx;
				
				pstmt = conn.prepareStatement(sql);
				result = pstmt.executeUpdate();
			
			result = 0;
				
			sql = "DELETE FROM "
					+ type
					+ " WHERE idx = "
					+ idx;
			
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();
			
			if(result == 0) {
				System.out.println("Error !!! : board_delete 게시글 db쪽 작동 0개");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}

	public int boardModify(String type, String idx, String subject, String content) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "UPDATE "
					+ type
					+ " SET SUBJECT = '"
					+ subject
					+ "' , CONTENT  = '"
					+ content
					+ "' WHERE IDX = "
					+ idx;
			
			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();
			
			if(result == 0) {
				System.out.println("Error! : db update(modify) 오류");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	public int like(String idx, String type) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "UPDATE "
						+ type
						+ " SET up = up + 1 WHERE "
						+ "IDX = "
						+ idx;

			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
		
	public int dislike(String idx, String type) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int result = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "UPDATE "
						+ type
						+ " SET down = down + 1 WHERE "
						+ "IDX = "
						+ idx;

			pstmt = conn.prepareStatement(sql);
			result = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				pstmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}




//	// 좋아요 업데이트
//	public void update_Like(int up) {
//		String sql = "update HUMOR_BOARD set up=up+1 where num=?";
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		
//		try {
//			conn = ds.getConnection();
//			pstmt = conn.prepareStatement(sql);
//			
//			pstmt.setInt(1, up);
//			
//			pstmt.executeUpdate();
//			
//		} catch (SQLException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}finally {
//			try {
//				pstmt.close();
//				conn.close();
//			} catch (Exception e2) {
//				// TODO: handle exception
//			}
//		}
//	}
//	
//	
//	// 좋아요 개수 찾기
//	public int select_Like(int up) {
//		String sql = "select up from HUMOR_BOARD where num=?";
//		
//		Connection conn = null;
//		PreparedStatement pstmt = null;
//		ResultSet rs = null;
//		
//		int like = 0;
//		
//		try {
//			conn = ds.getConnection();
//			pstmt = conn.prepareStatement(sql);
//			pstmt.setInt(1, up);
//			rs = pstmt.executeQuery();
//			
//			if(rs.next()) {
//				like = rs.getInt("up");
//			}
//		} catch (SQLException e) {
//			// TODO: handle exception
//			e.printStackTrace();
//		}finally {
//			try {
//				rs.close();
//				pstmt.close();
//				conn.close();
//			} catch (Exception e2) {
//				// TODO: handle exception
//			}
//		}
//		return like;
//	}
	
}






