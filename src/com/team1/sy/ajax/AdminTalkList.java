package com.team1.sy.ajax;

import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.team1.sy.dao.AdminTalkDao;
import com.team1.sy.dto.AdminTalk;
import com.team1.utils.*;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

@WebServlet("/AdminTalkList.sooyeon")
public class AdminTalkList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public AdminTalkList() {
        super();
    }

    private void doProcess(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	AdminTalkDao dao = new AdminTalkDao();
    	
    	int totalTalkCnt = dao.totalTalkCount();
    	int pagesize = 5;
    	
    	String cp = request.getParameter("cp"); // current page
				
		if (cp == null || cp.trim().equals("")) {
			cp = "1"; // 1번째 페이지 보겠다
		}
		
		int cpage = Integer.parseInt(cp);
		int pagecount = 0;
		
		if (totalTalkCnt % pagesize == 0) {
			pagecount = totalTalkCnt / pagesize; // 20 << 100/5
		} else {
			pagecount = (totalTalkCnt / pagesize) + 1;
		}
		
		List<AdminTalk> list = dao.adminTalkList(cpage);
		
		int pagersize=3; //[1][2][3]
		ThePager pager = new ThePager(totalTalkCnt,cpage,pagesize,pagersize,"talk.admin");
				
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		JSONArray jsonArr = new JSONArray();
		
		for(int i = 0; i < list.size(); i++) {
			String date = dateFormat.format(list.get(i).getWritedate());
			JSONObject jsonObj = new JSONObject();
			
			jsonObj.put("idx", list.get(i).getIdx());
			jsonObj.put("userid", list.get(i).getUserid_fk());
			jsonObj.put("content", list.get(i).getContent());
			jsonObj.put("writedate", date);
			
			jsonArr.add(jsonObj);
		}
    	
		JSONObject gogo = new JSONObject();
		
		gogo.put("pagecount", pagecount);
		gogo.put("cp", cp);
		gogo.put("data", jsonArr);
		
		response.setContentType("application/x-json; charset=UTF-8");
		response.getWriter().print(gogo);
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doProcess(request, response);
	}

}
