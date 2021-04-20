<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

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
		<div class="at-container">
			<!-- PC Logo -->
			
			<div class="header-logo">
			
				<a href="/">
					<img src="images/hsj/logo.png" alt="" style="width: 300px; text-align: center;" />
				</a>
				
			</div>
			<!-- PC Search -->
			<div class="header-search">
				<form name="tsearch" method="get" onsubmit="return tsearch_submit(this);" role="form" class="form">
				<input type="hidden" name="url"	value="https://gezip.net/bbs/search.php">
					<div class="input-group input-group-sm">
						<span class="input-group-btn">
						</span>
					</div>
				</form>
				<div class="header-keyword">
					<div class="basic-keyword">
			<span class="stx">
						<a href="https://gezip.net/bbs/search.php?stx="></a>
		</span>
	</div>
				</div>
			</div>
			<div class="clearfix"></div>
		</div>
	</header>
	
	
	
        <div id="menu">
            <div>
                <ul>
                	<!-- a : 페이지 이동을 처리하는 마크업 -->
                    <li><a href="${pageContext.request.contextPath}/HumorList.hsj">유머 게시판</a></li> <!-- BoardList.do ㄱㄱ  -->
					<li><a href="${pageContext.request.contextPath}/NoticeList.hsj">공지 사항</a></li> <!--BoardWrite.do ㄱㄱ  -->
					<li><a href="javscript:void(0);"></a></li>
					<li><a href="javscript:void(0);"></a></li>
                </ul>
            </div>
        </div>
        <div style="text-align:right;margin-top:1px;
        	border:solid 1px;padding:5px">
			<!--admin님 안녕하십니까 넣기? -->
        </div>
        
        
        