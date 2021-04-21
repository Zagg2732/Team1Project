package com.team1.sj.service;

import java.util.ArrayList;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.sj.dao.LSJ_board_dao;
import com.team1.sj.dto.LSJ_Reply;
import com.team1.sj.dto.LSJ_board;

public class LSJ_HumorContent implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		LSJ_board board = null;
		
		List<LSJ_Reply> replyList = null;
		
		try {
			String idx = request.getParameter("idx");
			String type = request.getParameter("type");
			String replyType = null;
			
			System.out.println("게시판으로부터 get으로 받아온 idx : " + idx);
			System.out.println("게시판으로부터 get으로 받아온 type : " + type);
			
			if(type.equals("humorboard")) {
				type = "humor_board"; //실제 DB 테이블이랑 이름맞추기
				replyType = "humor_reply";
			} else if (type.equals("noticeboard")) {
				type = "notice_board";
				replyType = "notice_reply";
			} else {
				System.out.println("게시판 type을 이상한걸 받아온듯합니다tlqkf!!!!");
			}
			
			System.out.println("DB테이블 이름에 맞게 바꾼 type : " + type);
			
			
			LSJ_board_dao dao = new LSJ_board_dao();
			boolean readnumAdd = dao.getReadNum(idx, type); //조회수증가
			
			if (readnumAdd) { //값이들어갔으면
				System.out.println("조회수 증가 함수가 제대로 작동했대요 의심되시면 DB에서 확인을!");
			} else {
				System.out.println("Error : 조회수증가 false 받음");
				forward = new ActionForward();
				forward.setRedirect(true);
				forward.setPath("home.lsj"); //임시코드		
			}
			
			board = dao.getContentDetails(idx, type); //DB에서 게시판 상세보기 정보 받아옴		
			replyList = dao.getReplyList(idx, replyType); //DB에서 해당 게시글 댓글목록 받아옴
			
			request.setAttribute("board", board); //게시자 정보			
			request.setAttribute("idx", idx); //글번호
			request.setAttribute("replyList", replyList); //리플
			
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/views/sj/board_lsj/board_content_detail.jsp");
			
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
