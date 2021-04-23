<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">


<!-- 부트  -->
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">

<!-- default css   -->
<link rel="Stylesheet" href="css/hsj_style/default.css">

<!-- summernote  -->
	<!-- include libraries(jQuery, bootstrap) -->
<!-- 	<link href="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.css" rel="stylesheet">
 -->	 <script src="http://cdnjs.cloudflare.com/ajax/libs/jquery/3.2.1/jquery.js"></script> 
	<script src="http://netdna.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.js"></script> 
	
	
	<!-- include summernote css/js-->
	<link href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css" rel="stylesheet">
	<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>



 <link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
  <script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script> 
	 
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script> 
	
	
 <script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>

<title>유머게시판 글쓰기</title>

<link rel="Stylesheet"
	href="${pageContext.request.contextPath}/css/hsj_style/default.css" />
<link rel="Stylesheet"
	href="${pageContext.request.contextPath}/css/hsj_style/write.css" />

<SCRIPT type="text/javascript">
	function check() {
		if (!bbs.subject.value) {
			alert("제목을 입력해 주세요");
			bbs.subject.focus();
			return false;
		}
		if (!bbs.writer.value) {

			alert("이름을 입력해 주세요");
			bbs.writer.focus();
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

	<article>

	<div class="container" role="main">
	<br>
		<b >유머 게시판</b>
		<br>
		<br>

		<form name="form" id="form" role="form" method="post"
			action="${pageContext.request.contextPath}/boardWriteOK.sj">

			<div class="mb-3">

				<label for="title">제목</label> <input type="text"
					class="form-control" name="title" id="title"
					placeholder="제목을 입력해 주세요" onfocus="this.placeholder = ''"
					onblur="this.placeholder = '제목을 입력해 주세요'">

			</div>




			<div class="mb-3">

				<label for="content">내용</label>

				<textarea id="summernote" class="form-control" rows="5" name="content" id="content"
					placeholder="내용을 입력해 주세요" onfocus="this.placeholder = ''"
					onblur="this.placeholder = '내용을 입력해 주세요'"></textarea>

			</div>


			<div class="custom-file">
				<input type="file" class="custom-file-input" id="customFile">
				<label class="custom-file-label" for="customFile"></label>
			</div>



		</form>

		<div>

			<a class="btn btn-outline-info" id="btnSave">저장</a> <a
				class="btn btn-outline-info" id="btnList" href="boardList.sj?type=humor_board&cp=${i}&ps=${pagesize}">목록</a>

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

<script type="text/javascript">
$(document).ready(function() {
	  $('#summernote').summernote();
	});
</script>

</html>