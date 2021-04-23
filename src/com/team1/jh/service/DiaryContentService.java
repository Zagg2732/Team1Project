package com.team1.jh.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.jh.dao.MiniDao;
import com.team1.jh.dto.DiaryDto;
import com.team1.jh.dto.DiaryReplyDto;
import com.team1.sy.dao.TotalDao;
import com.team1.sy.dto.Member;


public class DiaryContentService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;

		String idx = request.getParameter("idx");
		String cpage = request.getParameter("cp"); // current page
		String pagesize = request.getParameter("ps"); // pagesize
		
		DiaryDto diaryDto = new DiaryDto();
		List<DiaryReplyDto> diaryReplyList = new ArrayList<>();
		
		
		//화면단에서 로그인세션 쓰려고 request에 담았습니다 
		HttpSession session = request.getSession();
		Member userInfo = (Member)session.getAttribute("userInfo");
		//System.out.println("userInfo:" +userInfo);
		
		String userid;
		String nickname;
		int grade;
		
		if(userInfo != null) { //회원
			userid = userInfo.getUserId();
			nickname = userInfo.getNickName();
			grade = userInfo.getGrade();
		}
		else { //비회원
			userid = "";
			nickname = "";
			grade = 0;
		}

		boolean isread = false;

		try {
			
			MiniDao dao = new MiniDao();
			// 글 번호를 가지고 오지 않았을 경우 예외처리
			if (idx == null || idx.trim().equals("")) {
				response.sendRedirect("diary.mini"); //다이어리 목록으로
				return null;
			}
			
			idx = idx.trim();

			//List 페이지 처음 호출 ...
			if(cpage == null || cpage.trim().equals("")){
				//default 값 설정
				cpage = "1"; 
			}
		
			if(pagesize == null || pagesize.trim().equals("")){
				//default 값 설정
				pagesize = "5"; 
			}
			
			isread = dao.getReadNum(idx);
			
			if(isread) {
				diaryDto = dao.getContent(Integer.parseInt(idx));
				diaryReplyList = dao.diaryReplyList(idx);
			}
			
			request.setAttribute("idx", idx);
			request.setAttribute("cp", cpage);
			request.setAttribute("ps", pagesize);
			request.setAttribute("diaryDto", diaryDto);
			request.setAttribute("diaryReplyList", diaryReplyList);
			
			request.setAttribute("sessionUserid", userid);
			request.setAttribute("sessionNickname", nickname);
			request.setAttribute("sessionGrade", grade);
			
			forward = new ActionForward();
			forward.setRedirect(false); // forward
			forward.setPath("/WEB-INF/views/jh/diary_content.jsp");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return forward;
	}

}
