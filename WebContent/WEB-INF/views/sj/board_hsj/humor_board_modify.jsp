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
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<title>유머게시판</title>

<!-- 부트  -->
<link rel="Stylesheet" href="css/hsj_style/default.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">
<script type="text/javascript">
	function check() {
		if (!bbs.subject.value) {
			alert("제목을 입력해 주세요");
			bbs.subject.focus();
			return false;
		}
		if (!bbs.content.value) {
			alert("내용을 입력해 주세요");
			bbs.content.focus();
			return false;
		}
		document.bbs.submit();
	}
</script>

</head>
<body>

	<%-- <c:import url="/WEB-INF/views/sj/header_hsj.jsp"/> --%>
	<jsp:include page="/WEB-INF/views/sj/include/header_sj.jsp"></jsp:include>

	<c:set var="cpage" value="${requestScope.cpage}" />
	<c:set var="pagecount" value="${requestScope.pagecount}" />
	<c:set var="type" value="${param.type}" />
	<c:set var="idx" value="${param.idx}" />
	<c:set var="userInfo" value="${sessionScope.userInfo}" />

	<article>
	<h1>${type}</h1>
	<h1>${idx}</h1>
	
		<div class="container" role="main">
			<br> <b>유머게시판 글쓰기</b> <br> <br>

			<%-- action="${pageContext.request.contextPath}/boardWriteOK.sj?type=humor_board&cp=${i}&ps=${pagesize}" --%>
			<form name="bbs" id="form" role="form" method="post"
				action="boardModifyWriteOK.sj?type=${type}&idx=${idx}"
				enctype="multipart/form-data">
				<input type="hidden" name="userid" value="${userInfo.userId}"
					id="userid">

				<div class="mb-3">

					<label for="title">제목</label> <input type="text"
						class="form-control" name="subject" id="title"
						placeholder="제목을 입력해 주세요" onfocus="this.placeholder = ''"
						onblur="this.placeholder = '제목을 입력해 주세요'">
				</div>




				<div class="mb-3">

					<label for="content">내용</label>

					<textarea id="summernote" class="form-control" rows="5" cols="60"
						name="content" placeholder="내용을 입력해 주세요"
						onfocus="this.placeholder = ''"
						onblur="this.placeholder = '내용을 입력해 주세요'"></textarea>

				</div>


			
			<!-- 파일 선택 -->
			<label class="form-label" for="customFile">첨부파일</label>
				<input type="file" class="form-control" id="customFile" />

			</form>

			<div style="text-align: center;">
		<br>
		
				<a class="btn btn-outline-info" id="btnSave" onclick="check();">등록</a>
				&nbsp;&nbsp;&nbsp;
				<a class="btn btn-outline-info" id="btnList"
					href="boardList.sj?type=humor_board&cp=${i}&ps=${pagesize}">목록</a>

			</div>

		</div>

	</article>










	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-JEW9xMcG8R+pH31jmWH6WWP0WintQrMb4s7ZOdauHnUtxwoG2vI5DkLtS3qm9Ekf"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.1/dist/umd/popper.min.js"
		integrity="sha384-SR1sx49pcuLnqZUnnPwx6FCym0wLsk5JZuNx2bPPENzswTNFaQU1RDvt3wT4gWFG"
		crossorigin="anonymous"></script>
	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/js/bootstrap.min.js"
		integrity="sha384-j0CNLUeiqtyaRmlzUHCPZ+Gy5fQu0dQ6eZ/xAww941Ai1SxSY+0EQqNXNE6DZiVc"
		crossorigin="anonymous"></script>
	<script
		src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>

</body>


</html>