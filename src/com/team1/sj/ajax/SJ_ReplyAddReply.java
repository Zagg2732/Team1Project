package com.team1.sj.ajax;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.sj.dao.SJ_board_dao;

import net.sf.json.JSONObject;

//댓글에 댓글달기
public class SJ_ReplyAddReply implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String idx = request.getParameter("idx");
		String type = request.getParameter("type");
		String sessionId = request.getParameter("sessionId");
		String replyNickName = request.getParameter("replyNickName");
		String refer = request.getParameter("refer");
		String depth = request.getParameter("depth");
		String content = request.getParameter("content");

		
		//db접근해서 리플쓰기
		
		try {
			SJ_board_dao dao = new SJ_board_dao();
			
			int result = dao.replyAddReply( idx,  type,  sessionId,  content,  refer,  depth);
			
			if(result == 0) {
				System.out.println("replyAddReply db 업데이트 오류");
			}
			
		} catch (NamingException e1) {
			e1.printStackTrace();
		}
		
		
		
		JSONObject jsonObj = new JSONObject();
				
		jsonObj.put("idx", idx);
		jsonObj.put("type", type);
		jsonObj.put("sessionId", sessionId);
		jsonObj.put("replyNickName", replyNickName);
		jsonObj.put("refer", refer);
		jsonObj.put("depth", depth);

		response.setContentType("application/x-json; charset=UTF-8");
		try {
			response.getWriter().print(jsonObj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}

}
