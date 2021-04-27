<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.PrintWriter" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<meta name="viewport" content="width=device-width", initial-scale="1" >  <!-- 반응형 웹에 사용하는 메타태그 -->
	<link rel="stylesheet" href="css/yh/bootstrap.css"> <!-- 참조  -->

<title>Kim's Board 글쓰기</title>
</head>
<body>

	<c:set var="userInfo" value="${sessionScope.userInfo}" />
	
		<script type="text/javascript">
	function check() {
		if (!bbs.subject.value) {
			alert("제목을 입력해 주세요");
			bbs.subject.focus();
			return false;
		}
		
		if (!bbs.content.value) {
			alert("내용을 입력해 주세요");
			bbs.content.focus();
			return false;
		}
		document.bbs.submit();
	}
	</script>
	
	<jsp:include page="/WEB-INF/views/yh/include/header.jsp"></jsp:include>
    
    
    <div class="container">
        <div class="row">
        <form name="bbs" method="post" action="${pageContext.request.contextPath}/kimswriteok.kims">
        <input type="hidden" name="userid" value="${userInfo.userId}" id="userid">
            <table class="table table-striped" style="text-align:center; border:1px solid #dddddd">
                <thead>
                    <tr>
                        <th colspan="2" style="background-color:#eeeeee; text-align:center;">게시판 글쓰기 양식</th>
                    </tr>
                </thead>
                <tbody>
                    <tr>
                    	<td><input type="text" class="form-control" placeholder="글 제목"  name="subject" maxlength="50" ></td>
                    </tr>
                    <tr>
                    	<td><textarea class="form-control" placeholder="글 내용"  name="content" maxlength="2048" style="height:350px" ></textarea></td>
                    </tr>
                </tbody>
            </table>
                <input type="submit" class="btn btn-primary pull-right" value="글쓰기" onclick="check();">
            </form>
        </div>
    </div>
    <script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
    <script src="js/yh/bootstrap.js"></script>
</body>

</html>
