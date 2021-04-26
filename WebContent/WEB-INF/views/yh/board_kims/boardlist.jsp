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

<%
    String userid = null; // 로그인이 된 사람들은 로그인정보를 담을 수 있도록한다
    if (session.getAttribute("userid") != null)
    {
    	userid = (String)session.getAttribute("userid");
    }
%>



    <nav class ="navbar navbar-default">
        <div class="navbar-header"> <!-- 홈페이지의 로고 -->
            <button type="button" class="navbar-toggle collapsed"
                data-toggle="collapse" data-target="#bs-example-navbar-collapse-1"
                aria-expand="false">
                <span class ="icon-bar"></span> <!-- 줄였을때 옆에 짝대기 -->
                <span class ="icon-bar"></span>
                <span class ="icon-bar"></span>
            </button>
            <a class ="navbar-brand" href="indexyh.jsp">Kim's Board</a>
        </div>
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="indexyh.jsp">메인</a></li>
                <li class="active"><a href="boardlist.jsp">게시판</a></li>
            </ul>
            <%
            // 접속하기는 로그인이 되어있지 않은 경우만 나오게한다
                if(userid == null)
                {
            %>
            <ul class="nav navbar-nav navbar-right">
            <li>${sessionScope.userInfo.userId}님 환영합니다&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
                <li class="dropdown">
                <a href="#" class = "dropdown-toggle"
                    data-toggle="dropdown" role ="button" aria-haspopup="true"
                    aria-expanded="false">접속하기<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="Login.team1">로그인</a></li>
                        <li><a href="${pageContext.request.contextPath}/Register.team1">회원가입</a></li>                    
                    </ul>
                </li>
            </ul>
            <%
            // 로그인이 되어있는 사람만 볼수 있는 화면
                } else {
            %>
            <ul class="nav navbar-nav navbar-right">
                <li class="dropdown">
                <a href="#" class = "dropdown-toggle"
                    data-toggle="dropdown" role ="button" aria-haspopup="true"
                    aria-expanded="false">회원관리<span class="caret"></span></a>
                    <ul class="dropdown-menu">
                        <li><a href="Login.team1">로그아웃</a></li>
                    </ul>
                </li>
            </ul>
            <%
                }
            %>
        </div>
    </nav>
	<!-- 페이지설정 -->
		<div style="padding-top: 10px; text-align: cetner">
			<table width="76%" border="0" cellspacing="0" align="center">
				<tr>
					<td colspan="5">
						<!--  
							form 태그 action 전송 주소(목적지) >> submit()
							>> form name="list" ... action 없다면.. 
							>> [현재 URL 창에 있는 주소] 그대로  .....   
							>> board_list.jsp?ps=select 태그 값으로 .... 다시 호출 .....
							>>http://192.168.0.169:8090/WebServlet_5_Board_Model1_Sample/board/board_list.jsp?ps=10					
						-->
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
	                    <td><a href="boardcontent.kims?idx=${board.idx}">${board.subject}</td>
	                    <td>${board.userid_fk}</td>
	                    <td>${board.writedate}</td>
	                    <td>${board.readnum}</td>
                    </tr>
                </c:forEach>
                </tbody>
                
                <!-- forEach()  -->
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
<%-- 				<tr>
					<td colspan="5" align="center">
					${pager} &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; 총 게시물 수 : ${totalboardcount}
					</td>
				</tr> --%>
            </table>
            <a href="${pageContext.request.contextPath}/kimswrite.kims" class="btn btn-primary pull-right">글쓰기</a>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="js/yh/bootstrap.js"></script>
</body>
</html>
