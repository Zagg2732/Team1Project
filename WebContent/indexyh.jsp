<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
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

<!-- 세션정보 확인하기! 제이슨처럼 .으로 타고들어가면서 확인할 수 있다 -->
<h3>${sessionScope.userInfo}</h3>

<%
    String userid = null; // 로그인이 된 사람들은 로그인정보를 담을 수 있도록한다
    if (session.getAttribute("userid") != null)
    {
    	userid = (String)session.getAttribute("userid");
    }
%>
    <nav class ="navbar navbar-default">
        <div class="navbar-header"> <!-- 홈페이지의 로고 -->
            <button type="button" class="navbar-toggle collapsed"
                data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
                aria-expand="false">
                <span class ="icon-bar"></span> <!-- 줄였을때 옆에 짝대기 -->
                <span class ="icon-bar"></span>
                <span class ="icon-bar"></span>
            </button>
            <a class ="navbar-brand" href="indexyh.jsp">Kim's Board</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="indexyh.jsp">메인</a></li>
                <li><a href="${pageContext.request.contextPath}/kimslist.kims">게시판</a></li>
            </ul>
            <%
            // 접속하기는 로그인이 되어있지 않은 경우만 나오게한다
                if(userid == null)
                {
            %>
            <ul class="nav navbar-nav navbar-right">
            <li>${sessionScope.userInfo.userId}님 환영합니다&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
                <li class="dropdown">
                <a href="#" class = "dropdown-toggle"
                    data-toggle="dropdown" role ="button" aria-haspopup="true"
                    aria-expanded="false">접속하기<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="Login.team1">로그인</a></li>
                        <li><a href="${pageContext.request.contextPath}/Register.team1">회원가입</a></li>                    
                    </ul>
                </li>
            </ul>
            <%
            // 로그인이 되어있는 사람만 볼수 있는 화면
                } else {
            %>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                <a href="#" class = "dropdown-toggle"
                    data-toggle="dropdown" role ="button" aria-haspopup="true"
                    aria-expanded="false">회원관리<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="Login.team1">로그아웃</a></li>
                    </ul>
                </li>
            </ul>
            <%
                }
            %>
        </div>
    </nav>
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
                <li data-target="#myCarousel" data-slide-to="0"></li>
                <li data-target="#myCarousel" data-slide-to="0"></li>
            </ol>
            <div class="carousel-inner">
                <div class="item active">
                    <img src="images/1.jpg">
                </div>
                <div class="item">
                    <img src="images/2.jpg">
                </div>
                <div class="item">
                    <img src="images/3.jpg">
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
