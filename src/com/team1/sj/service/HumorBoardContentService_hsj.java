package com.team1.sj.service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.sj.dao.BoardDao_hsj;
import com.team1.sj.dto.Board_Reply_hsj;
import com.team1.sj.dto.Board_hsj;

public class HumorBoardContentService_hsj implements Action{

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
