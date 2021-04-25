package com.team1.sy.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.*;
import com.team1.sy.service.LoginAction;
import com.team1.sy.service.LogoutAction;
import com.team1.sy.service.RegisterOk;

@WebServlet("*.team1")
public class FrontMainController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public FrontMainController() {
        super();
    }


    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String requestURI = request.getRequestURI();
    	String contextPath = request.getContextPath();
    	String url_Command = requestURI.substring(contextPath.length());
	
    	Action action=null;
    	ActionForward forward=null;
    	
    	
    	if(url_Command.equals("/index.team1")) {
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("index.jsp");
    	}else if(url_Command.equals("/redirect.team1")) {
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("/WEB-INF/views/common/redirect.jsp");
    	}else if(url_Command.equals("/Login.team1")) { //만약 있다면 상세보기
    		//UI 제공 .../Register.team1
    		//예) /WEB-INF/views/memoview.jsp 가정
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("/WEB-INF/views/sy/total/login.jsp");
    	}else if(url_Command.equals("/LoginOk.team1")) {
    		action = new LoginAction();
    		forward = action.execute(request, response);
    	}else if(url_Command.equals("/Logout.team1")) {
    		action = new LogoutAction();
    		forward = action.execute(request, response);
    	}else if(url_Command.equals("/Register.team1")) {
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("/WEB-INF/views/sy/total/register.jsp");
    	}else if(url_Command.equals("/RegisterOk.team1")) {
    		action = new RegisterOk();
    		forward = action.execute(request, response);
    	}else if(url_Command.equals("/EditProfile.team1")) {
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("/WEB-INF/views/sy/total/editprofile.jsp");
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
