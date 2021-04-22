<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>    
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>	
	<style>
		 a:link { color: black; text-decoration: none;}
		 a:visited { color: black; text-decoration: none;}
		 a:hover { color: black; text-decoration: none;}
	</style>

<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
</head>
<body>
<div class="row at-row">
<div class="col-md-9 at-col at-main">
<div class="col-sm-6" style="float: left;">
 <table class="table table-hover">
	<thead>
		<tr>
			<th><h3>공지게시판</h3></th>
		
		</tr>
		
	</thead>
	<tbody>
		<c:forEach var="board" items = "${requestScope.noticelist}">
			<tr class="boardlist" onmouseover="this.style.backgroundColor='gray'" onmouseout="this.style.backgroundColor='white'">
				<td><a href = "board.sj?idx=${board.idx}&type=notice_board">${board.subject}</td>
				<td>닉네임 : ${board.nickname}</td>
				<td>추천 : ${board.up}</td>
				<td>비추천 : ${board.down}</td>
			</tr>
			<br>
		</c:forEach>
	</tbody>
</table>
<hr/>
</div>

<div class="col-sm-6" style="float: left;">
 <table class="table table-hover">
	<thead>
		<tr>
	<th><h3>유머게시판</h3></th>
		
		</tr>
	</thead>
	<tbody>
		<c:forEach var="board" items = "${requestScope.humorlist}">
			<tr class="boardlist" onmouseover="this.style.backgroundColor='gray'" onmouseout="this.style.backgroundColor='white'">
				<td><a href = "board.sj?idx=${board.idx}&type=humor_board">${board.subject}</td>
				<td>닉네임 : ${board.nickname}</td>
				<td>추천 : ${board.up}</td>
				<td>비추천 : ${board.down}</td>
			</tr>
			<br>
		</c:forEach>
	</tbody>
</table>
<hr/>

<!-- <a class="btn btn-default pull-right">글쓰기</a> -->

<!-- <div class="text-center">
	<ul class="pagination">
		<li><a href="#">1</a></li>
		<li><a href="#">2</a></li>
		<li><a href="#">3</a></li>
		<li><a href="#">4</a></li>
		<li><a href="#">5</a></li>

	</ul>

</div> -->

</div>

</div>
<div class="col-sm-2">
<div class="hidden-sm hidden-xs">
<table class="table table-hover">
	<thead>
		<tr>
			<th>Profile</th>
		
		</tr>
	</thead>
	</table>
</div>
</div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js" integrity="sha384-SR1sx49pcuLnqZUnnPwx6FCym0wLsk5JZuNx2bPPENzswTNFaQU1RDvt3wT4gWFG" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js" integrity="sha384-j0CNLUeiqtyaRmlzUHCPZ+Gy5fQu0dQ6eZ/xAww941Ai1SxSY+0EQqNXNE6DZiVc" crossorigin="anonymous"></script>
<!-- 
<script src="js/jquery-3.1.1.js"></script>	
<script src="js/bootstrap.js"></script> -->
</body>
</html>