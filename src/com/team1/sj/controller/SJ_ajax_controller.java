package com.team1.sj.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.sj.ajax.SJ_AddReply;
import com.team1.sj.ajax.SJ_ReplyAddForm;
import com.team1.sj.ajax.SJ_ReplyAddReply;
import com.team1.sj.ajax.SJ_ReplyDelete;
import com.team1.sj.ajax.SJ_ReplyList;


@WebServlet("*.sjajax")
public class SJ_ajax_controller extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

    public SJ_ajax_controller() {
        super();
    }
    
    protected void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String path1 = request.getContextPath(); //경로체크
    	String path2 = request.getRequestURI();	 //경로체크
    	String url_command = path2.substring(path1.length()); //URI 잘라서 command만 남김
    	
    	ActionForward forward = null;
    	Action action = null;
    	
    	if(url_command.equals("/replyInsert.sjajax")) {
    		action = new SJ_AddReply();
    		forward = action.execute(request, response);
    	} else if (url_command.equals("/replyList.sjajax")) {
    		action = new SJ_ReplyList();
    		forward = action.execute(request, response);
    	} else if (url_command.equals("/replyDelete.sjajax")) {
    		action = new SJ_ReplyDelete();
    		forward = action.execute(request, response);
    	} else if (url_command.equals("/replyAddForm.sjajax")) {
    		action = new SJ_ReplyAddForm();
    		forward = action.execute(request, response);
    	} else if (url_command.equals("/replyAddReply.sjajax")) {
    		action = new SJ_ReplyAddReply();
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
