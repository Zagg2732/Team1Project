package com.team1.sy.service;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.sy.dao.TotalDao;
import com.team1.sy.dto.Member;

public class RegisterOk implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String userId = request.getParameter("userId");
		String userNick = request.getParameter("userNick");
		String userName = request.getParameter("userName");
		String userPw = request.getParameter("userPw");
		boolean result = false;
		
		String msg = "";
		String url = "";
		
		ActionForward forward = new ActionForward();
	 	try {
			request.setCharacterEncoding("utf-8");
		 	
		 	TotalDao dao = new TotalDao();
		 	Member member = new Member();
		 	
		 	member.setUserId(userId);
		 	member.setNickName(userNick);
		 	member.setUserName(userName);
		 	member.setUserPassword(userPw);
		 	
		 	result = dao.insertMember(member);
		 	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("RegisterOk error");
			e.printStackTrace();
		}
	 	
	 	System.out.println(result);
	 	
	 	if(result==false){
	 		
	 		msg = "회원가입 실패";
	 		url = "Register.team1";
	 		
	 	}else {
	 		
	 		msg = "회원가입 성공";
	 		url = "Login.team1";
	 		
	 	}
	 	request.setAttribute("msg", msg);
	 	request.setAttribute("url", url);
	 	forward.setRedirect(false);
	 	forward.setPath("/WEB-INF/views/common/redirect.jsp");
	 	return forward;
	}

}
