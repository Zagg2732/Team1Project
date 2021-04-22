<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>
</head>
	<meta charset="UTF-8">
	<title>승준이 보드컨텐츠</title>
	<style>
		 a:link { color: black; text-decoration: none;}
		 a:visited { color: black; text-decoration: none;}
		 a:hover { color: black; text-decoration: none;}
	</style>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<body>
	<h3>첫화면</h3>
	<h1>공지사항</h1>
	<div>
		<c:forEach var="board" items = "${requestScope.noticelist}">
			<tr class="boardlist" onmouseover="this.style.backgroundColor='gray'" onmouseout="this.style.backgroundColor='white'">
				<td>글번호 : ${board.idx}</td>
				<td><a href = "board.lsj?idx=${board.idx}&type=notice_board">${board.subject}</td>
				<!-- 링크에 idx 태워서감 -->
				<td>닉네임 : ${board.nickname}</td>
				<td>추천 : ${board.up}</td>
				<td>비추천 : ${board.down}</td>
			</tr>
			<br>
		</c:forEach>
	</div>
	<h1>유머게시판</h1>
	<div>
		<c:forEach var="board" items = "${requestScope.humorlist}">
			<tr class="boardlist" onmouseover="this.style.backgroundColor='gray'" onmouseout="this.style.backgroundColor='white'">
				<td>글번호 : ${board.idx}</td>
				<td><a href = "board.lsj?idx=${board.idx}&type=humor_board">${board.subject}</td>
				<!-- 링크에 idx 태워서감 -->
				<td>닉네임 : ${board.nickname}</td>
				<td>추천 : ${board.up}</td>
				<td>비추천 : ${board.down}</td>
			</tr>
			<br>
		</c:forEach>
	</div>
</body>
</html>