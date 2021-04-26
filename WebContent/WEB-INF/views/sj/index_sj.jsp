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
  background-image: url(https://jjalbot.com/media/2018/12/lxsKuDq3I/zzal.gif);
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  height: 100vh;
  
}
</style>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
</head>
<body>
	<%-- <jsp:include page="/WEB-INF/views/sj/include/header_sj.jsp"></jsp:include>
	<jsp:include page="/WEB-INF/views/sj/include/section_sj.jsp"></jsp:include> --%>
	
	
<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <a class="navbar-brand" href="#">SJ Board</a>
  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
    <span class="navbar-toggler-icon"></span>
  </button>

  <div class="collapse navbar-collapse" id="navbarSupportedContent">
  
    <ul class="navbar-nav mr-auto">
      <li class="nav-item">
        <a class="nav-link" href="index.jsp">Home <span class="sr-only"></span></a>
      </li>
     
      <li class="nav-item">
        <a class="nav-link" href="boardList.sj?type=humor_board">Humor</a>
      </li>
            <li class="nav-item">
        <a class="nav-link" href="boardList.sj?type=notice_board">Notice</a>
      </li>
            <li class="nav-item">
        <a class="nav-link" href="EditProfile.team1">MyPage</a>
      </li>
      <li class="nav-item dropdown">

        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
          <a class="dropdown-item" href="index.jsp">Home</a>
          <a class="dropdown-item" href="boardList.sj?type=humor_board">ㅋㅋㅋㅋ</a>
          <a class="dropdown-item" href="boardList.sj?type=notice_board">공지사항</a>
          <a class="dropdown-item" href="EditProfile.team1">MyPage</a>
          <div class="dropdown-divider"></div>
          <a class="dropdown-item" href="#">Something else here</a>
        </div>
      </li>
    </ul>
  </div>
</nav>



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

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js" integrity="sha384-SR1sx49pcuLnqZUnnPwx6FCym0wLsk5JZuNx2bPPENzswTNFaQU1RDvt3wT4gWFG" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js" integrity="sha384-j0CNLUeiqtyaRmlzUHCPZ+Gy5fQu0dQ6eZ/xAww941Ai1SxSY+0EQqNXNE6DZiVc" crossorigin="anonymous"></script>


	
</body>
</html>