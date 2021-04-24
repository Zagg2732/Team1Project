<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>board_content</title>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<style>
		#replyList {
			margin-bottom: 250px;
		}
	</style>
</head>
<link rel="Stylesheet" href="css/hsj_style/default.css">
<body>
	<jsp:include page="/WEB-INF/views/sj/include/header_sj.jsp"></jsp:include>	               
	
	
	<c:set var="board" value="${requestScope.board}" />
	<c:set var="idx" value="${requestScope.idx}" />
	<c:set var="cpage" value="${requestScope.cp}" />
	<c:set var="pagesize" value="${requestScope.ps}" />
	<c:set var="replyList" value="${requestScope.replyList}" />
	<c:set var="sessionId" value="${sessionScope.userInfo.nickName}" scope="request" />
	
	<div id="pageContainer">
		<div style="padding-top: 30px; text-align: center">
		<h3>게시판 상세보기 임시디자인입니다</h3><br>
		<h3>${requestScope.pagesize}</h3><br>
		<h3>${requestScope.sessionId}</h3><br>
		<h3>게시판 상세보기 임시디자인입니다</h3><br>
		<h3>게시판 상세보기 임시디자인입니다</h3><br>
		<h3>${sessionScope.userInfo.nickName}</h3>

				<center>
				<form name="reply" action="#" method="POST">
						<!-- hidden 태그  값을 숨겨서 처리  -->
						<input type="hidden" name="idx" value="${idx}" id="idx"> 
						<input type="hidden" name="userid" value=""><!-- 추후 필요에 따라  -->
						<!-- hidden data -->
						<table width="80%" border="1">
							<tr>
								<th colspan="2">덧글 쓰기</th>
							</tr>
							<tr>
								<td align="left">작성자 :
								 	<input type="text" name="reply_writer" id="reply_writer" value = "${requestScope.sessionId}" disabled><br/> 
								 	내&nbsp;&nbsp;용 : 
								 	<textarea name="reply_content" rows="2" cols="50"  id="reply_content"></textarea>
								</td>
								<td align="left">
									<input type="button" id="replybtn"  value="등록">
								</td>
							</tr>
						</table>
				</form>
				<br> 
				<!-- 댓글의 답글 -->
				<div id="replyAddReplyBody" width="80%" border="1">
						<thead>
						<tbody id="replyAddReplyBody"></tbody>
				</div>
				
				<!-- 꼬리글 목록 테이블 -->
				<table id="replyList" width="80%" border="1">
						<thead>
						<tr>
							<th colspan="2">REPLY LIST</th>
						</tr>
						<thead>
						<tbody id="replybody"></tbody>
				</table>
				</center>
		</div>
	</div>
</body>
<script type="text/javascript">
	$(function() {
		replyList(); 
 		replyAdd(); 
	});

	function replyList(){	
		$.ajax({
			url : "replyList.sjajax",
			type : "GET",
	        dataType : "json",
			data : {
				idx : $('#idx').val(),
				type : "humor_reply"
			},
			success : function(data) {
					$.each(data, function(index,obj) {
						$('#replybody').append('<tr align="left"><td width="80%">[' 
								+ obj.nickname +'] : ' +obj.content 
								+ '<br> 작성일 :'+obj.writedate +'</td><td width="20%">' 
								+ '<form method="POST" name="replyDel">' 
								+ '<input type="hidden" name="no" value="' +obj.no +'" class="reply_no">' 
								+ '<input type="hidden" name = "replyNickname" value="' +obj.nickname +'" class="replyNickname">' 
								+ '<input type="hidden" name = "replyRefer" value="' +obj.refer +'" class="replyRefer">' 
								+ '<input type="hidden" name = "replyDepth" value="' +obj.depth +'" class="replyDepth">' 
								+ '<input type="hidden" id = "replyStep" value="' +obj.step +'" class="replyStep">'
								+ '<input type="button" id = "replyAddForm" value="답글" onclick="reply_add_form(this.form)">'
								+ '<input type="button" id = "replyDeleteBtn" value="삭제" onclick="reply_del(this.form)">'
								+ '</form></td></tr>');
					});		
			},
			error : function() {
				alert('replyList() error');
			}
		});
		
	}
	
	function reply_add_reply_button() {
		$('#replyAddForm').click(function() {
		
		});
	} 
	 
	 function replyAdd(){
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
						"type" : "humor_reply" //게시판종류와 세션ID 들고감
					},
					success : function(data) {
						$('#replybody').empty();
						replyList(); //댓글목록 다시불러옴
						$('#reply_writer').val("");
						$('#reply_content').val("");
						$('#password').val("");
						$('#reply_writer').val('${requestScope.sessionId}'); //닉네임 초기화되니까 다시입력해줌
					},
					error : function() {
						alert('댓글 등록 실패');
					}
				});
			});
		}
	
	 function reply_add_form(frm) {

			$.ajax({
				url :"replyAddForm.sjajax",
				type : "POST",
				datatype : "json",
				data :{
					"idx" : $('#idx').val(),
					"type" : "humor_reply",
					"sessionNickName" : '${sessionScope.userInfo.nickName}',
					"replyNickName" : frm.replyNickname.value,
					"refer" : frm.replyRefer.value,
					"depth" : frm.replyDepth.value
				},
				success : function(data){
					$('#replybody').empty();
					replyList(); //댓글목록 다시불러옴
					$('#reply_writer').val("");
					$('#reply_content').val("");
					$('#reply_writer').val('${requestScope.sessionId}'); //닉네임 초기화되니까 다시입력해줌
					$("#replyAddReplyBody").empty(); 
					$("#replyAddReplyBody").append(data.html); 
				},
				error : function() {
					alert('답글 data 받아오기 실패');
				}
			});
		}
	function reply_add_reply() {
			alert('눌렀네요 ^^');
			console.log(frm);
				
			$.ajax({
				url :"replyAddReply.sjajax",
				type : "POST",
				datatype : "json",
				data :{
					"idx" : $('#idx').val(),//게시판idx
					"type" : "humor_reply", //게시판종류
					"sessionNickName" : '${sessionScope.userInfo.nickName}', //현재로그인한유저
					"replyNickName" : frm.replyNickName.value,
					/*
					"refer" : frm.replyRefer.value,
					"depth" : frm.replyDepth.value 
					*/
				},
				success : function(data){
					$('#replybody').empty();
					replyList(); //댓글목록 다시불러옴
					$('#reply_writer').val("");
					$('#reply_content').val("");
					$('#reply_writer').val('${requestScope.sessionId}'); //닉네임 초기화되니까 다시입력해줌
					$("#replybody").eq(0).append(data.html); 	
				},
				error : function() {
					alert('답글 data 받아오기 실패');
				}
			});
		}
	
	function reply_del(frm) {

		$.ajax({
			url :"replyDelete.sjajax",
			type : "POST",
			datatype : "text",
			data :{
				"idx" : $('#idx').val(),
				"type" : "humor_reply",
				"sessionNickName" : '${sessionScope.userInfo.nickName}',
				"replyNickName" : $('#replyNickname').val(),
				"refer" : $('#replyRefer').val(),
				"depth" : $('#replyDepth').val(),
				"step" : $('#replyStep').val()
			},
			success : function(data){
				replyList();
				$('#reply_writer').val("");
				$('#reply_content').val("");
				$('#password').val("");
				$('#replybody').empty();
				$('#reply_writer').val('${requestScope.sessionId}'); //닉네임 초기화되니까 다시입력해줌
			},
			error : function() {
				alert('댓글 삭제 실패');
			}
		});
	}
</script>
</html>

