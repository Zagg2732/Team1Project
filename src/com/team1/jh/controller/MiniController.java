package com.team1.jh.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.jh.service.DiaryContentService;
import com.team1.jh.service.DiaryListService;
import com.team1.jh.service.ReplyAddService;

@WebServlet("*.jh")
public class MiniController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public MiniController() {
        super();

    }
    
	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String url_Command = requestURI.substring(contextPath.length());
    	
//    	System.out.println(requestURI); // /Team1Project/index.mini
//    	System.out.println(contextPath); // /Team1Project
//    	System.out.println(url_Command); // /index.mini
    	
    	Action action=null;
    	ActionForward forward=null;
    	
    	if(url_Command.equals("/home.jh")) { //홈
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("/WEB-INF/views/jh/home.html");
    		
    	}else if(url_Command.equals("/diary.jh")) { //다이어리 전체보기 
    		action = new DiaryListService();
    		forward = action.execute(request, response);
    		
    	}else if(url_Command.equals("/diaryContent.jh")) { //다이어리 상세보기 
        	action = new DiaryContentService();
        	forward = action.execute(request, response);
        	
    	}else if(url_Command.equals("/diaryReply.jh")) { //다이어리 댓글쓰기 
            action = new ReplyAddService();
            forward = action.execute(request, response);
        		
    	}else if(url_Command.equals("/guestbook.jh")) { //방명록
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("/WEB-INF/views/jh/guestbook.html");
    	}
    	
    	if(forward != null) {
    		if(forward.isRedirect()) { //true 
    			response.sendRedirect(forward.getPath());
    		}else { //false
    			RequestDispatcher dis  = request.getRequestDispatcher(forward.getPath());
    			dis.forward(request, response);
    		}
    	}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
