package com.team1.yh.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.Action;
import com.team1.action.ActionForward;

public class KimsBoardContentService implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		
		String idx = request.getParameter("idx");
		String cpage = request.getParameter("cp");
		String pagesize = request.getParameter("ps");
		
		return null;
	}

	
}
