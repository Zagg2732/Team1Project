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
	
    <title>- Team.1 ADMIN -</title>

</head>

<body id="page-top">
	<c:set var="pagesize" value="${requestScope.pagesize}" />
	<c:set var="cpage" value="${requestScope.cpage}" />
	<c:set var="pagecount" value="${requestScope.pagecount}" />
	<c:set var="list" value="${requestScope.list}" />
	<c:set var="totalboardcount" value="${requestScope.totalboardcount}" />
	<c:set var="pager" value="${requestScope.pager}" />
	<c:set var="userInfo" value="${sessionScope.userInfo}" />

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
					<div
						class="d-sm-flex align-items-center justify-content-between mb-4">
						<h1 class="h3 mb-0 text-gray-800">ㅁ 관리자 Talk</h1>
					</div>

					<!-- Content Row -->


					<div class="card shadow mb-4">
						<div
							class="card-header py-3 d-flex flex-row align-items-center justify-content-between">
							<h6 class="m-0 font-weight-bold text-primary">관리자 talk!</h6>
							<div class="dropdown no-arrow">
								<a class="dropdown-toggle" href="#" role="button"
									id="dropdownMenuLink" data-toggle="dropdown"
									aria-haspopup="true" aria-expanded="false"> <i
									class="fas fa-ellipsis-v fa-sm fa-fw text-gray-400"></i>
								</a>
								<div
									class="dropdown-menu dropdown-menu-right shadow animated--fade-in"
									aria-labelledby="dropdownMenuLink">
									<div class="dropdown-header">Dropdown Header:</div>
									<a class="dropdown-item" href="#">Action</a> <a
										class="dropdown-item" href="#">Another action</a>
									<div class="dropdown-divider"></div>
									<a class="dropdown-item" href="#">Something else here</a>
								</div>
							</div>
						</div>


						<div class="card-body">
							<div id="inputarea">
								<form name="talk">
										<input type="hidden" value="${userInfo.userId}" id="idString">
										<textarea rows="5" cols="80" name="content" id="content"></textarea>
										<input type="button" value="OK" class="btn btn-primary" id="talkok" onclick="talkInsert()">
								</form>

							</div>
							<hr>
							<div id="talklist">

							</div>
							
							<hr>

							<div id="pagearea">
								<ul id="ulid"></ul>
							</div>
							<br>
						</div><!-- card body -->


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
	<script type="text/javascript">
	$(function(){
		talkListAdd();
		
	});
	
	function talkListAdd(value){
		
		if(value == undefined){
			value = 1;
		}
		
		$.ajax({
			url : "AdminTalkList.sooyeon",
			type : "POST",
			dataType : "json",
			data : {
				"cp" : value.innerHTML
			},
			success : function(data){
				
				$('#ulid').empty();
				$('#talklist').empty();
				
				for(i = 1; i <= data.pagecount; i++){
					console.log
					let htmlStr = '<li><a href="javascript:void(0);" onclick="talkListAdd(this)" class="btn btn-primary">' + i + '</a></li>';
					
					$('#ulid').append(htmlStr);
				}
				
				$.each(data.data, function(index,obj){
					
					let tempCard = `
					<div class="card border-left-primary shadow h-100 py-2">
						<div class="card-body">
							<div class="row no-gutters align-items-center">
								<div class="col mr-2">
									<div class="text-xs">No.` + obj.idx + ` | ` + obj.writedate.toString() + `</div>
									<div class="text-md font-weight-bold text-primary text-uppercase mb-1">`
										+ obj.userid +
									`</div>
									<div>` + obj.content + `</div>
								</div>
							</div>
						</div>
					</div>
					<br>
					`
					
					$('#talklist').append(tempCard);
				});
				
			},
			error : function(xhr){
				console.log("!!!!error");
			}
			
		});
	}
	

	function talkInsert(){
		
		let formArea = document.talk;
						
		if(formArea.content.value == ""){
			alert('내용을 입력해주세요!!!');
			return;
		}
		
		$.ajax({
			url : "AdminTalkInsert.sooyeon",
			type : "POST",
			data : {
				"userid" : $('#idString').val(),
				"content" : formArea.content.value
			},
			success : function(data){
				talkListAdd();
				alert('등록 완료');
				$('#content').val("");
			},
			error : function(xhr){
				alert('등록 실패');
			}
			
		});
		
	}	
	</script>

</body>
</html>
