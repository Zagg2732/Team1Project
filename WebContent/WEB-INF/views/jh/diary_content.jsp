<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c"  uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
	<title>듀효니 미니홈피</title>
	<!-- css -->
  	<link rel="stylesheet" href="./css/jh/main.css">
    <link rel='stylesheet' href='https://use.fontawesome.com/releases/v5.3.1/css/all.css'>
    <link rel="stylesheet" href="./css/jh/player_style.css">
    <!-- jQuery -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
	<script src='https://cdnjs.cloudflare.com/ajax/libs/jqueryui/1.12.0/jquery-ui.min.js'></script>
	<!-- js -->
	<script src="./js/jh/main.js"></script>
	<script src="./js/jh/player_script.js"></script>
	<!-- bootStrap -->
	<link rel="stylesheet" href="//maxcdn.bootstrapcdn.com/bootstrap/4.0.0-beta.3/css/bootstrap.min.css">
	<script src="//cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js"></script>
	<style type="text/css">
		.card-wrap {
			width: 95%; margin: auto; 
		}
		.card-title {
			padding: 10px; border-top-color: gray; border-top-style: solid; margin:0px;
		}
		.card-content {
			padding: 10px; min-height: 40px;
		}
		 .font-small { 
		 	font-size: small; 
		}
		 .jb-medium { 
		 	font-size: medium; 
		}
	</style>	
