package com.team1.yh.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.yh.dao.KimsBoardDao;
import com.team1.yh.dto.KimsBoard;

public class KimsBoardContentService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = null;
		
		String idx = request.getParameter("idx");	
		//String cpage = request.getParameter("cp");
		//String pagesize = request.getParameter("ps");
		
		System.out.println("idx" + idx);
		//System.out.println("cp" + cpage);
		//System.out.println("ps" + pagesize);
		
		KimsBoard kimsboard = new KimsBoard();
		
			try {
			
			KimsBoardDao dao = new KimsBoardDao();
			// 글 번호를 가지고 오지 않았을 경우 예외처리
			if (idx == null || idx.trim().equals("")) {
				response.sendRedirect("kimslist.kims"); //다이어리 목록으로
				return null;
			}
			
			idx = idx.trim();
			
			int intIDX = Integer.parseInt(idx);
			
			kimsboard = dao.getContent(intIDX);
			
			/*
			//List 페이지 처음 호출 ...
			if(cpage == null || cpage.trim().equals("")){
				//default 값 설정
				cpage = "1"; 
			}
			
			if(pagesize == null || pagesize.trim().equals("")){
				//default 값 설정
				pagesize = "5"; 
			}
			*/
			
			request.setAttribute("idx", idx);

			request.setAttribute("kimsboard", kimsboard);
			
			forward = new ActionForward();
			forward.setRedirect(false); // forward
			forward.setPath("/WEB-INF/views/yh/board_kims/boardcontent.jsp");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
			
		return forward;
	}

}
