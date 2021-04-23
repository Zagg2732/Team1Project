package com.team1.sj.ajax;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.sj.dao.SJ_board_dao;

public class SJ_ReplyDelete implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String sessionNickName = request.getParameter("sessionNickName");
		String replyNickname = request.getParameter("replyNickName");
		String idx = request.getParameter("idx");
		String type = request.getParameter("type");
		String refer = request.getParameter("refer");
		String depth = request.getParameter("depth");
		String step = request.getParameter("step");
		
		System.out.println(refer);
		System.out.println(depth);
		System.out.println(step);
			
		try {
			SJ_board_dao dao = new SJ_board_dao();
			
			int result = dao.replyDelete(type, idx);
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return null;
	}

}
