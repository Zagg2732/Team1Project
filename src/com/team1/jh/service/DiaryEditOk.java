package com.team1.jh.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.jh.dao.MiniDao;


public class DiaryEditOk implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String idx = request.getParameter("idx");

		String msg = "";
		String url = "";
		ActionForward forward = null;
		
		try {
			MiniDao dao = new MiniDao();
			
			if (idx == null || idx.trim().equals("")) {
				msg = "글번호 입력 오류";
				url = "diaryList.jh";
				
			}else {
								
				int result = dao.diaryEdit(request);
				
				if (result > 0) {
					msg = "edit success";
					url = "diaryList.jh";
				} else {
					msg = "edit fail";
					url = "diaryEdit.jh?idx=" + idx;
				}
				
			}
			request.setAttribute("diary_msg", msg);
			request.setAttribute("diary_url", url);
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/views/jh/redirect.jsp");
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
