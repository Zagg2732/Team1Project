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

.container {
	width: 800px;
}

/* body {margin:0;}
 #wrap {margin:0 auto;text-align:center;}
 #quick_bg {margin:0 auto;text-align:center;width:1130px;position:relative;}
 #quick {position:absolute;z-index:2;top:15px;width:153px;right:0px;}
 #container {position:relative;} */
#sidebar {
	width: 250px;
	height: 300px;
	right: 0;
	border: 1px solid #DCDCDC;
	border-radius: 50px;
	text-align: center;
	margin-right: 50px;
	padding-top: 10px;
}

#sidebar ul {
	padding: 20px;
}
</style>
<script type="text/javascript"
	src="http://ajax.googleapis.com/ajax/libs/jquery/1.4.3/jquery.min.js"></script>

<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">
</head>
<body>
	<%-- <jsp:include page="/WEB-INF/views/sj/include/header_sj.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/sj/include/section_sj.jsp"></jsp:include> --%>

	<!-- 배너 -->
	<!-- <div id="wrap">
	<div id="container">
		<div id="quick_bg">
			<div id="quick">
				<a href="#form"><img src="images/hsj/d.png"></a>
			</div>
		</div>
	</div>
</div> -->





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
				<li class="nav-item"><a class="nav-link" href="index.team1">
						Home <span class="sr-only"></span>
				</a></li>

				<li class="nav-item"><a class="nav-link" href="#">Best</a></li>
				<li class="nav-item"><a class="nav-link" href="#">New</a></li>
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


	<!-- 사이드 -->
			<div id="sidebar" style="position: absolute;">
				<ul>

					<a href="#" style="color: red;">Best</a>
					<br>
					<br>
					<a href="#">New</a>
					<br>
					<br>
					<a href="boardList.sj?type=notice_board">공지게시판</a>
					<br>
					<br>
					<a href="boardList.sj?type=humor_board">유머게시판</a>
				</ul>
			</div>
			<!-- 사이드 끝 -->

<!-- PC Header -->
<header class="pc-header">
	<!-- PC Logo -->
	<div class="header-logo">
		<a href="index.sj"> <!-- 로고 누르면 홈화면 이동  --> <img
			src="images/hsj/logo.png" alt="" />
		</a>
	</div>
</header>





<div class="container">
	<!-- <div class="row at-row" > -->
	<div class="col-md-9 at-col at-main">

		<div class="row">





			<!-- 베스트  -->
			<div class="col-sm-6">
				<table class="table table-hover">
					<thead style="color: #ff0000;">

						<tr>
							<th><h3>Best</h3></th>

						</tr>

					</thead>
					<tbody>
						<c:forEach var="board" items="${requestScope.noticelist}">
							<tr class="boardlist">
								<td><a href="board.sj?idx=${board.idx}&type=notice_board">${board.subject}</a></td>

							</tr>
							<br>
						</c:forEach>

					</tbody>

				</table>
				<hr>
			</div>
			<!-- 베스트 끝  -->

			<!-- New -->
			<div class="col-sm-6">
				<table class="table table-hover">
					<thead>

						<tr>
							<th><h3>New</h3></th>

						</tr>

					</thead>

					<tbody>

						<c:forEach var="board" items="${requestScope.noticelist}">
							<tr class="boardlist">
								<td><a href="board.sj?idx=${board.idx}&type=notice_board">${board.subject}</a></td>

							</tr>
							<br>
						</c:forEach>
					</tbody>
				</table>
				<hr>

			</div>
			<!-- New 끝 -->

			<!-- 공지리스트 -->
			<div class="col-sm-6">
				<table class="table table-hover">
					<thead>

						<tr> 
							<th><h3>공지게시판</h3></th>

						</tr>

					</thead>
					<tbody>
						<c:forEach var="board" items="${requestScope.noticelist}">
							<tr class="boardlist">
								<td><a href="board.sj?idx=${board.idx}&type=notice_board">${board.subject}</a></td>

							</tr>
							<br>
						</c:forEach>
					</tbody>
				</table>
				<hr>
			</div>
			<!-- 공지리스트 끝 -->


			<!-- 유머리스트 -->
			<div class="col-sm-6">
				<table class="table table-hover">
					<thead>
						<tr>
							<th><h3>유머게시판</h3></th>

						</tr>
					</thead>
					<tbody>
						<c:forEach var="board" items="${requestScope.humorlist}">
							<tr class="boardlist">
								<td><a href="board.sj?idx=${board.idx}&type=humor_board">${board.subject}</a></td>

							</tr>
							<br>
						</c:forEach>
					</tbody>
				</table>
				<hr />

				<!-- <a class="btn btn-default pull-right">글쓰기</a> -->

				<!-- <div class="text-center">
	<ul class="pagination">
		<li><a href="#">1</a></li>
		<li><a href="#">2</a></li>
		<li><a href="#">3</a></li>
		<li><a href="#">4</a></li>
		<li><a href="#">5</a></li>

	</ul>

</div> -->

			</div>
			<!--유머리스트 끝 -->



		</div>

	</div>

</div>

<!-- </div> -->



<!--  
  <div class="container" style="text-align: center; color: skyblue;">
  <div class="row">
  <div class="col-md-4" style="margin: auto;">
  <br> <br> <br> <br> <br> <br> <br> <br> <br> <br>
  	<h2>지친하루 달래고 가세요☺️</h2>
  		<p>당신의 몇분을 제가 웃겨드릴게요</p>
  		</div>
  		</div>
  </div> -->

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

<script type="text/javascript">
/* var quick_menu = $('#quick');
var quick_top = 470;

quick_menu.css('top', $(window).height() );
$(document).ready(function(){
quick_menu.animate( { "top": $(document).scrollTop() + quick_top +"px" }, 200 ); 
$(window).scroll(function(){
quick_menu.stop();
quick_menu.animate( { "top": $(document).scrollTop() + quick_top + "px" }, 500 );
});
}); */
$(function() {
	var offset = $("#sidebar").offset();
	var topPadding = 500;  // 300px만큼 여백을 둘때 사용 
	$(window).scroll(function() {
		if ($(window).scrollTop() > offset.top) {
			$("#sidebar").stop().animate({
				marginTop: $(window).scrollTop() - offset.top + topPadding
			});
		} else {
			$("#sidebar").stop().animate({
				marginTop: 100
			});
		};
	});
});

</script>
</html>