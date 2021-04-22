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
<!-- <link rel="Stylesheet" href="./css/hsj_style/default.css"> -->
<link rel="Stylesheet" href="css/hsj_style/default.css">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6" crossorigin="anonymous">
</head>
<body>
	<%-- <c:import url="/WEB-INF/views/sj/header_hsj.jsp"/> --%>
	<jsp:include page="/WEB-INF/views/sj/header_hsj.jsp"></jsp:include>


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
				<!-- 데이터가 한건도 없는 경우  -->

				<!-- forEach()  목록 출력하기  -->
				<tbody>
				<c:forEach var="board" items="${list}">
					<tr>
						<td>${board.idx}</td>
						<td>${board.nickname}</td>
						<td>${board.subject}</td>
						<td>${board.readnum}</td>
						<td>${board.up}</td>
						<td>${board.down}</td>
						<td>${board.writedate}</td>
					</tr>
					
				</c:forEach>
				<!-- forEach()  -->
				<!-- <tr>
					<td colspan="5" align="center">
						<hr width="100%" color="red">
					</td>
				</tr> -->
				
				
			<%-- 	<tr>
					<td colspan="3" align="center">
					<!--  
					원칙적인 방법 아래 처럼 구현
					[1][2][3][다음]
					[이전][4][5][6][다음]
					[이전][7][8][9][다음]
					[이전][10][11]
					
					현재 아래 코드 [][][][][][][]...
					-->
					
						<!--이전 링크 --> 
						<c:if test="${cpage > 1}">
							<a href="HumorList.hsj?cp=${cpage-1}&ps=${pagesize}">이전</a>
						</c:if>
						<!-- page 목록 나열하기 -->
						<c:forEach var="i" begin="1" end="${pagecount}" step="1">
							<c:choose>
								<c:when test="${cpage==i}">
										<font color="red" >[${i}]</font>
								</c:when>
								<c:otherwise>
									<a href="HumorList.hsj?cp=${i}&ps=${pagesize}">[${i}]</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						<!--다음 링크 --> 
						<c:if test="${cpage < pagecount}">
							<a href="HumorList.hsj?cp=${cpage+1}&ps=${pagesize}">다음</a>
						</c:if>
					</td>
					<td colspan="2" align="center">총 게시물 수 : ${totalboardcount}
					</td>
				</tr> --%>
				<tr>
			<%-- 		<td colspan="5" align="center">

					${pager}
					</td> --%>
			</tbody>
			
			</table>
		</div>
	</div>
	<div>
		<ul class="pagination justify-content-center">
		<li><a href="#" style="margin-right:5px;" class="text-secondary">  ⬅️ </a></li>
		<li><a href="#" style="margin-right:5px;" class="text-secondary">  1  </a></li>
		<li><a href="#" style="margin-right:5px;" class="text-secondary">  2  </a></li>
		<li><a href="#" style="margin-right:5px;" class="text-secondary">  3  </a></li>
		<li><a href="#" style="margin-right:5px;" class="text-secondary">  4  </a></li>
		<li><a href="#" style="margin-right:5px;" class="text-secondary">  5  </a></li>
		<li><a href="#" style="margin-right:5px;" class="text-secondary">  ➡️ ️</a></li>
		
		</ul>
		 <a class="btn btn-outline-info pull-right" href="${pageContext.request.contextPath}/HumorWrite.sj">글쓰기</a>
	</div>
	</div>
	
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js" integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf" crossorigin="anonymous"></script>
<!-- 	<script src="js/jquery-3.1.1.js"></script>	
<script src="js/bootstrap.js"></script>
	 -->
	 <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js" integrity="sha384-SR1sx49pcuLnqZUnnPwx6FCym0wLsk5JZuNx2bPPENzswTNFaQU1RDvt3wT4gWFG" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js" integrity="sha384-j0CNLUeiqtyaRmlzUHCPZ+Gy5fQu0dQ6eZ/xAww941Ai1SxSY+0EQqNXNE6DZiVc" crossorigin="anonymous"></script>
</body>
</html>