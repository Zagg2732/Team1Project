package com.team1.yh.service;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.yh.dao.KimsBoardDao;
import com.team1.yh.dto.KimsBoard;


public class KimsBoardAddService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		
		
		
		
		String uploadpath = request.getSession().getServletContext().getRealPath("upload");
		System.out.println(uploadpath);
		
		int size = 1024*1024*10;
		
		try {
			MultipartRequest multi = new MultipartRequest(
				      request, //   javax.servlet.http.HttpServletRequest request 주소값
				      uploadpath, //실 저장 경로 (배포된 경로)   
				      size, //10M
				      "UTF-8",
				      new DefaultFileRenamePolicy() //파일 중복 (upload > a.txt > a.txt 업로드 > a_1.txt)    
				   );
			
			String userid = multi.getParameter("userid");
			String subject = multi.getParameter("subject");
			String content = multi.getParameter("content");
			
			System.out.println("-------------------------------");
			System.out.println(userid);
			System.out.println(subject);
			System.out.println(content);
			System.out.println("-------------------------------");
			
			KimsBoard kimsboard = new KimsBoard();
			
			kimsboard.setUserid(userid);
			kimsboard.setSubject(subject);
			kimsboard.setContent(content);
			
			
			
			int result = 0;
			
			try {
				KimsBoardDao dao = new KimsBoardDao();
				
				result = dao.writeok(kimsboard);
				
			} catch (NamingException e) {
				e.printStackTrace();
			}
			String msg = "";
			String url = "";
			
			if (result > 0) {
				msg = "글을 등록하였습니다.";
				url = "kimslist.kims";
			} else {
				msg = "글 등록에 실패하였습니다.";
				url = "kimswrite.kims";
			}
			
			request.setAttribute("board_msg", msg);
			request.setAttribute("board_url", url);
			
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} 
		
		ActionForward forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/yh/board_kims/redirect.jsp");

		return forward;

	}

}
