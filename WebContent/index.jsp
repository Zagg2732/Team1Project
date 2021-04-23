<%@page import="javax.naming.Context"%>
<%@page import="javax.naming.InitialContext"%>
<%@page import="javax.sql.DataSource"%>
<%@page import="java.sql.Connection"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	Connection conn = null;
	
	//JNDI (이름 기반 검색)
	Context context = new InitialContext(); //현재 프로젝트에서 특정 이름을 가진 녀석을 검색(이름 기반 검색)
	Context contextDetail = (Context)context.lookup("java:comp/env");
	
	DataSource ds = (DataSource)contextDetail.lookup("jdbc/oracle");  //java:comp/env/  +  jdbc/oracle   이름  => 정해진 약속
	
	//Pool 안에서 connection 가지고 오기
	conn = ds.getConnection();
	
	//POINT 반드시 작업 끝나면 객체반환
	conn.close(); // 연결 끊는 것이 아닌 pool에게 반환
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
	<!-- Page Preloder -->
	<div id="preloder">
		<div class="loader"></div>
	</div>
	<div id="wrap">


<jsp:include page="WEB-INF/views/sy/total/header.jsp"></jsp:include>

		<section class="hero-section">
			<div class="container">
				<div class="hs-slider owl-carousel">
					<div class="hs-item set-bg"
						data-setbg="https://random.imagecdn.app/1280/720">
						<div class="row">
							<div class="col-lg-12">
								<a href="index.sj" class="btn btn-light btn-lg"><i
									class="fab fa-trello"></i> SJ Board</a>
							</div>
						</div>
					</div>
					<div class="hs-item set-bg"
						data-setbg="https://random.imagecdn.app/1600/900">
						<div class="row">
							<div class="col-lg-12">
								<a href="javscript:void(0);" onclick="window.open('home.jh','minihomepy','width=1200,height=650,location=no,status=no,scrollbars=yes');" class="btn btn-light btn-lg"><i
									class="fas fa-home"></i> Joohyun's minihomepy</a>
							</div>
						</div>
					</div>
					<div class="hs-item set-bg"
						data-setbg="https://random.imagecdn.app/1170/780">
						<div class="row">
							<div class="col-lg-12">
								<a href="${pageContext.request.contextPath}/indexyh.jsp" class="btn btn-light btn-lg"><i
									class="fab fa-trello"></i> Kim's Board</a>
							</div>
						</div>
					</div>
				</div>
			</div>
		</section>
	</div>
	
	<jsp:include page="WEB-INF/views/sy/total/footer.jsp"></jsp:include>


</body>
</html>
