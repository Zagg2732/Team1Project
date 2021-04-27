package com.team1.sj.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.sj.dao.SJ_board_dao;

public class SJ_Dislike implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		String type = request.getParameter("type");
		String idx = request.getParameter("idx");
		
		System.out.println("--like 기능 type : " + type);
		System.out.println("--like 기능 idx : " + idx);
		
		try {
			SJ_board_dao dao = new SJ_board_dao();
			
			int result = dao.dislike(idx, type);
			
			if(result == 0) {
				System.out.println("Error ! : 싫어요 에러");
			}
			
			
		} catch (NamingException e) {
			e.printStackTrace();
		} 
		
		forward.setRedirect(false);
		forward.setPath("board.sj?type="+ type + "&idx=" + idx);

		return forward;
	}

}
