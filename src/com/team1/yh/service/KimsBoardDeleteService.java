package com.team1.yh.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.yh.dao.KimsBoardDao;
import com.team1.yh.dto.KimsBoard;

public class KimsBoardDeleteService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = null;
		
		String idx = request.getParameter("idx");
		
		//KimsBoard kimsboard = new KimsBoard();
		
		
		System.out.println("글삭제 idx: " + idx);
		
		String msg="";
		String url="";
		
		try {
			KimsBoardDao dao = new KimsBoardDao();
			
			int result = dao.kimsDelete(idx);
			
			if(result > 0){
				msg="글이 삭제되었습니다.";
				url="kimsboardcontent.kims";
			}else{
				msg="글이 삭제되지 않았습니다.";
				url="kimsboardcontent.kims";
			}
			
		} catch (Exception e) {
			System.out.println("deleteService 에러 :" +e.getMessage());
			e.printStackTrace();
		}
		
		request.setAttribute("board_msg",msg);
		request.setAttribute("board_url",url);
		
		forward = new ActionForward();
		forward.setRedirect(false); // forward
		forward.setPath("/WEB-INF/views/yh/board_kims/redirect.jsp");		
		
		return forward;
	}
}
