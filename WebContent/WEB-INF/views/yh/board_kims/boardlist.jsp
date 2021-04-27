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
<title>Kim's Board</title>
</head>
<body>
	<c:set var="pagesize" value="${requestScope.pagesize}" />
	<c:set var="cpage" value="${requestScope.cpage}" />
	<c:set var="pagecount" value="${requestScope.pagecount}" />
	<c:set var="list" value="${requestScope.list}" />
	<c:set var="totalboardcount" value="${requestScope.totalboardcount}" />
	<c:set var="pager" value="${requestScope.pager}" />
	<c:set var="board" value="${requestScope.kimsboard}" />

	<jsp:include page="/WEB-INF/views/yh/include/header.jsp"></jsp:include>
    
    
	<!-- 페이지설정 -->
		<div style="padding-top: 10px; text-align: cetner">
			<table width="76%" border="0" cellspacing="0" align="center">
				<tr>
					<td colspan="5">
						<form name="list">
							PageSize설정: <select name="ps" onchange="submit()">
								<c:forEach var="i" begin="5" end="20" step="5">
									<c:choose>
										<c:when test="${pagesize == i}">
											<option value="${i}" selected>${i}건</option>
										</c:when>
										<c:otherwise>
											<option value="${i}">${i}건</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</form>
					</td>
				</tr>
			</table>
		</div>
	<!-- 여기까지 -->
	
	<div class="container">
        <div class="row">
            <table class="table table-striped" style="text-align:center; border:1px solid #dddddd">
                <thead>
                    <tr>
                        <th style="background-color:#eeeeee; text-align:center;">번호</th>
                        <th style="background-color:#eeeeee; text-align:center;">제목</th>
                        <th style="background-color:#eeeeee; text-align:center;">작성자</th>
                        <th style="background-color:#eeeeee; text-align:center;">작성일</th>
                        <th style="background-color:#eeeeee; text-align:center;">조회수</th>
                    </tr>
                </thead>
                
                <!-- forEach()  목록 출력하기  -->
                <tbody>
                <c:forEach var="board" items="${list}">
                    <tr>
	                    <td>${board.rownum}</td>
	                    <td><a href="kimsboardcontent.kims?idx=${board.idx}&cp=${cpage}&ps=${pagesize}">${board.subject}</td>
	                    <td>${board.userid_fk}</td>
	                    <td>${board.writedate}</td>
	                    <td>${board.readnum}</td>
                    </tr>
                </c:forEach>
                </tbody>
                
                <!-- forEach()  -->
				<tr>
					<td colspan="3" align="center">
						<c:if test="${cpage > 1}">
							<a href="kimslist.kims?cp=${cpage-1}&ps=${pagesize}">이전</a>
						</c:if>
						<!-- page 목록 나열하기 -->
						<c:forEach var="i" begin="1" end="${pagecount}" step="1">
							<c:choose>
								<c:when test="${cpage==i}">
										<font color="red" >[${i}]</font>
								</c:when>
								<c:otherwise>
									<a href="kimslist.kims?cp=${i}&ps=${pagesize}">[${i}]</a>
								</c:otherwise>
							</c:choose>
						</c:forEach>
						
						<!--다음 링크 --> 
						<c:if test="${cpage < pagecount}">
							<a href="kimslist.kims?cp=${cpage+1}&ps=${pagesize}">다음</a>
						</c:if>
					</td>
					<td colspan="2" align="center">총 게시물 수 : ${totalboardcount}
					</td>
				</tr>
				
            </table>
            <a href="${pageContext.request.contextPath}/kimswrite.kims" class="btn btn-primary pull-right">글쓰기</a>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="js/yh/bootstrap.js"></script>
</body>
</html>
