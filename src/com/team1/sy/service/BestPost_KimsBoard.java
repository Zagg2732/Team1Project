package com.team1.sy.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.jh.dto.DiaryDto;
import com.team1.sj.dto.SJ_board;
import com.team1.sy.dao.AdminPostDao;
import com.team1.sy.dao.AdminUserDao;
import com.team1.sy.dto.Member;
import com.team1.utils.ThePager;
import com.team1.yh.dto.KimsBoard;

public class BestPost_KimsBoard implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		try {
			AdminPostDao dao = new AdminPostDao();

			// 상세보기 >> 다시 LIST 넘어올때 >> 현재 페이지 설정
			String ps = request.getParameter("ps"); // pagesize
			
			// List 페이지 처음 호출 ...
			if (ps == null || ps.trim().equals("")) {
				// default 값 설정
				ps = "5"; // 5개씩
			}

			int pagesize = Integer.parseInt(ps);
			
			List<KimsBoard> list = dao.kimBestList(pagesize);
			
			request.setAttribute("pagesize", pagesize);
			request.setAttribute("list", list);

			forward = new ActionForward();
			forward.setRedirect(false); // forward
			forward.setPath("/WEB-INF/views/sy/admin/bestpost_kimsboard.jsp");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return forward;	
	}

}
