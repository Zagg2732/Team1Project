<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="userInfo" value="${sessionScope.userInfo}" />

<link href="${pageContext.request.contextPath}/images/favicon.png" rel="icon">
<link href="${pageContext.request.contextPath}/images/apple-touch-icon.png" rel="apple-touch-icon">
       
		<div class="offcanvas-menu-overlay"></div>
		<div class="offcanvas-menu-wrapper">
			<div class="canvas-close">
				<span class="icon_close"></span>
			</div>
			<div class="logo">
				<a href="./index.jsp"> <img src="images/team1_logo1.png" alt="" style="height:45px;">
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
                            <a href="./index.team1"><img src="images/team1_logo1.png" alt="" style="height:45px;"></a>
                        </div>
                    </div>
				<div class="col-lg-2">
					<div class="ht-widget">
						<c:if test="${userInfo.grade > 0}">
							<a href="main.admin" class="btn btn-dark btn-sm"><i class="fas fa-cog"></i> admin</a>
						</c:if>
					</div>
				</div>
				<div class="col-lg-3">
                    </div>
                    <div class="col-lg-4">
                    	<div class="ht-widget">
	                    	
	                    	<c:if test="${not empty userInfo}">
	                    		<span style="font-size: 20px; padding-top: 10%;">
		                    		<a href="#" style="text-decoration: none; color: black;">
		                    			<i class="fas fa-user-circle"></i> ${userInfo.nickName}
		                    		</a>
	                    		</span>
	                    		<a href="EditProfile.team1" class="btn btn-dark btn-sm"><i class="fas fa-user-edit"></i></i> Edit Profile</a>
	                    	</c:if>


						</div>
                    </div>
					<div class="col-lg-1">
					<div class="ht-widget">

						<c:choose>
							<c:when test="${not empty userInfo}">
								<a href="Logout.team1" class="btn btn-dark btn-sm">Logout</a>
							</c:when>
							<c:otherwise>
								<a href="Login.team1" class="btn btn-dark btn-sm">Login</a>
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
                                <li class="active"><a href="index.team1">Home</a></li>
                                <li><a href="index.sj">SJ Board</a></li>
                                <li><a href="javscript:void(0);" onclick="window.open('home.jh','minihomepy','width=1200,height=650,location=no,status=no,scrollbars=yes');">Joohyun's minihomepy</a></li>
                                <li><a href="index.kims">Kim's Board</a></li>
                            </ul>
                        </nav>
                    </div>

                </div>
            </div>
        </div>

    </header>