package com.team1.yh.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.utils.ThePager;
import com.team1.yh.dao.KimsBoardDao;
import com.team1.yh.dto.KimsBoard;

public class KimsBoardListService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		//db연결해서 리스트 뽑아오기
		
		ActionForward forward = null;
		try {
			KimsBoardDao dao = new KimsBoardDao();
			
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
			
			
			List<KimsBoard> list = dao.list(cpage, pagesize);
			
			int pagersize = 3;
			ThePager pager = new ThePager(totalboardcount, cpage, pagesize, pagersize, "kimslist.kims");
			
			request.setAttribute("pagesize", pagesize);
			request.setAttribute("cpage", cpage);
			request.setAttribute("pagecount", pagecount);
			request.setAttribute("list", list);
			request.setAttribute("totalboardcount", totalboardcount);
			request.setAttribute("pager", pager);
			
			
			forward = new ActionForward();
			forward.setRedirect(false);
			forward.setPath("/WEB-INF/views/yh/board_kims/boardlist.jsp");
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return forward; // forward정보 리턴 
		
	}
	
}
