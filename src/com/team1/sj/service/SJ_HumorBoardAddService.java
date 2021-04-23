package com.team1.sj.service;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.sj.dao.SJ_board_dao;
import com.team1.sj.dto.SJ_board;


public class SJ_HumorBoardAddService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		String uploadpath = request.getSession().getServletContext().getRealPath("upload");
		
		System.out.println("uploadpath잘있늬???");
		System.out.println(uploadpath);
		
		int size = 1024*1024*10;
		
		try {
			MultipartRequest multi = new MultipartRequest(request, uploadpath, size, "UTF-8",new DefaultFileRenamePolicy());
			
			String userid_fk = multi.getParameter("userid_fk");
			String subject = multi.getParameter("subject");
			String content = multi.getParameter("content");
			String filename = multi.getFilesystemName("filename");
			
			String type = multi.getParameter("type");
			
			SJ_board board = new SJ_board();
			
			board.setUserid_fk(userid_fk);
			board.setSubject(subject);
			board.setContent(content);
			board.setFilename(filename);
			
		System.out.println("여기탔니??????");
			
			int result = 0;
			
			try {
				
				SJ_board_dao dao = new SJ_board_dao();
				
				result = dao.writeok(board, type);
				
				
			}catch(NamingException e) {
				e.printStackTrace();
			}
			String msg = "";
			String url = "";
			
		
			
			if(result>0) {
				msg = "success";
				url = "boardList.sj";
			}else {
				msg = "fail";
				url = "boardWrite.sj";
			}
			
			request.setAttribute("board_msg", msg);
			request.setAttribute("board_url", url);
			
		} catch (IOException e1) {
			System.out.println("여긴가 :" +e1.getMessage());
			e1.printStackTrace();
		}
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/sj/board_hsj/redirect_hsj.jsp");
		
		return forward;
	}

}
