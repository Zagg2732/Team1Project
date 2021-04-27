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

public class SJ_BoardModifyWriteOK implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String uploadpath = request.getSession().getServletContext().getRealPath("upload");
		
		int size = 1024*1024*10;
		
		MultipartRequest multi;
		try {
			multi = new MultipartRequest(request, uploadpath, size, "UTF-8",new DefaultFileRenamePolicy());
			
			
			String type = multi.getParameter("type");
			String idx = multi.getParameter("idx");
			String subject = multi.getParameter("subject");
			String content = multi.getParameter("content");
			String filename = multi.getParameter("filename");
			
			SJ_board_dao dao = new SJ_board_dao();
      
			int result = dao.boardModify(type, idx, subject, content);
			
			if(result == 0) {
				System.out.println("Error! : modify error");
			}
			
			ActionForward forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("board.sj?type="+ type +"&idx=" + idx);
			return forward;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
