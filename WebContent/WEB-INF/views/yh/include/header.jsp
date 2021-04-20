<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

		<div id="header">
            <div class="title">
                <a href="${pageContext.request.contextPath}">DEMO WEBSITE</a>
            </div>
            <div class="links">
                <a href="javscript:void(0);">${pageContext.request.contextPath}</a>
                <a href="javscript:void(0);">추후등록</a>
                <a href="javscript:void(0);">추후로그아웃</a>

            </div>
        </div>        
        <div id="menu">
            <div>
                <ul>
                	<!-- a : 페이지 이동을 처리하는 마크업 -->
                    <li><a href="${pageContext.request.contextPath}/kimslist.kims">글 목록</a></li>
					<li><a href="${pageContext.request.contextPath}/kimswrite.kims">글 쓰기</a></li>
                </ul>
            </div>
        </div>
        <div style="text-align:right;margin-top:1px;
        	border:solid 1px;padding:5px">
        	[ TOTAL : 명 ]
        	[ CURRENT : 명 ]
        </div>