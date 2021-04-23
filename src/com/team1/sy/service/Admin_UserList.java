package com.team1.sy.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.sy.dao.AdminDao;
import com.team1.sy.dto.Member;
import com.team1.utils.ThePager;

public class Admin_UserList implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		ActionForward forward = null;
		try {
			AdminDao dao = new AdminDao();

			// 게시물 총 건수
			int totalUserCnt = dao.totalUserCount();

			// 상세보기 >> 다시 LIST 넘어올때 >> 현재 페이지 설정
			String ps = request.getParameter("ps"); // pagesize
			String cp = request.getParameter("cp"); // current page
			
			System.out.println("ps : " + ps);
			System.out.println("cp : " + cp);

			// List 페이지 처음 호출 ...
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

			// 23건 % 5
			if (totalUserCnt % pagesize == 0) {
				pagecount = totalUserCnt / pagesize; // 20 << 100/5
			} else {
				pagecount = (totalUserCnt / pagesize) + 1;
			}

			// 102건 : pagesize=5 >> pagecount=21페이지

			// 전체 목록 가져오기
			List<Member> list = dao.list(cpage, pagesize); // list >> 1 , 20
			
			int pagersize=3; //[1][2][3]
			ThePager pager = new ThePager(totalUserCnt,cpage,pagesize,pagersize,"UserList.admin");
			
			
			request.setAttribute("pagesize", pagesize);
			request.setAttribute("cpage", cpage);
			request.setAttribute("pagecount", pagecount);
			request.setAttribute("list", list);
			request.setAttribute("totalboardcount", totalUserCnt);
			request.setAttribute("pager", pager);

			forward = new ActionForward();
			forward.setRedirect(false); // forward
			forward.setPath("/WEB-INF/views/sy/admin/admin_userlist.jsp");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return forward;	
	}

}
