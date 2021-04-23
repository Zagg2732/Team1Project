<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
	<title>듀효니 미니홈피</title>
  	<link rel="stylesheet" href="./css/jh/main.css">
  	<script src="./js/jh/main.js"></script>
  	<!--  문제의 부트.. -->
  	<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	
	<!-- include summernote css/js-->
	<link rel="stylesheet" href="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.css">
	<script src="http://cdnjs.cloudflare.com/ajax/libs/summernote/0.8.8/summernote.js"></script>
</head>
<body style="background-color: gray;">
	<div style="width:100%; max-width:900px;">
	  <div class="outer-box border-left-box" style="width: 27%; padding: 20px 0px 20px 20px">
		<div class="inner-box" style="padding: 10px 5px 10px 10px;">
		  <div class="content-box-header" style="justify-content: center">
			<span class="small-text">TODAY</span>&nbsp;<span style="color: red">999</span>
			&nbsp;&nbsp;|&nbsp;&nbsp;<span class="small-text">TOTAL</span>&nbsp;7979
		  </div>
		  <div class="content-box" style="padding: 20px">
		  </div>
		</div>
	  </div>
	  <div class="outer-box border-right-box" style="width: 72%; padding: 20px 20px 20px 0px">
		<div class="inner-box" style="padding: 10px 10px 10px 5px;">
		  <div class="content-box-header">
			<h1 style="margin:0px; font-size: 1.3em; font-weight: 550;" class="color-blue">&nbsp;&nbsp;듀효니 미니홈삐</h1>
			<span style="font-size: 12px;">URL</span>
		  </div>
		  <div class="content-box" style="box-shadow: 5px 5px 20px -10px grey; padding: 10px 20px;">
			<div style="display: flex; font-size: 14px;">				
			  <div class="col" style="display: flex; flex-direction: column;">
			  	
			  	<c:set var="idx" value="${requestScope.idx}" />
			 	<c:set var="cpage" value="${requestScope.cp}" />
				<c:set var="pagesize" value="${requestScope.ps}" />
				<c:set var="pagesize" value="${requestScope.subject}" />
				<c:set var="userInfo" value="${sessionScope.userInfo}" />
				
				답글쓰기 
				<form name="rewrite" action="diaryRewriteOk.jh" method="POST">
				
				<input type="hidden" name="cp" value="${cpage}" /> 
				<input type="hidden" name="ps" value="${pagesize}" /> 
				<input type="hidden" name="idx" value="${idx}" />
				<input type="hidden" name="userid" value="${userInfo.userId}" id="userid">
				<input type="hidden" name="nickname" value="${userInfo.nickName}" id="nickname">
				
				<input type="text" name="subject" class="form-control mt-4 mb-2"
				value="RE_${subject}" required
				>
				<div class="form-group">
				<textarea style="resize: none; "class="form-control" rows="10" name="content"
				placeholder="내용을 입력해주세요" required
				></textarea>
				</div>
				<input type="button" name="writebtn" class="btn btn-secondary mb-4" value="등록" onclick="rewritecheck();" id="writebtn">
				<input type="button" name="move" class="btn btn-secondary mb-4" value="목록" onclick="location.href='diary.jh';" id="move">
				
				</form>
				
			<div class="menu-item" onclick="location.href='home.jh';">홈</div>
			<div class="menu-item menu-selected" style="top: 48px" onclick="location.href='diary.jh';">다이어리</div>
			<div class="menu-item" style="top: 86px" onclick="location.href='guestbook.jh';">방명록</div>
		  </div>
		</div>
	  </div>
	</div>
</body>
<script type="text/javascript">
/* $(document).ready(function() {
	  $('#summernote').summernote();
	}); */
	function rewritecheck() {
		if(!rewrite.subject.value) {
			alert("제목을 입력하세요");
			write.subject.focus();
			return false;
		}
		if(!rewrite.content.value) {
			alert("내용을 입력하세요");
			return false;
		}
		document.rewrite.submit();
	}
	
</script>
</html>
