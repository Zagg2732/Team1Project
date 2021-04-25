package com.team1.sj.ajax;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.sj.dao.SJ_board_dao;

import net.sf.json.JSONObject;

public class SJ_ReplyDelete implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String sessionId = request.getParameter("sessionId");
		String replyUserId = request.getParameter("replyUserId");
		
		String idx = request.getParameter("idx");
		String type = request.getParameter("type");
		String refer = request.getParameter("refer");
		String depth = request.getParameter("depth");
		String step = request.getParameter("step");
		String msg ="";
			
		JSONObject jsonObj = new JSONObject();		
		
		if(sessionId.equals(replyUserId)) {
			
			
			SJ_board_dao dao;
			try {
				dao = new SJ_board_dao();
				int result = dao.replyDelete(sessionId, idx, type, refer, depth, step); //내가쓴글이면 replyDelete()실행
				
				if(result > 0) {
					msg += "댓글이 삭제되었습니다";
				} else {
					msg += "내가 쓴 글은 맞지만 삭제가 뭔가 안됐습니다. 이 메세지가 뜨면 안되는데??";
				}
				
				jsonObj.put("msg", msg); //msg를 JSON으로 돌려줍니당..
				response.setContentType("application/x-json; charset=UTF-8");
				try {
					response.getWriter().print(jsonObj);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			} catch (NamingException e) {
				e.printStackTrace();
			}
			
		} else { //자기 아이디가 아니라면?
			System.out.println("세션아이디랑안맞네?");
			msg += "자신이 쓴 글만 삭제할 수 있습니다";			
			jsonObj.put("msg", msg);
		}

		
		return null;
	}

}
