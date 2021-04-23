<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>SJ게시판</title>
<link rel="Stylesheet" href="css/hsj_style/default.css">
</head>
<body>
	<jsp:include page="/WEB-INF/views/sj/include/header_sj.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/sj/include/section_sj.jsp"></jsp:include>
</body>
</html>