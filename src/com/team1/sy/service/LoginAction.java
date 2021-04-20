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
		System.out.println("LoginAction ing...");
		ActionForward forward = new ActionForward();
		
		HttpSession session = request.getSession();
		
		TotalDao totalDao = new TotalDao();
		Member member = new Member();
		
		int result = -1;
		
		System.out.println();
		
		member.setUserId(request.getParameter("userid"));
		member.setUserPassword(request.getParameter("userpw"));
		
		result = totalDao.isMember(member);
		
		String msg = "";
		String url = "";
		
		if(result == 1) {
			
			session.setAttribute("userid", member.getUserId());
			forward.setRedirect(true);
	   		//forward.setPath("./BoardList.bo");
	   		forward.setPath("index.team1");
						
		}else {
			
			if(result == 0) {
				msg = "비밀번호를 확인해주세요.";
				url = "Login.team1";
			}else if(result == -1) {
				msg = "아이디가 없습니다.";
				url = "Login.team1";
				
			}
			
			request.setAttribute("msg", msg);
			request.setAttribute("url", url);
			
			//forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/views/common/redirect.jsp");
			
		}
		return forward;
	}

}
