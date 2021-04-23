<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="en">

<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>SB Admin 2 - Dashboard</title>

</head>

<body id="page-top">
	<c:set var="pagesize" value="${requestScope.pagesize}" />
	<c:set var="cpage" value="${requestScope.cpage}" />
	<c:set var="pagecount" value="${requestScope.pagecount}" />
	<c:set var="list" value="${requestScope.list}" />
	<c:set var="totalboardcount" value="${requestScope.totalboardcount}" />
	<c:set var="pager" value="${requestScope.pager}" />

    <!-- Page Wrapper -->
    <div id="wrapper">
		<jsp:include page="admin_sidebar.jsp" />

        <!-- Content Wrapper -->
        <div id="content-wrapper" class="d-flex flex-column">

            <!-- Main Content -->
            <div id="content">

                <jsp:include page="admin_topbar.jsp" />

                <!-- Begin Page Content -->
                <div class="container-fluid">

                    <!-- Page Heading -->
                    <div class="d-sm-flex align-items-center justify-content-between mb-4">
                        <h1 class="h3 mb-0 text-gray-800">ㅁ 회원 관리</h1> : ${totalboardcount}
                    </div>

                    <!-- Content Row -->


					<div class="card shadow mb-4">
						<div class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
							<h6 class="m-0 font-weight-bold text-primary">DataTables Example</h6>
							<div class="dropdown no-arrow">
                                        <a class="dropdown-toggle" href="#" role="button" id="dropdownMenuLink"
                                            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                            <i class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
                                        </a>
                                        <div class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
                                            aria-labelledby="dropdownMenuLink">
                                            <div class="dropdown-header">Dropdown Header:</div>
                                            <a class="dropdown-item" href="#">Action</a>
                                            <a class="dropdown-item" href="#">Another action</a>
                                            <div class="dropdown-divider"></div>
                                            <a class="dropdown-item" href="#">Something else here</a>
                                        </div>
                                    </div>
						</div>


						<div class="card-body" style="height:900px;">
						
						<form name="list">
							PageSize설정: <select name="ps" onchange="submit()">
								<c:forEach var="i" begin="5" end="20" step="5">
									<c:choose>
										<c:when test="${pagesize == i}">
											<option value="${i}" selected>${i}건</option>
										</c:when>
										<c:otherwise>
											<option value="${i}">${i}건</option>
										</c:otherwise>
									</c:choose>
								</c:forEach>
							</select>
						</form>
						<br>
							<div class="table-responsive">
                                <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                	<thead>
                                		<tr>
                                			<th>아이디</th>
                                			<th>이름</th>
                                			<th>닉네임</th>
                                			<th>가입일</th>
                                			<th>회원 등급</th>                                		
                                		</tr>
                                	</thead>
									<tbody>
										<!-- forEach()  목록 출력하기  -->
										<c:forEach var="user" items="${list}">
											<tr>
												<td align="center">${user.userId}</td>
												<td align="center">${user.userName}</td>
												<td align="center">${user.nickName}</td>
												<td align="center">${user.joinDate}</td>
												<td align="center">${user.grade}</td>
											</tr>
										</c:forEach>
										<!-- forEach()  -->
									</tbody>
								</table>
                            </div>
						
						</div>
						
					</div>


                </div>
                <!-- /.container-fluid -->

            </div>
            <!-- End of Main Content -->

            <jsp:include page="admin_footer.jsp" />

        </div>
        <!-- End of Content Wrapper -->

    </div>
    <!-- End of Page Wrapper -->

    <jsp:include page="admin_logout_modal.jsp" />


</body>

</html>
