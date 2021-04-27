package com.team1.jh.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.jh.dao.MiniDao;
import com.team1.jh.dto.DiaryDto;


public class DiaryEditService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		//글 수정하기
		String idx = request.getParameter("idx");
		String cpage = request.getParameter("cp"); // current page
		String pagesize = request.getParameter("ps"); // pagesize
		
		//System.out.println("idx :" +idx);
		//System.out.println("cp :" +cpage);
		//System.out.println("ps :" +pagesize);
		
		String msg="";
	    String url="";
	    
	    MiniDao dao;
	    ActionForward forward = null;
	    
	    try {
	    	if(idx == null || idx.trim().equals("")){
				response.sendRedirect("diaryList.jh"); //cpage=1 , ps=5
				return null;
			}

			dao = new MiniDao();
			
			DiaryDto diaryDto = dao.getEditContent(idx);
			
			if(diaryDto == null){
				msg ="데이터 오류";
				url ="diaryList.jh";
				
				request.setAttribute("diary_msg", msg);
				request.setAttribute("diary_url", url);
				
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/WEB-INF/views/jh/redirect.jsp");
				
			}else {
				request.setAttribute("idx", idx);
				request.setAttribute("cp", cpage);
				request.setAttribute("ps", pagesize);
				request.setAttribute("diaryDto", diaryDto);
				
				//System.out.println("diaryeditservice dx :" +idx);
				
				forward = new ActionForward();
				forward.setRedirect(false);
				forward.setPath("/WEB-INF/views/jh/diary_edit.jsp");
				
			}
		} catch (Exception e) {
			System.out.println("diaryEditService 오류 " +e.getMessage());
			e.printStackTrace();
		}
	    

		return null;
	}

}
