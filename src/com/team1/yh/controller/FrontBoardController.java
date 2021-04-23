package com.team1.yh.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.yh.service.KimsBoardAddService;
import com.team1.yh.service.KimsBoardListService;
import com.team1.yh.service.KimsBoardWriteService;


@WebServlet("*.kims")
public class FrontBoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public FrontBoardController() {
        super();
    }
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String url_Command = requestURI.substring(contextPath.length());
    	
    	Action action = null;
    	ActionForward forward = null;
    	System.out.println("controller 도착");
    	
    	//////////////////////////////////////////////
    	// 여기에 if문을 넣자
    	if(url_Command.equals("/kimslist.kims")) { //킴스게시판 글쓰기 처리
    		System.out.println("게시판 도착");
    		action = new KimsBoardListService();
    		forward = action.execute(request, response);
    	} else if (url_Command.equals("/kimswrite.kims")) { //만약 있다면 상세보기
    		System.out.println("글 쓰기 도착");
    		action = new KimsBoardWriteService();
    		forward = action.execute(request, response);
    	} else if (url_Command.equals("/kimswriteok.kims")) { //만약 있다면 상세보기
    		System.out.println("글 등록 도착");
    		action = new KimsBoardAddService();
    		forward = action.execute(request, response);
    	} 
    	
    	///////////////////////////////////////////////
    	
    	if(forward != null) {
    		if(forward.isRedirect()) { //true 
    			response.sendRedirect(forward.getPath());
    		}else {
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
