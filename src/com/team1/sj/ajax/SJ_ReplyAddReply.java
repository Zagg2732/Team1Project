package com.team1.sj.ajax;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.Action;
import com.team1.action.ActionForward;

import net.sf.json.JSONObject;

//댓글에 댓글달기
public class SJ_ReplyAddReply implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String idx = request.getParameter("idx");
		String type = request.getParameter("type");
		String sessionNickName = request.getParameter("sessionNickName");
		String replyNickName = request.getParameter("replyNickName");
		String refer = request.getParameter("refer");
		String depth = request.getParameter("depth");
		
		System.out.println("====================대댓글========================");
		System.out.println(idx);
		System.out.println(type);
		System.out.println(sessionNickName);
		System.out.println(replyNickName);
		System.out.println(refer);
		System.out.println(depth);
		System.out.println("=================================================");
		
		String html = "<tr> <td align='left'>" + replyNickName +"에게 답글쓰기 "
			    + "내&nbsp;&nbsp;용 :  <textarea name='reply_content' rows='2' cols='50'  id='reply_add_reply'></textarea>"
			    + "<input type='hidden' id = 'replyAddRefer' value=' +"+ refer +"' class='replyAddRefer'>" 
			    + "<input type='hidden' id = 'replyAddDepth' value=' +"+ depth +"' class='replyAddDepth'>" 
			    + "내&nbsp;&nbsp;용 :  <textarea name='reply_content' rows='2' cols='50'  id='reply_add_reply'></textarea>"
			    + "</td><td align='left'> <input type='button' id='replybtn' onclick='reply_add_reply()' value='등록'></td></tr>";	
		
		JSONObject jsonObj = new JSONObject();
				
		jsonObj.put("idx", idx);
		jsonObj.put("type", type);
		jsonObj.put("sessionNickName", sessionNickName);
		jsonObj.put("replyNickName", replyNickName);
		jsonObj.put("refer", refer);
		jsonObj.put("depth", depth);
		jsonObj.put("html", html);

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
