<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta name="viewport" content="width=device-width", initial-scale="1" >  <!-- 반응형 웹에 사용하는 메타태그 -->
<link rel="stylesheet" href="css/yh/bootstrap.css"> <!-- 참조  -->
<link rel="stylesheet" href="css/yh/custom.css"> <!-- 참조  -->
<title>Kim's Board</title>
</head>
<body>
<jsp:include page="/WEB-INF/views/yh/include/header.jsp"></jsp:include>
    
    
    <div class="container">
        <div class="jumbotron">
            <div class="container">
                <h1>Kim's Board</h1>
                <p>이 웹 사이트는 자유로운 회원 게시판입니다.</p>
                <p><a class="btn btn-primary btn-pull" href="#" role="button">자세히 알아보기</a></p>
            </div>
        </div>
    </div>
    <div class="container">
        <div id="myCarousel" class="carousel slide" data-ride="carousel">
            <ol class="carousel-indicators">
                <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
                <li data-target="#myCarousel" data-slide-to="1"></li>
                <li data-target="#myCarousel" data-slide-to="2"></li>
            </ol>
            <div class="carousel-inner">
                <div class="item active">
                    <img src="images/yh/1.jpg">
                </div>
                <div class="item">
                    <img src="images/yh/2.jpg">
                </div>
                <div class="item">
                    <img src="images/yh/3.jpg">
                </div>
            </div>
            <a class="left carousel-control" href="#myCarousel" data-slide="prev">
                <span class="glyphicon glyphicon-chevron-left"></span>
            </a>
            <a class="right carousel-control" href="#myCarousel" data-slide="next">
                <span class="glyphicon glyphicon-chevron-right"></span>
            </a>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="js/yh/bootstrap.js"></script>
</body>
</html>
