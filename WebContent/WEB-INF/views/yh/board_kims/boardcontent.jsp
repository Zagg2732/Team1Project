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
<title>JSP 게시판 웹 사이트</title>
</head>
<body>

	<c:set var="board" value="${requestScope.kimsboard}" />
	<c:set var="idx" value="${requestScope.idx}" />
	<c:set var="cpage" value="${requestScope.cp}" />
	<c:set var="pagesize" value="${requestScope.ps}" />
	<c:set var="userInfo" value="${sessionScope.userInfo}" />
	
    <nav class ="navbar navbar-default">
        <div class="navbar-header"> <!-- 홈페이지의 로고 -->
            <button type="button" class="navbar-toggle collapsed"
                data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
                aria-expand="false">
                <span class ="icon-bar"></span> <!-- 줄였을때 옆에 짝대기 -->
                <span class ="icon-bar"></span>
                <span class ="icon-bar"></span>
            </button>
            <a class ="navbar-brand" href="indexyh.jsp">JSP 게시판 웹 사이트</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li class="active"><a href="indexyh.jsp">메인</a></li>
                <li><a href="${pageContext.request.contextPath}/kimslist.kims">게시판</a></li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
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

            <ul class="nav navbar-nav navbar-right">
                <li>${sessionScope.userInfo.userId}님 환영합니다&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
                <li class="dropdown">
                <a href="#" class = "dropdown-toggle"
                    data-toggle="dropdown" role ="button" aria-haspopup="true"
                    aria-expanded="false">회원관리<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="loginAction.jsp">로그아웃</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>
    
    
    <div class="container">
        <div class="row">
            <table class="table table-striped" style="text-align:center; border:1px solid #dddddd">
                <thead>
                    <tr>
                        <th colspan="3" style="background-color:#eeeeee; text-align:center;">게시판 글 보기</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                        <td style="width:20%;">글 제목</td>
                        <td colspan="2">${board.subject}</td>
                    </tr>
                    <tr>
                        <td>작성자</td>
                        <td colspan="2">${board.userid_fk}</td>
                    </tr>
                    <tr>
                        <td>작성일자</td>
                        <td colspan="2">${board.writedate}</td>
                    </tr>
                    <tr>
                        <td>내용</td>
                        <td colspan="2" style="min-height:200px; text-align:left;">
                        ${board.content}
                        </td>
                        <!-- 특수문자를 제대로 출력하기위해 & 악성스크립트를 방지하기위해 -->
                    </tr>
                </tbody>
            </table>
            <a href="${pageContext.request.contextPath}/kimslist.kims" class="btn btn-primary">목록</a>
            
            <!-- 수정 삭제버튼 -->
<%--                 <a href="update.jsp?bbsID=<%=bbsID %>" class="btn btn-primary">수정</a>
                <a href="deleteAction.jsp?bbsID=<%=bbsID %>" class="btn btn-primary">삭제</a> --%>
                
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="js/yh/bootstrap.js"></script>
</body>
</html>
