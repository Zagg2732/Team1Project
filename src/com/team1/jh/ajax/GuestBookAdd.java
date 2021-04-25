package com.team1.jh.ajax;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.jh.dao.GuestBookDao;
import com.team1.jh.dto.GuestBookDto;
import com.team1.sy.dao.AdminTalkDao;
import com.team1.sy.dto.AdminTalk;


@WebServlet("/GuestBookAdd.ajax")
public class GuestBookAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public GuestBookAdd() {
        super();
        // TODO Auto-generated constructor stub
    }


    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	//String idx = request.getParameter("idx");
    	String userid = request.getParameter("userid");
    	String nickname = request.getParameter("nickname");
    	String content = request.getParameter("content");
    	String readyn = request.getParameter("readyn");
    	
    	//System.out.println("GuestBookAdd 확인; " +nickname);
    	    	
    	try {
    		GuestBookDao dao = new GuestBookDao();
    		
    		GuestBookDto guestBookDto = new GuestBookDto();
    		
    		//guestBookDto.setIdx(Integer.parseInt(int));
    		guestBookDto.setUserid_fk(userid);
    		guestBookDto.setNickName(nickname);    		
    		guestBookDto.setContent(content);
    		guestBookDto.setReadyn(readyn);
    		
    		int result = dao.guestBookAdd(guestBookDto);
			
		} catch (Exception e) {
			System.out.println("ReplyAdd.ajax 오류 : " +e.getMessage());
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
