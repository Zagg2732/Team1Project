package com.team1.jh.ajax;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.jh.dao.MiniDao;
import com.team1.jh.dto.DiaryReplyDto;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


@WebServlet("/ReplyList.ajax")
public class ReplyList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReplyList() {
        super();

    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	//System.out.println("ReplyList.ajax Start!!!!!");
    	
    	String idx= request.getParameter("idx"); 
    	System.out.println("idx : " +idx);
    	
		try {
			
			MiniDao dao = new MiniDao();
			List<DiaryReplyDto> replylist = dao.diaryReplyList(idx);
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			JSONArray jsonArr = new JSONArray();
			
			for(int i = 0; i < replylist.size(); i++) {
				String date = dateFormat.format(replylist.get(i).getWritedate());
				
				JSONObject jsonObj = new JSONObject();
				
				System.out.println(replylist.get(i).getNum());
				
				jsonObj.put("writedate", date);
				jsonObj.put("num", replylist.get(i).getNum());		
				jsonObj.put("userid_fk", replylist.get(i).getUserid_fk());
				jsonObj.put("content", replylist.get(i).getContent());
				jsonObj.put("idx_fk", replylist.get(i).getIdx_fk());
				jsonObj.put("username", replylist.get(i).getUserName());
				
				jsonArr.add(jsonObj);
				
			}
			
			response.setContentType("application/x-json; charset=UTF-8");
			response.getWriter().print(jsonArr);
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
}