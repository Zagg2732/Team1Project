package com.team1.sj.ajax;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.sj.dao.SJ_board_dao;

public class AddReply implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		ActionForward forward = null;
		
		String id = request.getParameter("reply_id"); // 닉네임은 unique하므로 id도 이걸로 찾아올수있음
		String content = request.getParameter("reply_content");   // input으로 받은 글내용
		String idx = request.getParameter("idx"); // 글의 idx
		String type = request.getParameter("type");
		
		System.out.println(id);
		System.out.println(content);
		System.out.println(idx);
		

		try {
			SJ_board_dao dao = new SJ_board_dao();
			
			int result = dao.replyWrite(id, content, idx, type);
			request.setAttribute("result", result);
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		System.out.println("AddReply 완성. 진짜됐나 db에서 확인해바~");
		
		/*
		 * forward.setRedirect(false); forward.setPath(
		 * "/Team1ProjectPlz/WebContent/WEB-INF/views/sj/ajax/reply.json"); list에쓰이는거
		 */
		
		return forward;
	}

}
