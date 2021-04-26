package com.team1.sy.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.sy.dao.TotalDao;
import com.team1.sy.dto.Member;

public class EditProfileAction implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		
		TotalDao totalDao = new TotalDao();
		boolean result = false;
		
		//userId=admin&userNick=관리자&beforePw=1004&userPw=1234&pwConfirm=1234
		String userid = request.getParameter("userId");
		String userNick = request.getParameter("userNick");
		String beforepw = request.getParameter("beforePw");
		String userpw = request.getParameter("userPw");
		
		Member member = totalDao.isMember(userid, beforepw);
		
		String msg = "";
		String url = "";
		
		if(member != null) {
			result = totalDao.editProfileUpdate(userid, userNick, userpw);
			
			if(result == true) {
				msg = "프로필 수정이 완료되었습니다. 다시 로그인 진행 해주세요.";
				url = "Logout.team1";
			}else {
				msg = "프로필 수정에 실패하였습니다. 잠시 후 다시 이용해주세요.";
				url = "EditProfile.team1";
			}
			
			
		}else {
			msg = "현재 비밀번호를 잘못 입력하셨습니다. 확인 후 이용해주세요.";
			url = "EditProfile.team1";
		}
		
		request.setAttribute("msg", msg);
		request.setAttribute("url", url);
		
		forward = new ActionForward();
		forward.setRedirect(false);
		forward.setPath("/WEB-INF/views/common/redirect.jsp");
		
		return forward;
	}

}
