package com.team1.sj.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.sj.ajax.AddReply;


@WebServlet("*.sjajax")
public class SJ_ajax_controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SJ_ajax_controller() {
        super();
    }
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	System.out.println("hi. Im sjajax"); 
    	
    	String path1 = request.getContextPath(); //경로체크
    	String path2 = request.getRequestURI();	 //경로체크
    	String url_command = path2.substring(path1.length()); //URI 잘라서 command만 남김
    	
    	ActionForward forward = null;
    	Action action = null;
    	
    	if(url_command.equals("/replyInsert.ajax")) {
    		action = new AddReply();
    		forward = action.execute(request, response);
    	}
    	
    	
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
