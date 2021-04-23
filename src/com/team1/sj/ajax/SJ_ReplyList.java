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

public class SJ_ReplyList implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String idx = request.getParameter("idx");
		String type = request.getParameter("type");
		
		try {
			SJ_board_dao dao = new SJ_board_dao();
			List<SJ_Board_Reply> replyList = dao.getReplyList(idx, type);
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			
			JSONArray jsonArr = new JSONArray();
			
			for (int i = 0;  i < replyList.size(); i++) {
				String date = dateFormat.format(replyList.get(i).getWritedate());
				
				JSONObject jsonObj = new JSONObject();
				
				jsonObj.put("content", replyList.get(i).getContent());
				jsonObj.put("up", replyList.get(i).getUp());
				jsonObj.put("down", replyList.get(i).getDown());
				jsonObj.put("writedate", date);
				jsonObj.put("refer", replyList.get(i).getRefer());
				jsonObj.put("depth", replyList.get(i).getDepth());
				jsonObj.put("step", replyList.get(i).getStep());
				jsonObj.put("nickname", replyList.get(i).getNickname());
				
				jsonArr.add(jsonObj);
			  
			}
			
			response.setContentType("application/x-json; charset=UTF-8");
			try {
				response.getWriter().print(jsonArr);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		
		return null;
	}

}
