package com.team1.sj.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.sj.dao.SJ_board_dao;

public class SJ_BoardDeleteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		String type = request.getParameter("type");
		String idx = request.getParameter("idx");
		String replyType = "";
		
		if(type.equals("humor_board")) {
			replyType = "humor_reply";
		} else if(type.equals("notice_board")) {
			replyType = "notice_reply";
		}
		
		try {
			SJ_board_dao dao = new SJ_board_dao();
			int result = dao.boardDelete(type, idx, replyType);
			
			if(result == 0) {
				System.out.println("게시글 삭제 DB Error");
			}
			
		} catch (NamingException e) {
			e.printStackTrace();
		}		
		
		forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("index.sj");
		
		return forward;
	}

}
