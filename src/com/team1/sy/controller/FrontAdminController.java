package com.team1.sy.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.sy.service.Admin_UserList;
import com.team1.sy.service.BestPost_SJ;
import com.team1.sy.service.LoginAction;


@WebServlet("*.admin")
public class FrontAdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public FrontAdminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String url_Command = requestURI.substring(contextPath.length());
	
    	Action action=null;
    	ActionForward forward=null;
    	
    	if(url_Command.equals("/main.admin")) {
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("/WEB-INF/views/sy/admin/admin_main.jsp");
    	}else if(url_Command.equals("/UserList.admin")) {
    		action = new Admin_UserList();
    		forward = action.execute(request, response);
    	}else if(url_Command.equals("/BestPostSJ.admin")) {
    		action = new BestPost_SJ();
    		forward = action.execute(request, response);
    	}else if(url_Command.equals("/talk.admin")) {
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("/WEB-INF/views/sy/admin/admin_talk.jsp");
    	}
    	
    	
    	if(forward != null) {
    		if(forward.isRedirect()) { //true 
    			response.sendRedirect(forward.getPath());
    		}else { //false (모든 자원 ) 사용
    			//UI
    			//UI + 로직
    			//forward url 주소 변환 없어 View 내용을 받을 수 있다
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
