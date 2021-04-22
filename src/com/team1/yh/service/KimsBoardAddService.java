package com.team1.yh.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.yh.dao.KimsBoardDao;
import com.team1.yh.dto.KimsBoard;

public class KimsBoardAddService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String subject = request.getParameter("subject");
		String userid = request.getParameter("userid");
		String content = request.getParameter("content");
		
		KimsBoard kimsboard = new KimsBoard();
		
		kimsboard.setSubject(subject);
		kimsboard.setUserid(userid);
		kimsboard.setContent(content);
		
		
		int result = 0;
		
		try {
			KimsBoardDao dao = new KimsBoardDao();
			
			result = dao.writeok(kimsboard);
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		String msg = "";
		String url = "";
		
		if (result > 0) {
			msg = "글을 등록하였습니다.";
			url = "kimslist.kims";
		} else {
			msg = "글 등록에 실패하였습니다.";
			url = "kimswrite.kims";
		}
		
		request.setAttribute("board_msg", msg);
		request.setAttribute("board_url", url); 
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/yh/board_kims/redirect.jsp");

		return forward;

	}
}
