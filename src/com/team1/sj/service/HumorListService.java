package com.team1.sj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.sj.dao.BoardDao;

public class HumorListService implements Action{ // action interface 참조
	//com.team1.sj.action에 Action, ActionForward객체 만듬 

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		ActionForward forward = null;
		try {
			BoardDao dao = new BoardDao();
			
			//게시물 총 건수
			int totalboardcount = dao.totalBoardCount();
			
			
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		return null;
		
	}
	
	

}
