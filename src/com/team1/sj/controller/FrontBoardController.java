package com.team1.sj.controller;

import java.io.IOException;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.sj.service.HumorBoardAddService_hsj;
import com.team1.sj.service.HumorBoardContentService_hsj;
import com.team1.sj.service.HumorListService_hsj;


@WebServlet("*.hsj")
public class FrontBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public FrontBoardController() {
        
    }

    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String url_Command = requestURI.substring(contextPath.length());
    	
    	Action action = null;
    	ActionForward forward = null;
    	System.out.println("url_command들어왔늬???");
    	System.out.println(url_Command);
    	
    	if(url_Command.equals("/HumorList.hsj")) { //유머게시판 리스트
    		action = new HumorListService_hsj();
    		forward = action.execute(request, response);
    		
    		System.out.println("humorlist들어왔니???");
    		
    /////////////////////////////////////////////////////////////////////
    		
    	}else if(url_Command.equals("/HumorWrite.hsj")) { //유머게시판 글쓰기
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("WEB-INF/views/sj/board_hsj/board_write_hsj.jsp");
    		
    /////////////////////////////////////////////////////////////////////
    		
    	}else if(url_Command.equals("/HumorWriteOK.hsj")) {
    		action = new HumorBoardAddService_hsj();
    		forward = action.execute(request, response);
    		
    //////////////////////////////////////////////////////////////////////
    		
    	}else if(url_Command.equals("/HumorBoardContent.hsj")) {
    		action = new HumorBoardContentService_hsj();
    		forward = action.execute(request, response);
    		
    /////////////////////////////////////////////////////////////////////
    		
    	}
    	
    	if(forward != null) {
    		if(forward.isRedirect()) {
    			response.sendRedirect(forward.getPath());
    		}else {
    			RequestDispatcher dis = request.getRequestDispatcher(forward.getPath());
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
