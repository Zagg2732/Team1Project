<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
			background-color: lightyellow; padding: 10px; border-top-color: orange; border-top-style: solid;
		}
		.card-content {
			padding: 10px; min-height: 80px;
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
		  <c:set var="guestbook" value="${requestScope.guestBookDto}" />
		  <c:set var="idx" value="${requestScope.idx}" />
		  <c:set var="replyList" value="${requestScope.guestBookList}" />
		  <c:set var="userInfo" value="${sessionScope.userInfo}" />	

			<!-- 방명록 글 작성-->
			<div class="card">
				<div class="card-body">
  					<!-- <label>비밀글은 미니홈피 주인에게만 공개됩니다 .</label> -->
    				<form name="guestbook">
    				<input type="hidden" name="userid" value="${userInfo.userId}" id="userid">
    				<input type="hidden" name="username" value="${userInfo.userName}" id="username">
      					<div class="form-group">
        					<textarea name="content" id="content" class="form-control" id="content" rows="3" style="resize: none; margin-bottom:10px;"></textarea>
        					
      						<input type="checkbox" name="readyn" value="Y" id="readyn">&nbsp;&nbsp;
      						<input type="hidden" name="readyn_hidden" value="N" id="readyn_hidden">
      						
      						<button type="button" class="btn btn-primary" id="guestBookbtn" onclick="guestBookAdd()">등록</button>
      					</div>
   					</form>
  				</div>
  					<!-- 방명록 목록 -->
  				<div id="guestbooklist" style="width: 95%; height:230px; margin: 10px auto; overflow: scroll;">
  				</div>
 			</div>
 			
			<div class="menu-item" onclick="location.href='home.jh';">홈</div>
			<div class="menu-item" style="top: 48px" onclick="location.href='diary.jh';">다이어리</div>
			<div class="menu-item menu-selected" style="top: 86px" onclick="location.href='guestBook.jh';">방명록</div>
		  </div>
		</div>
	  </div>
	  
	  <!-- bgm player -->
	  <div class="outer-box border-bgm-box" style="width: 25%; background: #8c8c8c" >
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

	var frm = document.guestbook; //guestbook form 전체
	var sessionUserid = '<%=(String)request.getAttribute("sessionUserid")%>';
	var sessionGrade = '<%=(int)request.getAttribute("sessionGrade")%>';
	
	$(function() {
		guestBookList();
	});
	
	function guestBookList(value) {
		
		$.ajax({
			url : "GuestBookList.ajax",
			type : "GET",
			dataType : "json",
			success : function(data) {
				console.log(data);
				
				$.each(data, function(index, obj) {
					let htmlString = `
	  				<div class="card-wrap">
  						<div class="card-title">` +
  							obj.username + ` | ` + obj.writedate + `
  						</div>
  						<div class="card-content">`
  							+ obj.content +
  						`</div>
  					</div>
					`
					$('#guestbooklist').append(htmlString);
				});
			},
			error : function() {
				alert('error !!!');
			}
		})
	}
	
	function guestBookAdd() {
		if(frm.content == "") {
			alert('내용을 입력하세요.');
			return false;
		}
		//체크박스 선택값에 따라 방명록 공개 여부 
		if($('#readyn').is(":checked") == true){
		    var readyn = 'N';
		}else {
			var readyn = 'Y';
		}
		//console.log(readyn);
		
		$.ajax ({
			url : "GuestBookAdd.ajax",
			type : "GET",
			data : {
				"userid" : $('#userid').val(),
				"username" : $('#username').val(),
				"content" : $('#content').val(),
				"readyn" : readyn
			},
			success : function(data) {
				//console.log(data);
				guestBookList();				
				$('#content').val("");
				$('#guestbooklist').empty();
			},
			error : function(xhr) {
				alert('등록 실패 ㅠ');
			}
		});
	}
</script>
</html>
