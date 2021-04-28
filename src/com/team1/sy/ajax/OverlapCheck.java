package com.team1.sy.ajax;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.sy.dao.TotalDao;

import net.sf.json.JSONObject;

@WebServlet("/OverlapCheck.sooyeon")
public class OverlapCheck extends HttpServlet {
	private static final long serialVersionUID = 1L;

    public OverlapCheck() {
        super();
    }

	private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		String value = request.getParameter("value");

		String col = "";
		boolean result = false;
		
		if (type.equals("id")) {
			col = "USERID";
		} else {
			col = "NICKNAME";
		}
		
		TotalDao dao = new TotalDao();
		result = dao.overlapCheck(col, value);

		JSONObject jObj = new JSONObject();

		jObj.put("result", result);

		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(jObj);
	}
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
