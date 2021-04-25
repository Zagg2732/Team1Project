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

<title>Insert title here</title>
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
				
					<h3 style="margin-top: 35px;">EditProfile</h3>
					<span style="font-size: 120px; color: lightgrey;">
						<i class="fas fa-user-circle"></i>
					</span><br>

					ID : <input type="text" value="${userInfo.userId}" disabled><br>
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
					<h5>닉네임 변경</h5>
					<form name="nickname">
						닉네임 : <input type="text" value="${userInfo.nickName}">
						<input type="button" value="중복체크">
					</form>
					<hr>
					<h5>비밀번호 변경</h5>
					현재 비밀번호 : <input type="password" value=""><br><br>
					변경 비밀번호 : <input type="password" value="">
					
					<hr>
					
					<input type="button" value="프로필 수정하기">

					</fieldset>
			</div>
			
		</div>
		</section>

		<jsp:include page="footer.jsp" />
	</div>

</body>
</html>