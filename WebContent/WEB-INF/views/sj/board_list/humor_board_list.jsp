<%@page import="com.team1.utils.ThePager"%>
<%@page import="java.util.List"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
	<meta charset=UTF-8">
	<title>유머게시판</title>
	<style>
		 a:link { color: black; text-decoration: none;}
		 a:visited { color: black; text-decoration: none;}
		 a:hover { color: black; text-decoration: none;}
	</style>
<!-- <link rel="Stylesheet" href="./css/hsj_style/default.css"> -->
<link rel="Stylesheet" href="css/hsj_style/default.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">
</head>
<body>
	<%-- <c:import url="/WEB-INF/views/sj/header_hsj.jsp"/> --%>
	<jsp:include page="/WEB-INF/views/sj/include/header_sj.jsp"></jsp:include>


	<!-- requestScope사용 가능한 이유??  -->
	<!-- request.setAttribute를 통해서 저장한 값이 forward 되어서 사용할수 있는거임  -->
	<c:set var="pagesize" value="${requestScope.pagesize}" />
	<!--request.getParameter()랑 같은거라고 생각하자  -->
	<c:set var="cpage" value="${requestScope.cpage}" />
	<c:set var="pagecount" value="${requestScope.pagecount}" />
	<c:set var="list" value="${requestScope.list}" />
	<c:set var="totalboardcount" value="${requestScope.totalboardcount}" />
	<c:set var="pager" value="${requestScope.pager}" />


	<div class="container">
		<div class="list-board">
		
		<!-- 시이작  -->
			<div class="list-head div-head">
				<table class="table table-hover table-striped text-center">
					<tr>
						<th>번호</th>
						<th>닉네임</th>
						<th>제목</th>
						<th>조회</th>
						<th>추천</th>
						<th>비추천</th>
						<th>날짜</th>

					</tr>

					<!-- forEach()  목록 출력하기  -->
					<tbody>
						<c:forEach var="board" items="${list}">
							<tr>
								<td>${board.idx}</td>
								<td>${board.nickname}</td>
								<td><a href = "board.sj?idx=${board.idx}&type=humor_board">${board.subject}</td>
								<td>${board.readnum}</td>
								<td>${board.up}</td>
								<td>${board.down}</td>
								<td>${board.writedate}</td>
							</tr>

						</c:forEach>


						<tr>
							<td colspan="9" align="center"> <!--페이징 처리 가운데 정렬  -->
			
					 <!--이전 링크 -->
					  <c:if test="${cpage > 1}">
						<a href="BoardList.sj?type=humor_board&cp=${cpage-1}&ps=${pagesize}">이전</a>
						</c:if> 
								
								
								
					<!-- page 목록 나열하기 --> 
					<c:forEach var="i" begin="1"
									end="${pagecount}" step="1">
									<c:choose>
										<c:when test="${cpage==i}">
											<font color="red">[${i}]</font>
										</c:when>
										<c:otherwise>
											<a href="BoardList.sj?type=humor_board&cp=${i}&ps=${pagesize}">[${i}]</a>
										</c:otherwise>
									</c:choose>
					</c:forEach> 
								
								
								<!--다음 링크 --> 
								<c:if test="${cpage < pagecount}">
									<a href="BoardList.sj?type=humor_board&cp=${cpage+1}&ps=${pagesize}">다음</a>
								</c:if>
							</td>
						</tr>
					</tbody>
				</table>
			</div> <!-- 끝  -->
			
			
			
			<a class="btn btn-outline-info">글쓰기</a>
		</div>

	</div>

	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
		crossorigin="anonymous"></script>
	<!-- 	<script src="js/jquery-3.1.1.js"></script>	
<script src="js/bootstrap.js"></script>
	 -->
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"
		integrity="sha384-SR1sx49pcuLnqZUnnPwx6FCym0wLsk5JZuNx2bPPENzswTNFaQU1RDvt3wT4gWFG"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js"
		integrity="sha384-j0CNLUeiqtyaRmlzUHCPZ+Gy5fQu0dQ6eZ/xAww941Ai1SxSY+0EQqNXNE6DZiVc"
		crossorigin="anonymous"></script>
</body>
</html>