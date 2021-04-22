<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="userInfo" value="${sessionScope.userInfo}" />
       
		<div class="offcanvas-menu-overlay"></div>
		<div class="offcanvas-menu-wrapper">
			<div class="canvas-close">
				<span class="icon_close"></span>
			</div>
			<div class="logo">
				<a href="./index.jsp"> <img src="images/new_team1_logo.png" alt="">
				</a>
			</div>
			<div id="mobile-menu-wrap"></div>
		</div>

		<header class="header-section">
        <div class="hs-top">
            <div class="container">
                <div class="row">
                    <div class="col-lg-2">
                        <div class="logo">
                            <a href="./index.team1"><img src="images/new_team1_logo.png" alt=""></a>
                        </div>
                    </div>
				<div class="col-lg-2">
					<div class="ht-widget">
						<c:if test="${userInfo.grade > 0}">
							<a href="temp.team1" class="btn btn-dark"><i class="fas fa-cog"></i> admin</a>
						</c:if>
					</div>
				</div>
				<div class="col-lg-4">
                    </div>
                    <div class="col-lg-3">
                    	<div class="ht-widget">
	                    	NickName : ${userInfo.nickName}
                    	</div>
                    </div>
					<div class="col-lg-1">
					<div class="ht-widget">

						<c:choose>
							<c:when test="${not empty userInfo}">
								<a href="Logout.team1" class="btn btn-dark">Logout</a>
							</c:when>
							<c:otherwise>
								<a href="Login.team1" class="btn btn-dark">Login</a>
							</c:otherwise>
						</c:choose>

					</div>
				</div>

                </div>
                <div class="canvas-open">
                    <span class="icon_menu"><i class="fas fa-bars"></i></span>
                </div>
            </div>
        </div>
        <div class="hs-nav">
            <div class="container">
                <div class="row">
                    <div class="col-lg-9">
                        <nav class="nav-menu">
                            <ul>
                                <li class="active"><a href="${pageContext.request.contextPath}/index.team1">Home</a></li>
                                <li><a href="${pageContext.request.contextPath}/temp_index_hsj.jsp">SJ Board</a></li>
                                <li><a href="javscript:void(0);" onclick="window.open('home.jh','minihomepy','width=1200,height=650,location=no,status=no,scrollbars=yes');">Joohyun's minihomepy</a></li>
                                <li><a href="${pageContext.request.contextPath}/indexyh.jsp">Kim's Board</a></li>
                            </ul>
                        </nav>
                    </div>

                </div>
            </div>
        </div>

    </header>