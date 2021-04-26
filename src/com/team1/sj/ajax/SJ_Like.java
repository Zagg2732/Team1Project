package com.team1.sj.ajax;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.sj.dao.SJ_board_dao;

public class SJ_Like implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String type = request.getParameter("type");
		String idx = request.getParameter("idx");
		
		try {
			SJ_board_dao dao = new SJ_board_dao();
			
			int result = dao.like(idx, type);
			
			if(result == 0) {
				System.out.println("Error ! : 좋아요 에러");
			}
			
		} catch (NamingException e) {
			e.printStackTrace();
		}

		return null;
	}

}
