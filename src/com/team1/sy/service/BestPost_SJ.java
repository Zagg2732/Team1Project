package com.team1.sy.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.sj.dto.SJ_board;
import com.team1.sy.dao.AdminPostDao;
import com.team1.sy.dao.AdminUserDao;
import com.team1.sy.dto.Member;
import com.team1.utils.ThePager;

public class BestPost_SJ implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		try {
			AdminPostDao dao = new AdminPostDao();

			// 상세보기 >> 다시 LIST 넘어올때 >> 현재 페이지 설정
			String bn = request.getParameter("bn");
			String ps = request.getParameter("ps"); // pagesize
			
			// List 페이지 처음 호출 ...
			if (ps == null || ps.trim().equals("")) {
				// default 값 설정
				ps = "5"; // 5개씩
			}
			if (bn == null || bn.trim().equals("")) {
				bn = "HUMOR_BOARD";
			}

			int pagesize = Integer.parseInt(ps);
			
			List<SJ_board> list = dao.sjBoardBestList(bn, pagesize);
			
			request.setAttribute("pagesize", pagesize);
			request.setAttribute("boardname", bn);
			request.setAttribute("list", list);


			forward = new ActionForward();
			forward.setRedirect(false); // forward
			forward.setPath("/WEB-INF/views/sy/admin/bestpost_sj.jsp");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return forward;	
	}

}
