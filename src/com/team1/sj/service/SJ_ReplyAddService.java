package com.team1.sj.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.sj.dao.SJ_board_dao;

public class SJ_ReplyAddService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		String writer = request.getParameter("reply_nickname");
		String content = request.getParameter("reply_content");
		String idx = request.getParameter("idx"); //idx는 사이트에 표시되지 않는데 일단 받아볼게
		
		
		try {
			SJ_board_dao dao = new SJ_board_dao();
			int whgatidx = Integer.parseInt(idx);
			
			//int result = dao.replyWrite();
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
