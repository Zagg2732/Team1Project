package com.team1.sj.service;

import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.sj.dao.LSJ_board_dao;
import com.team1.sj.dto.LSJ_board;

public class LSJ_HumorBoardList implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null; //return할 ActionForward 객체
		String type = "Humor_board";
		try {
			LSJ_board_dao dao = new LSJ_board_dao();
			List<LSJ_board> list = dao.list(type);
			
			
			request.setAttribute("list", list);
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/views/sj/board_lsj/board_content.jsp");
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		return forward;
	}

}
