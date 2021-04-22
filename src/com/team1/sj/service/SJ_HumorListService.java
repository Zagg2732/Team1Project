package com.team1.sj.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.sj.dao.SJ_board_dao;
import com.team1.sj.dto.SJ_board;
import com.team1.utils.ThePager;

public class SJ_HumorListService implements Action{ // action interface 참조
	//com.team1.sj.action에 Action, ActionForward객체 만듬 

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		ActionForward forward = null;
		try {
			SJ_board_dao dao = new SJ_board_dao();
			
			//게시물 총 건수
			int totalboardcount = dao.totalBoardCount();
			
			String ps = request.getParameter("ps");
			String cp = request.getParameter("cp");
			
			if(ps == null || ps.trim().equals("")) {
				ps = "5";
			}
			if(cp == null || cp.trim().equals("")) {
				cp = "1";
			}
			
			int pagesize = Integer.parseInt(ps);
			int cpage = Integer.parseInt(cp);
			int pagecount = 0;
			
			if(totalboardcount % pagesize == 0) {
				pagecount = totalboardcount / pagesize;
			}else {
				pagecount = (totalboardcount / pagesize) + 1;
			}
			
			List<SJ_board> list = dao.listWithPage(cpage, pagesize);
			
			int pagersize = 3;
			ThePager pager = new ThePager(totalboardcount, cpage, pagesize, pagersize, "HumorList.sj");
			
			request.setAttribute("pagesize", pagesize);
			request.setAttribute("cpage", cpage);
			request.setAttribute("pagecount", pagecount);
			request.setAttribute("list", list);
			request.setAttribute("totalboardcount", totalboardcount);
			request.setAttribute("pager", pager);
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("WEB-INF/views/sj/board_hsj/board_list_hsj.jsp");
			
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return forward; // forward정보 리턴 
		
	}
	
	

}
