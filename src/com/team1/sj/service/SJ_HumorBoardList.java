package com.team1.sj.service;

import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.sj.dao.SJ_board_dao;
import com.team1.sj.dto.SJ_board;

public class SJ_HumorBoardList implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null; //return할 ActionForward 객체
		String humor = "Humor_board";
		String notice = "notice_board";
		try {
			SJ_board_dao dao = new SJ_board_dao();
			
			List<SJ_board> humorlist = dao.list(humor);			
			List<SJ_board> noticelist = dao.list(notice);
			
			request.setAttribute("humorlist", humorlist);
			request.setAttribute("noticelist", noticelist);
					
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/views/sj/index_sj.jsp");
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
