package com.team1.sj.service;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.sj.dao.BoardDao_hsj;
import com.team1.sj.dto.Board_hsj;

public class HumorBoardAddService_hsj implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String uploadpath = request.getSession().getServletContext().getRealPath("upload");
		
		System.out.println("uploadpath잘있늬???");
		System.out.println(uploadpath);
		
		int size = 1024*1024*10;
		
		try {
			MultipartRequest multi = new MultipartRequest(request, uploadpath, size, "UTF-8",new DefaultFileRenamePolicy());
			
			String subject = multi.getParameter("subject");
			String content = multi.getParameter("content");
			String filename = multi.getFilesystemName("filename");
			
			Board_hsj board = new Board_hsj();
			
			board.setSubject(subject);
			board.setContent(content);
			board.setFilename(filename);
			
			int result = 0;
			
			try {
				
				BoardDao_hsj dao = new BoardDao_hsj();
				
				result = dao.writeok(board);
				
			}catch(NamingException e) {
				e.printStackTrace();
			}
			String msg = "";
			String url = "";
			
			if(result>0) {
				msg = "success";
				url = "HumorList.hsj";
			}else {
				msg = "fail";
				url = "HumorWrite.hsj";
			}
			
			request.setAttribute("board_msg", msg);
			request.setAttribute("board_url", url);
			
		} catch (IOException e1) {
			// TODO: handle exception
			e1.printStackTrace();
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/sj/board_hsj/redirect_hsj.jsp");
		
		return forward;
	}

}
