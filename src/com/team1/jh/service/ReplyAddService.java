package com.team1.jh.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.jh.dao.MiniDao;

public class ReplyAddService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String idx = request.getParameter("idx");
		String userid_fk = request.getParameter("userid_fk");
		String content = request.getParameter("content");
		
		
		//Reply reply = new Reply();
		String msg="";
		String url="";
		
		try {
			MiniDao dao = new MiniDao();
			int idx_fk = Integer.parseInt(idx);
			
			int result = dao.replyWrite(idx_fk, userid_fk, content);
			
			if(result > 0) {
				msg ="댓글 입력 성공";
				url ="diaryContent.jh?idx="+idx_fk;
			}else {
				msg ="댓글 입력 실패";
				url ="diaryContent.jh?idx="+idx_fk;
			}
			
		} catch (Exception e) {
			e.getStackTrace();
		}
		ActionForward forward = new ActionForward();
		request.setAttribute("diary_msg", msg);
		request.setAttribute("diary_url", url);
		forward.setPath("/WEB-INF/views/jh/redirect.jsp");
		
		return forward;
	}

}
