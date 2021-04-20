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

<title>Insert title here</title>
</head>
<body>

	<div id="signwrap" class="signwrap">
		<jsp:include page="header.jsp" />
		<section id="loginsection">
			<h3 style="padding-top:100px;"align="center">Login Form Example</h3>
			<!-- multistep form -->
			<form id="msform" action="<%= request.getContextPath()%>/LoginOk.team1" method="post">

				<!-- fieldsets -->
				<fieldset>
					<h2 class="fs-title">Login your account</h2><br>
					<input type="text" name="userid" placeholder="ID" />
					<input type="password" name="userpw" placeholder="Password" />
					<input type="button" name="login" class="next action-button" value="Login" />
					<button name="login2" class="next action-button" value="Login2"></button>
				</fieldset>
				
			</form>

		</section>

		<jsp:include page="footer.jsp" />
	</div>


	<!-- Js Plugins -->
	<script src="js/sy/jquery-3.3.1.min.js"></script>
	<script src="js/sy/bootstrap.min.js"></script>
	<script src="js/sy/jquery.magnific-popup.min.js"></script>
	<script src="js/sy/mixitup.min.js"></script>
	<script src="js/sy/jquery-ui.min.js"></script>
	<script src="js/sy/jquery.nice-select.min.js"></script>
	<script src="js/sy/jquery.slicknav.js"></script>
	<script src="js/sy/owl.carousel.min.js"></script>
	<script src="js/sy/jquery.richtext.min.js"></script>
	<script src="js/sy/image-uploader.min.js"></script>
	<script src="js/sy/main.js"></script>
	<script src="https://kit.fontawesome.com/c5fd5902bb.js"
		crossorigin="anonymous"></script>
</body>
</html>