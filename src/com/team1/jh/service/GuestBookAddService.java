package com.team1.jh.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.sy.dto.Member;

public class GuestBookAddService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		//화면단에서 로그인세션 쓰려고 request에 담았습니다 
		HttpSession session = request.getSession();
		Member userInfo = (Member)session.getAttribute("userInfo");
		
		//System.out.println("userInfo:" +userInfo);		
				
		String userid;
		String nickname;
		String username;
		int grade;
		
		if(userInfo != null) { //회원
			userid = userInfo.getUserId();
			nickname = userInfo.getNickName();
			username = userInfo.getUserName();
			grade = userInfo.getGrade();
		}
		else { //비회원
			userid = "";
			nickname = "";
			username = "";
			grade = 0;
		}
		
		request.setAttribute("sessionUserid", userid);
		request.setAttribute("sessionNickname", nickname);
		request.setAttribute("sessionUsername", username);
		request.setAttribute("sessionGrade", grade);
		
		forward = new ActionForward();
		forward.setRedirect(false); // forward
		forward.setPath("/WEB-INF/views/jh/guestbook_write.jsp");			
		
		
		return forward;
	}

}
