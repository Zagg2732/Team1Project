<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	System.out.println(request.getContextPath());
%>
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

	<div id="signwrap" class="signwrap">
		<jsp:include page="header.jsp" />
		<section id="loginsection">
			<h3 style="padding-top:250px;"align="center">Login Form</h3>
			<!-- multistep form -->
			<form id="msform" action="${pageContext.request.contextPath}/LoginOk.team1" method="post">

				<!-- fieldsets -->
				<fieldset>
					<h2 class="fs-title">Login your account</h2><br>
					<input type="text" name="userid" placeholder="ID" />
					<input type="password" name="userpw" placeholder="Password" />
					<button name="login" class="next action-button" value="Login">Login</button><br><br>
					혹시 아직도 <a href="${pageContext.request.contextPath}/Register.team1">회원가입</a>을 안하셨나요?<br>
				</fieldset>
			</form>

		</section>

		<jsp:include page="footer.jsp" />
	</div>

</body>
</html>