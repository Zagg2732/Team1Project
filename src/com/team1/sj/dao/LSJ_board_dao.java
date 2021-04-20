package com.team1.sj.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.team1.sj.dto.LSJ_board;


public class LSJ_board_dao {
DataSource ds = null;
	
	public LSJ_board_dao() throws NamingException {
		Context context = new InitialContext();
		ds = (DataSource)context.lookup("java:comp/env/jdbc/oracle");
	}
	
	public List<LSJ_board> list(String name) {
		Connection conn = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		List<LSJ_board> list = null;
		
		try {
			conn = ds.getConnection();
			String sql = "SELECT IDX , nickname , UP , DOWN , READNUM , WRITEDATE , SUBJECT FROM" 
						 + name //humor_board || notice_board
						 + "hb LEFT JOIN TEAM1_USER tu ON hb.USERID_FK = tu.USERID ;";
			
			
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return null;
	}
}
