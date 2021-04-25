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

<title>유머 게시판</title>
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<style>
#replyList {
	margin-bottom: 250px;
}
#replyAddReplyBody {
	background-color : #6c757d;
	width : 1117px;
	height : 89px;
	border: 1px solid black;
	margin-bottom: 15px;
}
</style>

<!-- SweetAlert2 -->
<script src="//cdn.jsdelivr.net/npm/sweetalert2@10"></script>

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
	<c:set var="sessionNickName" value="${sessionScope.userInfo.nickName}" scope="request" />
<%-- 	<c:set var="sessionId" value="${sessionScope.userInfo.userId}" scope="request" /> --%>



	<div id="container" style="text-align: center;">
		<div class="list-board"">
		<br>
		<br>

			<h3>게시판 상세보기 임시디자인입니다</h3>
			<br>
			
			<h4>글번호 : ${idx}</h4>
			<h4>글제목 : ${board.subject} </h4>
			<h4>글쓴이 : ${board.nickname} </h4>
			<h4>글쓴날짜 : ${board.writedate} </h4>
			<h4>조회수 : ${board.readnum} </h4>
			<h4>글내용 : ${board.content} </h4>
			<c:if test="${not empty board.filename}">
				<a href="<%= request.getContextPath() %>/shdownload.jsp?file_name=${board.filename}">${board.filename}</a><br>
				<img src="upload/${board.filename}">			
			</c:if>
			<h4>board.userid_kf ${board.userid_fk}  || 세션 ${sessionScope.userInfo.userId} </h4>
			<c:if test="${board.userid_fk eq sessionScope.userInfo.userId}">
				<a href="boardDelete.sj?type=humor_board&idx=${idx}">글쓴이는 삭제버튼이 보여요. 눌러서 삭제해볼래요?</a><br>		
			</c:if>
			<c:if test="${board.userid_fk eq sessionScope.userInfo.userId}">
				<a href="boardModifyWrite.sj?type=humor_board&idx=${idx}">글쓴이는 수정버튼이 보여요. 눌러서 수정해볼래요?</a><br>		
			</c:if>
			<h3>${requestScope.pagesize}</h3>
			<br>
			<h3>게시판 상세보기 임시디자인입니다</h3>
			<br>
			<h3>게시판 상세보기 임시디자인입니다</h3>
			<br>
			<h3>세션닉네임 : ${sessionScope.userInfo.nickName}</h3>


<br>
<br>
		<!-- 	<button type="button" class="btn btn-outline-info" id="up" name="up">좋아요!</button>
			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			 <button type="button" class="btn btn-outline-info" id="down"name="down">싫어요!</button>
  -->
 
<form id="like_form">
<table id="list">
<input type="hidden" name="command" value="like_it">
<input type="hidden" name="board_idx" value="${board.idx}">
<tr><input type="button" value="좋아요!" onclick="like(this.form)" > </tr>
<tr><div id="like_result">${board.up}</div> </tr>
</table>
</form>

