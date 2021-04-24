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
import com.team1.sj.service.SJ_HumorBoardAddService;
import com.team1.sj.service.SJ_HumorListService;
import com.team1.sj.service.SJ_HumorBoardList;
import com.team1.sj.service.SJ_HumorContent;


@WebServlet("*.sj")
public class SJ_controller extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public SJ_controller() {
        super();
    }
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	String path1 = request.getContextPath(); //경로체크
    	String path2 = request.getRequestURI();	 //경로체크
    	String url_command = path2.substring(path1.length()); //URI 잘라서 command만 남김
    	

    	ActionForward forward = null;
    	Action action = null;
    	
    	// type 받아주고 
    	String type = request.getParameter("type");
    	
    	
    	if(url_command.equals("/index.sj")) {    	
    		action = new SJ_HumorBoardList();
    		forward = action.execute(request, response);
    		
    	} else if (url_command.equals("/board.sj")) {
    		action = new SJ_HumorContent();
    		forward = action.execute(request, response);
    		
    		
    	} else if (url_command.equals("/boardList.sj")) {
    		action = new SJ_HumorListService();
    		forward = action.execute(request, response);
    		
    		
    	} else if (url_command.equals("/boardWrite.sj")) {
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("WEB-INF/views/sj/board_hsj/"+type+"_write.jsp");
    		
    		
    	} else if(url_command.equals("/boardWriteOK.sj")) {
    		action = new SJ_HumorBoardAddService();
    		forward = action.execute(request, response);

        
    	} else if(url_command.equals("/replyWrite.sj")) {
    		//action = new SJ_ReplyAddService();
    		forward = action.execute(request, response);

    		
    	} else {
    		System.out.println("Error : you entered *.lsj but you didn't set commanding it yet. 아님말고");
    	}
    		
    	
    	
    	if(forward != null) {
    		if(forward.isRedirect()) {
    			System.out.println("forward isRedirect error!!!");
    		} else {
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