</head>
<body>
<body style="background-color: gray;">
	<div style="width: 100%; max-width: 1300px; margin-top : 10px; margin-left : 10px;">
	  <div class="outer-box border-left-box" style="width: 20%; padding: 20px 0px 20px 20px">
		<div class="inner-box" style="padding: 10px 5px 10px 10px;">
		  <div class="content-box-header" style="justify-content: center">
			<span class="small-text">TODAY</span>&nbsp;
				<span style="color: red">999</span>&nbsp;&nbsp;|&nbsp;&nbsp;<span class="small-text">TOTAL</span>&nbsp;7979
		  </div>
		  <div class="content-box" style="padding: 20px">
			<img style="width: 100%" src="./images/jh/profile.png"></img>
			<p style="font-size: 14px">일촌신청 X<br><br><br><br><br><br></p>
			<div style="font-size: 10px">
			  <i style="color: #068cb9" class="fa fa-caret-right"></i><b>&nbsp;HISTORY</b>
			  <span style="float: right; font-size: 14px; color: #999999">
			  <i class="fa fa-caret-up"></i>&nbsp;<i class="fa fa-caret-down"></i></span>
			</div>
			<hr>
			<b class="color-blue">박주현</b><span style="color: #b3b3b3; font-size: 12px;">&nbsp;(<i class="fa fa-mars-stroke-v"></i>)</span>
			<div style="margin-top: 15px;"><select style="width: 100%"><option>파도타기</option></select></div>
		  </div>
		</div>
	  </div>
	  <div class="outer-box border-right-box" style="width: 55%; padding: 20px 20px 20px 0px">
		<div class="inner-box" style="padding: 10px 10px 10px 5px;">
		  <div class="content-box-header">
			<h1 style="margin:0px; font-size: 1.3em; font-weight: 550;" class="color-blue">&nbsp;&nbsp;주현's h.p</h1>
			<span style="font-size: 12px;">http://cyworld.com/joohyun</span>
		  </div>
		  <div class="content-box" style="box-shadow: 5px 5px 20px -10px grey; padding: 10px 20px;">
			  
			  	<c:set var="diary" value="${requestScope.diaryDto}" />
			  	<c:set var="idx" value="${requestScope.idx}" />
			 	<c:set var="cpage" value="${requestScope.cp}" />
				<c:set var="pagesize" value="${requestScope.ps}" />
				<c:set var="replyList" value="${requestScope.diaryReplyList}" />
				<c:set var="userInfo" value="${sessionScope.userInfo}" />
				<c:set var="pager" value="${requestScope.pager}" />
				
				<div class="container">
				<table class="table font-small">
		  			<tr>
		  				<td>작성일</td>
		  				<td style="border-right: 1px solid lightgrey;">${diary.writedate}</td>
		  				<td>작성자</td>
		  				<td>${userInfo.userName}</td>
		  			</tr>
		  			<tr>
		  				<td>제목</td>
		  				<td colspan="3">${diary.subject}</td>
		  			</tr>
		  			<tr>
		  				<td>내용</td>
		  				<td colspan="3">${diary.content}</td>
		  			</tr>
				</table>
				
				<!-- 권한별 버튼 활성화  -->
				<input type="button" class="btn btn-secondary mb-3 font-small float-right" value="목록" 
				style="margin:5px;" onclick="location.href='diary.jh?cp=${cpage}&ps=${pagesize}';">
				<c:choose>
					<c:when test="${!empty userInfo.grade}">
						<input type="button" class="btn btn-secondary mb-3 font-small float-right" value="답글" 
						style="margin:5px;"onclick="location.href='diaryRewrite.jh?idx=${idx}&cp=${cpage}&ps=${pagesize}';">
							<c:if test="${userInfo.grade == 1}">
								<%-- <input type="button" class="btn btn-secondary mb-3" value="편집" onclick="location.href='diaryEdit.jh?idx=${idx}&cp=${cpage}&ps=${pagesize}';"> --%>
								<input type="button" class="btn btn-secondary mb-3 font-small float-right" value="삭제" 
								style="margin:5px;" onclick="location.href='diaryDelete.jh?idx=${idx}&cp=${cpage}&ps=${pagesize}';">
							</c:if>
					</c:when>
				</c:choose>
				
				<!-- 댓글 달기 -->
				<form name="reply" action="diaryReply.jh" method="POST">
					<!-- hidden : 값 숨겨서 처리 --> 
					<input type="hidden" name="idx" value="${idx}" id="idx">
					<input type="hidden" name="userid" value="${userInfo.userId}" id="userid">
					<input type="hidden" name="username" value="${userInfo.userName}" id="username">
					
					<table class="table">
							<c:choose>
								<c:when test="${userInfo.userId == null}">
								</c:when>
								<c:otherwise>									
									<textarea name="reply_content" id="reply_content" class="form-control font-small" id="reply_content" rows="3" placeholder="댓글 내용을 입력해주세요." required 
      								style="resize: none; height: 40px; margin-bottom:10px;"></textarea>
									<button type="button" class="btn btn-outline-secondary btn-sm float-right" id="replybtn">등록</button>
								</c:otherwise>
							</c:choose>
					</table>
				</form>
				<div class="content-box">
				<div id="conwrap" style="padding: 10px;">
				<!-- 댓글 목록 -->	
				<c:choose>
					<c:when test="${userInfo.userId == null}">
					</c:when>
				<c:otherwise>
					<div class="cardreply">			
						<div id="replybody" style="width: 95%; height:100px; margin: 10px auto; overflow: scroll;">
  						</div>
  					</div> 
				</c:otherwise>
				</c:choose>
				</div>
				</div>
			</div><!-- 컨테이너 -->
			<div class="menu-item" onclick="location.href='home.jh';">홈</div>
			<div class="menu-item menu-selected" style="top: 48px" onclick="location.href='diary.jh';">다이어리</div>
			<div class="menu-item" style="top: 86px" onclick="location.href='guestBook.jh';">방명록</div>
		  </div>
		</div>
	  </div>
	  
	  <!-- bgm player -->
	  <div class="outer-box border-bgm-box" style="width: 25%; background: gray" >
			<div class="player">
   				<div class="title-wrap">
        			<div class="clearfix"></div>
        			<div class="trackDetails">
            			<span class="artist"></span>
            			<span class="title"></span>
        			</div>
        			<div class="controls">
            			<div class="rew">
                			<i class="fas fa-backward"></i>
            			</div>
            		<div class="play">
                		<svg viewBox="0 0 25 25" xml:space="preserve" style="visibility: visible;">
                    		<defs><rect x="-49.5" y="-132.9" width="446.4" height="366.4"></rect></defs>
                    		<g>
                        	<circle fill="none" cx="12.5" cy="12.5" r="10.8"></circle>
                        		<path fill-rule="evenodd" clip-rule="evenodd" d="M8.7,6.9V18c0,0,0.2,1.4,1.8,0l8.1-4.8c0,0,1.2-1.1-1-2L9.8,6.5 C9.8,6.5,9.1,6,8.7,6.9z"></path>
                    		</g>
                		</svg>
            		</div>
            		<div class="pause">
                		<svg viewBox="0 0 25 25" xml:space="preserve" style="visibility: visible;">
                    		<g>
                        		<rect x="6" y="4.6" width="2.8" height="13.7"></rect>
                        		<rect x="12" y="4.6" width="2.8" height="13.7"></rect>
                    		</g>
                		</svg>
            		</div>
            			<div class="fwd">
                			<i class="fas fa-forward"></i>
            			</div>
        			</div>
    			</div>
    		<div class="tracker"></div>
		</div>
		<ul class="playlist" id="playlist" >
    		<li audioURL="./bgm/jh/yourHomepy.mp3" artist="허니패밀리">너의 홈피</li>
    		<li audioURL="./bgm/jh/freestyle_Y.mp3" artist="프리스타일">Y</li>
    		<li audioURL="./bgm/jh/KineticFlow.mp3" artist="키네틱 플로우">몽환의 숲</li>
    		<li audioURL="./bgm/jh/Lyn.mp3" artist="린">사랑했잖아...</li>
    		<li audioURL="./bgm/jh/DrunkenTiger.mp3" artist="드렁큰 타이거">심의의 안 걸리는 사랑노래</li>
    		<li audioURL="./bgm/jh/Leessang.mp3" artist="리쌍">헤어지지 못하는 남자, 떠나가지 못하는 여자</li>
		</ul>
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
			alert('비회원은 댓글 서비스를 이용할 수 없습니다.');
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
					let htmlString = `
					<form>
					<div class="card-wrap font-small">
						<div class="card-title font-small">` +
							obj.username + ` | ` + obj.writedate + `
							<input type="button" value="삭제" class="btn btn-sm"
							onclick="reply_del(this.form)">
						</div>
						<div class ="card-content font-small">`
							+obj.content+ `
						</div>
					</div>
					<input type="hidden" name="num" value=` +obj.num + ` class="reply_num">
					<input type="hidden" name="idx" value=` +obj.idx_fk + ` class="reply_idx">
					<input type="hidden" name="userid" value=` +obj.userid_fk + ` class="reply_userid">
					</form>
					`
					$('#replybody').append(htmlString);
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
					"username" : $('#username').val(),
					"reply_content" : $('#reply_content').val()
				},
				success : function(data) {
					replyList();
					$('#reply_content').val("");
					$('#username').val("");
					$('#replybody').empty();
				},
				error : function() {
					alert('댓글 등록 실패');
				}
			});
		});
	}
	
	var del = document.replybody;
	
	//미니홈피 주인만 모든 댓글 삭제 가능 
	//그 밖의 회원은 본인 댓글만 삭제 가능
	function reply_del(value) {

		if(sessionGrade != 1) {
			if(value.userid.value != sessionUserid) {
				alert('본인이 작성한 댓글만 삭제가 가능합니다!');
				return false;
			}
		}

		$.ajax({
			url :"ReplyDelete.ajax",
			type : "POST",
			datatype : "text",
			data :{
				"num" : value.num.value,
				"idx_fk" : value.idx.value,
				"userid_fk" : value.userid.value
			},
			success : function(data){
				console.log(data);
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
