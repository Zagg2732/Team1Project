package com.team1.jh.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.jh.dao.MiniDao;
import com.team1.jh.dto.DiaryDto;
import com.team1.utils.ThePager;


public class DiaryListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;

		try {
			MiniDao dao = new MiniDao();
			
			//다이어리 게시물 총 건수
			int totalboardcount = dao.totalBoardCount();
			
			//상세보기 후 다시 list 넘어올 때 현재 페이지 설정 
			String ps = request.getParameter("ps"); // pagesize
			String cp = request.getParameter("cp"); // current page
			
			System.out.println("ps : " + ps);
			System.out.println("cp : " + cp);
			
			//list 페이지 첫 호출 
			if (ps == null || ps.trim().equals("")) {
				// default 값 설정
				ps = "5"; // 5개씩
			}

			if (cp == null || cp.trim().equals("")) {
				// default 값 설정
				cp = "1"; // 1번째 페이지 보겠다
			}

			int pagesize = Integer.parseInt(ps);
			int cpage = Integer.parseInt(cp);
			int pagecount = 0;
			
			//다이어리 전체조회 
			List<DiaryDto> list = dao.list(cpage, pagesize);
			//System.out.println(list.get(0).getIdx() + list.get(0).getContent());
			
			int pagersize=3; //[1][2][3]
			ThePager pager = new ThePager(totalboardcount,cpage,pagesize,pagersize,"diary.mini");
			
			request.setAttribute("list", list);
			
			forward = new ActionForward();
			forward.setRedirect(false);
    		forward.setPath("/WEB-INF/views/jh/diary.jsp");

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return forward;
	}

}