<form id="like_form">
<table id="list">
<input type="hidden" name="command" value="like_it">
<input type="hidden" name="board_idx" value="${board.idx}">
<tr><input type="button" value="글삭제" onclick="deleteBoard.sj?type=humor_board&idx=${idx}" > </tr>
<tr><div id="like_result">${board.up}</div> </tr>
</table>
</form>

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
										class="form-control ml-2" value="${sessionScope.userInfo.nickName}"
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
			<div id = "replyAddReplyBody">
			안뇽 replyaddbody얌 ㅎ
			</div>
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
						$('#replybody').append(
								'<table class="table table-hover table-striped text-center">'
								+'<tr align="left"><td>[' 
								+ obj.nickname +'] <br> ' +obj.content 
								+ '<br> 작성일 :'+obj.writedate +'</td><td>' 
								+ '<form method="POST" name="replyDel">' 
								+ '<input type="hidden" name = "replyUserId" value="' +obj.userid +'" class="replyId">' 
								+ '<input type="hidden" name = "replyNickname" value="' +obj.nickname +'" class="replyNickname">' 
								+ '<input type="hidden" name = "replyRefer" value="' +obj.refer +'" class="replyRefer">' 
								+ '<input type="hidden" name = "replyDepth" value="' +obj.depth +'" class="replyDepth">' 
								+ '<input type="hidden" name = "replyStep" value="' +obj.step +'" class="replyStep">'
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
						$('#reply_writer').val('${requestScope.sessionNickName}'); //닉네임 초기화되니까 다시입력해줌
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
					$('#reply_writer').val('${requestScope.sessionNickName}'); //닉네임 초기화되니까 다시입력해줌
					$("#replyAddReplyBody").empty(); 
					$("#replyAddReplyBody").append(data.html); 
				},
				error : function() {
					alert('답글 data 받아오기 실패');
				}
			});
		}
	function reply_add_reply(frm) {
				
			$.ajax({
				url :"replyAddReply.sjajax",
				type : "POST",
				datatype : "json",
				data :{
					"idx" : $('#idx').val(),//게시판idx
					"type" : "humor_reply", //게시판종류
					"sessionId" : '${sessionScope.userInfo.userId}',
					"replyNickName" : frm.replyNickName.value,					
					"refer" : frm.refer.value,
					"depth" : frm.depth.value, 
					"content" : frm.content.value
				},
				success : function(data){
					$('#replybody').empty();
					replyList(); //댓글목록 다시불러옴
					$('#reply_writer').val("");
					$('#reply_content').val("");
					$('#password').val("");
					$('#reply_writer').val('${requestScope.sessionNickName}'); //닉네임 초기화되니까 다시입력해줌
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
			datatype : "ajax",
			data :{
				"sessionId" : '${sessionScope.userInfo.userId}',
				"idx" : $('#idx').val(),
				"type" : "humor_reply",		
				"replyUserId" : frm.replyUserId.value,
				"refer" : frm.replyRefer.value,
				"depth" : frm.replyDepth.value, 
				"step" : frm.replyStep.value
			},
			success : function(data){
				replyList();
				$('#reply_writer').val("");
				$('#reply_content').val("");
				$('#password').val("");
				$('#replybody').empty();
				$('#reply_writer').val('${sessionScope.userInfo.nickName}');
				alert(data.msg);//닉네임 초기화되니까 다시입력해줌
			},
			error : function() {
				alert('댓글 삭제 실패');
			}
		});
	}
	
	function like(frm){
		alert("추천하셨습니당!")
		
		$.ajax({
			url: "like.sjajax",
			type: "POST",
			cache: false,
			dataType: "json",
			data: $('#like_form').serialize(), //아이디가 like_form인 곳의 모든 정보를 가져와 파라미터 전송 형태(표준 쿼리형태)로 만들어줌
			success:
			function(data){ //ajax통신 성공시 넘어오는 데이터 통째 이름 =data
			alert("'좋아요'가 반영되었습니다!") ; // data중 put한 것의 이름 like
			$("#like_result").html(data.like); //id값이 like_result인 html을 찾아서 data.like값으로 바꿔준다.
			},
			error:
			function (request, status, error){
			alert("ajax실패")
			}
			});
		}


	
	/* 
	$('#up').click(function(){
		  var pk = $(this).attr('name') 
		  $.ajax({
		      url: "{ }", 
		      data: { }, 
		      dataType: "json", 

		      success: function(response){
		        // 요청이 성공했을 경우 좋아요/싫어요 개수 
		        $('#up).html("count : "+ response.down_count);
		        $('#down).html("count : "+ response.up_count);
		      },
		      error:function(error){
		        // 요청이 실패했을 경우
		        alert(error)
		      }
		  });
		}) */

		
		
	/* 	// 싫어요 버튼 처리
		// 버튼 클릭 > ajax통신 (dislike url로 전달) > views의 dislike 메소드에서 리턴하는 값 전달받기 > 성공시 콜백 호출
		$('#down').click(function(){
		  var pk = $(this).attr('name') // 클릭한 요소의 attribute 중 name의 값을 가져온다.
		  $.ajax({
		      url: "{% url 'pledge:pledge_dislike' pledge.pk %}", // 통신할 url을 지정한다.
		      data: {'csrfmiddlewaretoken': '{{ csrf_token }}'}, // 서버로 데이터를 전송할 때 이 옵션을 사용한다.
		      dataType: "json", // 서버측에서 전송한 데이터를 어떤 형식의 데이터로서 해석할 것인가를 지정한다. 없으면 알아서 판단한다.

		      success: function(response){
		        // 요청이 성공했을 경우 좋아요/싫어요 개수 레이블 업데이트
		        $('#like_count'+ pk).html("count : "+ response.like_count);
		        $('#dislike_count'+ pk).html("count : "+ response.dislike_count);
		      },
		      error:function(error){
		        // 요청이 실패했을 경우
		        alert(error)
		      }
		  });
		})  */
	
</script>
</html>

