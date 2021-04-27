<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@page import="javax.sql.DataSource"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.naming.Context"%>
<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.Connection"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>

<meta charset="UTF-8">
<title>SJ게시판</title>

<link rel="Stylesheet" href="css/hsj_style/default.css">

<style type="text/css">
/* Underline Effect*/
.nav-item {
	position: relative;
}

.navbar-collapse ul li a.nav-link:before {
	position: absolute;
	bottom: -5px;
	left: 0;
	width: 100%;
	height: 2px;
	background: transparent;
	content: '';
	opacity: 0;
	-ms-transition: opacity 0.3s, -webkit-transform 0.3s;
	-webkit-transition: opacity 0.3s, -webkit-transform 0.3s;
	transition: opacity 0.3s, transform 0.3s;
	-ms-transform: translateY(10px);
	-webkit-transform: translateY(10px);
	transform: translateY(10px);
}

.navbar-collapse ul li:hover a.nav-link:before {
	opacity: 1;
	-ms-transform: translateY(0px);
	-webkit-transform: translateY(0px);
	transform: translateY(0px);
	bottom: 0px;
	background: #dd4343;
}

.dropdown-item:hover, .dropdown-item:focus {
	color: #ffffff;
	text-decoration: none;
	background-color: #dd4343;
}

.dropdown-menu {
	border: 0px;
}

/* General Styles*/
body {
	background-image: url();
	background-size: cover;
	background-position: center;
	background-repeat: no-repeat;
	height: 100vh;
}

a:link {
	color: black;
	text-decoration: none;
}

a:visited {
	color: black;
	text-decoration: none;
}

a:hover {
	color: black;
	text-decoration: none;
}

.col-sm-6 {
	display: flex;
	width: 50%;
}

.col-md-9 at-col at-main {
	height: auto !important;
	min-height: 0px !important;
}

.row {
	margin-right: -15px;
	margin-left: -15px;
	justify-content: flex-end;
}
</style>


<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">
</head>
<body>


	<nav class="navbar navbar-expand-lg navbar-light bg-light">
		<a class="navbar-brand" href="index.sj">&nbsp;&nbsp;&nbsp;&nbsp;SJ Board</a>
		<button class="navbar-toggler" type="button" data-toggle="collapse"
			data-target="#navbarSupportedContent"
			aria-controls="navbarSupportedContent" aria-expanded="false"
			aria-label="Toggle navigation">
			<span class="navbar-toggler-icon"></span>
		</button>

		<div class="collapse navbar-collapse" id="navbarSupportedContent">

			<ul class="navbar-nav mr-auto">
				<li class="nav-item"><a class="nav-link" href="index.jsp">
						Home <span class="sr-only"></span>
				</a></li>

					
					
						<li class="nav-item"><a class="nav-link"
					href="boardList.sj?type=humor_board">Humor</a></li>
				<li class="nav-item"><a class="nav-link"
					href="boardList.sj?type=notice_board">Notice</a></li>
				<li class="nav-item"><a class="nav-link"
					href="EditProfile.team1">MyPage</a></li>
			
			
			</ul>
		</div>
	</nav>
	<head>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@5.15.3/css/fontawesome.min.css">
</head>

<!-- PC Header -->
<header class="pc-header">
	<!-- PC Logo -->
	<div class="header-logo">
		<a href="index.sj"> <!-- 로고 누르면 홈화면 이동  --> <img
			src="images/hsj/logo.png" alt="" />
		</a>
	</div>
</header>


<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
	integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"
	integrity="sha384-SR1sx49pcuLnqZUnnPwx6FCym0wLsk5JZuNx2bPPENzswTNFaQU1RDvt3wT4gWFG"
	crossorigin="anonymous"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js"
	integrity="sha384-j0CNLUeiqtyaRmlzUHCPZ+Gy5fQu0dQ6eZ/xAww941Ai1SxSY+0EQqNXNE6DZiVc"
	crossorigin="anonymous"></script>



</body>

</html>

