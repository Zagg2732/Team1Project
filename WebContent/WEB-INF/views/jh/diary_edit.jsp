<%@page import="java.util.List"%>
<%@ page import="com.team1.sy.dto.Member"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>듀효니 미니홈피</title>
  	<link rel="stylesheet" href="./css/jh/main.css">
  	<!--  문제의 부트.. -->
  	<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css">
  	<script src="./js/jh/main.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
</head>
<body style="background-color: gray;">
	<div style="width:100%; max-width:900px;">
	  <div class="outer-box border-left-box" style="width: 27%; padding: 20px 0px 20px 20px">
		<div class="inner-box" style="padding: 10px 5px 10px 10px;">
		  <div class="content-box-header" style="justify-content: center">
			<span class="small-text">TODAY</span>&nbsp;<span style="color: red">999</span>
			&nbsp;&nbsp;|&nbsp;&nbsp;<span class="small-text">TOTAL</span>&nbsp;7979
		  </div>
		  <div class="content-box" style="padding: 20px">
		  </div>
		</div>
	  </div>
	  <div class="outer-box border-right-box" style="width: 72%; padding: 20px 20px 20px 0px">
		<div class="inner-box" style="padding: 10px 10px 10px 5px;">
		  <div class="content-box-header">
			<h1 style="margin:0px; font-size: 1.3em; font-weight: 550;" class="color-blue">&nbsp;&nbsp;듀효니 미니홈삐</h1>
			<span style="font-size: 12px;">URL</span>
		  </div>
		  <div class="content-box" style="box-shadow: 5px 5px 20px -10px grey; padding: 10px 20px;">
			<div style="display: flex; font-size: 14px;">				
			  <div class="col" style="display: flex; flex-direction: column;">
			  
			  	<c:set var="diary" value="${requestScope.diaryDto}" />
			  	<c:set var="idx" value="${requestScope.idx}" />
			 	<c:set var="cpage" value="${requestScope.cp}" />
				<c:set var="pagesize" value="${requestScope.ps}" />
				<c:set var="replyList" value="${requestScope.diaryReplyList}" />
				<c:set var="userInfo" value="${sessionScope.userInfo}" />
				<c:set var="pager" value="${requestScope.pager}" />
				
				<div class="container">
				<table class="table">
		  			<tr>
		  				<td>작성일</td>
		  				<td>${diary.writedate}</td>
		  			</tr>
		  			<tr>
		  				<td>작성자</td>
		  				<td>${diary.userid_fk}</td>
		  			</tr>
		  			<tr>
		  				<td>제목</td>
		  				<td>${diary.subject}</td>
		  			</tr>
		  			<tr>
		  				<td>내용</td>
		  				<td>${diary.content}</td>
		  			</tr>
				</table>

				</div>
			<div class="menu-item" onclick="location.href='home.jh';">홈</div>
			<div class="menu-item menu-selected" style="top: 48px" onclick="location.href='diary.jh';">다이어리</div>
			<div class="menu-item" style="top: 86px" onclick="location.href='guestBook.jh';">방명록</div>
		  </div>
		</div>
	  </div>
	</div>
</body>
<script type="text/javascript">

	
</script>
</html>
