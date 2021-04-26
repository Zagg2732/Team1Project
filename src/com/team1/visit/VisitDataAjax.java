package com.team1.visit;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/VisitDataAjax.sooyeon")
public class VisitDataAjax extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public VisitDataAjax() {
		super();
	}

	private void doProcess(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		VisitCountDAO dao = VisitCountDAO.getInstance();
		
		JSONArray list = new JSONArray();

		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		//System.out.println("today : " + df.format(cal.getTime()));
		
		for(int i = 1; i <= 7; i++) {
			cal.add(Calendar.DATE, -1);
			
			String strDate = df.format(cal.getTime());
			int result = dao.getThatdayCount(strDate);
			
			JSONObject obj = new JSONObject();
			
			obj.put("date", strDate.toString());
			
			obj.put("count", result);
			
			list.add(obj);
		}
		JSONObject result = new JSONObject();
		
		result.put("list", list);
		
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(list);
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doProcess(request, response);
	}

}
