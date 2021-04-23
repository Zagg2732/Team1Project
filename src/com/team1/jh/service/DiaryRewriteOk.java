package com.team1.jh.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.jh.dao.MiniDao;
import com.team1.jh.dto.DiaryDto;


public class DiaryRewriteOk implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String strIdx = request.getParameter("idx").trim();		
		String subject = request.getParameter("subject");
		String content = request.getParameter("content");
		String userid_fk = request.getParameter("userid");
		
		
		String msg="";
	    String url="";
		
//	    System.out.println("strIdx :" +strIdx);
//	    System.out.println("subject :" +subject);
//	    System.out.println("content :" +content);
//	    System.out.println("userid_fk :" +userid_fk);
				
		try {
			int idx = Integer.parseInt(strIdx);
			
			DiaryDto diaryDto = new DiaryDto();
			
			diaryDto.setSubject(subject);
			diaryDto.setContent(content);
			diaryDto.setIdx(idx);
			diaryDto.setUserid_fk(userid_fk);

			MiniDao dao = new MiniDao();
			
			int result = dao.reWrite(diaryDto);

		    if(result > 0){
		    	msg ="답글쓰기 성공!";
		    	url ="diary.jh";
		    }else{
		    	msg="답글쓰기 실패!";
		    	url="diaryContent.jh?idx="+diaryDto.getIdx();
		    }
		    
		} catch (Exception e) {
			System.out.println("오류 잡았다요놈 : " +e.getMessage());
			e.printStackTrace();
		}
		
		request.setAttribute("diary_msg", msg);
		request.setAttribute("diary_url", url);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/jh/redirect.jsp");
		
		return forward;
	}

}
