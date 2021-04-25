<%@page import="java.util.List"%>
<%@ page import="com.team1.sy.dto.Member"%>
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
		  				<td>작성자</td>
		  				<td>${diary.userid_fk}</td>
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
				
				<!-- 권한별 버튼 활성화  -->
				<input type="button" class="btn btn-secondary mb-3" value="목록가기" onclick="location.href='diary.jh?cp=${cpage}&ps=${pagesize}';">
				<c:choose>
					<c:when test="${!empty userInfo.grade}">
						<input type="button" class="btn btn-secondary mb-3" value="답글달기" onclick="location.href='diaryRewrite.jh?idx=${idx}&cp=${cpage}&ps=${pagesize}';">
							<c:if test="${userInfo.grade == 1}">
								<input type="button" class="btn btn-secondary mb-3" value="편집" onclick="location.href='diaryEdit.jh?idx=${idx}&cp=${cpage}&ps=${pagesize}';">
								<input type="button" class="btn btn-secondary mb-3" value="삭제" onclick="location.href='diaryDelete.jh?idx=${idx}&cp=${cpage}&ps=${pagesize}';">
							</c:if>
					</c:when>
				</c:choose>
				<!-- 댓글 달기 -->
				<form name="reply" action="diaryReply.jh" method="POST">
					<!-- hidden : 값 숨겨서 처리 --> 
					<input type="hidden" name="idx" value="${idx}" id="idx">
					<input type="hidden" name="userid" value="${userInfo.userId}" id="userid">
					<input type="hidden" name="nickname" value="${userInfo.nickName}" id="nickname">
					
					<table class="table">
							<c:choose>
								<c:when test="${userInfo.userId == null}">
									<h1 style="margin:0px; font-size: 1em; font-weight: 550; color:gray;">로그인 후 댓글 작성이 가능합니다 !</h1>
								</c:when>
								<c:otherwise>
									<textarea name="reply_content" id="reply_content" style= "width : 430px;"></textarea>
									<input type="button" class="btn btn-secondary mb-4"  id="replybtn"  value="등록">
								</c:otherwise>
							</c:choose>
					</table>
				</form>
				
				<!-- 댓글 목록 -->
				<table class="table">
					<tbody id="replybody"></tbody>
				</table>
				</div>
			<div class="menu-item" onclick="location.href='home.jh';">홈</div>
			<div class="menu-item menu-selected" style="top: 48px" onclick="location.href='diary.jh';">다이어리</div>
			<div class="menu-item" style="top: 86px" onclick="location.href='guestBook.jh';">방명록</div>
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
	
	var frm = document.reply; //reply form 전체
	var sessionUserid = '<%=(String)request.getAttribute("sessionUserid")%>';
	var sessionGrade = '<%=(int)request.getAttribute("sessionGrade")%>';
	
	function replyList() {
 		
		//비회원은 댓글을 못봐요
		if(sessionUserid == "") {
			return false;
		}

		$.ajax({
			url : "ReplyList.ajax",
			type : "GET",
			dataType : "json",
			data : {
				idx : $('#idx').val()
			},
			success : function(data) {
				//console.log(data);
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
		//미니홈피 주인만 모든 댓글 삭제 가능 
		//그 밖의 회원은 본인 댓글만 삭제 가능
		if(sessionGrade == 1) {
			return true;
		}else if(sessionGrade != 1) {
			if(frm.userid.value != sessionUserid) {
				alert('본인이 작성한 댓글만 삭제가 가능합니다!');
				return false;
			}
		}

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
