<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<!-- Google Font -->
<link
	href="https://fonts.googleapis.com/css?family=Montserrat:300,400,500,600,700,800,900&display=swap"
	rel="stylesheet">
<link
	href="https://fonts.googleapis.com/css?family=Lato:400,700,900&display=swap"
	rel="stylesheet">

<!-- Css Styles -->
<link rel="stylesheet" href="css/sy/bootstrap.min.css" type="text/css">
<link rel="stylesheet" href="css/sy/elegant-icons.css" type="text/css">
<link rel="stylesheet" href="css/sy/jquery-ui.min.css" type="text/css">
<link rel="stylesheet" href="css/sy/nice-select.css" type="text/css">
<link rel="stylesheet" href="css/sy/owl.carousel.min.css"
	type="text/css">
<link rel="stylesheet" href="css/sy/magnific-popup.css" type="text/css">
<link rel="stylesheet" href="css/sy/slicknav.min.css" type="text/css">
<link rel="stylesheet" href="css/sy/style.css" type="text/css">

<title>- Team.1 Page -</title>
</head>
<body>
<c:set var="userInfo" value="${sessionScope.userInfo}" />
<c:if test="${empty userInfo}">
	<script>
		alert("잘못된 접근 입니다.");
		location.href = 'index.team1';
	</script>
</c:if>


	<div id="signwrap" class="signwrap">
		<jsp:include page="header.jsp" />
		<section id="editsection">
		<div id="editwrap">
			
			<div id="msform">
				<fieldset>
				
					<h3 style="margin-top: 30px;">EditProfile</h3>
					<span style="font-size: 100px; color: lightgrey;">
						<i class="fas fa-user-circle"></i>
					</span><br>

					<form name="nickname" action="ChangeInfo.team1" method="post">
					ID : <input type="text" name="userId" id="userId" value="${userInfo.userId}" readonly><br>
					가입일 : <b>${userInfo.joinDate}</b><br>
					회원 등급 :
					<c:choose>
						<c:when test="${userInfo.grade > 0}">
							관리자
						</c:when>
						<c:otherwise>
							일반 회원
						</c:otherwise>
					</c:choose>
					<hr>
					<h5>닉네임 변경</h5><br>
					
						<input type="hidden" id="beforeNick" value="${userInfo.nickName}">
						닉네임 : <input type="text" name="userNick" id="userNick" placeholder="NICKNAME" value="${userInfo.nickName}" style="width:52%;"/>
					<input type="button" name="nickCheck" id="nickCheck" class="action-button" value="중복확인" onclick="overlapCheckNick()">
					<span id="nickregex"></span>
					<hr>
					<h5>비밀번호 변경</h5><br>
					현재 비밀번호 : <input type="password" name="beforePw" id="beforePw" placeholder="PASSWORD" value=""><br>
					변경 비밀번호 : <input type="password" name="userPw" id="userPw" placeholder="PASSWORD" />
					<br><span id="pwregex"></span>
					
					<input type="password" name="pwConfirm" id="pwConfirm" placeholder="PASSWORD CONFIRM" />
					<span id="repwregex"></span>
					
					<hr>
					
					<input type="submit" value="프로필 수정하기" class="action-button" id="changeInfo" onclick="changeInfo()">
					</form>

					</fieldset>
			</div>
			
		</div>
		</section>

		<jsp:include page="footer.jsp" />
	</div>
	<!-- makeJs -->
	<script src="js/sy/register.js"></script>
</body>
</html>