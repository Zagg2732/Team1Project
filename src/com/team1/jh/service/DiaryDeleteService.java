package com.team1.jh.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.jh.dao.MiniDao;

public class DiaryDeleteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		//글 삭제하기 
		String idx = request.getParameter("idx");
		String cpage = request.getParameter("cp"); // current page
		String pagesize = request.getParameter("ps"); // pagesize	
		
		String msg="";
		String url="";
		
		try {
			MiniDao dao = new MiniDao();
			
			int result = dao.diaryDelete(idx);
			
			if(result > 0){
				msg="글 삭제 성공 !";
				url="diaryContent.jh";
			}else{
				msg="글 삭제 실패 ㅠ";
				url="diaryContent.jh";
			}
			
		} catch (Exception e) {
			System.out.println("deleteService 에러 :" +e.getMessage());
			e.printStackTrace();
		}
		
		request.setAttribute("diary_msg",msg);
		request.setAttribute("diary_url",url);
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false); // forward
		forward.setPath("/WEB-INF/views/jh/redirect.jsp");		
		
		return forward;
	}

}
