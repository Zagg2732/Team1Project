<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<!-- 부트  -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">

<link rel="Stylesheet" href="css/hsj_style/default.css">


<title>공지게시판 글쓰기</title>

<%-- <link rel="Stylesheet"
	href="${pageContext.request.contextPath}/css/hsj_style/default.css" /> --%>
<link rel="Stylesheet"
	href="${pageContext.request.contextPath}/css/hsj_style/write_css.css" />

<SCRIPT type="text/javascript">
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
</SCRIPT>

</head>
<body>

	<%-- <c:import url="/WEB-INF/views/sj/header_hsj.jsp"/> --%>
	<jsp:include page="/WEB-INF/views/sj/include/header_sj.jsp"></jsp:include>

	<c:set var="cpage" value="${requestScope.cpage}" />
	<c:set var="pagecount" value="${requestScope.pagecount}" />
	<c:set var="userInfo" value="${sessionScope.userInfo}" />

	<article>

	<div class="container" role="main">
		<br> <b>공지 게시판</b> <br> <br>

			<%-- action="${pageContext.request.contextPath}/boardWriteOK.sj?type=humor_board&cp=${i}&ps=${pagesize}" --%>
		<form name="bbs" id="form" role="form" method="post"
			action="boardWriteOK.sj?type=notice_board" enctype="multipart/form-data"
			>
		<input type="hidden" name="userid" value="${userInfo.userId}" id="userid">

			<div class="mb-3">

				<label for="title">제목</label> <input type="text"
					class="form-control" name="subject" id="title"
					placeholder="제목을 입력해 주세요"
					 onfocus="this.placeholder = ''"
					onblur="this.placeholder = '제목을 입력해 주세요'">
			</div>




			<div class="mb-3">

				<label for="content">내용</label>

				<textarea id="summernote" class="form-control" rows="5"
					name="content" placeholder="내용을 입력해 주세요"
					onfocus="this.placeholder = ''"
					onblur="this.placeholder = '내용을 입력해 주세요'"></textarea>

			</div>


			<div class="custom-file">
				<!-- <input type="file" class="custom-file-input" id="customFile"
					name="filename"> <label class="custom-file-label"
					for="customFile"></label> -->
					
                       
                        <input type="file" name="filename">
                   
			</div> 
			
			



		</form>

		<div>

			<a class="btn btn-outline-info" id="btnSave" onclick="check();">등록</a>
			<a class="btn btn-outline-info" id="btnList"
				href="boardList.sj?type=notice_board&cp=${i}&ps=${pagesize}">목록</a>

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

</body>



</html>