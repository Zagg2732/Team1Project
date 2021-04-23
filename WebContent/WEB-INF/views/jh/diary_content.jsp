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
  	<!--  문제의 부트.. -->
  	<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css">
  	<script src="./js/jh/main.js"></script>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src="//cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
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
			  
			  	<c:set var="diary" value="${requestScope.diaryDto}" />
			  	<c:set var="idx" value="${requestScope.idx}" />
			 	<c:set var="cpage" value="${requestScope.cp}" />
				<c:set var="pagesize" value="${requestScope.ps}" />
				<c:set var="replyList" value="${requestScope.diaryReplyList}" />
				<c:set var="userInfo" value="${sessionScope.userInfo}" />
				<c:set var="pager" value="${requestScope.pager}" />
				
				<div class="container">
				<table class="table">
		  			<tr>
		  				<td>작성일</td>
		  				<td>${diary.writedate}</td>
		  			</tr>
		  			<tr>
		  				<td>제목</td>
		  				<td>${diary.subject}</td>
		  			</tr>
		  			<tr>
		  				<td>내용</td>
		  				<td>${diary.content}</td>
		  			</tr>
				</table>
				
				<input type="button" class="btn btn-secondary mb-3" value="목록가기" onclick="location.href='diary.jh?cp=${cpage}&ps=${pagesize}';">
		  		<input type="button" class="btn btn-secondary mb-3" value="답글달기" onclick="diaryRewrite.jh?idx=${idx}&cp=${cpage}&ps=${pagesize}';">
		  		<input type="button" class="btn btn-secondary mb-3" value="편집" onclick="diaryEdit.jh?idx=${idx}&cp=${cpage}&ps=${pagesize}';">
		  		<input type="button" class="btn btn-secondary mb-3" value="삭제" onclick="diaryDelete.jh?idx=${idx}&cp=${cpage}&ps=${pagesize}';">
		  		
				<!-- 댓글 달기 -->
				<form name="reply" action="diaryReply.jh" method="POST">
					<!-- hidden : 값 숨겨서 처리 --> 
					<input type="hidden" name="idx" value="${idx}" id="idx">
					<input type="hidden" name="userid" value="${userInfo.userId}" id="userid">
					<input type="hidden" name="nickname" value="${userInfo.nickName}" id="nickname">
					
					<table class="table">
							<c:choose>
								<c:when test="${userInfo.grade != null}">
									<textarea name="reply_content" id="reply_content" style= "width : 430px;"></textarea>
									<input type="button" class="btn btn-secondary mb-4"  id="replybtn"  value="등록">
								</c:when>
								<c:otherwise>
									<h5>회원가입 후 댓글 등록이 가능합니다 쏴리</h5>
								</c:otherwise>
							</c:choose>
					</table>
				</form>
				
				<!-- 댓글 목록 -->
				<table class="table">
					<thead>
						<tr>
							<th colspan="2"> 댓글 목록</th>
						</tr>
					<thead>
					<tbody id="replybody"></tbody>
				</table>
				</div>
			<div class="menu-item" onclick="location.href='home.jh';">홈</div>
			<div class="menu-item menu-selected" style="top: 48px" onclick="location.href='diary.jh';">다이어리</div>
			<div class="menu-item" style="top: 86px" onclick="location.href='guestbook.jh';">방명록</div>
		  </div>
		</div>
	  </div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		replyList();
		replyAdd();
	});
	
	function replyList() {
		$.ajax({
			url : "ReplyList.ajax",
			type : "GET",
			dataType : "json",
			data : {
				idx : $('#idx').val()
			},
			success : function(data) {
				console.log(data);
				$.each(data, function(index,obj) {
				$('#replybody').append(
					'<tr align="left"><td width="80%">[' +obj.nickname+ '] : ' + obj.content +
					'<br> 작성일 :'+obj.writedate +'</td><td width="20%">' +
					'<form method="POST" name="replyDel">' +
					
					'<input type="hidden" name="num" value="' +obj.num +'" class="reply_num">' +
					'<input type="hidden" name="idx" value="' +obj.idx_fk +'" class="reply_idx">' +
					'<input type="hidden" name="userid" value="' +obj.userid_fk +'" class="reply_userid">' +
					'<input type="button" value="삭제" onclick="reply_del(this.form)">' +
					'</form></td></tr>');
				});
			},
			error : function() {
				alert('error');
			}		
		});
	}
	
	function replyAdd() {
		$('#replybtn').click(function() {
			var frm = document.reply;
			
			if (frm.reply_content) {
				if (frm.reply_content.value == "") {
					alert("내용을 입력하세요.");
					return false;
				}
			}
			
			
			$.ajax({
				url : "ReplyAdd.ajax",
				type : "POST",
				data : {
					"idx" : $('#idx').val(),
					"userid" : $('#userid').val(),
					"nickname" : $('#nickname').val(),
					"reply_content" : $('#reply_content').val()
				},
				success : function(data) {
					replyList();
					$('#reply_content').val("");
					$('#nickname').val("");
					$('#replybody').empty();
				},
				error : function() {
					alert('댓글 등록 실패');
				}
			});
		});
	}
	
	function reply_del(frm) {
		

		$.ajax({
			url :"ReplyDelete.ajax",
			type : "POST",
			datatype : "text",
			data :{
				"num" : frm.num.value,
				"idx_fk" : frm.idx.value,
				"userid_fk" : frm.userid.value
			},
			success : function(data){
				replyList();
				$('#reply_content').val("");
				$('#replybody').empty();
			},
			error : function() {
				alert('댓글 삭제 실패');
			}
		});
	}
	
</script>
</html>
