package com.team1.sj.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.sj.service.HumorListService;


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
    	
    	if(url_Command.equals("/HumorList.hsj")) { //유머게시판 글쓰기 처리
    		action = new HumorListService();
    		forward = action.execute(request, response);
    		
    	}
	}
    
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
