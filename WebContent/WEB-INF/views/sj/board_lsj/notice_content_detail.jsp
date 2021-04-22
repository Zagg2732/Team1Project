<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>board_content</title>
	<style>
		 a:link { color: black; text-decoration: none;}
		 a:visited { color: black; text-decoration: none;}
		 a:hover { color: black; text-decoration: none;}
	</style>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>
	<h3>뭘봐? 디테일한 게시판 첨바?</h3>
	<h3>닉네임 : ${board.nickname}</h3>
	<h3>조회수 : ${board.readnum}</h3>
	<h3>글번호 : ${requestScope.idx}</h3>
	<h3>추천 : ${board.up}</h3>
	<h3>비추천 : ${board.down}</h3>
	<h3>내용 : ${board.content}</h3>
	<h3>작성일 : ${board.writedate}</h3>
	<br><br><br><br><br><br><br><br><br>
	
	<h3>댓글 정보들</h3>
	<c:forEach var="list" items="${requestScope.replyList}">
	<form>
		<tr class="boardlist" onmouseover="this.style.backgroundColor='gray'" onmouseout="this.style.backgroundColor='white'">
			<td colspan="2" align="center">댓글 닉네임 : ${list.nickname}</td>
			<td colspan="2" align="center">댓글 내용 : ${list.content}</td>
			<td colspan="2" align="center">댓글 작성일 : ${list.writedate}</td>
			<td colspan="2" align="center">댓글 추천수 : ${list.up}</td>
			<td colspan="2" align="center">댓글 비추수 : ${list.down}</td>
		</tr>
		<br>
	</form>
	</c:forEach>
	
</body>
</html>