<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
		<section id="registersection">
			<h3 style="padding-top: 70px;" align="center">Register Form</h3>
			
			<!-- multistep form -->
			<form id="msform" action="${pageContext.request.contextPath}/RegisterOk.team1" method="post">
				<!-- progressbar -->
				<ul id="progressbar">
					<li class="active">Terms and Conditions</li>
					<li>Account Setup</li>
				</ul>
				<!-- fieldsets -->
				<fieldset>
					<h2 class="fs-title">Terms and Conditions</h2><br>
										
					<div id="terms">
						<jsp:include page="../../common/terms.jsp" />
					</div><br>
					
					<input type="checkbox" name="termsCheck" value="agree" style="width:30px;" />
					<label for="termsCheck"> 약관에 동의합니다. </label><br>

					<input type="button" name="next" id="step1" class="next action-button" value="Next" />
				</fieldset>
				
				<fieldset id="step22">
					<h2 class="fs-title">Social Profiles</h2>
					<h3 class="fs-subtitle">Your presence on the social network</h3>
					
					<input type="text" name="userId" id="userId" placeholder="ID" style="width:52%;"/>
					<input type="button" name="idCheck" id="idCheck" class="next action-button" value="중복확인">
					<br><span id="idregex"></span>
					
					<input type="text" name="userNick" id="userNick" placeholder="NICKNAME" style="width:52%;"/>
					<input type="button" name="nickCheck" id="nickCheck" class="next action-button" value="중복확인">
					<br><span id="nickregex"></span>
					
					<input type="text" name="userName" id="userName" placeholder="NAME" />
					<br><span id="nameregex"></span>
					
					<input type="password" name="userPw" id="userPw" placeholder="PASSWORD" />
					<br><span id="pwregex"></span>
					
					<input type="password" name="pwConfirm" id="pwConfirm" placeholder="PASSWORD CONFIRM" />
					<br><span id="repwregex"></span><br>
					
					<input type="button" name="previous" class="previous action-button" value="Previous" />
					<input type="submit" name="submit" class="submit action-button" value="Submit" />
				</fieldset>
				<!-- USERID,USERNAME,NICKNAME,PASSWORD,JOINDATE,GRADE_FK -->

			</form>

		</section>

		<jsp:include page="footer.jsp" />
	</div>


	<!-- makeJs -->
	<script src="js/sy/register.js"></script>

</body>
</html>