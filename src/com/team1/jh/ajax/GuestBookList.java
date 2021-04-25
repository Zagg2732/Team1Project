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
import javax.servlet.http.HttpSession;

import com.team1.jh.dao.GuestBookDao;
import com.team1.jh.dao.MiniDao;
import com.team1.jh.dto.DiaryReplyDto;
import com.team1.jh.dto.GuestBookDto;
import com.team1.sy.dto.Member;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/GuestBookList.ajax")
public class GuestBookList extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GuestBookList() {
        super();
        // TODO Auto-generated constructor stub
    }

private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	//String idx_fk = request.getParameter("idx"); 
    	//System.out.println("GuestBookList 잘 받아오니? : " +idx_fk);
    	
		try {			
			
			GuestBookDao dao = new GuestBookDao();
			List<GuestBookDto> guestBooklist = dao.guestBookList();
			
			DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

			JSONArray jsonArr = new JSONArray();
			
			for(int i = 0; i < guestBooklist.size(); i++) {
				String date = dateFormat.format(guestBooklist.get(i).getWritedate());
				
				JSONObject jsonObj = new JSONObject();
				
				jsonObj.put("userid_fk", guestBooklist.get(i).getUserid_fk());
				jsonObj.put("content", guestBooklist.get(i).getContent());
				jsonObj.put("writedate", date);
				jsonObj.put("nickname", guestBooklist.get(i).getNickName());
				jsonObj.put("readyn", guestBooklist.get(i).getReadyn());
				
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
