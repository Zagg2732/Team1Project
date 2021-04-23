package com.team1.jh.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.jh.dao.MiniDao;
import com.team1.jh.dto.DiaryDto;

public class DiaryAddService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		//String idx = request.getParameter("idx");
		String userid_fk = request.getParameter("userid");
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		
		DiaryDto diaryDto = new DiaryDto();
		
		//diaryDto.setIdx(Integer.parseInt(idx));
		diaryDto.setUserid_fk(userid_fk);
		diaryDto.setSubject(subject);
		diaryDto.setContent(content);
		
		int result = 0;
		
		try {
			MiniDao dao = new MiniDao();
			
			result = dao.diaryWrite(diaryDto);		
			
		} catch (NamingException e) {
			System.out.println("DiaryAddService 오류!! : " +e.getMessage());
			e.printStackTrace();
		}
		
		String msg = "";
		String url = "";
		
		if (result > 0) {
			msg = "글쓰기 성공!";
			url = "diary.jh"; //다이어리 첫화면
		} else {
			msg = "글쓰기 실패ㅠ";
			url = "diaryWrite.jh"; //글쓰기 화면
		}
		
		request.setAttribute("diary_msg", msg);
		request.setAttribute("diary_url", url);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/jh/redirect.jsp");
		
		return forward;
	}

}
