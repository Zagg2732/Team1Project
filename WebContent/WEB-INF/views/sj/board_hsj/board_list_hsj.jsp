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
<title>Insert title here</title>
<link rel="Stylesheet" href="./css/hsj_style/default.css">
</head>
<body>
	<c:import url="/WEB-INF/views/sj/header_hsj.jsp"/>


	<!-- requestScope사용 가능한 이유??  -->
	<!-- request.setAttribute를 통해서 저장한 값이 forward 되어서 사용할수 있는거임  -->
	<c:set var="pagesize" value="${requestScope.pagesize}" />
	<!--request.getParameter()랑 같은거라고 생각하자  -->
	<c:set var="cpage" value="${requestScope.cpage}" />
	<c:set var="pagecount" value="${requestScope.pagecount}" />
	<c:set var="list" value="${requestScope.list}" />
	<c:set var="totalboardcount" value="${requestScope.totalboardcount}" />
	<c:set var="pager" value="${requestScope.pager}" />



<div id="pagecontainer">
		<div style="padding-top: 30px; text-align: cetner">
			<table width="80%" border="1" cellspacing="0" align="center">
				<tr>
					<td colspan="5">
						<!--  
							form 태그 action 전송 주소(목적지) >> submit()
							>> form name="list" ... action 없다면.. 
							>> [현재 URL 창에 있는 주소] 그대로  .....   
							>> board_list.jsp?ps=select 태그 값으로 .... 다시 호출 .....
							>>http://192.168.0.169:8090/WebServlet_5_Board_Model1_Sample/board/board_list.jsp?ps=10					
						-->
						<form name="list" >
						묶음보기 
							<select name="ps" onchange="submit()">
							   <c:forEach var="i" begin="5" end="20" step="5">
							   		<c:choose>
							   			<c:when test="${pagesize == i}">
							   				<option value="${i}" selected>${i}건</option>
							   			</c:when>
						   				<c:otherwise>
						   					<option value="${i}">${i}건 </option>
						   				</c:otherwise>
							   		</c:choose>
							   </c:forEach>
		   					</select>
						</form>
					</td>
				</tr>
				
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
					<tr onmouseover="this.style.backgroundColor='gray'" onmouseout="this.style.backgroundColor='white'">
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
				<tr>
					<td colspan="5" align="center">
						<hr width="100%" color="red">
					</td>
				</tr>
				<tr>
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
				</tr>
				<tr>
					<td colspan="5" align="center">

					${pager}
					</td>
			</tbody>
			</table>
		</div>
	</div>
</body>
</html>