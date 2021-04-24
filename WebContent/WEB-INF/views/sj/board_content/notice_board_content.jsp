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

<title>공지 게시판</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
#replyList {
	margin-bottom: 250px;
}
</style>

<!-- 부트  -->
<link rel="Stylesheet" href="css/hsj_style/default.css">
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-eOJMYsd53ii+scO/bJGFsiCZc+5NDVN2yr8+0RDqr0Ql0h+rP48ckxlpbzKgwra6"
	crossorigin="anonymous">
</head>
<body>
	<jsp:include page="/WEB-INF/views/sj/include/header_sj.jsp"></jsp:include>


	<c:set var="board" value="${requestScope.board}" />
	<c:set var="idx" value="${requestScope.idx}" />
	<c:set var="cpage" value="${requestScope.cp}" />
	<c:set var="pagesize" value="${requestScope.ps}" />
	<c:set var="replyList" value="${requestScope.replyList}" />
	<c:set var="sessionId" value="${sessionScope.userInfo.nickName}"
		scope="request" />



	<div id="container" style="text-align: center;">
		<div class="list-board"">
		<br>
		<br>
		
			<h3>게시판 상세보기 임시디자인입니다</h3>
			<br>
			<h3>${requestScope.pagesize}</h3>
			<br>
			<h3>${requestScope.sessionId}</h3>
			<br>
			<h3>게시판 상세보기 임시디자인입니다</h3>
			<br>
			<h3>게시판 상세보기 임시디자인입니다</h3>
			<br>
			<h3>${sessionScope.userInfo.nickName}</h3>

<br>
<br>


			<button type="button" class="btn btn-outline-info" id="up" name="up">좋아요!</button>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			<button type="button" class="btn btn-outline-info" id="down"
				name="down">싫어요!</button>

			<br>
			 <br>


			<form action="#" name="reply" method="POST">

				<div class="card mb-2">
					<div class="card-header bg-light">
						<i class="fa fa-comment fa"></i> Comment
					</div>
					<div class="card-body">

						<!-- hidden 태그  값을 숨겨서 처리  -->
						<input type="hidden" name="idx" value="${idx}" id="idx"> <input
							type="hidden" name="userid" value="">
						<!-- 추후 필요에 따라  -->


						<ul class="list-group list-group-flush">
							<li class="list-group-item">
								<div class="form-inline mb-2">
									<label for="replyId"><i
										class="fa fa-user-circle-o fa-2x"></i></label>

									<!-- 닉네임  -->
									<input type="text" name="reply_writer"
										class="form-control ml-2" value="${requestScope.sessionId}"
										disabled id="reply_writer">

									<!-- 내용  -->
									<textarea class="form-control" id="reply_content" rows="3"
										name="reply_content" placeholder="이쁜말 사용하기^^"></textarea>
									<input id="replybtn" type="button" class="btn btn-dark mt-3"
										value="등록">


								</div>

							</li>
						</ul>
					</div>

				</div>
			</form>


			<!-- 꼬리글 목록 테이블 -->

			<table class="table table-striped text-center">
				<tbody id="replybody">

					<tr>
						<th>comment</th>
					</tr>

				</tbody>
			</table>




		</div>
	</div>

</body>


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


<script type="text/javascript">
	$(function() {
		replyList();
		replyAdd();
	});

	function replyList() {
		$
				.ajax({
					url : "replyList.sjajax",
					type : "GET",
					dataType : "json",
					data : {
						idx : $('#idx').val(),
						type : "notice_reply"
					},
					success : function(data) {
						$
								.each(
										data,
										function(index, obj) {
											$('#replybody')
													.append(
															'<tr align="left"><td width="80%">['
																	+ obj.nickname
																	+ '] : '
																	+ obj.content
																	+ '<br> 작성일 :'
																	+ obj.writedate
																	+ '</td><td width="20%">'
																	+ '<form method="POST" name="replyDel">'
																	+ '<input type="hidden" name="no" value="' +obj.no +'" class="reply_no">'
																	+ '<input type="hidden" name="idx" value="' +obj.idx_fk +'" class="reply_idx">'
																	+ ' <input type="button" value="삭제" onclick="reply_del(this.form)">'
																	+ '</form></td></tr>');
										});
					},
					error : function() {
						alert('replyList() error');
					}
				});

	}

	function replyAdd() {
		$('#replybtn').click(function() {
			var frm = document.reply; //reply form 전체
			//댓글 유효성
			if (frm.reply_content.value == "") {
				alert("내용을 입력해주세요!");
				return false;
			}

			$.ajax({
				url : "replyInsert.sjajax",
				type : "POST",
				data : {
					"reply_writer" : $('#reply_writer').val(),
					"reply_content" : $('#reply_content').val(),
					"idx" : $('#idx').val(),
					"sessionId" : '${sessionScope.userInfo.userId}',
					"type" : "notice_reply"
				},
				success : function(data) {
					$('#replybody').empty();
					replyList();
					$('#reply_writer').val("");
					$('#reply_content').val("");
					$('#password').val("");

				},
				error : function() {
					alert('댓글 등록 실패');
				}
			});
		});
	}
	/* 	
	 function reply_del(frm) {

	 if (frm.delPwd.value == "") {
	 alert("비밀번호를 입력하세요");
	 frm.delPwd.focus();
	 return false;
	 }
	
	 $.ajax({
	 url :"ReplyDelete.ajax",
	 type : "POST",
	 datatype : "text",
	 data :{
	 "pwd" : frm.delPwd.value,
	 "no" : frm.no.value,
	 "idx_fk" : frm.idx.value
	 },
	 success : function(data){
	 replyList();
	 $('#reply_writer').val("");
	 $('#reply_content').val("");
	 $('#password').val("");
	 $('#replybody').empty();
	 },
	 error : function() {
	 alert('댓글 삭제 실패');
	 }
	 });
	 } */
</script>
</html>

