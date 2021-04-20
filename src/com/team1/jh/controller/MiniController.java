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
import com.team1.jh.service.DiaryListService;

@WebServlet("*.mini")
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
    	
    	if(url_Command.equals("/index.mini")) { //홈
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("/WEB-INF/views/jh/main.html");
    		
    	}else if(url_Command.equals("/diary.mini")) { //다이어리 
    		action = new DiaryListService();
    		forward = action.execute(request, response);
    		
    	}else if(url_Command.equals("/guestbook.mini")) { //방명록
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
