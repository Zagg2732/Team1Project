package com.team1.jh.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.jh.dao.MiniDao;


@WebServlet("/ReplyDelete.ajax")
public class ReplyDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReplyDelete() {
        super();

    }
    
    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	//System.out.println("ReplyDelete.ajax Start!!!!!");
    	
		String idx_fk = request.getParameter("idx_fk");
		String userid_fk = request.getParameter("userid_fk");
		String num = request.getParameter("num");
		
		//System.out.println(idx_fk);
		//System.out.println(userid_fk);
		//System.out.println(num);
		
		
		try {
			
			PrintWriter out = response.getWriter();
			
			MiniDao dao = new MiniDao();
			int result = dao.replyDelete(num, userid_fk);
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}
}
