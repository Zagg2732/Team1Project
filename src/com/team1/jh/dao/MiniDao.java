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
import javax.servlet.http.HttpServletRequest;
import javax.sql.DataSource;

import com.team1.jh.dto.DiaryDto;
import com.team1.jh.dto.DiaryReplyDto;
import com.team1.jh.dto.GuestBookDto;

public class MiniDao {
	DataSource ds = null;
	
	public MiniDao() throws NamingException {
		//JNDI
		Context context = new InitialContext(); //현재 프로젝트에서 특정 이름을 가진 녀석을 검색(이름 기반 검색)
		Context contextDetail = (Context)context.lookup("java:comp/env");
		
		ds = (DataSource)contextDetail.lookup("jdbc/oracle");  //java:comp/env/  +  jdbc/oracle   이름  => 정해진 약속	
	}
	//다이어리 글쓰기(원본글)
	public int diaryWrite(DiaryDto diarydata) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		
		int row = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "INSERT INTO DIARY (IDX, USERID_FK, SUBJECT, CONTENT, WRITEDATE, READNUM, REFER) " + 
						 "VALUES (DIARY_idx.nextval, ?, ?, ?, sysdate, 0, ?)";
			
			pstmt = conn.prepareStatement(sql);
			
			//userid_fk, subject, content, filename 
			pstmt.setString(1, diarydata.getUserid_fk());
			pstmt.setString(2, diarydata.getSubject());
			pstmt.setString(3, diarydata.getContent());
			//pstmt.setString(4, diarydata.getFilename());
			
			int refermax = getMaxRefer();
			int refer = refermax + 1;
			pstmt.setInt(4,refer);
			
