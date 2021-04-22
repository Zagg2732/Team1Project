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
			<!-- a : 페이지 이동을 처리하는 마크업 -->
			<li><a href="HumorList.sj">유머게시판&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</a></li>
			<!-- HumorList.hsj ㄱㄱ  -->
			<li><a href="${pageContext.request.contextPath}/NoticeList.hsj">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;공지
					사항</a></li>
			<!--NoticeList.hsj ㄱㄱ  -->
			<li><a href="javscript:void(0);"></a></li>
			<li><a href="javscript:void(0);"></a></li>
		</ul>
	</div>
</div>
