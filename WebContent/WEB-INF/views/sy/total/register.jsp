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
					<h2 class="fs-title">Account Setup</h2><br>
					<div class="form-group">
						<input validates="required|between:5,15|idcheck" id="userId" name="userId" type="text" placeholder="ID" class="form-control" value="">
					</div>
						<input type="button" name="idCheck" id="idCheck" class="btn btn-gray" value="아이디 중복확인" style="width:30%;">
					
					<div class="form-group">
						<input validates="required|between:2,8|nickcheck" id="userNick" name="userNick" type="text" placeholder="NICKNAME" class="form-control" value="">
					</div>
						<input type="button" name="nickCheck" id="nickCheck" class="btn btn-gray" value="닉네임 중복확인" style="width:30%;">
										
				    <div class="form-group">
				        <input validates="required|between:6,15|strong" id="Password" name="password" type="password" placeholder="PASSWORD" class="form-control" value="">
				    </div>
					
				    <div class="form-group">
				        <input validates="required|same:Password" name="password" type="password" placeholder="RE-PASSWORD" class="form-control" value="">
				    </div>
					
					<input type="button" name="previous" class="previous action-button" value="Previous" />
					<input type="submit" name="submit" class="submit action-button" value="send" />
				</fieldset>
				<!-- USERID,USERNAME,NICKNAME,PASSWORD,JOINDATE,GRADE_FK -->

			</form>

		</section>

		<jsp:include page="footer.jsp" />
	</div>


	<!-- makeJs -->
	<script src="js/sy/register.js"></script>
	<script src="js/sy/jquery.validateMini.min.js"></script>
	<script type="text/javascript">
	$('form').validateMini({
		validates: { //<input type="button" name="idCheck" id="idCheck" class="next action-button" value="중복확인">
			idcheck: (params, value)=>{
				return /^[a-z\d]{5,11}$/.test(value) || "아이디를 규칙에 맞게 입력해주세요"
			},
			nickcheck: (params, value)=>{
				return /^[가-힣]{2,8}$/.test(value) || "닉네임을 규칙에 맞게 입력해주세요"
			},
			strong: (params, value)=>{
				return /^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])/.test(value) || "A Strong Password Required!"
			},
		}

	});
	</script>

</body>
</html>