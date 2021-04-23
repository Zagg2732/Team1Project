package com.team1.sy.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.sy.dao.TotalDao;
import com.team1.sy.dto.Member;

public class LoginAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = new ActionForward();
		
		HttpSession session = request.getSession();
		
		TotalDao totalDao = new TotalDao();
						
		String userid = request.getParameter("userid");
		String userpw = request.getParameter("userpw");
		
		Member member = totalDao.isMember(userid, userpw);
		
		String msg = "";
		String url = "";
		
		if(member != null) {
			session.setAttribute("userInfo", member);
			forward.setRedirect(true);
	   		forward.setPath("index.team1");
			
		}else {
			msg = "아이디 또는 비밀번호를 확인해주세요.";
			url = "Login.team1";
			
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			
			//forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/views/common/redirect.jsp");
		}
		
		return forward;
	}

}
