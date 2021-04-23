package com.team1.jh.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.Action;
import com.team1.action.ActionForward;

public class DiaryRewriteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String idx = request.getParameter("idx");
		String cpage = request.getParameter("cp");
		String pagesize = request.getParameter("ps");
		String subject = request.getParameter("subject"); // 답글의 제목으로 사용
		
//		try {
//			if (idx == null || subject == null || idx.trim().equals("") || subject.trim().equals("")) {
//				response.sendRedirect("diary.jh");
//				return null;
//			}
//			if (cpage == null || pagesize == null) {
//				cpage = "1";
//				pagesize = "5";
//			}
//
//			
//		} catch (Exception e) {
//			// TODO: handle exception
//			e.getStackTrace();
//		}
		
		request.setAttribute("idx", idx);
		request.setAttribute("cp", cpage);
		request.setAttribute("ps", pagesize);
		request.setAttribute("subject", subject);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/jh/diary_rewrite.jsp");
		
		
		return forward;
		
		
	}

}
