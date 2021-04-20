<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
       
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
                            <a href="./index.jsp"><img src="images/new_team1_logo.png" alt=""></a>
                        </div>
                    </div>
                    <div class="col-lg-7">
                    </div>
                    <div class="col-lg-2">
                    	<div class="ht-widget">
	                    	Login ID : <%=session.getAttribute("userid") %>
                    	</div>
                    </div>
					<div class="col-lg-1">
						<div class="ht-widget">
							<a href="Login.team1" class="btn btn-light">Login</a>
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
                                <li class="active"><a href="./index.html">Home</a></li>
                                <li><a href="#">SJ Board</a></li>
                                <li><a href="#">Joohyun's minihomepy</a></li>
                                <li><a href="#">Kim's Board</a></li>
                            </ul>
                        </nav>
                    </div>

                </div>
            </div>
        </div>

    </header>