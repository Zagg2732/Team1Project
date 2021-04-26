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
    	String userid_fk = request.getParameter("userid");
    	String username = request.getParameter("username");
    	String content = request.getParameter("content");
    	String readyn = request.getParameter("readyn");
    	
    	try {
    		GuestBookDao dao = new GuestBookDao();
    		
    		GuestBookDto guestBookDto = new GuestBookDto();
    		
    		//guestBookDto.setIdx(Integer.parseInt(idx));
    		guestBookDto.setUserid_fk(userid_fk);
    		guestBookDto.setUserName(username);    		
    		guestBookDto.setContent(content);
    		guestBookDto.setReadyn(readyn);
    		
    		int result = dao.guestBookAdd(guestBookDto);
			
		} catch (Exception e) {
			System.out.println("GuestBookAdd.ajax 오류 : " +e.getMessage());
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
