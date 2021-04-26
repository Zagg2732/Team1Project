package com.team1.yh.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.yh.dao.KimsBoardDao;
import com.team1.yh.dto.KimsBoard;


public class KimsBoardAddService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
			
			String userid_fk = request.getParameter("userid");
			String subject = request.getParameter("subject");
			String content = request.getParameter("content");
			
			
			KimsBoard kimsboard = new KimsBoard();
			
			
			kimsboard.setUserid_fk(userid_fk); 
			kimsboard.setSubject(subject);
			kimsboard.setContent(content);
			
			System.out.println("-------------------------------");
			System.out.println(userid_fk);
			System.out.println(subject);
			System.out.println(content);
			System.out.println("-------------------------------");
			
			int result = 0;
			
			String msg = "";
			String url = "";
			
			try {
				KimsBoardDao dao = new KimsBoardDao();
				result = dao.writeok(kimsboard);
				
				if (result > 0) {
					msg = "글을 등록하였습니다.";
					url = "kimslist.kims";
				} else {
					msg = "글 등록에 실패하였습니다.";
					url = "kimswrite.kims";
				}
				
			} catch (Exception e) {
				e.getStackTrace();
			}
			ActionForward forward = new ActionForward();
			request.setAttribute("board_msg", msg);
			request.setAttribute("board_url", url);
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/views/yh/board_kims/redirect.jsp");
			
			return forward;
			
	}

}
