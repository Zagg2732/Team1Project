<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%-- <div id="header">
            <div class="title">
                <a href="${pageContext.request.contextPath}">SJ게시판</a>
            </div>
            <div class="links">
                <a href="javscript:void(0);">${pageContext.request.contextPath}</a>
                <a href="javscript:void(0);">뭘넣을까</a>
                <a href="javscript:void(0);">뭘넣어야할까</a>

            </div>
        </div>    --%>
        
<head>
	<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/@fortawesome/fontawesome-free@5.15.3/css/fontawesome.min.css">
</head>


<!-- PC Header -->
<header class="pc-header">
		<!-- PC Logo -->
		<div class="header-logo">
			<a href="index.sj">
				<!-- 로고 누르면 홈화면 이동  --> <img src="images/hsj/logo.png" alt="" />
			</a>
		</div>	
</header>



<div id="menu" style="text-align: center;">
	<div>
		<ul>
			<!-- <li><a href="index.jsp" class="fa fa-home aria-hidden="true">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li> -->
			
			
			<!-- a : 페이지 이동을 처리하는 마크업 -->
			<li><a href="index.jsp">
			 Home</a></li>
			<li><a href="boardList.sj?type=humor_board"> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			 Humor &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
			<!-- HumorList.hsj ㄱㄱ  -->
			<li><a href="boardList.sj?type=notice_board">Notice &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
			<!--NoticeList.hsj ㄱㄱ  -->
			<li><a href="Login.team1">Login(임시) &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
			<!--NoticeList.hsj ㄱㄱ  -->
			<li><a href="EditProfile.team1">MyPage &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
			
			
			<li><a href="javscript:void(0);"></a></li>
			<li><a href="javscript:void(0);"></a></li>
		</ul>
	</div>
</div>
	<br>
	<br>

