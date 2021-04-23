package com.team1.sj.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.team1.action.Action;
import com.team1.action.ActionForward;


public class SJ_HumorBoardContentService implements Action{

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		ActionForward forward = null;;
		
		String idx = request.getParameter("idx");
		String cpage = request.getParameter("cp");
		String pagesize = request.getParameter("ps");
		
		
		
		return null;
	}

	
}
