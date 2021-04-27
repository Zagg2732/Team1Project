package com.team1.jh.ajax;

import java.io.IOException;
import java.io.PrintWriter;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.jh.dao.GuestBookDao;
import com.team1.jh.dao.MiniDao;

/**
 * Servlet implementation class GuestBookDelete
 */
@WebServlet("/GuestBookDelete.ajax")
public class GuestBookDelete extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public GuestBookDelete() {
        super();
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	System.out.println("GuestBookDelete.ajax Start!!!!!");
		String idx = request.getParameter("idx");
		String userid_fk = request.getParameter("userid_fk");
		
		System.out.println("idx :" +idx);
		System.out.println("userid_fk :" +userid_fk);		
		
		try {
			
			PrintWriter out = response.getWriter();
			
			GuestBookDao dao = new GuestBookDao();
			int result = dao.guestBookDelete(idx, userid_fk);
			
		} catch (NamingException e) {
			System.out.println("guestBookDelete.ajax 오류 :" +e.getMessage());
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
