package com.team1.sj.ajax;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.sj.dao.SJ_board_dao;
import com.team1.sj.dto.SJ_Board_Reply;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class SJ_ReplyAddForm implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {

		String idx = request.getParameter("idx");
		String type = request.getParameter("type");
		String sessionNickName = request.getParameter("sessionNickName");
		String replyNickName = request.getParameter("replyNickName");
		String refer = request.getParameter("refer");
		String depth = request.getParameter("depth"); //refer depth까진 필요함
		
		
		String html = "<form action='reply_add_reply()' method='POST' id='replyAddReply' style='width:300px; height:300px;'"
						+ "<input type='hidden' name='st_name' value = '"+ sessionNickName +"'><br>"
						+ "<input type='hidden' name='st_name' value = '"+ replyNickName +"'><br>"
						+ "<input type='hidden' name='st_name' value = '"+ refer +"'><br>"
						+ "<input type='hidden' name='st_name' value = '"+ depth +"'><br>"
						+ "<input type='text' name='st_id'><br>"
						+ "<input type='submit'><br>"
						+ "</form>";
				
				
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
