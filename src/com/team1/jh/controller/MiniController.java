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
import com.team1.jh.service.DiaryAddService;
import com.team1.jh.service.DiaryContentService;
import com.team1.jh.service.DiaryDeleteService;
import com.team1.jh.service.DiaryEditOk;
import com.team1.jh.service.DiaryEditService;
import com.team1.jh.service.DiaryListService;
import com.team1.jh.service.DiaryRewriteOk;
import com.team1.jh.service.DiaryRewriteService;
import com.team1.jh.service.GuestBookAddService;
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
    		
    	}else if(url_Command.equals("/diary.jh")) { //다이어리 전체보기(첫화면) 
    		action = new DiaryListService();
    		forward = action.execute(request, response);
    		
    	}else if(url_Command.equals("/diaryWrite.jh")) { //다이어리 글쓰기
    		forward = new ActionForward();
    		forward.setRedirect(false);
    		forward.setPath("/WEB-INF/views/jh/diary_write.jsp");
    	
    	}else if(url_Command.equals("/diaryWriteOk.jh")) {
    		action = new DiaryAddService();
    		forward = action.execute(request, response);
    		
    	}else if(url_Command.equals("/diaryContent.jh")) { //다이어리 상세보기 
        	action = new DiaryContentService();
        	forward = action.execute(request, response);
        	
    	}else if(url_Command.equals("/diaryRewrite.jh")) { //다이어리 답글쓰기
    		action = new DiaryRewriteService();
    		forward = action.execute(request, response);
    		
    	}else if(url_Command.equals("/diaryRewriteOk.jh")) {
    		action = new DiaryRewriteOk();
    		forward = action.execute(request, response);
    	
    	}else if(url_Command.equals("/diaryEdit.jh")) { //다이어리 글 수정하기
    		action = new DiaryEditService();
    		forward = action.execute(request, response);
    		
    	}else if(url_Command.equals("/diaryEditOk.jh")) { 
    		action = new DiaryEditOk();
    		forward = action.execute(request, response);
    		
    	}else if(url_Command.equals("/diaryDelete.jh")) { //다이어리 글 삭제
    		action = new DiaryDeleteService();
    		forward = action.execute(request, response);
    		
    	}else if(url_Command.equals("/diaryReply.jh")) { //다이어리 댓글쓰기 
            action = new ReplyAddService();
            forward = action.execute(request, response);
        
    	}else if(url_Command.equals("/guestBook.jh")) { //방명록 글쓰기
    		action = new GuestBookAddService();
        	forward = action.execute(request, response);
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
