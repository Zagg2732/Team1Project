<%@page import="java.util.List"%>
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
			<span style="font-size: 12px;">http://www.jhmini.com</span>
		  </div>
		  <div class="content-box" style="box-shadow: 5px 5px 20px -10px grey; padding: 10px 20px;">
			<div style="display: flex; font-size: 14px;">				
			  <div class="col" style="display: flex; flex-direction: column;">
			  
				<c:set var="pagesize" value="${requestScope.pagesize}" />
				<c:set var="cpage" value="${requestScope.cpage}" />
				<c:set var="pagecount" value="${requestScope.pagecount}" />
				<c:set var="list" value="${requestScope.list}" />
				<c:set var="totaldiarycount" value="${requestScope.totaldiarycount}" />
				<c:set var="pager" value="${requestScope.pager}" />
					
				<table class="table">
		  		<thead>
		  			<tr>
		  				<th>글번호</th>
		  				<th>제목</th>
		  				<th>날짜</th>
		  				<th>조회수</th>
		  			</tr>
		  		</thead>
		  		<tbody>
		  			<c:forEach var="diaryContents" items="${list}">
		  				<tr>
		  					<td>${diaryContents.idx}</td>
			  				<td>${diaryContents.subject}</td>
			  				<td>${diaryContents.writedate}</td>
			  				<td>${diaryContents.readnum}</td>
		  				</tr>
		  			</c:forEach>
		  			
		  			<tr>
		  			<td colspan="3" align="center">
		  			<!-- 이전 링크 -->
		  			<c:if test="${cpage > 1}">
		  			<a href="diary.mini?cp=${cpage-1}&ps=${pagesize}">이전</a>
		  			</c:if>
		  			
		  			<!-- page 목록 나열하기 -->
		  			<c:forEach var="i" begin="1" end="${pagecount}" step="1">
		  				<c:choose>
							<c:when test="${cpage==i}">
									<font color="red" >[${i}]</font>
							</c:when>
							<c:otherwise>
								<a href="diary.mini?cp=${i}&ps=${pagesize}">[${i}]</a>
							</c:otherwise>
						</c:choose>
					</c:forEach>
					
					<!--다음 링크 --> 
					<c:if test="${cpage < pagecount}">
						<a href="diary.mini?cp=${cpage+1}&ps=${pagesize}">다음</a>
					</c:if>
					</td>
						<td colspan="2" align="center">총 게시물 수 : ${totaldiarycount}</td>
					</tr>
					
					<tr>
					<td colspan="5" align="center">
					${pager}
					</td>
		  		</tbody>
		  		</table>
			<div class="menu-item" onclick="handleClickMenu(this)">홈</div>
			<div class="menu-item menu-selected" style="top: 48px" onclick="handleClickMenu(this)">다이어리</div>
			<div class="menu-item" style="top: 86px" onclick="handleClickMenu(this)">방명록</div>
		  </div>
		</div>
	  </div>
	</div>
</body>
</html>