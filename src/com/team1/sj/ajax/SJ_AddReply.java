package com.team1.sj.ajax;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.sj.dao.SJ_board_dao;

public class SJ_AddReply implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String id = request.getParameter("sessionId");
		String content = request.getParameter("reply_content");
		String type = request.getParameter("type");
		String idx  = request.getParameter("idx");
		
		try {
			SJ_board_dao dao = new SJ_board_dao();
			int result = dao.replyWrite(id, content, idx, type);
			
			if(result == 0) {
				System.out.println("replyAdd(ajax) db 등록실패");
			}
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
