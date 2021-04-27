<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

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
                <li class="active"><a href="index.team1">메인</a></li>
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
            <c:if test="${not empty userInfo}">
            <li style="padding-top: 15px;">${sessionScope.userInfo.userId}님 환영합니다&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
            </c:if>
                <li class="dropdown">
                <a href="#" class = "dropdown-toggle"
                    data-toggle="dropdown" role ="button" aria-haspopup="true"
                    aria-expanded="false">회원관리<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="Login.team1">로그아웃</a></li>
                    </ul>
                </li>
            </ul>
        </div>
    </nav>