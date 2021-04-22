package com.team1.jh.ajax;

import java.io.IOException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.jh.dao.MiniDao;



@WebServlet("/ReplyAdd.ajax")
public class ReplyAdd extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReplyAdd() {
        super();
    }


    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
		String idx_fk = request.getParameter("idx");
		String userid_fk = request.getParameter("userid");
		String content = request.getParameter("reply_content");
		String nickName = request.getParameter("nickname");
		
//		System.out.println(content);
//		System.out.println(idx_fk);
//		System.out.println(userid_fk);
//		System.out.println("nickName : " +nickName); 
		
		try {
			MiniDao dao = new MiniDao();
			int result = dao.replyWrite(Integer.parseInt(idx_fk), userid_fk, content);
			
		} catch (NamingException e) {
			e.printStackTrace();
			System.out.println("ReplyAdd.ajax 오류 : " +e.getMessage());
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
}
