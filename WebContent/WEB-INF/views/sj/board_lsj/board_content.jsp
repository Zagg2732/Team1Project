<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>승준이 보드컨텐츠</title>
	<style>
		 a:link { color: red; text-decoration: none;}
		 a:visited { color: black; text-decoration: none;}
		 a:hover { color: blue; text-decoration: underline;}
	</style>

	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<h3>승준이 보드컨텐츠에요</h3><br>
	<h3>이곳에서는 게시글 목록이 보일 예정이랍니다 ^^</h3>
	<p>[더보기]</p>
	<p>=================list=================================</p>
	<p>${requestScope.list}</p>
	<p>==========================================================</p>
	<table width = "80%" border = "1" cellspacing = "0" align="center">
		<tr>
			<th>순번</th>
			<th>제목</th>
			<th>글쓴이</th>
			<th>날짜</th>
			<th>조회수</th>
			<th>추천</th>
			<th>비추천</th>
		</tr>
		<!-- 해당 게시글 목록은 유머게시판이라고 가정하고 GET으로 유머게시판 이름을 가지고갑니다. -->
		<c:forEach var="board" items = "${requestScope.list}">
			<tr class="boardlist" onmouseover="this.style.backgroundColor='gray'" onmouseout="this.style.backgroundColor='white'">
				<td>${board.idx}</td>
				<td><a href = "humorContent.lsj?idx=${board.idx}&type=humor_board">${board.subject}</td>
				<!-- 링크에 idx 태워서감 -->
				<td>${board.nickname}</td>
				<td>${board.writedate}</td>
				<td>${board.readnum}</td>
				<td>${board.up}</td>
				<td>${board.down}</td>
			</tr>
		</c:forEach>
	</table>
	
	
	
	
</body>
<script type="text/javascript">
	$(function(){
		$('.boardlist').click(function(){
			console.log('clicked!');
		});
	});
</script>

</html>