			row = pstmt.executeUpdate();
		} catch (Exception e) {
			System.out.println("오류 너니? : " +e.getMessage());
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				conn.close();//반환하기
			} catch (Exception e2) {
			
			}
		}
		return row;
	}
	
	//다이어리 글쓰기 refer값 생성하기 
	private int getMaxRefer() {
		Connection conn = null;
		PreparedStatement pstmt=null;
		ResultSet rs = null;
		
		int refer_max=0;
		
		try {
			conn = ds.getConnection(); 
			String sql="SELECT NVL(MAX(REFER),0) FROM DIARY";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				refer_max = rs.getInt(1);
			}
			
		} catch (Exception e) {
			System.out.println("오류: " +e.getMessage());
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close(); // 반납이요 ^^
			}catch (Exception e) {
				
			}
		}
		return refer_max;
		
	}
	
	//다이어리 게시물 총 건수 조회 
	public int totalDiaryCount() {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int totalcount = 0;
		try {
			conn = ds.getConnection(); //dbcp 연결객체 얻기
			
			String sql="SELECT COUNT(*) CNT FROM DIARY";
			pstmt = conn.prepareStatement(sql);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				totalcount = rs.getInt("cnt");
			}
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("오류: " +e.getMessage());
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
	
	//다이어리 전체조회 
	public List<DiaryDto> list(int cpage , int pagesize){
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<DiaryDto> list = null;
		
		try {
			conn = ds.getConnection();			
			
			String sql = "SELECT * FROM " +
							"(SELECT ROWNUM RN, IDX, USERID_FK, SUBJECT, CONTENT, WRITEDATE, READNUM, FILENAME, REFER, DEPTH, STEP " +
							"FROM (SELECT * FROM DIARY ORDER BY REFER DESC , STEP ASC) "+
							"WHERE ROWNUM <= ?" +  //endrow
							") WHERE RN >= ?"; //startrow

			pstmt = conn.prepareStatement(sql);
			
			//공식같은 로직
			int start = cpage * pagesize - (pagesize -1); //1 * 5 - (5 - 1) >> 1
			int end = cpage * pagesize; // 1 * 5 >> 5
			
			pstmt.setInt(1, end);
			pstmt.setInt(2, start);
			
			//System.out.println("start : " +start);
			//System.out.println("end : " +end);
			
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
			e.printStackTrace();
			System.out.println("오류: " +e.getMessage());
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
	
	
	//다이어리 게시물 상세보기
	public DiaryDto getContent(int idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		DiaryDto diary = null;
			
			try {
				conn = ds.getConnection();
				String sql="SELECT * FROM DIARY WHERE IDX=?"; 
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt(1, idx);
				
				rs = pstmt.executeQuery();
				if(rs.next()) {					
					String userid_fk = rs.getString("userid_fk");
					String subject = rs.getString("subject");
					String content = rs.getString("content");
					java.sql.Date writedate = rs.getDate("writedate");
					int readnum = rs.getInt("readnum");
					String filename = rs.getString("filename");

					int refer = rs.getInt("refer");
					int step = rs.getInt("step");
					int depth = rs.getInt("depth");
					
					diary = new DiaryDto(idx, userid_fk, subject, content, writedate, readnum, filename, refer, depth, step);
				}
				
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("오류: " +e.getMessage());
			}finally {
				try {
					pstmt.close();
					rs.close();
					conn.close();//반환하기
				} catch (Exception e2) {
					
				}
			}
			
			return diary;
		}
	
	//다이어리 조회수 증가
	public boolean getReadNum(String idx) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		boolean result = false;
		
		try {
			conn = ds.getConnection();
			String sql="UPDATE DIARY SET READNUM = READNUM + 1 WHERE IDX=?";
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, idx);
			
			int row = pstmt.executeUpdate();
			if(row > 0) {
				result = true;
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("오류: " +e.getMessage());
		}finally {
			try {
				pstmt.close();
				conn.close(); //반환 
			} catch (Exception e2) {
				
			}
		}
		return result;
		
	}
	
	
	//다이어리 글 삭제
	@SuppressWarnings("resource")
	public int diaryDelete(String idx) {
		//원본글에 답글이 있으면 원본글, 답글, 댓글 다 삭제할거임
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		
		try {
			conn = ds.getConnection();
			
			//글을 삭제하면 답글까지 삭제해주기 위해 refer, depth를 꺼내봅니다
			String sql_refer_depth = "SELECT REFER, DEPTH FROM DIARY WHERE IDX=?";
			
			//글을 삭제할 때 답글, 답글의 답글, 답글의 답글의 답글에 달린 댓글 전부를 먼저 삭제
			String sql_reply = "DELETE FROM DIARY_REPLY " +
							   "WHERE IDX_FK IN (SELECT IDX FROM DIARY WHERE IDX=? OR (REFER=? AND DEPTH >=?))";
			//삭제 대상 글 삭제
			String sql_diary = "DELETE FROM DIARY WHERE IDX=? OR (REFER=? AND DEPTH >=?)";
			
			//refer 꺼내기 
			pstmt = conn.prepareStatement(sql_refer_depth);
			pstmt.setString(1, idx);
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				int refer = rs.getInt("refer");
				int depth = rs.getInt("depth");
				
				//실 삭제 처리
				//트랜잭션 (둘다 처리 , 둘다 실패)
				//두개를 하나의 논리적 단위
				//JDBC : auto commit 
				conn.setAutoCommit(false);//개발자가 rollback , commit 강제
				
				//댓글삭제
			 	pstmt = conn.prepareStatement(sql_reply);
			 	pstmt.setString(1,idx);
			 	pstmt.setInt(2,refer);
			 	pstmt.setInt(3,depth);
			 	pstmt.executeUpdate();
			 	
			 	//게시글 삭제 (원본글)
			 	pstmt = conn.prepareStatement(sql_diary);
			 	pstmt.setString(1,idx);
			 	pstmt.setInt(2,refer);
			 	pstmt.setInt(3,depth);
			 	row = pstmt.executeUpdate();
			 	
			 	if(row > 0) {
			 		conn.commit(); //두개의 delete 실반영
			 	
				}else { //삭제하려는 글이 존재하지 않는 경우 
					row = 0;
				
				}
			}
		} catch (Exception e) {
			System.out.println("diaryDelete dao 에러 :" +e.getMessage());
			try {
				conn.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();//반환
			} catch (Exception e2) {
				
			}
		}
		return row;
	}
			
	
	//다이어리 댓글 입력 (Table diary_reply : fk(diary idx)
	public int replyWrite(int idx_fk, String userid_fk, String content) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		int row = 0;
		
		try {
			conn = ds.getConnection();
			String sql = "INSERT INTO DIARY_REPLY (IDX_FK, USERID_FK, NUM, CONTENT) "
					+ "VALUES (?, ?, DIARY_REPLY_num.NEXTVAL,?)";
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setInt(1, idx_fk);
			pstmt.setString(2,  userid_fk);
			pstmt.setString(3,  content);
			
			row = pstmt.executeUpdate();
	
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("오류: " +e.getMessage());
		}finally {
			try {
				pstmt.close();
				conn.close();//반환
			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
		return row;
	}
	
	
	//다이어리 댓글 조회 
	public List<DiaryReplyDto> diaryReplyList(String idx_fk) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		ArrayList<DiaryReplyDto> list = null;
		
		try {
			conn = ds.getConnection();
			String reply_sql = "SELECT DR.IDX_FK, DR.USERID_FK, TU.USERNAME, DR.CONTENT, DR.WRITEDATE, DR.NUM "
							 + "FROM DIARY_REPLY DR "
							 + "JOIN TEAM1_USER TU ON DR.USERID_FK = TU.USERID "
							 + "WHERE DR.IDX_FK = ? "
							 + "ORDER BY DR.NUM DESC";
			
			pstmt = conn.prepareStatement(reply_sql);
			pstmt.setString(1, idx_fk);
			
			rs = pstmt.executeQuery();

			list = new ArrayList<>();
			
			while(rs.next()) {
				int idx = Integer.parseInt(rs.getString("idx_fk"));
				String userid_fk = rs.getString("userid_fk");
				int num = Integer.parseInt(rs.getString("num"));
				String content  =rs.getString("content");
				java.sql.Date writedate = rs.getDate("writedate");
				String username  =rs.getString("username");
				
				DiaryReplyDto replydto = new DiaryReplyDto(idx, userid_fk, num, content, writedate, username);
				list.add(replydto);

			}			

		} catch (Exception e) {
			System.out.println("댓글 조회 오류: " +e.getMessage());
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
	
	//다이어리 댓글 삭제 
	public int replyDelete(String num, String userid_fk) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		
		try {
			String replyselect = "SELECT USERID_FK FROM DIARY_REPLY WHERE NUM=?";
			String replydelete = "DELETE FROM DIARY_REPLY WHERE NUM=?";
			
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(replyselect);
			pstmt.setString(1, num);
			rs =pstmt.executeQuery();
			
			if(rs.next()) {
				String userid = rs.getString("userid_fk");
				if(userid_fk.equals(userid)) {
					pstmt.close();
					pstmt = conn.prepareStatement(replydelete);
					pstmt.setString(1, num);
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
	
	//다이어리 답글 쓰기
	@SuppressWarnings("resource")
	public int reWrite(DiaryDto diarydata) {
		//diary_content.jsp -> 답글 -> rewrite.jsp -> 입력 -> submit() -> rewrite.jsp
		//내가 답글을 달려고하는 글의 글번호가 필요함 
		
		//refer , step , depth 설정을 하려면 기존 정보(read 글)
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int result = 0;
		
		try {
			conn = ds.getConnection();
			
			int idx = diarydata.getIdx(); //현재 읽은 글의 글번호
			
			String userid_fk = diarydata.getUserid_fk();
			String subject = diarydata.getSubject();
			String content = diarydata.getContent();
			//String filename = diarydata.getUserid_fk();
			
			//1.답글
			//현재 내가 읽은 글의 refer, depth, step 
			String refer_depth_step_sql = "SELECT REFER , DEPTH , STEP FROM DIARY WHERE IDX=?";
			
			//2.계층형 위치
			//step : 순서 >> 최신 답글을 위로 올라오게 함 
			//내가 읽은 글의 step 보다 큰 값은 +1 해서 증가시킴
			String step_update_sql = "UPDATE DIARY SET STEP = STEP+1 WHERE STEP > ? AND REFER =? ";
			
			//답글 INSERT
			String rewrite_sql = "INSERT INTO DIARY (IDX, USERID_FK, SUBJECT, CONTENT, WRITEDATE, READNUM, REFER, DEPTH, STEP) " + 
									 "VALUES (DIARY_idx.nextval, ?, ?, ?, sysdate, 0, ?, ?, ?)";
			
			pstmt = conn.prepareStatement(refer_depth_step_sql);
			pstmt.setInt(1, idx);
			rs = pstmt.executeQuery();
			
			if(rs.next()) { //원본글에 refer, depth, step 존재한다면!
				int refer = rs.getInt("refer");
				int depth = rs.getInt("depth");
				int step = rs.getInt("step");
				
				//기존 step+1 update 구문 실행 
				pstmt = conn.prepareStatement(step_update_sql);
				pstmt.setInt(1, step);
				pstmt.setInt(2, refer);
				pstmt.executeUpdate();
				
				//userid_fk, subject, content, filename, refer, depth, step
				pstmt = conn.prepareStatement(rewrite_sql); //컴파일
				pstmt.setString(1, userid_fk);
				pstmt.setString(2, subject);
				pstmt.setString(3, content);
				//pstmt.setString(1, filename);
				
				//refer, depth, step
				pstmt.setInt(4, refer);
				pstmt.setInt(5, depth+1); //현재 읽은 글에 depth +1
				pstmt.setInt(6, step+1); //순서 update를 통해 자리 확보 
				
				int row = pstmt.executeUpdate();
				if(row > 0) {
					result = row;
				}else {
					result = -1;
				}

			}
	
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();//반환
			}catch (Exception e) {
				
			}
		}
		
		return result;
	}
	
	//다이어리 글 수정 
	public DiaryDto getEditContent(String idx) {
		return this.getContent(Integer.parseInt(idx));
		//조회화면 동일 (기존에 있는 함수 재활용)
	}
	
	//다이어리 글 수정 처리 
	public int diaryEdit(HttpServletRequest diarydata) {
		
		String idx = diarydata.getParameter("idx");
		String userid_fk = diarydata.getParameter("userid_fk");
		String subject = diarydata.getParameter("subject");
		String content = diarydata.getParameter("content");
		//String filename = diarydata.getParameter("filename");
		System.out.println("dao idx : " +idx);
		System.out.println("dao userid_fk : " +userid_fk);
		System.out.println("dao subject : " +subject);
		System.out.println("dao content : " +content);
		
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		int row = 0;
		
		try {
			conn = ds.getConnection();
			String sql_idx = "SELECT IDX FROM DIARY WHERE IDX=?";
			String sql_udpate = "UPDATE DIARY SET USERID_FK=? SUBJECT=? , CONTENT=? WHERE IDX=?";
			
			pstmt = conn.prepareStatement(sql_idx);
			pstmt.setString(1, idx);
			
			rs = pstmt.executeQuery();
			
			if(rs.next()) {
				pstmt.close();
				
				//UPDATE
				pstmt = conn.prepareStatement(sql_udpate);
				//userid_fk, subject, content, filename, idx
				pstmt.setString(1, userid_fk);
				pstmt.setString(2, subject);
				pstmt.setString(3, content);
				pstmt.setString(4, idx);
				row = pstmt.executeUpdate();
				
				//System.out.println("row : " + row);
			}
		} catch (Exception e) {
			System.out.println("오류: " +e.getMessage());
		}finally {
			try {
				pstmt.close();
				rs.close();
				conn.close();//반환
			} catch (Exception e2) {
				
			}
		}
	
		return row;
	}

}
