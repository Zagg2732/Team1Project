package com.team1.sj.service;

import javax.naming.NamingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.action.Action;
import com.team1.action.ActionForward;
import com.team1.sj.dao.SJ_board_dao;

public class SJ_BoardModifyWriteOK implements Action {

	@Override
	public ActionForward execute(HttpServletRequest request, HttpServletResponse response) {
		String type = request.getParameter("type");
		String idx = request.getParameter("idx");
		
		System.out.println("modify-------------------------");
		System.out.println(type);
		System.out.println(idx);
		
		try {
			SJ_board_dao dao = new SJ_board_dao();
			//dao.boardModify();
		} catch (NamingException e) {
			e.printStackTrace();
		}
		
		
		
		
		return null;
	}

}
